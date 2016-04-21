package com.leoman.controller.weixin;

import com.leoman.controller.common.CommonController;
import com.leoman.core.Constant;
import com.leoman.entity.*;
import com.leoman.service.ActivityService;
import com.leoman.service.CouponService;
import com.leoman.service.KUserService;
import com.leoman.utils.CookiesUtils;
import com.leoman.utils.PathUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Administrator on 2016/3/23.
 */
@Controller
@RequestMapping(value = "/weixin/user")
public class WeixinUserController extends CommonController {

    @Autowired
    private KUserService service;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private CouponService couponService;

    @RequestMapping("index")
    public String index(HttpServletRequest request, Model model) {
        KUser user = (KUser) request.getSession().getAttribute(Constant.SESSION_WEIXIN_USER);
        WxUser wxUser = (WxUser) request.getSession().getAttribute(Constant.SESSION_WEIXIN_WXUSER);
        KUser kUser = service.getById(user.getId());
        model.addAttribute("user", kUser);
        model.addAttribute("wxUser", wxUser);
        return "weixin/user-detail";
    }

    @RequestMapping("invite")
    public String invite(HttpServletRequest request, Model model, Long userId, Long couponId, String wxUserHead, String wxUserName) {
        // 获取活动详情
        Activity activity = activityService.getById(1L);
        model.addAttribute("activity", activity);

        WxUser wxUser = (WxUser) request.getSession().getAttribute(Constant.SESSION_WEIXIN_WXUSER);
        model.addAttribute("wxUser", wxUser);

        System.out.println("***********************************************************1、准备邀请***********************************************************");
        System.out.println("邀请人id：" + userId);
        System.out.println("优惠券id：" + couponId);
        System.out.println("***********************************************************1、准备邀请***********************************************************");

        model.addAttribute("fromUserId", userId);
        model.addAttribute("couponId", couponId);
        model.addAttribute("wxUserHead", wxUserHead);
        model.addAttribute("wxUserName", wxUserName);
        return "weixin/invite-friend";
    }

    @RequestMapping("invite2")
    public String invite2(HttpServletRequest request, Model model, Long userId, Long couponId, String wxUserHead, String wxUserName) {
        // 获取活动详情
        Activity activity = activityService.getById(1L);
        model.addAttribute("activity", activity);

        // 获取优惠券详情
        if (null != couponId) {
            Coupon coupon = couponService.getById(couponId);
            if (null != coupon) {
                coupon.setIsChanged(0);
                couponService.update(coupon);

                model.addAttribute("couponDate", coupon.getEndDate());
            }
        }

        System.out.println("***********************************************************1、准备邀请***********************************************************");
        System.out.println("邀请人id：" + userId);
        System.out.println("优惠券id：" + couponId);
        System.out.println("***********************************************************1、准备邀请***********************************************************");

        model.addAttribute("fromUserId", userId);
        model.addAttribute("couponId", couponId);
        model.addAttribute("wxUserHead", wxUserHead);
        model.addAttribute("wxUserName", wxUserName);
        return "weixin/get-coupon";
    }

    @RequestMapping("toAddress")
    public String toAddress(HttpServletRequest request, Model model) {
        KUser weixinUser = (KUser) request.getSession().getAttribute(Constant.SESSION_WEIXIN_USER);
        List<Address> list = service.findAllAddressByUserId(weixinUser.getId());
        model.addAttribute("list", list);
        return "weixin/address-list";
    }

    @RequestMapping("findDefaultAddress")
    @ResponseBody
    public Address findDefaultAddress(HttpServletRequest request) {
        KUser weixinUser = (KUser) request.getSession().getAttribute(Constant.SESSION_WEIXIN_USER);
        Address address = service.findDefaultAddressByUserId(weixinUser.getId());
        return address;
    }

    @RequestMapping("logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute(Constant.SESSION_WEIXIN_USER);
        request.getSession().removeAttribute(Constant.SESSION_WEIXIN_WXUSER);
        CookiesUtils.logoutCookie(request, response);
        request.getSession().setAttribute(Constant.GO_URL, PathUtils.getRemotePath() + "/weixin/user/index");
        return "weixin/login";
    }

    @RequestMapping("toLogin")
    public String toLogin(Model model, Long fromUserId, Long couponId) {
        System.out.println("***********************************************************2、登录界面***********************************************************");
        System.out.println("邀请人id：" + fromUserId);
        System.out.println("优惠券id：" + couponId);
        System.out.println("***********************************************************2、登录界面***********************************************************");

        model.addAttribute("fromUserId", fromUserId);
        model.addAttribute("couponId", couponId);
        return "weixin/login";
    }
}
