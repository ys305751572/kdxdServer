package com.leoman.controller.business;

import com.leoman.controller.common.CommonController;
import com.leoman.core.Constant;
import com.leoman.service.LoginService;
import com.leoman.utils.CookiesUtils;
import com.leoman.utils.Md5Util;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by wangbin on 2015/8/13.
 */
@Controller("business")
@RequestMapping(value = "business")
public class BusinessController extends CommonController{

    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "/login")
    public String login(HttpServletRequest request,
                        HttpServletResponse response,
                        String error,
                        ModelMap model) {
        if (StringUtils.isNotBlank(error)) {
            model.addAttribute("error", error);
        }
        // 先读取cookies，如果存在，则将用户名回写到输入框
        Map<String, Object> params = CookiesUtils.ReadCookieMap(request);
        if (params != null && params.size() != 0) {
            String username = (String) params.get("username");
            model.put("username",username);
        }

        return "商家/登录";
    }

    @RequestMapping(value = "/login/check")
    public String checkLogin(String username,
                             String password,
                             HttpServletRequest request, HttpServletResponse response,
                             String remark,
                             ModelMap model) {
        Boolean success = loginService.login(request, username, Md5Util.md5(password), Constant.MEMBER_TYPE_BUSINESS,remark );
        if (success) {
            // 登录成功后，将用户名放入cookies
            int loginMaxAge = 30 * 24 * 60 * 60; // 定义cookies的生命周期，这里是一个月。单位为秒
            CookiesUtils.addCookie(response, "username", username, loginMaxAge);
            return "redirect:/business/dashboard";
        }
        model.addAttribute("error", "用户名或密码错误!");
        return "redirect:/business/login";
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response,
                         ModelMap model) {
        loginService.logOut(request, Constant.MEMBER_TYPE_BUSINESS);
        return "商家/登录";
    }

    @RequestMapping(value = "/")
    public String index(){

        return "redirect:/business/dashboard";
    }


    @RequestMapping(value = "/dashboard")
    public String dashboard(HttpServletRequest request,
                            HttpServletResponse response,
                            ModelMap model) {

        return "商家/控制面板";
    }




}
