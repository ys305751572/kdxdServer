package com.leoman.controller.weixin;

import com.leoman.controller.common.CommonController;
import com.leoman.core.Constant;
import com.leoman.entity.Coupon;
import com.leoman.entity.KUser;
import com.leoman.entity.WxUser;
import com.leoman.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Administrator on 2016/3/18.
 */
@RequestMapping(value = "weixin/coupons")
@Controller
public class WeixinCouponsController extends CommonController {

    @Autowired
    private CouponService service;

    @RequestMapping("/list")
    public String list(HttpServletRequest request, ModelMap model) {
        KUser kUser = (KUser) request.getSession().getAttribute(Constant.SESSION_WEIXIN_USER);
        WxUser wxUser = (WxUser) request.getSession().getAttribute(Constant.SESSION_WEIXIN_WXUSER);
        List<Coupon> list = service.findListByUserId(kUser.getId());
        model.addAttribute("couponList", list);
        model.addAttribute("wxUser", wxUser);
        model.addAttribute("user", kUser);
        return "weixin/coupon-list";
    }

    @RequestMapping("/detail")
    public String detail(HttpServletRequest request, Long id, Model model) {
        KUser kUser = (KUser) request.getSession().getAttribute(Constant.SESSION_WEIXIN_USER);
        WxUser wxUser = (WxUser) request.getSession().getAttribute(Constant.SESSION_WEIXIN_WXUSER);
        Coupon coupon = service.getById(id);
        model.addAttribute("coupon", coupon);
        model.addAttribute("wxUser", wxUser);
        model.addAttribute("user", kUser);
        return "weixin/coupon-detail";
    }
}
