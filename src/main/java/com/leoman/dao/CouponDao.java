package com.leoman.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leoman.entity.Coupon;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by wangbin on 2015/8/31.
 */
public interface CouponDao extends JpaRepository<Coupon, Long>, JpaSpecificationExecutor<Coupon> {

    @Query("select a from Coupon a where a.userId = ?1 and a.isUsed = 0 and a.endDate > ?2 and a.status = 0 order by a.id desc")
    public List<Coupon> findListByUserId(Long userId, Long currentDate);

    @Query("select a from Coupon a where a.userId = ?1 and a.status = 0 and a.isUsed = 0 order by a.id desc")
    public List<Coupon> findListByUserId(Long userId);

    @Query("select a from Coupon a where a.userId = ?1 and a.isUsed = 0 order by a.id desc")
    public List<Coupon> findListByUserId2(Long userId);
}
