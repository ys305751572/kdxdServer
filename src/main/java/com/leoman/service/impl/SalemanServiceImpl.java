package com.leoman.service.impl;

import com.leoman.dao.SalemanDao;
import com.leoman.entity.Saleman;
import com.leoman.service.SalemanService;
import org.apache.commons.lang.StringUtils;
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
 * Created by Administrator on 2016/4/21.
 */
@Service
public class SalemanServiceImpl implements SalemanService{

    @Autowired
    private SalemanDao dao;

    @Override
    public List<Saleman> findAll() {
        return null;
    }

    @Override
    public Page<Saleman> find(int pageNum, int pageSize) {
        return null;
    }

    @Override
    public Page<Saleman> find(int pageNum) {
        return null;
    }

    @Override
    public Saleman getById(Long id) {
        return dao.findOne(id);
    }

    @Override
    public Saleman deleteById(Long id) {
        Saleman s = getById(id);
        dao.delete(s);
        return null;
    }

    @Override
    public Saleman create(Saleman saleman) {
        return dao.save(saleman);
    }

    @Override
    public Saleman update(Saleman saleman) {
        return dao.save(saleman);
    }

    @Override
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            deleteById(id);
        }
    }

    @Override
    public Page<Saleman> findPage(final String mobile,final String name, int pagenum, int pagesize) {
        Specification<Saleman> spec = new Specification<Saleman>() {
            @Override
            public Predicate toPredicate(Root<Saleman> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<Predicate>();
                if(StringUtils.isNotBlank(mobile)) {
                    list.add(cb.like(root.get("mobile").as(String.class), "%" + mobile + "%"));
                }
                if(StringUtils.isNotBlank(name)) {
                    list.add(cb.like(root.get("name").as(String.class),"%" + name + "%"));
                }
                return cb.and(list.toArray(new Predicate[list.size()]));
            }
        };
        return dao.findAll(spec,new PageRequest(pagenum - 1,pagesize, Sort.Direction.DESC,"id"));
    }
}
