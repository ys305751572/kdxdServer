package com.leoman.controller;

import com.google.gson.internal.LinkedTreeMap;
import com.leoman.common.exception.GeneralExceptionHandler;
import com.leoman.common.factory.DataTableFactory;
import com.leoman.controller.common.CommonController;
import com.leoman.core.bean.Result;
import com.leoman.entity.Image;
import com.leoman.entity.Information;
import com.leoman.entity.Product;
import com.leoman.entity.ProductBuyRecord;
import com.leoman.service.InfomationService;
import com.leoman.service.ProductBuyRecordService;
import com.leoman.service.ProductService;
import com.leoman.utils.DateUtils;
import com.leoman.utils.JsonUtil;
import com.leoman.utils.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2016/3/9.
 */
@Controller
@RequestMapping(value = "admin/pro")
public class ProductController extends CommonController {

    @Autowired
    private ProductService service;

    @Autowired
    private ProductBuyRecordService pbService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "pro/list1";
    }

    /**
     *
     * @param response
     * @param draw
     * @param start
     * @param length
     * @param pro
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public void list(HttpServletResponse response,
                     Integer draw,
                     Integer start,
                     Integer length,
                     Product pro,
                     Integer type) {
        try {
            int pageNum = getPageNum(start, length);
            Page<Product> page = service.findPage(pro,type, pageNum, length);
            List<Product> list = page.getContent();
            if(list != null && !list.isEmpty()) {
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

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add() {
        return "pro/add";
    }

    /**
     *
     * @param response
     * @param title
     * @param startDate
     * @param serviceStartDate
     * @param counts
     * @param couponsCounts
     * @param imageIds
     * @param imageId
     * @param productService
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public void save(HttpServletResponse response,
                     String title,String startDate,String serviceStartDate,Integer counts,
                     Integer couponsCounts, String imageIds,Integer imageId,String productService) {
        try {
            Product pro = new Product();
            pro.setTitle(title);
            pro.setStartDate(DateUtils.stringToLong(startDate,"yyyy-MM-dd HH:mm"));
            pro.setServiceStartDate(DateUtils.stringToLong(serviceStartDate,"yyyy-MM-dd HH:mm"));
            pro.setCounts(counts);
            pro.setCouponsCounts(couponsCounts);

            Set<LinkedTreeMap> mapList = JsonUtil.json2Obj(productService,Set.class);
            Set<com.leoman.entity.ProductService> list = new HashSet<com.leoman.entity.ProductService>();

            for (LinkedTreeMap map : mapList) {
                com.leoman.entity.ProductService ps = new com.leoman.entity.ProductService();
                ps.setDays(Integer.parseInt(map.get("days").toString()));
                ps.setMoney(Double.parseDouble(map.get("money").toString()));
                list.add(ps);
            }

            pro.setServiceList(list);

            String[] _imageIds = imageIds.split(",");
            Image image = null;
            Set<Image> imageSet = new HashSet<Image>();
            for (String id : _imageIds) {
                image = new Image();
                image.setId(Integer.parseInt(id));
                imageSet.add(image);
            }
            pro.setList(imageSet);

            Image coverImage = new Image();
            coverImage.setId(imageId);
            pro.setCoverImage(coverImage);
            service.create(pro);
            WebUtil.print(response, new Result(true).msg("操作成功!"));
        } catch (Exception e) {
            e.printStackTrace();
            WebUtil.print(response, new Result(false).msg("操作失败!"));
        }
    }

    /**
     * 删除
     *
     * @param response
     * @param id
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public void delete(HttpServletResponse response,Long id) {
        try {
            service.deleteById(id);
            WebUtil.print(response, new Result(true).msg("操作成功!"));
        } catch (Exception e) {
            e.printStackTrace();
            WebUtil.print(response, new Result(false).msg("操作失败!"));
        }
    }

    /**
     * 详情
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public String detail(Long id,Model model) {
        Product pro = service.getById(id);
        model.addAttribute("pro",pro);
        return "pro/detail";
    }

    @RequestMapping(value = "/tosnapup/end", method = RequestMethod.POST)
    public void endToSnapUp(HttpServletResponse response,Long id) {
        try {
            Product product = service.getById(id);
            product.setStatus(2);
            service.update(product);
            WebUtil.print(response, new Result(true).msg("操作成功!"));
        } catch (Exception e) {
            e.printStackTrace();
            WebUtil.print(response, new Result(false).msg("操作失败!"));
        }
    }

    @RequestMapping(value = "/kuserindex", method = RequestMethod.GET)
    public String kuserIndex() {
        return "pro/people-list";
    }

    /**
     * 抢购人员列表
     * @param response
     * @param draw
     * @param start
     * @param length
     * @param id
     * @param isPay
     * @param isUseCoupons
     */
    @RequestMapping(value = "kuserlist", method = RequestMethod.POST)
    public void kuserList(HttpServletResponse response,Integer draw, Integer start,
                          Integer length, Long id,Integer isPay, Integer isUseCoupons) {
        try {
            int pageNum = getPageNum(start, length);
            ProductBuyRecord pbr = new ProductBuyRecord();
            pbr.setId(id);
            Page<ProductBuyRecord> page = pbService.findPage(pbr,isPay,isUseCoupons,pageNum,length);
            Map<String, Object> result = DataTableFactory.fitting(draw, page);
            WebUtil.print(response, result);
        } catch (Exception e) {
            GeneralExceptionHandler.log(e);
            WebUtil.print(response, emptyData);
        }
    }
}