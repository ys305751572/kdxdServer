package com.leoman.controller.weixin;

import com.leoman.core.Configue;
import com.leoman.core.Constant;
import com.leoman.core.UrlManage;
import com.leoman.entity.Information;
import com.leoman.entity.Product;
import com.leoman.service.InfomationService;
import com.leoman.utils.BeanUtil;
import com.leoman.utils.WebUtil;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.WxMenu;
import me.chanjar.weixin.common.util.StringUtils;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpCustomMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutNewsMessage;
import me.chanjar.weixin.mp.bean.custombuilder.NewsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.PostConstruct;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by wangbin on 2015/7/29.
 */
@Controller
@RequestMapping(value = "weixin")
public class WeixinInitController {

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private WxMpMessageRouter wxMpMessageRouter;

    @PostConstruct
    protected void menuInit() {

        WxMenu menu = new WxMenu();
        WxMenu.WxMenuButton button1 = new WxMenu.WxMenuButton();
        button1.setType(WxConsts.BUTTON_CLICK);
        button1.setName("活动资讯");
        button1.setKey(Constant.EVENT_ACTIVITY_LIST);

        WxMenu.WxMenuButton button2 = new WxMenu.WxMenuButton();
        button2.setType(WxConsts.BUTTON_CLICK);
        button2.setName("限时抢购");
        button2.setKey(Constant.EVENT_PRODUCT_LIST);

        WxMenu.WxMenuButton button3 = new WxMenu.WxMenuButton();
        button3.setName("个人中心");

        WxMenu.WxMenuButton button4 = new WxMenu.WxMenuButton();
        button4.setType(WxConsts.BUTTON_VIEW);
        button4.setName("个人信息");
        System.out.println("个人信息url:" + UrlManage.getProUrl("weixin/user/index"));
        button4.setUrl(UrlManage.getProUrl("weixin/user/index"));

        WxMenu.WxMenuButton button5 = new WxMenu.WxMenuButton();
        button5.setType(WxConsts.BUTTON_VIEW);
        button5.setName("我的优惠券");
        System.out.println("我的优惠券url:" + UrlManage.getProUrl("weixin/user/index"));
        button5.setUrl(UrlManage.getProUrl("weixin/coupons/list"));

        WxMenu.WxMenuButton button6 = new WxMenu.WxMenuButton();
        button6.setType(WxConsts.BUTTON_VIEW);
        button6.setName("我的订单");
        System.out.println("我的订单url:" + UrlManage.getProUrl("weixin/user/index"));
        button6.setUrl(UrlManage.getProUrl("weixin/order/index"));

        button3.getSubButtons().add(button4);
        button3.getSubButtons().add(button5);
        button3.getSubButtons().add(button6);

        menu.getButtons().add(button1);
        menu.getButtons().add(button2);
        menu.getButtons().add(button3);

        try {
            System.out.println("==============menuCreate()==================");
            wxMpService.menuCreate(menu);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 微信接口验证
     *
     * @param request
     * @param response
     * @param signature
     * @param timestamp
     * @param nonce
     * @param echostr
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.HEAD})
    public void handleGet(HttpServletRequest request,
                          HttpServletResponse response,
                          @RequestParam(value = "signature") String signature,
                          @RequestParam(value = "timestamp") String timestamp,
                          @RequestParam(value = "nonce") String nonce,
                          @RequestParam(value = "echostr") String echostr) throws ServletException, IOException {
        if (wxMpService.checkSignature(timestamp, nonce, signature)) {
            WebUtil.print(response, echostr);
            return;
        }
        WebUtil.print(response, "非法请求");
    }


    /**
     * 微信获取用户请求
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.HEAD})
    public void handlePost(HttpServletRequest request,
                           HttpServletResponse response) throws Exception {
        String encryptType = StringUtils.isBlank(request.getParameter("encrypt_type")) ? "raw" : request.getParameter("encrypt_type");
        if ("raw".equals(encryptType)) {
            // 明文传输的消息
            WxMpXmlMessage inMessage = WxMpXmlMessage.fromXml(request.getInputStream());
            System.out.println("-------------------");
            System.out.println("in:" + inMessage);
            System.out.println("-------------------");
            if((inMessage.getEvent().equals("SCAN") || inMessage.getEvent().equals("subscribe")) && org.apache.commons.lang.StringUtils.isNotBlank(inMessage.getEventKey()) && org.apache.commons.lang.StringUtils.isNotBlank(inMessage.getTicket())) {
                System.out.println("==========================================:" +inMessage.getEventKey());
                String eventKey = inMessage.getEventKey().toString();
                eventKey = eventKey.replace("qrscene_","");
                String[] eventKeys = eventKey.split(",");
                String productId = eventKeys[0];
                String salemanId = eventKeys[1];

                // 获取限时抢购列表
                com.leoman.service.ProductService productService = (com.leoman.service.ProductService) BeanUtil.getBean("productServiceImpl");
                Product product = productService.getById(Long.parseLong(productId));

                NewsBuilder news = WxMpCustomMessage.NEWS();
                news = news.toUser(inMessage.getFromUserName());
                WxMpCustomMessage.WxArticle article  = new WxMpCustomMessage.WxArticle();
                article.setUrl(Configue.getBaseUrl() + "weixin/product/detail?id=" + product.getId() + "&salemanId=" + salemanId);
                article.setPicUrl(Configue.getUploadUrl() + product.getCoverImage().getPath());
                article.setDescription(product.getContent());
                article.setTitle(product.getTitle());
                news.addArticle(article);

                WxMpService wxMpService = (WxMpService) BeanUtil.getBean("wxMpService");
                wxMpService.customMessageSend(news.build());
            }
            WxMpXmlOutMessage outMessage = wxMpMessageRouter.route(inMessage);
            WebUtil.print(response, outMessage.toXml());
            return;
        }
    }
}
