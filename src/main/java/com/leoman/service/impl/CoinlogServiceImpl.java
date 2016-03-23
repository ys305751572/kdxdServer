package com.leoman.service.impl;

import com.leoman.dao.CoinlogDao;
import com.leoman.entity.Coinlog;
import com.leoman.service.CoinlogService;
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
 * Created by Administrator on 2016/3/18.
 */
@Service
public class CoinlogServiceImpl implements CoinlogService{

    @Autowired
    private CoinlogDao dao;

    @Override
    public Page<Coinlog> findPageByUserId(final Long id,int pagenum,int pagesize) {
        Specification spec = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();
                list.add(criteriaBuilder.equal(root.get("userId").as(Long.class),id));

                return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
            }
        };
        return dao.findAll(spec,new PageRequest(pagenum - 1,pagesize, Sort.Direction.DESC,"id"));
    }

    @Override
    public List<Coinlog> findAll() {
        return null;
    }

    @Override
    public Page<Coinlog> find(int pageNum, int pageSize) {
        return null;
    }

    @Override
    public Page<Coinlog> find(int pageNum) {
        return null;
    }

    @Override
    public Coinlog getById(Long id) {
        return null;
    }

    @Override
    public Coinlog deleteById(Long id) {
        return null;
    }

    @Override
    public Coinlog create(Coinlog coinlog) {
        return null;
    }

    @Override
    public Coinlog update(Coinlog coinlog) {
        return null;
    }

    @Override
    public void deleteAll(Long[] ids) {

    }
}
