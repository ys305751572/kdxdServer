package com.leoman.controller.weixin;

import com.leoman.common.exception.GeneralExceptionHandler;
import com.leoman.common.factory.DataTableFactory;
import com.leoman.controller.common.CommonController;
import com.leoman.core.Configue;
import com.leoman.core.Constant;
import com.leoman.core.bean.Result;
import com.leoman.entity.*;
import com.leoman.service.*;
import com.leoman.service.ProductService;
import com.leoman.utils.ConfigUtil;
import com.leoman.utils.DateUtils;
import com.leoman.utils.RandomUtil;
import com.leoman.utils.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.*;

/**
 * Created by Administrator on 2016/3/16.
 */
@Controller
@RequestMapping(value = "weixin/product")
public class WeixinProductController extends CommonController {

    @Autowired
    private ProductService service;

    @Autowired
    private CouponService cService;

    @Autowired
    private ProductBuyRecordService pbservice;

    @Autowired
    private CouponService couponService;

    @Autowired
    private KUserService userService;

    @Autowired
    private PsService psService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private OrderService orderService;

    /**
     * 商品列表 测试
     *
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "weixin/product-list-test";
    }

    /**
     * 商品列表查询
     *
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
     *
     * @param request
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/detail")
    public String detail(HttpServletRequest request, Long id, Model model) {
        try {
            KUser weixinUser = (KUser) request.getSession().getAttribute(Constant.SESSION_WEIXIN_USER);
            Product product = service.getById(id);

            Set<Image> list = product.getList();
            for (Image a : list) {
                a.setPath(ConfigUtil.getString("upload.url") + a.getPath());
            }
            List<Coupon> counts = (weixinUser == null ? Collections.<Coupon>emptyList() : cService.findListByUserId(weixinUser.getId()));
            Integer buyCount = pbservice.findCountByProductId(id);
            model.addAttribute("product", product);
            model.addAttribute("counts", counts.size());
            model.addAttribute("buyCount", buyCount);
            model.addAttribute("kUser", weixinUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "weixin/product-detail";
    }

    /**
     * 抢购
     *
     * @param request
     * @param id
     * @param isUsed
     */
    @RequestMapping(value = "/snapUp")
    public void snapUp(HttpServletRequest request, HttpServletResponse response, Long id, Boolean isUsed) {

        KUser user = (KUser) request.getSession().getAttribute(Constant.SESSION_WEIXIN_USER);
//        if(user == null) {
//            final String toLoginUrl = request.getContextPath() + "/weixin/login/toLogin";
//            System.out.println("toLoginUrl:" + toLoginUrl);
//            try {
//                response.sendRedirect(toLoginUrl);
//                return;
//            } catch (IOException e) {
//                e.printStackTrace();
//                return;
//            }
//        }

        try {
            ProductBuyRecord pbr = service.createProductByRecord(response, id, isUsed, user.getId());
            WebUtil.print(response, new Result(true).data(pbr));
        } catch (Exception e) {
            e.printStackTrace();
            WebUtil.print(response, new Result(false).msg("操作失败!"));
        }
    }

