package com.leoman.service;


import javax.servlet.http.HttpServletRequest;

import com.leoman.entity.Member;

/**
 * Created by wangbin on 2015/3/3.
 */
public interface LoginService {

    public Member getMember(HttpServletRequest request,String type);

    public Boolean login(HttpServletRequest request, String username, String password, String remark);

    public Boolean login(HttpServletRequest request, Member member, String remark,String type);

    public Boolean login(HttpServletRequest request, String username, String password, String type, String remark);

    public void logOut(HttpServletRequest request,String type);



}
