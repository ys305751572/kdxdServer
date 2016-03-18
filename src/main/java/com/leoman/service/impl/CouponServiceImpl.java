package com.leoman.service.impl;

import com.leoman.dao.CouponDao;
import com.leoman.entity.Coupon;
import com.leoman.service.CouponService;
import com.leoman.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/3/18.
 */
public class CouponServiceImpl implements CouponService{

    public static final int DAY = 3;

    @Autowired
    private CouponDao dao;

    @Override
    public void createCoupon(Long userId) {
        Coupon c = new Coupon();
        c.setUserId(userId);
        c.setIsUsed(0);
        c.setEndDate(DateUtils.daysAfter(new Date(),DAY));
        dao.save(c);
    }

    @Override
    public Page<Coupon> findPageByUserId(final Long userId, int pagenum, int pagesize) {
        Specification<Coupon> spec = new Specification<Coupon>() {
            @Override
            public Predicate toPredicate(Root<Coupon> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();
                list.add(criteriaBuilder.equal(root.get("userId").as(Long.class),userId));
                return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
            }
        };
        return dao.findAll(spec,new PageRequest(pagenum - 1,pagesize, Sort.Direction.DESC,"id"));
    }

    @Override
    public void use(Long id) {
        Coupon c = dao.findOne(id);
        c.setIsUsed(1);
        dao.save(c);
    }

    @Override
    public Integer findCountByUserId(Long userId) {
        return dao.findCountByUserId(userId,System.currentTimeMillis());
    }

    @Override
    public List<Coupon> findAll() {
        return null;
    }

    @Override
    public Page<Coupon> find(int pageNum, int pageSize) {
        return null;
    }

    @Override
    public Page<Coupon> find(int pageNum) {
        return null;
    }

    @Override
    public Coupon getById(Long id) {
        return null;
    }

    @Override
    public Coupon deleteById(Long id) {
        return null;
    }

    @Override
    public Coupon create(Coupon coupon) {
        return null;
    }

    @Override
    public Coupon update(Coupon coupon) {
        return null;
    }

    @Override
    public void deleteAll(Long[] ids) {

    }
}
