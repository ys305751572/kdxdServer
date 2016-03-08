package com.leoman.message.handers;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;

import java.util.Map;

import com.leoman.core.UrlManage;

/**
 * 文本消息处理器
 * Created by wangbin on 2015/6/24.
 */
public class TextMessageHandler implements WxMpMessageHandler {


    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService, WxSessionManager sessionManager) throws WxErrorException {
        String content =  wxMessage.getContent();
        return  WxMpXmlOutMessage.TEXT().content("你说:"+content).fromUser(wxMessage.getToUserName()).toUser(wxMessage.getFromUserName()).build();
    }





}
