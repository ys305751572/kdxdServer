package com.leoman.service;

import com.leoman.entity.Coupon;
import com.leoman.service.common.ICommonService;
import org.springframework.data.domain.Page;

/**
 * Created by wangbin on 2015/8/31.
 */
public interface CouponService extends ICommonService<Coupon> {

    public void createCoupon(Long userId);

    public Page<Coupon> findPageByUserId(Long userId,int pagenum,int pagesize);

    public void use(Long id);

    public Integer findCountByUserId(Long userId);
}
