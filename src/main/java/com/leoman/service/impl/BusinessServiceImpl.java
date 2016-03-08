package com.leoman.service.impl;

import com.leoman.core.Constant;
import com.leoman.dao.BusinessDao;
import com.leoman.entity.Business;
import com.leoman.entity.Member;
import com.leoman.entity.WxUser;
import com.leoman.service.BusinessService;
import com.leoman.service.MemberService;
import com.leoman.service.WxUserService;
import com.leoman.utils.ClassUtil;
import com.leoman.utils.SeqNoUtils;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by wangbin on 2015/8/10.
 */
@Service
@Transactional(readOnly = true)
public class BusinessServiceImpl implements BusinessService {

    @Autowired
    private BusinessDao businessDao;
    @Autowired
    private MemberService memberService;
    @Autowired
    private WxUserService wxUserService;


    @Override
    public List<Business> findAll() {
        return businessDao.findAll();
    }

    @Override
    public Page<Business> find(int pageNum, int pageSize) {
        return businessDao.findAll(new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id"));
    }

    @Override
    public Page<Business> find(int pageNum) {
        return find(pageNum, Constant.PAGE_DEF_SZIE);
    }

    @Override
    public Business getById(int id) {
        return businessDao.findOne(id);
    }

    @Override
    @Transactional
    public Business deleteById(int id) {
        Business business = getById(id);
        WxUser wxUser = business.getWxUser();
        if(wxUser!=null){
            wxUserService.deleteById(wxUser.getId());
        }
        businessDao.delete(business);
        return business;
    }

    @Override
    @Transactional
    public Business create(Business business) {
        business.setCreateDate(new Date());
        business.setUpdateDate(new Date());
        businessDao.save(business);
        return business;
    }

    @Override
    @Transactional
    public Business update(Business business) {
        business.setUpdateDate(new Date());
        return businessDao.save(business);
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
    public Business optionsBusiness(String username, String password, Integer businessId) {
        Business business = getById(businessId);

        if(StringUtils.isNotBlank(business.getCode())){
            throw new RuntimeException("已经设置过账户!");
        }

        business.setCode(SeqNoUtils.getBusinessCode(businessId));
        update(business);

        Member member = new Member();
        member.setUsername(username);
        member.setPassword(password);
        member.setType("BUSINESS");
//        member.setBusiness(business);
        memberService.create(member);

        return business;
    }

    @Override
    @Transactional
    public Business update(Integer businessId, Business business) {
        Business origBusiness = getById(businessId);
        ClassUtil.copyProperties(origBusiness,business);
        return update(origBusiness);
    }


}