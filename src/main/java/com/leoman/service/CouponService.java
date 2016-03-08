package com.leoman.service;

import com.leoman.entity.Coupon;
import com.leoman.service.common.ICommonService;

/**
 * Created by wangbin on 2015/8/31.
 */
public interface CouponService extends ICommonService<Coupon> {


    public void batchCreate(Coupon coupon,Integer number);

    public void batchCreate2User(Coupon coupon);

}
