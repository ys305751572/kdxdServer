package com.leoman.controller.weixin;

import com.leoman.entity.KUser;
import com.leoman.service.KUserService;
import com.leoman.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2016/3/16.
 */
@RequestMapping(value = "weixin/login")
@Controller
public class WeixinBaseController {

    @Autowired
    private LoginService service;

    @Autowired
    private KUserService userService;

    public String register(String mobile, String code) {

        KUser user = new KUser();
        user.setMobile(mobile);
        userService.register(user);
        return null;
    }

    public String login(HttpServletRequest request, HttpServletResponse response, String mobile, String password, Model model) {
        Boolean success = service.loginWeixin(request, mobile, password);
        if(success) {
            return "";
        }
        model.addAttribute("error","用户名密码错误");
        return "";
    }

    public void sendCode() {

    }
}
