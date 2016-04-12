package com.leoman.controller.weixin;

import com.leoman.core.Configue;
import com.leoman.entity.Order;
import com.leoman.entity.WxUser;
import com.leoman.service.OrderService;
import com.leoman.service.WxUserService;
import com.leoman.utils.WebUtil;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpPayCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

/**
 * Created by wangbin on 2015/10/8.
 */
@Controller
@RequestMapping(value = "weixin/pay")
public class WeixinPayController {

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private WxUserService wxUserService;

    @RequestMapping(value = "index")
    public String index(HttpServletRequest request,
                        HttpServletResponse response){
        return "weixin/支付测试";
    }

    @RequestMapping(value = "go")
    public void go(HttpServletRequest request,
                   HttpServletResponse response,
                   String orderId){
        WxUser wxUser = wxUserService.getWXUserByRequest(request);
        Map<String,String> result = wxMpService.getJSSDKPayInfo(wxUser.getOpenId(), orderId, 0.01, "xxx", "JSAPI",
                request.getRemoteAddr(), Configue.getBaseUrl()+"weixin/pay/callback");
        WebUtil.printJson(response, result);
    }

    @RequestMapping(value = "goPay")
    public void goPay(HttpServletRequest request,
                      HttpServletResponse response,
                      Long orderId) {
        Order order = orderService.getById(orderId);

        String orderNo = order.getSn();
        Double totalPrice = order.getMoney();
        WxUser wxUser = wxUserService.getWXUserByRequest(request);
        Map<String, String> result = wxMpService.getJSSDKPayInfo(wxUser.getOpenId(), orderNo, totalPrice, order.getProduct().getTitle(), "JSAPI",
                request.getRemoteAddr(), Configue.getBaseUrl() + "weixin/pay/callback");
        WebUtil.printJson(response, result);
    }

    @RequestMapping(value = "index2")
    public String index2(HttpServletRequest request,
                         HttpServletResponse response,
                         ModelMap model) {
        WxUser wxUser = wxUserService.getWXUserByRequest(request);
        Map<String, String> result = wxMpService.getJSSDKPayInfo(wxUser.getOpenId(), new Date().getTime() + "", 0.01, "xxxtest", "JSAPI",
                request.getRemoteAddr(), Configue.getBaseUrl() + "weixin/pay/callback");

        model.put("appId", result.get("appId"));
        model.put("timeStamp", result.get("timeStamp"));
        model.put("nonceStr", result.get("nonceStr"));
        model.put("packageVal", result.get("package"));
        model.put("signType", result.get("signType"));
        model.put("paySign", result.get("paySign"));

        return "weixin/支付测试2";
    }

    @RequestMapping(value = "callback")
    public void callBack(HttpServletRequest request,
                         HttpServletResponse response) {
        try {

            //把如下代码贴到的你的处理回调的servlet 或者.do 中即可明白回调操作
            System.out.print("微信支付回调数据开始");
            String inputLine;
            String notityXml = "";
            try {
                while ((inputLine = request.getReader().readLine()) != null) {
                    notityXml += inputLine;
                }
                request.getReader().close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println("接收到的报文：" + notityXml);

            WxMpPayCallback wxMpPayCallback = wxMpService.getJSSDKCallbackData(notityXml);

            //判断支付成功，更改订单状态
            if ("SUCCESS".equals(wxMpPayCallback.getReturn_code())) {
                String orderNo = wxMpPayCallback.getOut_trade_no();
                Order order = orderService.findByOrderSn(orderNo);
                //只有当订单状态为未付款时，将状态改为已付款待发货
                if ("0".equals(order.getStatus())) {
                    order.setStatus(1);
                    order.setUpdateDate(System.currentTimeMillis());
                    orderService.update(order);
                }
            }
            System.out.println("----------");
            System.out.println(wxMpPayCallback);
            String xmlResult = "<xml>" +
                    "<return_code><![CDATA[SUCCESS]]></return_code>" +
                    "<return_msg><![CDATA[OK]]></return_msg>" +
                    "</xml>";
            WebUtil.print(response, xmlResult);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
