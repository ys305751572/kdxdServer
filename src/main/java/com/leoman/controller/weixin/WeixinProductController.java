package com.leoman.controller.weixin;

import com.leoman.common.exception.GeneralExceptionHandler;
import com.leoman.common.factory.DataTableFactory;
import com.leoman.controller.common.CommonController;
import com.leoman.core.Constant;
import com.leoman.core.bean.Result;
import com.leoman.entity.*;
import com.leoman.service.*;
import com.leoman.service.ProductService;
import com.leoman.utils.DateUtils;
import com.leoman.utils.KdxgUtils;
import com.leoman.utils.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/3/16.
 */
@Controller
@RequestMapping(value = "weixin/product")
public class WeixinProductController extends CommonController{

    @Autowired
    private ProductService service;

    @Autowired
    private CouponService cService;

    @Autowired
    private ProductBuyRecordService pbservice;

    @Autowired
    private PsService psService;

    @Autowired
    private KUserService userService;

    @RequestMapping(value = "/index" , method = RequestMethod.GET)
    public String index() {
        return "weixin/product-list-test";
    }

    /**
     * @param response
     * @param draw
     * @param start
     * @param length
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public void list(HttpServletResponse response,
                     Integer draw,
                     Integer start,
                     Integer length,
                     Integer type) {
        try {
            int pageNum = getPageNum(start, length);
            Product p = new Product();
            Page<Product> page = service.findPage(p, type, pageNum, length);
            List<Product> list = page.getContent();
            if (list != null && !list.isEmpty()) {
                for (Product product : list) {
                    Long counts = service.findBuyCount(product.getId());
                    product.setBuyCount(counts);
                }
            }
            Map<String, Object> result = DataTableFactory.fitting(draw, page);
            WebUtil.print(response, result);
        } catch (Exception e) {
            GeneralExceptionHandler.log(e);
            WebUtil.print(response, emptyData);
        }
    }

    /**
     * 商品详情
     * @param request
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String detail(HttpServletRequest request, Long id, Model model) {

        KUser weixinUser = (KUser) request.getSession().getAttribute(Constant.SESSION_WEIXIN_USER);
        Product product = service.getById(id);
        Integer counts = cService.findCountByUserId(weixinUser.getId());
        Integer buyCount = pbservice.findCountByProductId(id);
        model.addAttribute("product", product);
        model.addAttribute("counts", counts);
        model.addAttribute("buyCount", buyCount);
        return "weixin/product-detail";
    }

    /**
     * 抢购
     * @param request
     * @param id
     * @param isUsed
     */
    @RequestMapping(value = "/snapUp", method = RequestMethod.POST)
    public void snapUp(HttpServletRequest request,HttpServletResponse response,Long id,Boolean isUsed) {

        KUser user = (KUser) request.getSession().getAttribute(Constant.SESSION_WEIXIN_USER);
        try {
            ProductBuyRecord pbr = service.createProductByRecord(response,id,isUsed,user.getId());
            WebUtil.print(response,new Result(true).data(pbr));
        } catch (Exception e) {
            e.printStackTrace();
            WebUtil.print(response, new Result(false).msg("操作失败!"));
        }
    }

    /**
     * 抢购
     * @param request
     * @param response
     * @param id
     * @param serviceId
     * @param isUsed
     */
    @Deprecated
    @RequestMapping(value = "creaeOrder", method = RequestMethod.POST)
    public void buy(HttpServletRequest request, HttpServletResponse response, Long id,Long serviceId,Boolean isUsed) {
//        KUser user = (KUser) request.getSession().getAttribute(Constant.SESSION_WEIXIN_USER);
//        service.buy(id,serviceId,user.getId(),isUsed,request,response);


    }



    /**
     * 跳转订单详情
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "toSnapUpResult", method = RequestMethod.POST)
    public String toSnapUpResult(HttpServletRequest request,Long pbrId,Model model) {

        KUser weixinUser = (KUser) request.getSession().getAttribute(Constant.SESSION_WEIXIN_USER);
        ProductBuyRecord pbr = pbservice.getById(pbrId);
        model.addAttribute("pbr",pbr);

        Address address = userService.findDefaultAddressByUserId(weixinUser.getId());
        model.addAttribute("address",address);

        return "weixin/order-detail";
    }

    /**
     * 跳转支付详情页面
     * @param id
     * @param isUsed
     * @param model
     * @return
     */
    @RequestMapping(value = "toPay", method = RequestMethod.POST)
    public String toPay(Long id,Boolean isUsed,Model model) {
        Product product = service.reduceInventory(id);
        model.addAttribute("product",product);
        model.addAttribute("isUser",isUsed);
        model.addAttribute("id",id);
        return "weixin/pay-detail";
    }
}