//package com.leoman.controller.weixin;
//
//import com.leoman.core.Configue;
//import com.leoman.core.bean.Result;
//import com.leoman.entity.Business;
//import com.leoman.entity.WxUser;
//import com.leoman.service.BusinessService;
//import com.leoman.service.WxUserService;
//import com.leoman.utils.HttpUtil;
//import com.leoman.utils.WebUtil;
//
//import me.chanjar.weixin.common.bean.WxJsapiSignature;
//import me.chanjar.weixin.mp.api.WxMpConfigStorage;
//import me.chanjar.weixin.mp.api.WxMpService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// * Created by wangbin on 2015/8/5.
// */
//@Controller("weixin.bussiness")
//@RequestMapping(value = "weixin/business")
//public class BusinessController {
//
//
//    @Autowired
//    private WxMpService wxMpService;
//    @Autowired
//    private WxMpConfigStorage wxMpConfigStorage;
//    @Autowired
//    private WxUserService wxUserService;
//    @Autowired
//    private BusinessService businessService;
//
//    @RequestMapping(value = "/in")
//    public String businessIn(HttpServletRequest request,
//                             HttpServletResponse response,
//                             ModelMap model)throws Exception{
//
//        String fullUrl = HttpUtil.getFullUrl(request, Configue.getBaseDomain());
//
//        WxJsapiSignature wxJsapiSignature =  wxMpService.createJsapiSignature(fullUrl);
//        model.put("wxJsapiSignature",wxJsapiSignature);
//        model.put("appid",wxMpConfigStorage.getAppId());
//
//        return "/微信/商家入驻";
//    }
//
//
//    @RequestMapping(value = "/submit")
//    public void businessSubmit(HttpServletRequest request,
//                               HttpServletResponse response,
//                               Business business){
//
//        WxUser wxUser =  wxUserService.getWXUserByRequest(request);
//        business.setWxUser(wxUser);
//        businessService.create(business);
//        WebUtil.print(response,new Result(true));
//    }
//
//}
