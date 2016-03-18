package com.leoman.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leoman.entity.Coupon;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by wangbin on 2015/8/31.
 */
public interface CouponDao extends JpaRepository<Coupon, Long>,JpaSpecificationExecutor<Coupon> {

    @Query("select count(a) from Coupon a where a.userId = ?1 and a.isUsed = 0 and a.endDate > ?2" )
    public Integer findCountByUserId(Long userId,Long currentDate);
}
