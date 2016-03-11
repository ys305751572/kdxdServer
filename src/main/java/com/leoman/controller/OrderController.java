package com.leoman.controller;

import com.leoman.common.exception.GeneralExceptionHandler;
import com.leoman.common.factory.DataTableFactory;
import com.leoman.controller.common.CommonController;
import com.leoman.core.bean.Result;
import com.leoman.entity.KUser;
import com.leoman.entity.Order;
import com.leoman.entity.Product;
import com.leoman.service.OrderService;
import com.leoman.utils.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

/**
 * Created by Administrator on 2016/3/10.
 */
@Controller
@RequestMapping(value = "admin/order")
public class OrderController extends CommonController {

    @Autowired
    private OrderService service;

    /**
     * 列表页面
     *
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "order/list";
    }


    /**
     * @param request
     * @param response
     * @param length
     * @param start
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public void list(HttpServletRequest request,
                     HttpServletResponse response,
                     Integer length,
                     Integer start,
                     Order order,
                     String nickname,
                     String title,
                     Integer draw) {
        try {
            int pageNum = getPageNum(start, length);

            KUser _user = new KUser();
            _user.setNickname(nickname);

            Product pro = new Product();
            pro.setTitle(title);

            order.setUser(_user);
            order.setProduct(pro);

            Page<Order> page = service.findPage(order, pageNum, length);
            Map<String, Object> result = DataTableFactory.fitting(draw, page);
            WebUtil.print(response, result);
        } catch (Exception e) {
            GeneralExceptionHandler.log(e);
            WebUtil.print(response, emptyData);
        }
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Long id, Model model) {

        if (id != null) {
            try {
                Order order = service.getById(id);
                model.addAttribute("order", order);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "order/detail";
    }

    /**
     * 保存/编辑
     *
     * @param response
     * @param order
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public void save(HttpServletResponse response, Order order) {
        try {
            service.update(order);
            WebUtil.print(response, new Result(true).msg("操作成功!"));
        } catch (Exception e) {
            e.printStackTrace();
            WebUtil.print(response, new Result(false).msg("操作失败!"));
        }
    }

    /**
     * 确认签收
     *
     * @param response
     * @param id
     * @param status
     */
    @RequestMapping(value = "/modifyStatus")
    public void modifyStatus(HttpServletResponse response,
                             Long id,
                             Integer status) {
        try {
            service.modifyStatus(id, status);
            WebUtil.print(response, new Result(true).msg("操作成功!"));
        } catch (Exception e) {
            e.printStackTrace();
            WebUtil.print(response, new Result(false).msg("操作失败!"));
        }
    }
}



