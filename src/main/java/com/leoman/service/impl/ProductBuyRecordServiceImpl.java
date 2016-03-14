package com.leoman.service.impl;

import com.leoman.dao.ProductBuyRecordDao;
import com.leoman.entity.ProductBuyRecord;
import com.leoman.service.ProductBuyRecordService;
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
 * Created by Administrator on 2016/3/11.
 */
@Service
public class ProductBuyRecordServiceImpl implements ProductBuyRecordService{

    @Autowired
    private ProductBuyRecordDao dao;

    @Override
    public Page<ProductBuyRecord> findPage(final ProductBuyRecord record, final Integer isPay, final Integer isUseCoupons, int pagenum, int pagesize) {
        Specification<ProductBuyRecord> spec = new Specification<ProductBuyRecord>() {
            @Override
            public Predicate toPredicate(Root<ProductBuyRecord> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();
                if(record.getUser().getMobile() != null) {
                    criteriaBuilder.like(root.get("user").get("mobile").as(String.class),record.getUser().getMobile());
                }
                if(isPay != null) {
                    if(isPay == 0) {
                        criteriaBuilder.gt(root.get("payMoney").as(Double.class),0);
                    }
                    else {
                        criteriaBuilder.lt(root.get("payMoney").as(Double.class),0);
                    }
                }
                if(record.getIsUserCoupons() != null) {
                    criteriaBuilder.equal(root.get("isUserCoupons").as(Integer.class),record.getIsUserCoupons());
                }
                return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
            }
        };
        return dao.findAll(spec,new PageRequest(pagenum - 1,pagesize, Sort.Direction.DESC,"id"));
    }

    @Override
    public List<ProductBuyRecord> findAll() {
        return null;
    }

    @Override
    public Page<ProductBuyRecord> find(int pageNum, int pageSize) {
        return null;
    }

    @Override
    public Page<ProductBuyRecord> find(int pageNum) {
        return null;
    }

    @Override
    public ProductBuyRecord getById(Long id) {
        return null;
    }

    @Override
    public ProductBuyRecord deleteById(Long id) {
        return null;
    }

    @Override
    public ProductBuyRecord create(ProductBuyRecord productBuyRecord) {
        return null;
    }

    @Override
    public ProductBuyRecord update(ProductBuyRecord productBuyRecord) {
        return null;
    }

    @Override
    public void deleteAll(Long[] ids) {

    }
}
