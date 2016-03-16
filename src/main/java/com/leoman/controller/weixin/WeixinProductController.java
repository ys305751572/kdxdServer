package com.leoman.controller.weixin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2016/3/16.
 */
@Controller
@RequestMapping(value = "weixin/product")
public class WeixinProductController{

    @RequestMapping(value = "buy",method = RequestMethod.POST)
    public void buy() {

    }

    @RequestMapping(value = "buyWithCoupons", method = RequestMethod.POST)
    public void buyWithCoupons(HttpServletResponse response,Long id) {

    }
}
