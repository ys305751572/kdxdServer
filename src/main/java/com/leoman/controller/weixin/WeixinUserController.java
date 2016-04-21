package com.leoman.controller.weixin;

import com.leoman.controller.common.CommonController;
import com.leoman.core.Constant;
import com.leoman.entity.Address;
import com.leoman.entity.KUser;
import com.leoman.entity.WxUser;
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
    public String invite(HttpServletRequest request, Model model, Long userId, Long couponId) {
        WxUser wxUser = (WxUser) request.getSession().getAttribute(Constant.SESSION_WEIXIN_WXUSER);
        model.addAttribute("wxUser", wxUser);
        request.getSession().setAttribute(Constant.TEMP_FROMUSERID, userId);
        request.getSession().setAttribute(Constant.TEMP_COUPONID, couponId);
        return "weixin/invite-friend";
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
    public String toLogin(HttpServletRequest request) {
        String fromUserId = (String) request.getSession().getAttribute(Constant.TEMP_FROMUSERID);
        String couponId = (String) request.getSession().getAttribute(Constant.TEMP_COUPONID);

        System.out.println("***********************************************************登录界面***********************************************************");
        System.out.println("邀请人id：" + fromUserId);
        System.out.println("优惠券id：" + couponId);
        System.out.println("***********************************************************登录界面***********************************************************");
        return "weixin/login";
    }
}
