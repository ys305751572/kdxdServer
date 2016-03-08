package com.leoman.controller;

import com.leoman.common.exception.GeneralExceptionHandler;
import com.leoman.common.factory.DataTableFactory;
import com.leoman.controller.common.CommonController;
import com.leoman.core.bean.Result;
import com.leoman.entity.ProductType;
import com.leoman.service.ProductTypeService;
import com.leoman.utils.JsonUtil;
import com.leoman.utils.WebUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by wangbin on 2015/8/20.
 */
@RequestMapping(value = "admin/product/type")
@Controller
public class ProductTypeController extends CommonController{



    @Autowired
    private ProductTypeService productTypeService;


    @RequestMapping(value = "/index")
    public String index(HttpServletRequest request,
                        HttpServletResponse response){
        return "商品类型";
    }


    @RequestMapping(value = "/save")
    public void picSave(HttpServletRequest request,
                        HttpServletResponse response,
                        ProductType productType){
        try {
            if (productType.getId() == null) {
                productTypeService.create(productType);
            } else {
                productTypeService.update(productType);
            }
            WebUtil.print(response, new Result(true).msg("商品类型操作成功!"));
        } catch (Exception e) {
            GeneralExceptionHandler.log("商品类型操作失败", e);
            WebUtil.print(response, new Result(false).msg("商品类型操作失败!"));
        }
    }

    @RequestMapping(value = "/list")
    public void picList(HttpServletRequest request,
                        HttpServletResponse response,
                        String type,
                        Integer draw,
                        Integer start,
                        Integer length){
        try {
            int pageNum = getPageNum(start, length);
            Page<ProductType> page = productTypeService.find(pageNum, length);
            Map<String, Object> result = DataTableFactory.fitting(draw, page);
            WebUtil.print(response, result);
        } catch (Exception e) {
            WebUtil.print(response, emptyData);
            GeneralExceptionHandler.log(e);
        }
    }


    @RequestMapping(value = "/delete")
    public void picDel(HttpServletRequest request,
                       HttpServletResponse response,
                       String  ids){
        try {
            int[] arrayId = JsonUtil.json2Obj(ids, int[].class);
            productTypeService.deleteAll(arrayId);
            WebUtil.print(response, new Result(true).msg("删除商品类型成功！"));
        } catch (Exception e) {
            WebUtil.print(response, new Result(false).msg("删除商品类型失败！"));
        }
    }



}
