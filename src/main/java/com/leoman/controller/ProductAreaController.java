package com.leoman.controller;

import com.leoman.common.exception.GeneralExceptionHandler;
import com.leoman.common.factory.DataTableFactory;
import com.leoman.controller.common.CommonController;
import com.leoman.core.bean.Result;
import com.leoman.entity.ProductArea;
import com.leoman.service.ProductAreaService;
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
@RequestMapping(value = "admin/product/area")
@Controller
public class ProductAreaController extends CommonController {

    @Autowired
    private ProductAreaService productAreaService;


    @RequestMapping(value = "/index")
    public String index(HttpServletRequest request,
                        HttpServletResponse response){
        return "商品产区";
    }


    @RequestMapping(value = "/save")
    public void picSave(HttpServletRequest request,
                        HttpServletResponse response,
                        ProductArea productArea){
        try {
            if (productArea.getId() == null) {
                productAreaService.create(productArea);
            } else {
                productAreaService.update(productArea);
            }
            WebUtil.print(response, new Result(true).msg("商品产区操作成功!"));
        } catch (Exception e) {
            GeneralExceptionHandler.log("商品产区操作失败", e);
            WebUtil.print(response, new Result(false).msg("商品产区操作失败!"));
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
            Page<ProductArea> page = productAreaService.find(pageNum, length);
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
            productAreaService.deleteAll(arrayId);
            WebUtil.print(response, new Result(true).msg("删除商品产区成功！"));
        } catch (Exception e) {
            WebUtil.print(response, new Result(false).msg("删除商品产区失败！"));
        }
    }


}
