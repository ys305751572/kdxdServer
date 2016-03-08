//package com.leoman.controller.weixin;
//
//import com.leoman.core.Configue;
//import com.leoman.entity.WxUser;
//import com.leoman.service.WxUserService;
//import com.leoman.utils.HttpUtil;
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
// * Created by wangbin on 2015/8/3.
// */
//@Controller
//@RequestMapping(value = "weixin/map")
//public class MapController {
//
//    @Autowired
//    private WxMpService wxMpService;
//    @Autowired
//    private WxMpConfigStorage wxMpConfigStorage;
//    @Autowired
//    private WxUserService wxUserService;
//
//    @RequestMapping(value = "/index")
//    public String index(HttpServletRequest request,
//                        HttpServletResponse response,
//                        ModelMap model){
//        try {
//            WxUser wxUser = wxUserService.getWXUserByRequest(request);
//            System.out.println(wxUser);
//
//            String fullUrl = HttpUtil.getFullUrl(request, Configue.getBaseDomain());
//
//            WxJsapiSignature wxJsapiSignature =  wxMpService.createJsapiSignature(fullUrl);
//            model.put("wxJsapiSignature",wxJsapiSignature);
//            model.put("appid",wxMpConfigStorage.getAppId());
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return "/微信/地图";
//    }
//
//
//
//
//}
