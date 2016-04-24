package com.leoman.controller.weixin;

import com.leoman.controller.common.CommonController;
import com.leoman.core.Constant;
import com.leoman.core.bean.Result;
import com.leoman.entity.Address;
import com.leoman.entity.KUser;
import com.leoman.entity.Order;
import com.leoman.service.AddressService;
import com.leoman.service.KUserService;
import com.leoman.utils.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    private KUserService kUserService;

    @RequestMapping("/index")
    public String index() {
        return "weixin/address-list";
    }

    @RequestMapping("/update")
    public String update(ModelMap model, Long id, Long pbrId) {
        model.addAttribute("pbrId", pbrId);
        if (null != id) {
            Address address = service.getById(id);
            model.addAttribute("address", address);
        }
        return "weixin/address-detail";
    }

    @RequestMapping("/list")
    public String list(HttpServletRequest request, ModelMap model, Long pbrId) {
        try {
            model.addAttribute("pbrId", pbrId);
            KUser kUser = (KUser) request.getSession().getAttribute(Constant.SESSION_WEIXIN_USER);

            // 首先，根据用户id查询对应的收货地址，如果没有默认收货地址，则跳转到新增收货地址界面
            Address address = service.findDefaultByUserId(kUser.getId());
            if (null == address) {
                return "weixin/address-detail";
            }

            List<Address> list = service.findByUserId(kUser.getId());

            model.addAttribute("addressList", list);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "weixin/address-list";
    }

    @RequestMapping("/addressList")
    public String addressList(ModelMap model, Long pbrId, Long userId) {
        try {
            KUser kUser = kUserService.getById(userId);
            List<Address> list = service.findByUserId(kUser.getId());

            model.addAttribute("addressList", list);
            model.addAttribute("userId", userId);
            model.addAttribute("pbrId", pbrId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "weixin/buy-address-list";
    }

    /**
     * 选择默认地址
     *
     * @param response
     * @param userId
     * @param addressId
     */
    @RequestMapping(value = "addressDefault", method = RequestMethod.POST)
    public void addressDefault(HttpServletRequest request, HttpServletResponse response, Long userId, Long addressId) {
        if (null == userId) {
            KUser kUser = (KUser) request.getSession().getAttribute(Constant.SESSION_WEIXIN_USER);
            userId = kUser.getId();
        }
        service.addressDefault(userId, addressId);
        WebUtil.print(response, new Result(true).msg("操作成功"));
    }

    @RequestMapping("save")
    @ResponseBody
    public Long save(HttpServletRequest request, Long addressId, String name, String mobile, String address) {
        try {
            KUser kUser = (KUser) request.getSession().getAttribute(Constant.SESSION_WEIXIN_USER);

            // 判断是否有默认收货地址，如果没有，则将当前收货地址设置为默认地址
            Address defaultAddress = service.findDefaultByUserId(kUser.getId());

            Address addressInfo = null;

            if (null == addressId) {
                addressInfo = new Address();
            } else {
                addressInfo = service.getById(addressId);
            }

            addressInfo.setName(name);
            addressInfo.setMobile(mobile);
            addressInfo.setAddress(address);
            addressInfo.setUserId(kUser.getId());
            if (null == defaultAddress) {
                addressInfo.setIsDefault(0);
            } else {
                addressInfo.setIsDefault(1);
            }

            service.update(addressInfo);

            return addressInfo.getId();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0L;
    }

    @RequestMapping("delete")
    @ResponseBody
    public int delete(Long addressId) {
        try {
            service.deleteById(addressId);

            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }
}
