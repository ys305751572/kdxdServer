package com.leoman.service.impl;

import com.leoman.dao.WebPayrecordDao;
import com.leoman.entity.Payrecord;
import com.leoman.entity.WebPayrecord;
import com.leoman.service.WebPayrecordService;
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
public class WebPayrecordServiceImpl implements WebPayrecordService{

    @Autowired
    private WebPayrecordDao dao;

    @Override
    public List<WebPayrecord> findAll() {
        return null;
    }

    @Override
    public Page<WebPayrecord> find(int pageNum, int pageSize) {
        return null;
    }

    @Override
    public Page<WebPayrecord> find(int pageNum) {
        return null;
    }

    @Override
    public WebPayrecord getById(Long id) {
        return null;
    }

    @Override
    public WebPayrecord deleteById(Long id) {
        return null;
    }

    @Override
    public WebPayrecord create(WebPayrecord webPayrecord) {
        return null;
    }

    @Override
    public WebPayrecord update(WebPayrecord webPayrecord) {
        return null;
    }

    @Override
    public void deleteAll(Long[] ids) {

    }

    @Override
    public Page<WebPayrecord> findPage(final WebPayrecord payrecord,final Integer type, int pagenum, int pagesize) {
        Specification<WebPayrecord> spec = new Specification<WebPayrecord>() {
            @Override
            public Predicate toPredicate(Root<WebPayrecord> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();
                if(payrecord.getRecordCode() != null) {
                    list.add(criteriaBuilder.like(root.get("recordCode").as(String.class),"%" + payrecord.getRecordCode() + "%"));
                }
                if(type != null) {
                    if(type == 0) {
                        // 收入
                        list.add(criteriaBuilder.gt(root.get("money").as(Double.class),0));
                    }
                    else {
                        // 支出
                        list.add(criteriaBuilder.lt(root.get("money").as(Double.class),0));
                    }
                }
                return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
            }
        };
        return dao.findAll(spec,new PageRequest(pagenum - 1,pagesize, Sort.Direction.DESC,"id"));
    }
}
