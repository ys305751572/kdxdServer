package com.leoman.controller.weixin;

import com.leoman.core.Configue;
import com.leoman.entity.*;
import com.leoman.service.*;
import com.leoman.utils.WebUtil;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpPayCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
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

    @Autowired
    private KUserService kUserService;

    @Autowired
    private PayrecordService payrecordService;

    @Autowired
    private CoinlogService coinlogService;

    @Autowired
    private WebPayrecordService webPayrecordService;

    @Autowired
    private SalamanRecordService salamanRecordService;

    @RequestMapping(value = "index")
    public String index(HttpServletRequest request,
                        HttpServletResponse response) {
        return "weixin/支付测试";
    }

    @RequestMapping(value = "go")
    public void go(HttpServletRequest request,
                   HttpServletResponse response,
                   String orderId) {
        WxUser wxUser = wxUserService.getWXUserByRequest(request);
        Map<String, String> result = wxMpService.getJSSDKPayInfo(wxUser.getOpenId(), orderId, 0.01, "xxx", "JSAPI",
                request.getRemoteAddr(), Configue.getBaseUrl() + "weixin/pay/callback");
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
//        Map<String, String> result = wxMpService.getJSSDKPayInfo(wxUser.getOpenId(), orderNo, totalPrice, order.getProductName(), "JSAPI",
//                request.getRemoteAddr(), Configue.getBaseUrl() + "weixin/pay/callback");

        Map<String, String> result = wxMpService.getJSSDKPayInfo(wxUser.getOpenId(), orderNo, 0.01, order.getProductName(), "JSAPI",
                request.getRemoteAddr(), Configue.getBaseUrl() + "weixin/pay/callback");
        WebUtil.printJson(response, result);
    }

    @RequestMapping(value = "recharge")
    public void recharge(HttpServletRequest request,
                         HttpServletResponse response,
                         String sn) {
        System.out.println("sn:" + sn);

        Payrecord payrecord = payrecordService.findOneBySn(sn);

        System.out.println("payrecord:" + payrecord);

        Double totalPrice = payrecord.getMoney();
        WxUser wxUser = wxUserService.getWXUserByRequest(request);

        System.out.println("wxUser--openId:" + wxUser.getOpenId());
        System.out.println("recharge--orderNo:" + sn);
        System.out.println("totalPrice:" + totalPrice);

        Map<String, String> result = wxMpService.getJSSDKPayInfo(wxUser.getOpenId(), sn, totalPrice, "在线充值", "JSAPI",
                request.getRemoteAddr(), Configue.getBaseUrl() + "weixin/pay/coinCallback");
        WebUtil.printJson(response, result);
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
                if (order.getStatus() == 0) {
                    order.setStatus(1);
                    order.setUpdateDate(System.currentTimeMillis());
                    orderService.update(order);



                }
                KUser kuser = order.getUser();
                System.out.println("============kuser.getId()=========:" +kuser.getId());
                List<SalemanRecord> list = salamanRecordService.findByUserId(kuser.getId());
                if(list != null && !list.isEmpty()) {
                    Saleman saleman = list.get(0).getSaleman();
                    SalemanRecord record = new SalemanRecord();
                    record.setKuser(kuser);
                    record.setSaleman(saleman);
                    record.setContent("购买");
                    record.setCreateDate(System.currentTimeMillis());
                    record.setUpdateDate(System.currentTimeMillis());
                    salamanRecordService.create(record);

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

    @RequestMapping(value = "coinCallback")
    public void coinCallback(HttpServletRequest request,
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

                Payrecord payrecord = payrecordService.findOneBySn(orderNo);

                if (null != payrecord) {
                    // 生成充值记录
                    Coinlog coinlog = new Coinlog();
                    coinlog.setUserId(payrecord.getUser().getId());
                    coinlog.setMoney(payrecord.getMoney());
                    coinlog.setCreateDate(System.currentTimeMillis());

                    coinlogService.create(coinlog);

                    KUser kUser = payrecord.getUser();
                    kUser.setMoney(kUser.getMoney() + payrecord.getMoney());
                    kUserService.update(kUser);

                    // 生成网站收支记录
                    WebPayrecord r = new WebPayrecord();
                    r.setRecordCode(String.valueOf(System.currentTimeMillis()));
                    r.setMoney(payrecord.getMoney());
                    r.setPlat(0);
                    webPayrecordService.create(r);
                } else {
                    System.out.println("充值记录号有误！！！！！！！");
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
