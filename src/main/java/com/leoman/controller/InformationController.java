package com.leoman.controller;

import com.leoman.common.exception.GeneralExceptionHandler;
import com.leoman.common.factory.DataTableFactory;
import com.leoman.controller.common.CommonController;
import com.leoman.core.bean.Result;
import com.leoman.entity.Information;
import com.leoman.service.InfomationService;
import com.leoman.utils.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by Administrator on 2016/3/9.
 */
@Controller
@RequestMapping(value = "admin/info")
public class InformationController extends CommonController {

    @Autowired
    private InfomationService service;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "info/list";
    }

    /**
     *
     * @param response
     * @param draw
     * @param start
     * @param length
     * @param info
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public void list(HttpServletResponse response, Integer draw, Integer start, Integer length, Information info) {
        try {
            int pageNum = getPageNum(start, length);
            Page<Information> page = service.findPage(info, pageNum, length);
            Map<String, Object> result = DataTableFactory.fitting(draw, page);
            WebUtil.print(response, result);
        } catch (Exception e) {
            GeneralExceptionHandler.log(e);
            WebUtil.print(response, emptyData);
        }
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add() {
        return "info/add";
    }

    /**
     * 保存
     *
     * @param response
     * @param info
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public void save(HttpServletResponse response, Information info) {
        try {
            service.create(info);
            WebUtil.print(response, new Result(true).msg("操作成功!"));
        } catch (Exception e) {
            e.printStackTrace();
            WebUtil.print(response, new Result(false).msg("操作失败!"));
        }
    }

    /**
     * 删除
     *
     * @param response
     * @param id
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public void delete(HttpServletResponse response, Long id) {
        try {
            service.deleteById(id);
            WebUtil.print(response, new Result(true).msg("操作成功!"));
        } catch (Exception e) {
            e.printStackTrace();
            WebUtil.print(response, new Result(false).msg("操作失败!"));
        }
    }

    /**
     * 详情
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public String detail(Long id,Model model) {

        Information info = service.getById(id);
        model.addAttribute("info",info);
        return "info/detail";
    }

    /**
     *
     * @param response
     * @param ids
     */
    @RequestMapping(value = "/publish", method =  RequestMethod.POST)
    @ResponseBody
    public void publish(HttpServletResponse response,Long[] ids) {
        service.publish(ids);
        WebUtil.print(response, new Result(false).msg("操作成功!"));
    }
}
