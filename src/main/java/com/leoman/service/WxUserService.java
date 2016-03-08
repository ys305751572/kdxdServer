package com.leoman.service;

import com.leoman.entity.WxUser;
import com.leoman.service.common.ICommonService;

import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wangbin on 2015/7/8.
 */
public interface WxUserService extends ICommonService<WxUser> {


    public WxUser create(WxMpUser wxMpUser);

    public WxUser findByOpenId(String openid);

    public WxUser getWxUserByToken(WxMpOAuth2AccessToken wxMpOAuth2AccessToken);


    public WxUser getWXUserByRequest(HttpServletRequest request);
}
