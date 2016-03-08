package com.leoman.message.handers;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;

import java.util.Map;

import com.leoman.core.Constant;

/**
 * Created by wangbin on 2015/8/6.
 */
public class EventMessageHandler implements WxMpMessageHandler {


    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService, WxSessionManager sessionManager) throws WxErrorException {
        if (WxConsts.EVT_SUBSCRIBE.equals(wxMessage.getEvent())) {
            return WxMpXmlOutMessage.TEXT().content(Constant.EVENT_DEF_SUBSCRIBE_TEXT).fromUser(wxMessage.getToUserName()).toUser(wxMessage.getFromUserName()).build();
        } else if(WxConsts.EVT_CLICK.equals(wxMessage.getEvent())){
            //家庭用酒
            if(Constant.EVENT_WINE_ONLINE_HOME.equals(wxMessage.getEventKey())){
                return WxMpXmlOutMessage.TEXT().content("家庭用酒").fromUser(wxMessage.getToUserName()).toUser(wxMessage.getFromUserName()).build();
            }
            //酒店直送
            else if(Constant.EVENT_WINE_ONLINE_HOTEL.equals(wxMessage.getEventKey())){
                return WxMpXmlOutMessage.TEXT().content("酒店直送").fromUser(wxMessage.getToUserName()).toUser(wxMessage.getFromUserName()).build();
            }
        }


        return null;
    }
}
