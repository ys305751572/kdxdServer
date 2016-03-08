package com.leoman.filter;

import com.leoman.common.exception.GeneralExceptionHandler;
import com.leoman.core.Constant;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by wangbin on 2015/8/13.
 */
public class CommonInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Object business = request.getSession().getAttribute(Constant.SESSION_MEMBER_BUSINESS);
        Object globle = request.getSession().getAttribute(Constant.SESSION_MEMBER_GLOBLE);
        if(business==null&&globle==null){
            GeneralExceptionHandler.handle("没有访问权限!");
        }
        return true;
    }



}
