package com.leoman.controller.weixin;

import com.leoman.common.exception.GeneralExceptionHandler;
import com.leoman.common.factory.DataTableFactory;
import com.leoman.controller.common.CommonController;
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
     * 抢购
     * @param request
     * @param id
     * @param isUsed
     * @param model
     */
    @RequestMapping(value = "/snapUp", method = RequestMethod.POST)
    public void snapUp(HttpServletRequest request,Long id,Boolean isUsed,Model model) {

    }

    /**
     * 抢购
     * @param request
     * @param response
     * @param id
     * @param serviceId
     * @param isUsed
     */
    @RequestMapping(value = "buyWithCoupons", method = RequestMethod.POST)
    public void buyWithCoupons(HttpServletRequest request, HttpServletResponse response, Long id,Long serviceId,Boolean isUsed) {
        KUser user = (KUser) request.getSession().getAttribute(Constant.SESSION_WEIXIN_USER);
        Integer buyCount = pbservice.findCountByProductId(id);
        Product product = service.getById(id);
        if(buyCount >= product.getCounts()) {
            WebUtil.print(response, new Result(false).msg("商品已抢完!"));
            return;
        }
        service.buy(id,serviceId,user.getId(),isUsed,request,response);
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
     * 跳转订单详情
     * @param request
     * @param id
     * @param isUsed
     * @param model
     * @return
     */
    @RequestMapping(value = "toOrder", method = RequestMethod.POST)
    public String toOrder(HttpServletRequest request,Long id,Boolean isUsed,Model model) {

        KUser weixinUser = (KUser) request.getSession().getAttribute(Constant.SESSION_WEIXIN_USER);
        model = service.toOrder(id,isUsed,weixinUser.getId(),model);
        return "order-detail";
    }

    /**
     * 跳转支付强请页面
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