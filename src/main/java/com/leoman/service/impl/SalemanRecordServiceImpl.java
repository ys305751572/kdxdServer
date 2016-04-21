package com.leoman.service.impl;

import com.leoman.dao.SalemanRecordDao;
import com.leoman.entity.SalemanRecord;
import com.leoman.service.SalamanRecordService;
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
public class SalemanRecordServiceImpl implements SalamanRecordService {

    @Autowired
    private SalemanRecordDao dao;

    @Override
    public Page<SalemanRecord> findPage(final String salemanName, int pagenum, int pagesize) {
        Specification<SalemanRecord> spec = new Specification<SalemanRecord>() {
            @Override
            public Predicate toPredicate(Root<SalemanRecord> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<Predicate>();
                if(StringUtils.isNotBlank(salemanName)) {
                    list.add(cb.like(root.get("saleman").get("name").as(String.class),"%" + salemanName + "%"));
                }
                return cb.and(list.toArray(new Predicate[list.size()]));
            }
        };
        return dao.findAll(spec,new PageRequest(pagenum - 1,pagesize, Sort.Direction.DESC,"id"));
    }

    @Override
    public List<SalemanRecord> findAll() {
        return null;
    }

    @Override
    public Page<SalemanRecord> find(int pageNum, int pageSize) {
        return null;
    }

    @Override
    public Page<SalemanRecord> find(int pageNum) {
        return null;
    }

    @Override
    public SalemanRecord getById(Long id) {
        return dao.findOne(id);
    }

    @Override
    public SalemanRecord deleteById(Long id) {

        dao.delete(getById(id));
        return null;
    }

    @Override
    public SalemanRecord create(SalemanRecord salemanRecord) {
        return dao.save(salemanRecord);
    }

    @Override
    public SalemanRecord update(SalemanRecord salemanRecord) {
        return dao.save(salemanRecord);
    }

    @Override
    public void deleteAll(Long[] ids) {

    }
}
