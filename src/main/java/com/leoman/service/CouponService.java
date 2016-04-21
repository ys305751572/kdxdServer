package com.leoman.service;

import com.leoman.entity.Coupon;
import com.leoman.service.common.ICommonService;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by wangbin on 2015/8/31.
 */
public interface CouponService extends ICommonService<Coupon> {

    public void createCoupon(Long userId);

    public Page<Coupon> findPageByUserId(Long userId, int pagenum, int pagesize);

    public void use(Long id);

    public void reUse(Long couponId);

    public List<Coupon> findListByUserId(Long userId);

    // 根据用户id查询优惠券信息
    public Coupon findOneByUserId(Long userId);
}
