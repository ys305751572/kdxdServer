package com.leoman.controller.weixin;

import com.leoman.controller.common.CommonController;
import com.leoman.core.Constant;
import com.leoman.entity.Activity;
import com.leoman.entity.Coupon;
import com.leoman.entity.KUser;
import com.leoman.entity.WxUser;
import com.leoman.service.ActivityService;
import com.leoman.service.CouponService;
import com.leoman.utils.CommonUtils;
import com.leoman.utils.DateUtils;
import com.leoman.utils.HttpRequestUtil;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import net.sf.json.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2016/3/18.
 */
@RequestMapping(value = "weixin/coupons")
@Controller
public class WeixinCouponsController extends CommonController {

    @Autowired
    private CouponService service;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private WxMpConfigStorage wxMpConfigStorage;

    @RequestMapping("/list")
    public String list(HttpServletRequest request, ModelMap model) {
        // 获取活动详情
        Activity activity = activityService.getById(1L);
        model.addAttribute("activity", activity);

        KUser kUser = (KUser) request.getSession().getAttribute(Constant.SESSION_WEIXIN_USER);
        WxUser wxUser = (WxUser) request.getSession().getAttribute(Constant.SESSION_WEIXIN_WXUSER);

        System.out.println("踢踢用户信息：" + kUser);
        System.out.println("微信用户信息：" + wxUser);

        List<Coupon> list = service.findListByUserId2(kUser.getId());
        model.addAttribute("couponList", list);
        model.addAttribute("wxUser", wxUser);
        model.addAttribute("user", kUser);

        // 生成时间戳
        String timestamp = System.currentTimeMillis() + "";
        timestamp = timestamp.substring(0, 10);

        // 生成随机字符串
        String noncestr = String.valueOf(System.currentTimeMillis() / 1000);

        // 生成签名
        String signature = CommonUtils.getSignature(request, noncestr, timestamp, "http://qq.tt/kdxgServer/weixin/coupons/list", wxMpConfigStorage);

        model.addAttribute("timestamp", timestamp);
        model.addAttribute("noncestr", noncestr);
        model.addAttribute("signature", signature);

        return "weixin/coupon-list";
    }

    @RequestMapping("/detail")
    public String detail(HttpServletRequest request, Long id, Model model) {
        // 获取活动详情
        Activity activity = activityService.getById(1L);
        model.addAttribute("activity", activity);

        KUser kUser = (KUser) request.getSession().getAttribute(Constant.SESSION_WEIXIN_USER);
        WxUser wxUser = (WxUser) request.getSession().getAttribute(Constant.SESSION_WEIXIN_WXUSER);
        Coupon coupon = service.getById(id);
        model.addAttribute("coupon", coupon);
        model.addAttribute("wxUser", wxUser);
        model.addAttribute("user", kUser);

        // 生成时间戳
        String timestamp = System.currentTimeMillis() + "";
        timestamp = timestamp.substring(0, 10);

        // 生成随机字符串
        String noncestr = String.valueOf(System.currentTimeMillis() / 1000);

        // 生成签名
        String signature = CommonUtils.getSignature(request, noncestr, timestamp, "http://qq.tt/kdxgServer/weixin/coupons/detail?id=" + id, wxMpConfigStorage);

        model.addAttribute("timestamp", timestamp);
        model.addAttribute("noncestr", noncestr);
        model.addAttribute("signature", signature);

        return "weixin/coupon-detail";
    }
}
