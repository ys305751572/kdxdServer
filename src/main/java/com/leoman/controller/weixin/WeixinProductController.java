package com.leoman.controller.weixin;

import com.leoman.core.Constant;
import com.leoman.core.bean.Result;
import com.leoman.entity.KUser;
import com.leoman.entity.Product;
import com.leoman.entity.ProductBuyRecord;
import com.leoman.service.CouponService;
import com.leoman.service.ProductBuyRecordService;
import com.leoman.service.ProductService;
import com.leoman.service.PsService;
import com.leoman.utils.DateUtils;
import com.leoman.utils.KdxgUtils;
import com.leoman.utils.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.Date;

/**
 * Created by Administrator on 2016/3/16.
 */
@Controller
@RequestMapping(value = "weixin/product")
public class WeixinProductController {

    @Autowired
    private ProductService service;

    @Autowired
    private CouponService cService;

    @Autowired
    private ProductBuyRecordService pbservice;

    @Autowired
    private PsService psService;


    @RequestMapping(value = "buy", method = RequestMethod.POST)
    public void buy() {

    }

    @RequestMapping(value = "buyWithCoupons", method = RequestMethod.POST)
    public void buyWithCoupons(HttpServletRequest request, HttpServletResponse response, Long id,Long serviceId,Boolean isUsed) {
        KUser weixinUser = (KUser) request.getSession().getAttribute(Constant.SESSION_WEIXIN_USER);
        Integer buyCount = pbservice.findCountByProductId(id);
        Product product = service.getById(id);
        if(buyCount >= product.getCounts()) {
            WebUtil.print(response, new Result(false).msg("商品已抢完!"));
            return;
        }
        service.buy(id,serviceId,weixinUser.getId(),isUsed,request,response);
    }

    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String detail(HttpServletRequest request, Long id, Model model) {

        KUser weixinUser = (KUser) request.getSession().getAttribute(Constant.SESSION_WEIXIN_USER);

        Product product = service.getById(id);
        Integer counts = cService.findCountByUserId(weixinUser.getId());
        Integer buyCount = pbservice.findCountByProductId(id);
        model.addAttribute("product", product);
        model.addAttribute("counts", counts);
        model.addAttribute("buyCount", buyCount);
        return null;
    }
}
