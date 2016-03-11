package com.leoman.controller;

import com.leoman.common.exception.GeneralExceptionHandler;
import com.leoman.common.factory.DataTableFactory;
import com.leoman.controller.common.CommonController;
import com.leoman.core.bean.Result;
import com.leoman.entity.Information;
import com.leoman.entity.Product;
import com.leoman.service.InfomationService;
import com.leoman.service.ProductService;
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
@RequestMapping(value = "admin/pro")
public class ProductController extends CommonController {

    @Autowired
    private ProductService service;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "pro/list";
    }

    /**
     *
     * @param response
     * @param draw
     * @param start
     * @param length
     * @param pro
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public void list(HttpServletResponse response,
                     Integer draw,
                     Integer start,
                     Integer length,
                     Product pro,
                     Integer type) {
        try {
            int pageNum = getPageNum(start, length);
            Page<Product> page = service.findPage(pro,type, pageNum, length);
            Map<String, Object> result = DataTableFactory.fitting(draw, page);
            WebUtil.print(response, result);
        } catch (Exception e) {
            GeneralExceptionHandler.log(e);
            WebUtil.print(response, emptyData);
        }
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add() {
        return "pro/add";
    }

    /**
     * 保存
     *
     * @param response
     * @param pro
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public void save(HttpServletResponse response,
                     Product pro) {
        try {
            service.create(pro);
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
    public void delete(HttpServletResponse response,
                       Long id) {
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

        Product pro = service.getById(id);
        model.addAttribute("pro",pro);
        return "pro/detail";
    }

}
