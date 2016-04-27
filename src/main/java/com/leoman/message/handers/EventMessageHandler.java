package com.leoman.message.handers;

import com.leoman.core.Configue;
import com.leoman.core.Constant;
import com.leoman.entity.Information;
import com.leoman.entity.Product;
import com.leoman.service.InfomationService;
import com.leoman.utils.BeanUtil;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpCustomMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutNewsMessage;
import me.chanjar.weixin.mp.bean.outxmlbuilder.NewsBuilder;

import java.util.List;
import java.util.Map;

/**
 * Created by wangbin on 2015/8/6.
 */
public class EventMessageHandler implements WxMpMessageHandler {

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService, WxSessionManager sessionManager) throws WxErrorException {


        if (WxConsts.EVT_SUBSCRIBE.equals(wxMessage.getEvent())) {
            return WxMpXmlOutMessage.TEXT().content(Constant.EVENT_DEF_SUBSCRIBE_TEXT).fromUser(wxMessage.getToUserName()).toUser(wxMessage.getFromUserName()).build();
        }
        if (WxConsts.EVT_CLICK.equals(wxMessage.getEvent())) {
            // 活动资讯
            if (Constant.EVENT_ACTIVITY_LIST.equals(wxMessage.getEventKey())) {
                // 获取活动资讯列表
                InfomationService infomationService = (InfomationService) BeanUtil.getBean("informationServiceImpl");
                List<Information> list = infomationService.findList(1, 10).getContent();
                NewsBuilder news = WxMpXmlOutMessage.NEWS();

                for (Information info : list) {
                    WxMpXmlOutNewsMessage.Item item = new WxMpXmlOutNewsMessage.Item();
                    item.setUrl(Configue.getBaseUrl() + "weixin/information/detail?id=" + info.getId());
                    item.setPicUrl(Configue.getUploadUrl() + info.getImage().getPath());
                    item.setDescription(info.getIntroduction());
                    item.setTitle(info.getTitle());
                    news.addArticle(item);
                }
                WxMpXmlOutNewsMessage wxMpXmlOutNewsMessage = null;
                try {
                    wxMpXmlOutNewsMessage = news.fromUser(wxMessage.getToUserName()).toUser(wxMessage.getFromUserName()).build();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return wxMpXmlOutNewsMessage;
            }
            // 限时抢购
            else if (Constant.EVENT_PRODUCT_LIST.equals(wxMessage.getEventKey())) {
                // 获取限时抢购列表
                List<Product> list = null;
                try {
                    com.leoman.service.ProductService productService = (com.leoman.service.ProductService) BeanUtil.getBean("productServiceImpl");
                    list = productService.findList(1, 10).getContent();

                } catch (Exception e) {
                    e.printStackTrace();
                }

                NewsBuilder news = WxMpXmlOutMessage.NEWS();
                if (list != null && !list.isEmpty()) {
                    for (Product product : list) {
                        WxMpXmlOutNewsMessage.Item item = new WxMpXmlOutNewsMessage.Item();
                        item.setUrl(Configue.getBaseUrl() + "weixin/product/detail?id=" + product.getId());
                        item.setPicUrl(Configue.getUploadUrl() + product.getCoverImage().getPath());
                        item.setDescription(product.getContent());
                        item.setTitle(product.getTitle());
                        news.addArticle(item);
                    }
                    return news.fromUser(wxMessage.getToUserName()).toUser(wxMessage.getFromUserName()).build();
                } else {
                    WxMpCustomMessage message = WxMpCustomMessage.TEXT().toUser(wxMessage.getFromUserName()) .content("此次抢购已结束，期待下次抢购").build();
                    wxMpService.customMessageSend(message);
                }
            }
        }
        return null;
    }
}