    /**
     * 抢购结果
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("toSnapUpResult")
    public String toSnapUpResult(HttpServletRequest request, Long pbrId, Long addressId, Model model) {
        KUser weixinUser = (KUser) request.getSession().getAttribute(Constant.SESSION_WEIXIN_USER);
        ProductBuyRecord pbr = pbservice.getById(pbrId);
        model.addAttribute("pbr", pbr);

        Address address = null;
        if (null == addressId) {
            address = userService.findDefaultAddressByUserId(weixinUser.getId());
        } else {
            address = addressService.getById(addressId);
        }
        model.addAttribute("address", address);

        try {
            if (pbr.getIsGetCoupons() == 1) {
                // 查询是否有优惠券
                Coupon coupon = couponService.findOneByUserId(pbr.getUser().getId());
                model.addAttribute("coupon", coupon);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (pbr.getResultStatus() == 0) {
            return "weixin/order-detail";
        } else {
            return "weixin/order-fail-detail";
        }
    }

    /**
     * 跳转支付详情页面
     *
     * @param pbrId
     * @param model
     * @return
     */
    @RequestMapping("toPay")
    public String toPay(Long pbrId, Model model) {
        // 查询 ProductService list
        ProductBuyRecord productBuyRecord = pbservice.getById(pbrId);
        List<com.leoman.entity.ProductService> list = psService.findListByProductId(productBuyRecord.getProduct().getId());

        // 默认加载第一条商品服务信息
        if (null != list && list.size() > 0) {
            com.leoman.entity.ProductService productService = list.get(0);
            try {
                String startTime = DateUtils.longToString(service.getById(productService.getProductId()).getServiceStartDate(), "yyyy-MM-dd");
                Date date = DateUtils.longToDate(service.getById(productService.getProductId()).getServiceStartDate(), "yyyy-MM-dd hh:mm:ss");
                String endTime = DateUtils.longToString(DateUtils.daysAfter(date, productService.getDays()), "yyyy-MM-dd");
                productService.setStartYear(startTime.substring(0, 4));
                productService.setStartDate(startTime.substring(6));
                productService.setEndYear(endTime.substring(0, 4));
                productService.setEndDate(endTime.substring(6));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            model.addAttribute("productService", productService);
        } else {
            model.addAttribute("productService", new com.leoman.entity.ProductService());
        }

        model.addAttribute("productServiceList", list);
        model.addAttribute("pbrId", pbrId);

        return "weixin/pay-detail";
    }

    /**
     * 根据商品服务id查询对应的服务信息
     *
     * @param pbrId
     * @param productServiceId
     * @param model
     * @return
     */
    @RequestMapping("getInfo")
    public String getInfo(Long pbrId, Long productServiceId, Model model) {
        try {
            // 查询 ProductService list
            ProductBuyRecord productBuyRecord = pbservice.getById(pbrId);
            List<com.leoman.entity.ProductService> list = psService.findListByProductId(productBuyRecord.getProduct().getId());

            // 默认加载第一条商品服务信息
            com.leoman.entity.ProductService productService = psService.getById(productServiceId);

            String startTime = DateUtils.longToString(service.getById(productService.getProductId()).getServiceStartDate(), "yyyy-MM-dd");
            Date date = DateUtils.longToDate(service.getById(productService.getProductId()).getServiceStartDate(), "yyyy-MM-dd hh:mm:ss");
            String endTime = DateUtils.longToString(DateUtils.daysAfter(date, productService.getDays() - 1), "yyyy-MM-dd");
            productService.setStartYear(startTime.substring(0, 4));
            productService.setStartDate(startTime.substring(6));
            productService.setEndYear(endTime.substring(0, 4));
            productService.setEndDate(endTime.substring(6));

            model.addAttribute("productService", productService);
            model.addAttribute("productServiceList", list);
            model.addAttribute("pbrId", pbrId);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "weixin/pay-detail";
    }

    /**
     * 生成订单
     *
     * @param request
     * @param response
     * @param pbrId
     * @param productServiceId
     */
    @Deprecated
    @RequestMapping(value = "createOrder", method = RequestMethod.POST)
    @ResponseBody
    public Long buy(HttpServletRequest request, HttpServletResponse response, Long pbrId, Long productServiceId) {
        try {
            // 获取抢购信息详情
            ProductBuyRecord productBuyRecord = pbservice.getById(pbrId);

            // 获取收货地址信息
            Address address = addressService.findDefaultByUserId(productBuyRecord.getUser().getId());

            // 获取服务信息
            com.leoman.entity.ProductService productService = psService.getById(productServiceId);

            // 获取当天最新的流水号
            String orderNo = "";
            List<Order> orderList = orderService.findNewOne();
            if (null != orderList && orderList.size() > 0) {
                orderNo = orderList.get(0).getSn();
            }

            // 生成订单信息
            Order order = new Order();
            order.setSn(RandomUtil.getOrderNo(orderNo));
//            order.setProductService(productService);

            order.setDays(productService.getDays());
            order.setServiceMoney(productService.getMoney());
            order.setProductName(productBuyRecord.getProduct().getTitle());
            order.setStartDate(productBuyRecord.getProduct().getStartDate());
            order.setServiceStartDate(productBuyRecord.getProduct().getServiceStartDate());
            order.setUserName(productBuyRecord.getUser().getNickname());

            // order.setProduct(productBuyRecord.getProduct());
            changeImgPath(productBuyRecord.getProduct().getCoverImage());
            order.setProductImg(productBuyRecord.getProduct().getCoverImage().getPath());
            order.setUser(productBuyRecord.getUser());
            order.setMoney(productService.getMoney());
            order.setStatus(0);
            order.setName(address.getName());
            order.setMobile(address.getMobile());
            order.setAddress(address.getAddress());
            order.setCreateDate(System.currentTimeMillis());

            orderService.create(order);

            String startDate = DateUtils.longToString(productBuyRecord.getProduct().getServiceStartDate(), "yyyy-MM-dd HH:mm:ss");
            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(productBuyRecord.getProduct().getServiceStartDate());
            c.add(Calendar.DAY_OF_YEAR, productService.getDays());
            String endDate = DateUtils.longToString(c.getTimeInMillis(), "yyyy-MM-dd HH:mm:ss");

            String isGetCoupon = productBuyRecord.getIsGetCoupons() == 1 ? "优惠券一张" : "";
            String result = "水果一份(" + startDate + "-" + endDate + ")" + isGetCoupon;
            productBuyRecord.setResult(result);
            pbservice.create(productBuyRecord);

            return order.getId();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0L;
    }

    /**
     * 跳转到最新抢购界面
     *
     * @param model
     * @return
     */
    @RequestMapping("toBuy")
    public String toBuy(Model model, Long couponId) {
        Coupon coupon = couponService.getById(couponId);
        KUser kUser = userService.getById(coupon.getUserId());

        List<Product> productList = service.findList(1, 1000000).getContent();
        Product product = productList.get(0);

        Set<Image> list = product.getList();
        for (Image a : list) {
            a.setPath(ConfigUtil.getString("upload.url") + a.getPath());
        }
        List<Coupon> counts = (kUser == null ? Collections.<Coupon>emptyList() : cService.findListByUserId(kUser.getId()));
        Integer buyCount = pbservice.findCountByProductId(product.getId());
        model.addAttribute("product", product);
        model.addAttribute("counts", counts.size());
        model.addAttribute("buyCount", buyCount);
        model.addAttribute("kUser", kUser);
        return "weixin/product-detail";
    }

    public static void changeImgPath(Image image) {
        if (image == null) {
            return;
        }
        String path = image.getPath();
        if (path.contains(Configue.getUploadUrl())) {
            return;
        }
        image.setPath(Configue.getUploadUrl() + image.getPath());
    }
}