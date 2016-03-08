package com.leoman.controller;

import com.leoman.common.exception.GeneralExceptionHandler;
import com.leoman.common.factory.DataTableFactory;
import com.leoman.controller.common.CommonController;
import com.leoman.core.Configue;
import com.leoman.core.bean.Result;
import com.leoman.entity.Business;
import com.leoman.entity.Image;
import com.leoman.service.BusinessService;
import com.leoman.utils.JsonUtil;
import com.leoman.utils.Md5Util;
import com.leoman.utils.WebUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by wangbin on 2015/8/11.
 */
@Controller
@RequestMapping(value = "admin/business")
public class BussinessManageController extends CommonController {

    @Autowired
    private BusinessService businessService;


    @RequestMapping(value = "/index")
    public String index(){
        return "入驻商家";
    }


    @RequestMapping(value = "/list")
    public void list(HttpServletRequest request,
                         HttpServletResponse response,
                         Integer draw,
                         Integer start,
                         Integer length){
        try {
            int pageNum = getPageNum(start, length);
            Page<Business> page = businessService.find(pageNum, length);
            Map<String, Object> result = DataTableFactory.fitting(draw, page);
            WebUtil.print(response, result);
        } catch (Exception e) {
            GeneralExceptionHandler.log(e);
            WebUtil.print(response, emptyData);
        }
    }

    @RequestMapping(value = "/delete")
    public void delete(HttpServletRequest request,
                        HttpServletResponse response,
                        String  ids){
        try {
            int[] arrayId = JsonUtil.json2Obj(ids, int[].class);
            businessService.deleteAll(arrayId);
            WebUtil.print(response, new Result(true).msg("删除成功！"));
        } catch (Exception e) {
            GeneralExceptionHandler.log(e);
            WebUtil.print(response, new Result(false).msg("删除失败！"));
        }
    }

    @RequestMapping(value = "/options")
    public void options(HttpServletRequest request,
                        HttpServletResponse response,
                        Integer businessId,
                        String  username,
                        String  password){

        try {
            businessService.optionsBusiness(username, Md5Util.md5(password), businessId);
            WebUtil.print(response, new Result(true).msg("设置成功！"));
        }catch (Exception e){
            GeneralExceptionHandler.log(e);
            WebUtil.print(response, new Result(true).msg("设置失败！"));
        }
    }

    @RequestMapping(value = "/info")
    public String info(HttpServletRequest request,
                       HttpServletResponse response,
                       Integer id,
                       ModelMap model){

        Business business = businessService.getById(id);
        if(business.getImage()!=null){
            Image image = business.getImage();
            image.setPath(Configue.getUploadUrl() + image.getPath());
        }
        model.put("business",business);
        return "商家详情页";
    }



}
