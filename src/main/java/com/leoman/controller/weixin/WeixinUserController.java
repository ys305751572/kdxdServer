package com.leoman.controller.weixin;

import com.leoman.controller.common.CommonController;
import com.leoman.core.Constant;
import com.leoman.entity.Address;
import com.leoman.entity.KUser;
import com.leoman.service.KUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Administrator on 2016/3/23.
 */
@Controller
@RequestMapping(value = "weixin/user")
public class WeixinUserController extends CommonController{

    @Autowired
    private KUserService service;

    @RequestMapping(value = "toAddress", method = RequestMethod.GET)
    public String toAddress(HttpServletRequest request, Model model) {
        KUser weixinUser = (KUser) request.getSession().getAttribute(Constant.SESSION_WEIXIN_USER);
        List<Address> list = service.findAllAddressByUserId(weixinUser.getId());
        model.addAttribute("list",list);
        return "weixin/address-list";
    }

    @RequestMapping(value = "findDefaultAddress", method = RequestMethod.POST)
    @ResponseBody
    public Address findDefaultAddress(HttpServletRequest request) {
        KUser weixinUser = (KUser) request.getSession().getAttribute(Constant.SESSION_WEIXIN_USER);
        Address address = service.findDefaultAddressByUserId(weixinUser.getId());
        return address;
    }
}
