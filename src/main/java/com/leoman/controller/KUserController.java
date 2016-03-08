package com.leoman.controller;

import com.leoman.common.exception.GeneralExceptionHandler;
import com.leoman.common.factory.DataTableFactory;
import com.leoman.controller.common.CommonController;
import com.leoman.core.bean.Result;
import com.leoman.entity.KUser;
import com.leoman.entity.Product;
import com.leoman.service.KUserService;
import com.leoman.utils.WebUtil;
import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by Administrator on 2016/3/8.
 */
@Controller
@RequestMapping(value = "admin/kuser")
public class KUserController extends CommonController {

    @Autowired
    private KUserService service;

    /**
     * 列表页面
     *
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "kuser/list";
    }

    /**
     * 查询列表
     * @param request
     * @param response
     * @param draw
     * @param start
     * @param length
     * @param user
     * @param model
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public void list(HttpServletRequest request, HttpServletResponse response,
                     Integer draw, Integer start, Integer length, KUser user, Model model) {
        try {
            int pageNum = getPageNum(start, length);
            Page<KUser> page = service.findPage(user,pageNum,length);
            Map<String, Object> result = DataTableFactory.fitting(draw, page);
            WebUtil.print(response, result);
        } catch (Exception e) {
            GeneralExceptionHandler.log(e);
            WebUtil.print(response, emptyData);
        }
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Long id,Model model) {

        if(id != null) {
            KUser _user = service.getById(id);
            model.addAttribute("user",_user);
        }
        return "kuser/add";
    }

    /**
     * 保存/编辑 会员
     * @param response
     * @param user
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public void save(HttpServletResponse response,KUser user) {
        try{
            service.update(user);
            WebUtil.print(response, new Result(true).msg("操作成功!"));
        } catch (Exception e) {
            e.printStackTrace();
            WebUtil.print(response, new Result(false).msg("操作失败!"));
        }
    }

    /**
     * 禁用/启用 会员
     * @param response
     * @param id
     * @param status
     */
    @RequestMapping(value = "/modifyStatus", method = RequestMethod.POST)
    public void modifyStatus(HttpServletResponse response,Long id,Integer status) {
        try{
            service.modifyStatus(id,status);
            WebUtil.print(response, new Result(true).msg("操作成功!"));
        } catch (Exception e) {
            e.printStackTrace();
            WebUtil.print(response, new Result(false).msg("操作失败!"));
        }
    }
}