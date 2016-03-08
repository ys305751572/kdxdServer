package com.leoman.controller;

import com.leoman.common.exception.GeneralExceptionHandler;
import com.leoman.common.factory.DataTableFactory;
import com.leoman.controller.common.CommonController;
import com.leoman.core.bean.Result;
import com.leoman.entity.Coupon;
import com.leoman.service.CouponService;
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
 * Created by wangbin on 2015/8/31.
 */
@RequestMapping(value = "admin/coupon")
@Controller
public class CouponController extends CommonController {

    @Autowired
    private CouponService couponService;


    @RequestMapping(value = "/index")
    public String index(){
        return "优惠券管理";
    }



    @RequestMapping(value = "/save")
    public void picSave(HttpServletRequest request,
                        HttpServletResponse response,
                        Integer number,
                        Boolean toUser,
                        Coupon coupon){
        try {
            if(toUser){
                couponService.batchCreate2User(coupon);
            }else{
                couponService.batchCreate(coupon,number);
            }
            WebUtil.print(response, new Result(true).msg("批量生产优惠券成功!"));
        } catch (Exception e) {
            GeneralExceptionHandler.log("批量生产优惠券失败", e);
            WebUtil.print(response, new Result(false).msg("批量生产优惠券失败!"));
        }
    }

    @RequestMapping(value = "/list")
    public void picList(HttpServletRequest request,
                        HttpServletResponse response,
                        Integer draw,
                        Integer start,
                        Integer length){
        try {
            int pageNum = getPageNum(start, length);
            Page<Coupon> page = couponService.find(pageNum, length);
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
            couponService.deleteAll(arrayId);
            WebUtil.print(response, new Result(true).msg("删除商品产区成功！"));
        } catch (Exception e) {
            WebUtil.print(response, new Result(false).msg("删除商品产区失败！"));
        }
    }




}
