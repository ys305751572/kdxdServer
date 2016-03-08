package com.leoman.controller;

import com.leoman.common.exception.GeneralExceptionHandler;
import com.leoman.common.factory.DataTableFactory;
import com.leoman.controller.common.CommonController;
import com.leoman.core.Configue;
import com.leoman.core.bean.Result;
import com.leoman.entity.Image;
import com.leoman.entity.Product;
import com.leoman.entity.ProductArea;
import com.leoman.entity.ProductType;
import com.leoman.service.ProductAreaService;
import com.leoman.service.ProductImagesService;
import com.leoman.service.ProductService;
import com.leoman.service.ProductTypeService;
import com.leoman.utils.JsonUtil;
import com.leoman.utils.WebUtil;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangbin on 2015/8/17.
 */
@RequestMapping(value = "admin/product")
@Controller
public class ProductController extends CommonController{


    @Autowired
    private ProductService productService;
    @Autowired
    private ProductImagesService productImagesService;
    @Autowired
    private ProductAreaService productAreaService;
    @Autowired
    private ProductTypeService productTypeService;

    @RequestMapping(value = "/index")
    public String index(HttpServletRequest request,
                        HttpServletResponse response,
                        ModelMap model){
        return "商品列表";
    }


    @RequestMapping(value = "/list")
    public void list(HttpServletRequest request,
                     HttpServletResponse response,
                     Integer draw,
                     Integer start,
                     Integer length,
                     ModelMap model) {
        try {
            int pageNum = getPageNum(start, length);
            Page<Product> page = productService.find(pageNum,length);
            Map<String, Object> result = DataTableFactory.fitting(draw, page);
            WebUtil.print(response, result);
        } catch (Exception e) {
            GeneralExceptionHandler.log(e);
            WebUtil.print(response, emptyData);
        }
    }

    @RequestMapping(value = "/save")
    public void save(HttpServletRequest request,
                     HttpServletResponse response,
                     String imageIds,
                     Product product){

        try {
            if(product.getId()==null){
                if(StringUtils.isBlank(imageIds)){
                    WebUtil.print(response, new Result(false).msg("至少需要上传一张图片!"));
                    return;
                }
                productService.create(product,imageIds);
            }else{
                List<Image> images = productImagesService.findImageListByProduct(product.getId());
                if(images==null||images.size()==0){
                    if(StringUtils.isBlank(imageIds)){
                        WebUtil.print(response, new Result(false).msg("至少需要上传一张图片!"));
                        return;
                    }
                }
                productService.update(product,imageIds);
            }
            WebUtil.print(response, new Result(true).msg("商品操作成功!"));
        }
        catch (DataIntegrityViolationException ex){
            GeneralExceptionHandler.log("商品编号已存在", ex);
            WebUtil.print(response, new Result(false).msg("商品编号已存在!"));
        }
        catch (Exception e){
            GeneralExceptionHandler.log("商品添加失败", e);
            WebUtil.print(response, new Result(false).msg("商品操作失败!"));
        }
    }



    @RequestMapping(value = "/delete")
    public void delete(HttpServletRequest request,
                       HttpServletResponse response,
                       String  ids){
        try {
            int[] arrayId = JsonUtil.json2Obj(ids, int[].class);
            productService.deleteAll(arrayId);
            WebUtil.print(response, new Result(true).msg("删除成功！"));
        } catch (Exception e) {
            GeneralExceptionHandler.log(e);
            WebUtil.print(response, new Result(false).msg("删除失败！"));
        }
    }


    @RequestMapping(value = "/add")
    public String add(HttpServletRequest request,
                      HttpServletResponse response,
                      Integer id,
                      ModelMap model){

        List<ProductArea> productAreaList =  productAreaService.findAll();
        List<ProductType> productTypeList = productTypeService.findAll();
        model.put("productAreaList",productAreaList);
        model.put("productTypeList",productTypeList);
        if(id!=null){
            Product product =  productService.getById(id);
            product.setContent(product.getContent().replace("&lt","<").replace("&gt",">"));
            List<Image> imageList =  productImagesService.findImageListByProduct(id);
//            imageList.forEach(image -> {
//                image.setPath(Configue.getUploadUrl() + image.getPath());
//            });
            product.setImages(imageList);
            model.put("product", product);
        }
        return "添加商品";
    }


    /**
     * 上下架商品
     * @param request
     * @param response
     * @param id
     * @param isAdded
     */
    @RequestMapping("setting/isAdded")
    public void settingAdded(HttpServletRequest request,
                             HttpServletResponse response,
                             Integer id,
                             Boolean isAdded){
        try {
            productService.settingAdded(id);
            String msg = "上架商品成功!";
            if (isAdded) {
                msg = "下架商品成功!";
            }
            WebUtil.print(response, new Result(true).msg(msg));
        }catch (Exception e){
            GeneralExceptionHandler.log("操作商品失败!", e);
            WebUtil.print(response, new Result(false).msg("操作商品失败!"));
        }
    }


    /**
     * 删除图片
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "delPic")
    public void delPic(HttpServletRequest request,
                       HttpServletResponse response,
                       Integer productId,
                       Integer imageId){
        productImagesService.delete(productId, imageId);
        WebUtil.print(response, new HashMap<>());
    }



}
