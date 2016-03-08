package com.leoman.controller;

import com.leoman.common.exception.GeneralExceptionHandler;
import com.leoman.common.factory.DataTableFactory;
import com.leoman.controller.common.CommonController;
import com.leoman.core.bean.Result;
import com.leoman.entity.Order;
import com.leoman.service.OrderService;
import com.leoman.utils.JsonUtil;
import com.leoman.utils.WebUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by wangbin on 2015/8/24.
 */
@RequestMapping(value = "admin/order")
@Controller
public class OrderController extends CommonController {


    @Autowired
    private OrderService orderService;



    @RequestMapping(value = "/index")
    public String index(){
        return "订单列表";
    }


    @RequestMapping(value = "/list")
    public void list(HttpServletRequest request,
                     HttpServletResponse response,
                     Integer draw,
                     Integer start,
                     Integer length,
                     ModelMap model) {
        try {
            int pageNum = getPageNum(start, length);
            Page<Order> page = orderService.find(pageNum,length);
            Map<String, Object> result = DataTableFactory.fitting(draw, page);
            WebUtil.print(response, result);
        } catch (Exception e) {
            GeneralExceptionHandler.log(e);
            WebUtil.print(response, emptyData);
        }
    }

    @RequestMapping(value = "/save")
    public void save(HttpServletRequest request,
                     HttpServletResponse response,
                     Order order){

        try {
            if(order.getId()==null){
                orderService.create(order);
            }else{
                orderService.update(order);
            }
            WebUtil.print(response, new Result(true).msg("商品操作成功!"));
        }catch (Exception e) {
            GeneralExceptionHandler.log(e);
            WebUtil.print(response, new Result(true).msg("订单操作失败!"));
        }
        
    }



    @RequestMapping(value = "/delete")
    public void delete(HttpServletRequest request,
                       HttpServletResponse response,
                       String  ids){
        try {
            int[] arrayId = JsonUtil.json2Obj(ids, int[].class);
            orderService.deleteAll(arrayId);
            WebUtil.print(response, new Result(true).msg("删除成功！"));
        } catch (Exception e) {
            GeneralExceptionHandler.log(e);
            WebUtil.print(response, new Result(false).msg("删除失败！"));
        }
    }





}
