package com.leoman.service.impl;

import com.leoman.core.Constant;
import com.leoman.entity.Admin;
import com.leoman.entity.KUser;
import com.leoman.entity.Member;
import com.leoman.entity.WxUser;
import com.leoman.service.AdminService;
import com.leoman.service.KUserService;
import com.leoman.service.LoginService;
import com.leoman.service.WxUserService;
import com.leoman.utils.CookiesUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;

/**
 * Created by wangbin on 2015/3/3.
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AdminService adminService;

    @Autowired
    private KUserService userService;

    @Autowired
    private WxUserService wxUserService;

    @Override
    public Member getMember(HttpServletRequest request, String type) {
        Member member = null;
        if (Constant.MEMBER_TYPE_GLOBLE.equals(type)) {
            member = (Member) request.getSession().getAttribute(Constant.SESSION_MEMBER_GLOBLE);
        } else if (Constant.MEMBER_TYPE_BUSINESS.equals(type)) {
            member = (Member) request.getSession().getAttribute(Constant.SESSION_MEMBER_BUSINESS);
        }
        return member;
    }

    // 总后台登陆
    @Override
    public Boolean login(HttpServletRequest request, String username, String password, String remark) {
//        Member member = memberService.findByUsernameAndPassword(username,password);
//        if(member!=null){
//            request.getSession().setAttribute(Constant.SESSION_MEMBER_GLOBLE, member);
//            if(StringUtils.isNotBlank(remark)){
//                request.getSession().setMaxInactiveInterval(60*60*24*7);
//            }
//            return true;
//        }else {
//            return false;
//        }
        return false;
    }

    @Override
    public Boolean login(HttpServletRequest request, Member member, String remark, String type) {
        if (member != null) {
            if (Constant.MEMBER_TYPE_GLOBLE.equals(type)) {
                request.getSession().setAttribute(Constant.SESSION_MEMBER_GLOBLE, member);
            } else if (Constant.MEMBER_TYPE_BUSINESS.equals(type)) {
                request.getSession().setAttribute(Constant.SESSION_MEMBER_BUSINESS, member);
            }
            if (StringUtils.isNotBlank(remark)) {
                request.getSession().setMaxInactiveInterval(60 * 60 * 24 * 7);
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean login(HttpServletRequest request, String username, String password, String type, String remark) {
//        Member member = memberService.findByUsernameAndPassword(username, password, type);
        Admin admin = adminService.findByUsername(username);
        if (admin != null && password.equals(admin.getPassword())) {
            if (Constant.MEMBER_TYPE_GLOBLE.equals(type)) {
                request.getSession().setAttribute(Constant.SESSION_MEMBER_GLOBLE, admin);
            } else if (Constant.MEMBER_TYPE_BUSINESS.equals(type)) {
                request.getSession().setAttribute(Constant.SESSION_MEMBER_BUSINESS, admin);
            }
            if (StringUtils.isNotBlank(remark)) {
                request.getSession().setMaxInactiveInterval(60 * 60 * 24 * 7);
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void logOut(HttpServletRequest request, String type) {
        HttpSession session = request.getSession(false);
        if (Constant.MEMBER_TYPE_GLOBLE.equals(type)) {
            session.removeAttribute(Constant.SESSION_MEMBER_GLOBLE);
        } else if (Constant.MEMBER_TYPE_BUSINESS.equals(type)) {
            session.removeAttribute(Constant.SESSION_MEMBER_BUSINESS);
        }
    }

    @Override
    public Boolean loginWeixin(HttpServletRequest request, HttpServletResponse response, String username, String password) {
        try {
            KUser user = userService.findByMobile(username);
            if (user != null && password.equals(user.getPassword())) {
                WxUser wxUser = (WxUser) request.getSession().getAttribute(Constant.SESSION_WEIXIN_WXUSER);

                if (null != wxUser) {
                    WxUser wxUser1 = wxUserService.findByOpenId(wxUser.getOpenId());
                    if (null == wxUser1) {
                        wxUserService.create(wxUser);
                    }
                    user.setWxUser(wxUser);
                    userService.update(user);
                }

                request.getSession().setAttribute(Constant.SESSION_WEIXIN_USER, user);

                // 登录成功后，写入cookies
                try {
                    int loginMaxAge = 7 * 24 * 60 * 60; // 定义cookies的生命周期，这里是一个月。单位为秒
                    CookiesUtils.addCookie(response, "mobile", URLEncoder.encode(user.getMobile(), "UTF-8"), loginMaxAge);
                    CookiesUtils.addCookie(response, "password", URLEncoder.encode(user.getPassword(), "UTF-8"), loginMaxAge);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
