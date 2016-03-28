package com.leoman.controller.weixin;

import com.leoman.controller.common.CommonController;
import com.leoman.core.bean.Result;
import com.leoman.entity.Address;
import com.leoman.entity.KUser;
import com.leoman.service.AddressService;
import com.leoman.service.KUserService;
import com.leoman.utils.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Administrator on 2016/3/18.
 */
@Controller
@RequestMapping(value = "weixin/address")
public class WeixinAddressController extends CommonController {

    @Autowired
    private AddressService service;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return null;
    }

    @RequestMapping("/list")
    public String list(HttpServletResponse response, Long userId, Long pbrId, Model model) {
        List<Address> list = service.findByUserId(userId);
        model.addAttribute("list", list);
        model.addAttribute("userId", userId);
        model.addAttribute("pbrId", pbrId);
        return "weixin/address-list";
    }

    /**
     * 选择默认地址
     *
     * @param response
     * @param userId
     * @param addressId
     */
    @RequestMapping(value = "addressDefault", method = RequestMethod.POST)
    public void addressDefault(HttpServletResponse response, Long userId, Long addressId) {
        service.addressDefault(userId, addressId);
        WebUtil.print(response, new Result(true).msg("操作成功"));
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public void save(HttpServletResponse response, Long userId, Address address) {
        if (address.getId() == null) {
            address.setUserId(userId);
            service.create(address);
        } else {
            Address _address = service.getById(address.getId());
            _address.setName(address.getName());
            _address.setMobile(address.getMobile());
            _address.setAddress(address.getAddress());
        }
        WebUtil.print(response, new Result(true).msg("操作成功"));
    }


}
