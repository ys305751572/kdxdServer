package com.leoman.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leoman.entity.Coupon;

/**
 * Created by wangbin on 2015/8/31.
 */
public interface CouponDao extends JpaRepository<Coupon, Integer> {
}
