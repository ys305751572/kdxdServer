package com.leoman.controller.weixin;

import com.leoman.common.exception.GeneralExceptionHandler;
import com.leoman.common.factory.DataTableFactory;
import com.leoman.controller.common.CommonController;
import com.leoman.core.Configue;
import com.leoman.core.Constant;
import com.leoman.entity.Image;
import com.leoman.entity.Information;
import com.leoman.entity.KUser;
import com.leoman.entity.Order;
import com.leoman.service.OrderService;
import com.leoman.utils.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/3/18.
 */
@Controller
@RequestMapping(value = "weixin/order")
public class WeixinOrderController extends CommonController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/index")
    public String index(HttpServletRequest request, ModelMap model, Integer pageNum, Integer pageSize) {
        List<Order> list = null;
        try {
            if (null == pageNum) {
                pageNum = 1;
            }
            if (null == pageSize) {
                pageSize = 10;
            }

            KUser kUser = (KUser) request.getSession().getAttribute(Constant.SESSION_WEIXIN_USER);
            Page<Order> page = orderService.pageByUserId(kUser.getId(), pageNum, pageSize);
            list = page.getContent();

            model.addAttribute("orderList", list);
            model.addAttribute("current", pageNum);
            model.addAttribute("totalPage", page.getTotalPages());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "weixin/order-list";
    }

//    public void list(HttpServletResponse response, Integer draw, Integer start, Integer length, Long userId) {
//        try {
//            Order order = new Order();
//            KUser user = new KUser();
//            user.setId(userId);
//            order.setUser(user);
//
//            int pageNum = getPageNum(start, length);
//            Page<Order> page = orderService.findPage(order, pageNum, length);
//            Map<String, Object> result = DataTableFactory.fitting(draw, page);
//            WebUtil.print(response, result);
//        } catch (Exception e) {
//            GeneralExceptionHandler.log(e);
//            WebUtil.print(response, emptyData);
//        }
//    }
}
