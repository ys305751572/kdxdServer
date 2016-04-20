package com.leoman.controller.weixin;

import com.leoman.controller.common.CommonController;
import com.leoman.core.Configue;
import com.leoman.core.Constant;
import com.leoman.entity.Address;
import com.leoman.entity.KUser;
import com.leoman.entity.WxUser;
import com.leoman.service.KUserService;
import com.leoman.service.WxUserService;
import com.leoman.utils.BeanUtil;
import com.leoman.utils.CookiesUtils;
import com.leoman.utils.HttpUtil;
import com.leoman.utils.PathUtils;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
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
        model.addAttribute("userId", userId);
        model.addAttribute("couponId", couponId);
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

    @RequestMapping("toRegister")
    public String toRegister(HttpServletRequest request, Model model, Long userId, Long couponId) {
        KUser user = service.getById(userId);
        return "weixin/address-list";
    }
}
