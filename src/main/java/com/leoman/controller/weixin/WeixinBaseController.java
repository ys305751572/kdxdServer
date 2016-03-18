package com.leoman.controller.weixin;

import com.leoman.cache.CacheService;
import com.leoman.core.bean.Result;
import com.leoman.entity.KUser;
import com.leoman.pay.util.XMLUtil;
import com.leoman.service.KUserService;
import com.leoman.service.LoginService;
import com.leoman.utils.CommonUtils;
import com.leoman.utils.SmsSendUtils;
import com.leoman.utils.WebUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(HttpServletResponse response, String mobile, String code) {

        // TODO 判断验证码
        if (StringUtils.isBlank(mobile) && StringUtils.isBlank(code)) {
            WebUtil.print(response, new Result(false).msg("参数错误"));
        }
        String hasCode = cacheService.get(mobile);
        if (!code.equals(hasCode)) {
            WebUtil.print(response, new Result(false).msg("验证码错误"));
        }
        // TODO 验证用户是否已注册
        KUser _user = userService.findByMobile(mobile);
        if (_user != null) {
            WebUtil.print(response, new Result(false).msg("该手机号码已注册"));
        }
        KUser user = new KUser();
        user.setMobile(mobile);
        userService.register(user);
        return "";

    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpServletRequest request, HttpServletResponse response, String mobile, String password, Model model) {
        Boolean success = service.loginWeixin(request, mobile, password);
        if (success) {
            return "";
        }
        model.addAttribute("error", "用户名密码错误");
        return "";
    }

    /**
     * 发送验证码
     *
     * @param response
     * @param mobile
     */
    @RequestMapping(value = "/sendCode", method = RequestMethod.POST)
    public void sendCode(HttpServletResponse response, String mobile) {
        try {
            String code = CommonUtils.getCode(6);
            if (SmsSendUtils.sendTemplateSms(mobile, "JSM40387-0001", CommonUtils.getCode(6))) {
                cacheService.put(mobile, code);
                WebUtil.print(response, new Result(true).msg("操作成功!"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            WebUtil.print(response, new Result(false).msg("发送失败!"));
        }
    }

    @RequestMapping(value = "modifyPassword", method = RequestMethod.POST)
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
        return null;
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
}
