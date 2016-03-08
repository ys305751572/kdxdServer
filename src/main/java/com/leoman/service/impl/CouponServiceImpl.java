package com.leoman.service.impl;

import com.leoman.core.Constant;
import com.leoman.dao.CouponDao;
import com.leoman.entity.Coupon;
import com.leoman.entity.User;
import com.leoman.service.CouponService;
import com.leoman.service.UserService;
import com.leoman.utils.ClassUtil;
import com.leoman.utils.SeqNoUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by wangbin on 2015/8/31.
 */
@Service
@Transactional(readOnly = true)
public class CouponServiceImpl implements CouponService {

    @Autowired
    private CouponDao couponDao;

    @Autowired
    private UserService userService;


    @Override
    public List<Coupon> findAll() {
        return couponDao.findAll();
    }

    @Override
    public Page<Coupon> find(int pageNum, int pageSize) {
        return couponDao.findAll(new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id"));
    }

    @Override
    public Page<Coupon> find(int pageNum) {
        return find(pageNum, Constant.PAGE_DEF_SZIE);
    }

    @Override
    public Coupon getById(int id) {
        return couponDao.findOne(id);
    }

    @Override
    @Transactional
    public Coupon deleteById(int id) {
        Coupon coupon = getById(id);
        couponDao.delete(coupon);
        return coupon;
    }

    @Override
    @Transactional
    public Coupon create(Coupon coupon) {
        coupon.setStatus(false);
        coupon.setCreateDate(new Date());
        couponDao.save(coupon);
        coupon.setCode(SeqNoUtils.getCouponCode(coupon.getId(), 7));
        return coupon;
    }

    @Override
    @Transactional
    public Coupon update(Coupon coupon) {
        return couponDao.save(coupon);
    }

    @Override
    @Transactional
    public void deleteAll(int[] ids) {
        for (int id : ids) {
            deleteById(id);
        }
    }

    @Override
    @Transactional
    public void batchCreate(Coupon coupon, Integer number) {
        for(int i=0;i<number;i++){
            Coupon newCoupon = new Coupon();
            ClassUtil.copyProperties(newCoupon,coupon);
            create(newCoupon);
        }
    }


    @Override
    @Transactional
    public void batchCreate2User(Coupon coupon) {
        for(int i =1;;i++){
            Page<User> page = userService.find(i,50);
            if(page==null||!page.hasContent()){
                break;
            }
//            page.getContent().forEach(user -> {
//                Coupon newCoupon = new Coupon();
//                ClassUtil.copyProperties(newCoupon,coupon);
//                newCoupon.setUser(user);
//                create(newCoupon);
//            });
        }
    }



}