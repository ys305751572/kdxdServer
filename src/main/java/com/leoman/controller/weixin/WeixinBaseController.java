package com.leoman.controller.weixin;

import com.leoman.cache.CacheService;
import com.leoman.core.Constant;
import com.leoman.core.bean.Result;
import com.leoman.entity.KUser;
import com.leoman.pay.util.XMLUtil;
import com.leoman.service.KUserService;
import com.leoman.service.LoginService;
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

    @Resource(name = "cacheTempCodeServiceImpl")
    private CacheService<String> cacheService;

    @RequestMapping("/toLogin")
    public String toLogin(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> params = CookiesUtils.ReadCookieMap(request);
        if (params != null && params.size() != 0) {
            String mobile = (String) params.get("mobile");
            String password = (String) params.get("password");
            if (StringUtils.isNotBlank(mobile) && StringUtils.isNotBlank(password)) {
                Boolean result = service.loginWeixin(request, response, mobile, password);
                if (result) {
                    return "redirect:/weixin/user/index";
                }
            }
        }

        return "weixin/login";
    }

    @RequestMapping("/lostPwd")
    public String lostPwd() {
        return "weixin/find-password";
    }

    @RequestMapping("/toRegister")
    public String toRegister() {
        return "weixin/register";
    }

    @RequestMapping("/toPassword")
    public String toSetPassword(String username, String yzm, Model model) {
        Map<String, String> reg = new HashMap<String, String>();
        reg.put("username", username);
        reg.put("code", yzm);
        model.addAttribute("reg", reg);
        return "weixin/setpassword";
    }

    @RequestMapping("/register")
    public void register(HttpServletResponse response, String username, String password, String code) {
        // 参数验证
        if (StringUtils.isBlank(username) && StringUtils.isBlank(code)) {
            WebUtil.print(response, new Result(false).msg("参数错误"));
            return;
        }

        // TODO 判断验证码
        String hasCode = cacheService.get(username);
        if (!code.equals(hasCode)) {
            WebUtil.print(response, new Result(false).msg("验证码错误"));
            return;
        }
        // TODO 验证用户是否已注册
        KUser _user = userService.findByMobile(username);
        if (_user != null) {
            WebUtil.print(response, new Result(false).msg("该手机号码已注册"));
            return;
        }
        KUser user = new KUser();
        user.setMobile(username);
        user.setPassword(password);
        userService.register(user);
        WebUtil.print(response, new Result(true));
    }

    @RequestMapping("/loginCheck")
    public String login(HttpServletRequest request, HttpServletResponse response, String mobile, String password, Model model) {
        Boolean success = service.loginWeixin(request, response, mobile, password);
        Object goUrlObj = request.getSession().getAttribute(Constant.GO_URL);
        System.out.println("goUrlObj：" + goUrlObj);
        if (success) {
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
