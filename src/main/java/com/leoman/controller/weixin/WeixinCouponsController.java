package com.leoman.controller.weixin;

import com.leoman.controller.common.CommonController;
import com.leoman.core.Constant;
import com.leoman.entity.Activity;
import com.leoman.entity.Coupon;
import com.leoman.entity.KUser;
import com.leoman.entity.WxUser;
import com.leoman.service.ActivityService;
import com.leoman.service.CouponService;
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
        List<Coupon> list = service.findListByUserId(kUser.getId());
        model.addAttribute("couponList", list);
        model.addAttribute("wxUser", wxUser);
        model.addAttribute("user", kUser);

        // 生成时间戳
        String timestamp = System.currentTimeMillis() + "";
        timestamp = timestamp.substring(0, 10);

        // 生成随机字符串
        String noncestr = String.valueOf(System.currentTimeMillis() / 1000);

        // 生成签名
        String signature = getSignature(request, noncestr, timestamp, "http://qq.tt/kdxgServer/weixin/coupons/list");

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
        String signature = getSignature(request, noncestr, timestamp, "http://qq.tt/kdxgServer/weixin/coupons/detail");

        model.addAttribute("timestamp", timestamp);
        model.addAttribute("noncestr", noncestr);
        model.addAttribute("signature", signature);

        return "weixin/coupon-detail";
    }

    public String getSignature(HttpServletRequest request, String noncestr, String timestamp, String url) {
        try {
            String ticket = (String) request.getSession().getAttribute(Constant.jsApi_ticket);

            String base_jsApi_ticket = wxMpConfigStorage.getJsapiTicket();
            System.out.println("base_jsApi_ticket：" + base_jsApi_ticket);

            System.out.println("ticket：" + ticket);

            if (null != ticket && !ticket.equals("")) {
                return getSignature(ticket, timestamp, noncestr, url);
            }

            String accesstoken = wxMpConfigStorage.getAccessToken();
            String urljson = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + accesstoken + "&type=jsapi";

            String result = HttpRequestUtil.sendGet(urljson);

            JSONObject str = JSONObject.fromObject(result);

            String jsApi_ticket = str.get("ticket").toString();

            System.out.println("jsApi_ticket：" + jsApi_ticket);

            request.getSession().setAttribute(Constant.jsApi_ticket, jsApi_ticket);

            System.out.println("accessToken：" + accesstoken);
            System.out.println("urljson：" + urljson);
            System.out.println("jsApi_ticket：" + jsApi_ticket);

            return getSignature(jsApi_ticket, timestamp, noncestr, url);
        } catch (IOException e) {
            e.printStackTrace();
            return "false";
        }
    }

    // 获得js signature
    public static String getSignature(String jsapi_ticket, String timestamp, String nonce, String jsurl) throws IOException {
        /****
         * 对 jsapi_ticket、 timestamp 和 nonce 按字典排序 对所有待签名参数按照字段名的 ASCII
         * 码从小到大排序（字典序）后，使用 URL 键值对的格式（即key1=value1&key2=value2…）拼接成字符串
         * string1。这里需要注意的是所有参数名均为小写字符。 接下来对 string1 作 sha1 加密，字段名和字段值都采用原始值，不进行
         * URL 转义。即 signature=sha1(string1)。
         * **如果没有按照生成的key1=value&key2=value拼接的话会报错
         */
        String[] paramArr = new String[]{"jsapi_ticket=" + jsapi_ticket,
                "timestamp=" + timestamp, "noncestr=" + nonce, "url=" + jsurl};
        Arrays.sort(paramArr);
        // 将排序后的结果拼接成一个字符串
        String content = paramArr[0].concat("&" + paramArr[1]).concat("&" + paramArr[2])
                .concat("&" + paramArr[3]);
        System.out.println("拼接之后的content为:" + content);
        String gensignature = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            // 对拼接后的字符串进行 sha1 加密
            byte[] digest = md.digest(content.toString().getBytes());
            gensignature = byteToStr(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        // 将 sha1 加密后的字符串与 signature 进行对比
        if (gensignature != null) {
            return gensignature.toLowerCase();// 返回signature
        } else {
            return "false";
        }
    }

    /**
     * 将字节数组转换为十六进制字符串
     *
     * @param byteArray
     * @return
     */
    private static String byteToStr(byte[] byteArray) {
        String strDigest = "";
        for (int i = 0; i < byteArray.length; i++) {
            strDigest += byteToHexStr(byteArray[i]);
        }
        return strDigest;
    }

    /**
     * 将字节转换为十六进制字符串
     *
     * @param mByte
     * @return
     */
    private static String byteToHexStr(byte mByte) {
        char[] Digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
                'B', 'C', 'D', 'E', 'F'};
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = Digit[mByte & 0X0F];
        String s = new String(tempArr);
        return s;
    }
}
