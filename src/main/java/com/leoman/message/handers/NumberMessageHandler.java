package com.leoman.message.handers;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;

import java.util.Map;

import com.leoman.core.Constant;
import com.leoman.core.UrlManage;

/**
 * Created by wangbin on 2015/8/6.
 */
public class NumberMessageHandler  implements WxMpMessageHandler {

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService, WxSessionManager sessionManager) throws WxErrorException {
        if(wxMessage.getContent().equals("1")){
            String text = "很高兴为您服务: \n";
            String aTag = "<a href=\""+ UrlManage.getProUrl("weixin/business/in")+"\">认证链接</a>";
            return WxMpXmlOutMessage.TEXT().content(text+aTag).fromUser(wxMessage.getToUserName()).toUser(wxMessage.getFromUserName()).build();
        }else{
            return WxMpXmlOutMessage.TEXT().content(Constant.MSG_DEF_TEXT).fromUser(wxMessage.getToUserName()).toUser(wxMessage.getFromUserName()).build();

        }
    }


}
