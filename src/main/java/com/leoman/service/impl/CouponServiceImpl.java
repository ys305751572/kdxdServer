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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/3/18.
 */
@Service
public class CouponServiceImpl implements CouponService {

    public static final int DAY = 3;

    @Autowired
    private CouponDao dao;

    @Override
    public void createCoupon(Long userId) {
        Coupon c = new Coupon();
        c.setUserId(userId);
        c.setIsUsed(0); // 默认为未使用
        c.setStatus(0); // 默认为有效
        c.setEndDate(DateUtils.daysAfter(new Date(), DAY));
        dao.save(c);
    }

    @Override
    public Page<Coupon> findPageByUserId(final Long userId, int pagenum, int pagesize) {
        Specification<Coupon> spec = new Specification<Coupon>() {
            @Override
            public Predicate toPredicate(Root<Coupon> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();
                list.add(criteriaBuilder.equal(root.get("userId").as(Long.class), userId));
                return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
            }
        };
        return dao.findAll(spec, new PageRequest(pagenum - 1, pagesize, Sort.Direction.DESC, "id"));
    }

    @Override
    public void use(Long id) {
        Coupon c = dao.findOne(id);
        c.setIsUsed(1);
        dao.save(c);
    }

    @Override
    public void reUse(Long couponId) {
        Coupon c = dao.findOne(couponId);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        c.setEndDate(calendar.getTimeInMillis());
        dao.save(c);
    }

    @Override
    public List<Coupon> findListByUserId(Long userId) {
        return dao.findListByUserId(userId, System.currentTimeMillis());
    }

    @Override
    public Coupon findOneByUserId(Long userId) {
        List<Coupon> list = dao.findListByUserId(userId);

        if (null == list || list.size() == 0) {
            return null;
        } else {
            return list.get(0);
        }
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
        return dao.findOne(id);
    }

    @Override
    public Coupon deleteById(Long id) {
        dao.delete(id);
        return null;
    }

    @Override
    public Coupon create(Coupon coupon) {
        return dao.save(coupon);
    }

    @Override
    public Coupon update(Coupon coupon) {
        return dao.save(coupon);
    }

    @Override
    @Transactional
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            deleteById(id);
        }
    }
}
