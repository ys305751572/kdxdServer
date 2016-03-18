package com.leoman.controller.weixin;

import com.leoman.common.exception.GeneralExceptionHandler;
import com.leoman.common.factory.DataTableFactory;
import com.leoman.controller.common.CommonController;
import com.leoman.entity.Information;
import com.leoman.entity.KUser;
import com.leoman.entity.Order;
import com.leoman.service.OrderService;
import com.leoman.utils.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by Administrator on 2016/3/18.
 */
@Controller
@RequestMapping(value = "weixin/order")
public class WeixinOrderController extends CommonController {

    @Autowired
    private OrderService service;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return null;
    }

    public void list(HttpServletResponse response, Integer draw, Integer start, Integer length, Long userId) {
        try {
            Order order = new Order();
            KUser user = new KUser();
            user.setId(userId);
            order.setUser(user);

            int pageNum = getPageNum(start, length);
            Page<Order> page = service.findPage(order,pageNum,length);
            Map<String, Object> result = DataTableFactory.fitting(draw, page);
            WebUtil.print(response, result);
        } catch (Exception e) {
            GeneralExceptionHandler.log(e);
            WebUtil.print(response, emptyData);
        }
    }
}
