package com.leoman.controller.weixin;

import com.leoman.common.exception.GeneralExceptionHandler;
import com.leoman.common.factory.DataTableFactory;
import com.leoman.controller.common.CommonController;
import com.leoman.entity.Coupon;
import com.leoman.entity.Information;
import com.leoman.service.CouponService;
import com.leoman.utils.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/3/18.
 */
@RequestMapping(value = "weixin/coupons")
@Controller
public class WeixinCouponsController extends CommonController{

    @Autowired
    private CouponService service;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return null;
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public void list(HttpServletResponse response, Integer draw, Integer start, Integer length, Long userId) {
        try {
            int pageNum = getPageNum(start, length);
            Page<Coupon> page = service.findPageByUserId(userId,pageNum,length);
            List<Coupon> list = page.getContent();
            for (Coupon c : list) {
                if(System.currentTimeMillis() > c.getEndDate()) {
                    c.setOverdue(true);
                }
            }

            Map<String, Object> result = DataTableFactory.fitting(draw, page);
            WebUtil.print(response, result);
        } catch (Exception e) {
            GeneralExceptionHandler.log(e);
            WebUtil.print(response, emptyData);
        }
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public String detail(Long id, Model model) {

        Coupon c = service.getById(id);
        model.addAttribute("c",c);
        return null;
    }
}
