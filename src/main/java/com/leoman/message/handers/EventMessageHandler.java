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
        System.out.println("***************************************************************************************************************");
        System.out.println("进入过滤器");
        System.out.println("***************************************************************************************************************");

        if (WxConsts.EVT_SUBSCRIBE.equals(wxMessage.getEvent())) {
            return WxMpXmlOutMessage.TEXT().content(Constant.EVENT_DEF_SUBSCRIBE_TEXT).fromUser(wxMessage.getToUserName()).toUser(wxMessage.getFromUserName()).build();
        }
        if (WxConsts.EVT_CLICK.equals(wxMessage.getEvent())) {
            // 活动资讯
            if (Constant.EVENT_ACTIVITY_LIST.equals(wxMessage.getEventKey())) {
                System.out.println("***************************************************************************************************************");
                System.out.println("获取活动资讯列表");
                System.out.println("***************************************************************************************************************");

                // 获取活动资讯列表
                InfomationService infomationService = (InfomationService) BeanUtil.getBean("infomationService");
                List<Information> list = infomationService.findList(1, 10).getContent();

                WxMpXmlOutNewsMessage.Item item = null;

                NewsBuilder news = WxMpXmlOutMessage.NEWS();

                for (Information info : list) {
                    item = new WxMpXmlOutNewsMessage.Item();
                    item.setUrl("http://t.cn/RyhQ2V2");
                    item.setPicUrl(Configue.getUploadPath() + info.getImage().getPath());
                    item.setDescription(info.getContent());
                    item.setTitle(info.getTitle());

                    news.addArticle(item);
                }

                return news.fromUser(wxMessage.getToUserName()).toUser(wxMessage.getFromUserName()).build();
            }
            // 限时抢购
            else if (Constant.EVENT_PRODUCT_LIST.equals(wxMessage.getEventKey())) {
                System.out.println("***************************************************************************************************************");
                System.out.println("获取限时抢购列表");
                System.out.println("***************************************************************************************************************");

                // 获取限时抢购列表
                com.leoman.service.ProductService productService = (com.leoman.service.ProductService) BeanUtil.getBean("productService");
                List<Product> list = productService.findList(1, 10).getContent();

                WxMpXmlOutNewsMessage.Item item = null;

                NewsBuilder news = WxMpXmlOutMessage.NEWS();

                for (Product product : list) {
                    item = new WxMpXmlOutNewsMessage.Item();
                    item.setUrl("http://t.cn/RyhQ2V2");
                    item.setPicUrl(Configue.getUploadPath() + product.getCoverImage().getPath());
                    item.setDescription(product.getContent());
                    item.setTitle(product.getTitle());

                    news.addArticle(item);
                }

                return news.build();
            }
        }

        return null;
    }
}
