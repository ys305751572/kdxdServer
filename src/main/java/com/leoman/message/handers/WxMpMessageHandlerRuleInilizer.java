package com.leoman.message.handers;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 初始化消息处理器规则。
 * @author Administrator
 *
 */
@Component
public class WxMpMessageHandlerRuleInilizer {

    @Autowired
    private WxMpMessageRouter router;

    @PostConstruct
	public void init(){
        router.rule().async(false).msgType(WxConsts.XML_MSG_TEXT).rContent("\\d").handler(new NumberMessageHandler()).end();
        router.rule().async(false).msgType(WxConsts.XML_MSG_TEXT).handler(new TextMessageHandler()).end();
        router.rule().async(false).msgType(WxConsts.XML_MSG_EVENT).handler(new EventMessageHandler()).end();
	}
	
}
