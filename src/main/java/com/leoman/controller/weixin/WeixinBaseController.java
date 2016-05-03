package com.leoman.controller.weixin;

import com.leoman.cache.CacheService;
import com.leoman.core.Constant;
import com.leoman.core.bean.Result;
import com.leoman.entity.Coupon;
import com.leoman.entity.KUser;
import com.leoman.entity.Saleman;
import com.leoman.entity.SalemanRecord;
import com.leoman.pay.util.XMLUtil;
import com.leoman.service.CouponService;
import com.leoman.service.KUserService;
import com.leoman.service.LoginService;
import com.leoman.service.SalamanRecordService;
import com.leoman.utils.CommonUtils;
import com.leoman.utils.CookiesUtils;
import com.leoman.utils.SmsSendUtils;
import com.leoman.utils.WebUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/3/16.
 */
@RequestMapping(value = "weixin/login")
@Controller
public class WeixinBaseController {

    @Autowired
    private LoginService service;

    @Autowired
    private KUserService userService;

    @Autowired
    private CouponService couponService;

    @Autowired
    private SalamanRecordService salamanRecordService;

    @Resource(name = "cacheTempCodeServiceImpl")
    private CacheService<String> cacheService;

    @RequestMapping("/toLogin")
    public String toLogin(HttpServletRequest request, HttpServletResponse response, Long salemanId, Model model) {
        Map<String, Object> params = CookiesUtils.ReadCookieMap(request);
        if (params != null && params.size() != 0) {
            String mobile = (String) params.get("mobile");
            String password = (String) params.get("password");
            if (StringUtils.isNotBlank(mobile) && StringUtils.isNotBlank(password)) {
                Boolean result = service.loginWeixin(request, response, mobile, password);

                Object goUrlObj = request.getSession().getAttribute(Constant.GO_URL);
                if(goUrlObj != null) {
                    String goUrl = (String) goUrlObj;
                    try {
                        response.sendRedirect(goUrl);
                        return null;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else if (result) {
                    return "redirect:/weixin/user/index";
                }
            }
        }
        System.out.println("salemanId:" + salemanId);
        model.addAttribute("salemanId", salemanId);
        return "weixin/login";
    }

    @RequestMapping("/lostPwd")
    public String lostPwd() {
        return "weixin/find-password";
    }

    @RequestMapping("/toRegister")
    public String toRegister(Model model, Long fromUserId, Long couponId, Long salemanId) {
        System.out.println("***********************************************************3、准备注册***********************************************************");
        System.out.println("邀请人id：" + fromUserId);
        System.out.println("优惠券id：" + couponId);
        System.out.println("***********************************************************3、准备注册***********************************************************");

        model.addAttribute("fromUserId", fromUserId);
        model.addAttribute("couponId", couponId);

        System.out.println("salemanId:" + salemanId);
        model.addAttribute("salemanId", salemanId);
        return "weixin/register";
    }

    @RequestMapping("/toPassword")
    public String toSetPassword(String username, String yzm, Model model, Long fromUserId, Long couponId, Long salemanId) {
        System.out.println("***********************************************************4、准备注册***********************************************************");
        System.out.println("邀请人id：" + fromUserId);
        System.out.println("优惠券id：" + couponId);
        System.out.println("***********************************************************4、准备注册***********************************************************");

        Map<String, String> reg = new HashMap<String, String>();
        reg.put("username", username);
        reg.put("code", yzm);
        model.addAttribute("reg", reg);
        model.addAttribute("fromUserId", fromUserId);
        model.addAttribute("couponId", couponId);

        System.out.println("salemanId:" + salemanId);

        model.addAttribute("salemanId", salemanId);
        return "weixin/setpassword";
    }

    @RequestMapping("/register")
    public void register(HttpServletRequest request, HttpServletResponse response, String username, String password, String code, Long fromUserId, Long couponId, Long salemanId) {
        try {


            // 参数验证
            if (StringUtils.isBlank(username) && StringUtils.isBlank(code)) {
                WebUtil.print(response, new Result(false).msg("参数错误"));
                return;
            }

            // 判断验证码
            String hasCode = cacheService.get(username);
            if (!code.equals(hasCode)) {
                WebUtil.print(response, new Result(false).msg("验证码错误"));
                return;
            }

            // 验证用户是否已注册
            KUser _user = userService.findByMobile(username);
            if (_user != null) {
                WebUtil.print(response, new Result(false).msg("该手机号码已注册"));
                return;
            }

            KUser user = new KUser();
            user.setMobile(username);
            user.setPassword(password);
            user.setCount(0);
            KUser resultUser = userService.register(user);

            System.out.println("salemanId:" + salemanId);
            if (salemanId != null) {
                SalemanRecord record = new SalemanRecord();
                Saleman s = new Saleman();
                s.setId(salemanId);

                KUser u = new KUser();
                u.setId(resultUser.getId());

                record.setSaleman(s);
                record.setKuser(u);
                record.setContent("注册");

                salamanRecordService.create(record);
            }

            if (null != resultUser) {
                System.out.println("***********************************************************5、注册界面***********************************************************");
                System.out.println("邀请人id:" + fromUserId);
                System.out.println("优惠券id:" + couponId);
                System.out.println("***********************************************************5、注册界面***********************************************************");

                // 邀请人注册成功后，增加邀请人的邀请数量
                if (null != fromUserId) {
                    if (null == couponId) {
                        KUser inviteUser = userService.getById(fromUserId);

                        inviteUser.setCount(inviteUser.getCount() + 1);

                        if (inviteUser.getCount() % 3 == 0) {
                            inviteUser.setCount(0);
                            // 添加优惠券
                            couponService.createCoupon(fromUserId);
                        }
                        userService.update(inviteUser);
                    } else {
                        couponService.reUse(couponId);
                    }
                } else {
                    if (null != couponId) {
                        Coupon coupon = couponService.getById(couponId);
                        if (null != coupon && coupon.getIsChanged() == 0) {
                            coupon.setUserId(user.getId());
                            couponService.update(coupon);
                        }
                    }
                }
            }

            WebUtil.print(response, new Result(true));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/loginCheck")
    public String login(HttpServletRequest request, HttpServletResponse response, String mobile, String password, Model model, Long fromUserId, Long couponId) {
        Boolean success = service.loginWeixin(request, response, mobile, password);
        Object goUrlObj = request.getSession().getAttribute(Constant.GO_URL);
        System.out.println("goUrlObj：" + goUrlObj);
        if (success) {
            KUser kUser = (KUser) request.getSession().getAttribute(Constant.SESSION_WEIXIN_USER);

            if (null != couponId) {
                Coupon coupon = couponService.getById(couponId);
                if (null != coupon && coupon.getIsChanged() == 0) {
                    coupon.setUserId(kUser.getId());
                    couponService.update(coupon);
                }
            }

            if (goUrlObj != null) {
                String goUrl = (String) goUrlObj;
                try {
                    response.sendRedirect(goUrl);
                    return null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return "redirect:/weixin/user/index";
        }
        model.addAttribute("error", "用户名密码错误");
        return "weixin/login";
    }

    /**
     * 发送验证码
     *
     * @param response
     * @param mobile
     */
    @RequestMapping("/sendCode")
    public void sendCode(HttpServletResponse response, String mobile) {
        try {
            String code = CommonUtils.getCode(6);
            if (SmsSendUtils.sendTemplateSms(mobile, "JSM40387-0001", code)) {
                cacheService.put(mobile, code);
                WebUtil.print(response, new Result(true));
            }
        } catch (Exception e) {
            e.printStackTrace();
            WebUtil.print(response, new Result(false).msg("发送失败!"));
        }
    }

    @RequestMapping("modifyPassword")
    public String modifyPassword(HttpServletResponse response, String mobile, String password, String code) {

        String hasCode = cacheService.get(mobile);
        if (!code.equals(hasCode)) {
            WebUtil.print(response, new Result(false).msg("验证码错误!"));
        }
        KUser _user = userService.findByMobile(mobile);
        if (_user == null) {
            WebUtil.print(response, new Result(false).msg("用户不存在"));
        }
        _user.setPassword(password);
        userService.update(_user);
        return "weixin/login";
    }

    @RequestMapping(value = "")
    public void weixinNotify(HttpServletRequest request) {
        Map<String, Object> result = parse(request);
        String status = result.get("result_code").toString();
        if ("SUCCESS".equals(status)) {
            String sn = result.get("out_trade_no").toString();

            try {

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> parse(HttpServletRequest request) {
        Map<String, Object> resultMap = null;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
            String line = null;
            String result = "";
            while ((line = reader.readLine()) != null) {
                result += line;
            }
            System.out.println("result:" + result);
            resultMap = XMLUtil.doXMLParse(result);
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    @RequestMapping("/findPwd")
    public void findPwd(HttpServletResponse response, String mobile, String code) {
        // 参数验证
        if (StringUtils.isBlank(mobile) && StringUtils.isBlank(code)) {
            WebUtil.print(response, new Result(false).msg("参数错误"));
            return;
        }

        // 判断验证码
        String hasCode = cacheService.get(mobile);
        if (!code.equals(hasCode)) {
            WebUtil.print(response, new Result(false).msg("验证码错误"));
            return;
        }

        KUser kUser = userService.findByMobile(mobile);

        if (null == kUser) {
            WebUtil.print(response, new Result(false).msg("手机号不存在"));
            return;
        }

        WebUtil.print(response, new Result(true).msg(kUser.getPassword()));
    }
}
