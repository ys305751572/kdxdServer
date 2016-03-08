//package com.leoman.controller.business;
//
//import com.leoman.controller.common.CommonController;
//import com.leoman.core.Configue;
//import com.leoman.core.Constant;
//import com.leoman.core.bean.Result;
//import com.leoman.entity.Business;
//import com.leoman.entity.Image;
//import com.leoman.entity.Member;
//import com.leoman.service.BusinessService;
//import com.leoman.service.LoginService;
//import com.leoman.utils.WebUtil;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// * Created by wangbin on 2015/8/13.
// */
//@Controller
//@RequestMapping(value = "business/info")
//public class InformationController extends CommonController{
//
//    @Autowired
//    private LoginService loginService;
//    @Autowired
//    private BusinessService businessService;
//
//    @RequestMapping(value = "index")
//    public String index(HttpServletRequest request,
//                        HttpServletResponse response,
//                        ModelMap model){
//
//        Member member = loginService.getMember(request, Constant.MEMBER_TYPE_BUSINESS);
//
////        Integer businessId =   member.getBusiness().getId();
//        Integer businessId =   null;
//        Business business = businessService.getById(businessId);
//        if(business.getImage()!=null){
//            Image image = business.getImage();
//            image.setPath(Configue.getUploadUrl() + image.getPath());
//        }
//        model.put("business", business);
//        return "商家/商家信息";
//    }
//
//
//
//    @RequestMapping(value = "save")
//    public void save(HttpServletRequest request,
//                     HttpServletResponse response,
//                     Integer imageId,
//                     Business business){
//        if (imageId != null) {
//            Image image = new Image();
//            image.setId(imageId);
//            business.setImage(image);
//        }else{
//            WebUtil.print(response, new Result(false).msg("图片不能为空!"));
//            return;
//        }
//
//        Member member = loginService.getMember(request, Constant.MEMBER_TYPE_BUSINESS);
//        Integer businessId =   null;
//        //member.getBusiness().getId();
//        businessService.update(businessId,business);
//        WebUtil.print(response, new Result(true).msg("资料保存成功!"));
//    }
//
//
//}
