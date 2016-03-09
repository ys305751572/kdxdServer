package com.leoman.service.impl;

import com.leoman.dao.PayrecordDao;
import com.leoman.entity.Payrecord;
import com.leoman.service.PayrecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/9.
 */
@Service
public class PayrecordServiceImpl implements PayrecordService{

    @Autowired
    private PayrecordDao dao;

    @Override
    public List<Payrecord> findAll() {
        return null;
    }

    @Override
    public Page<Payrecord> find(int pageNum, int pageSize) {
        return null;
    }

    @Override
    public Page<Payrecord> find(int pageNum) {
        return null;
    }

    @Override
    public Payrecord getById(Long id) {
        return null;
    }

    @Override
    public Payrecord deleteById(Long id) {
        return null;
    }

    @Override
    public Payrecord create(Payrecord payrecord) {
        return null;
    }

    @Override
    public Payrecord update(Payrecord payrecord) {
        return null;
    }

    @Override
    public void deleteAll(Long[] ids) {
    }

    @Override
    public Page<Payrecord> findPage(final Payrecord payrecord,final Integer isEnd, int pagenum, int pagesize) {
        Specification<Payrecord> spec = new Specification<Payrecord>() {
            @Override
            public Predicate toPredicate(Root<Payrecord> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();
                if(payrecord.getUser().getMobile() != null) {
                    list.add(criteriaBuilder.like(root.get("user").get("mobile").as(String.class),"%" + payrecord.getUser().getMobile() + "%" ));
                }
                if(isEnd != null) {
                    if(isEnd ==1) {
                        list.add(criteriaBuilder.gt(root.get("endDate").as(Long.class),System.currentTimeMillis()));
                    }
                    else {
                        list.add(criteriaBuilder.lt(root.get("endDate").as(Long.class),System.currentTimeMillis()));
                    }
                }
                return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
            }
        };
        return dao.findAll(spec,new PageRequest(pagenum - 1,pagesize, Sort.Direction.DESC,"id"));
    }
}
