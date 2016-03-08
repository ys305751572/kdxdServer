package com.leoman.filter;

import com.leoman.core.Constant;
import com.leoman.entity.WxUser;
import com.leoman.service.WxUserService;

import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by wangbin on 2015/8/4.
 */
public class CustomWeixinInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private WxMpService wxMpService;
//    @Autowired
//    private WxUserService wxUserService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String code = request.getParameter("code");
        if(Constant.WEIXIN_STATE.equals(request.getParameter("state"))&&StringUtils.isNotBlank(code)){
            WxMpOAuth2AccessToken wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
//            WxUser wxUser = wxUserService.getWxUserByToken(wxMpOAuth2AccessToken);
//            request.getSession().setAttribute(Constant.SESSION_WEIXIN_USER, wxUser);
        }
        return true;
    }


}
