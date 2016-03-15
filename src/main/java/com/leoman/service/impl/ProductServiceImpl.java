package com.leoman.service.impl;

import com.leoman.core.Constant;
import com.leoman.dao.InfomationDao;
import com.leoman.dao.ProductDao;
import com.leoman.entity.*;
import com.leoman.service.ProductImageService;
import com.leoman.service.ProductService;
import com.leoman.service.PsService;
import com.leoman.utils.ClassUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2016/3/10.
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao dao;

    @Autowired
    private ProductImageService service;

    @Autowired
    private PsService psService;

    @PersistenceContext
    private EntityManager em;

    @Override
    public Page<Product> findPage(final Product pro,final Integer type, int pagenum, int pagesize) {
        Specification<Product> spec = new Specification<Product>() {
            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();
                if(pro.getTitle() != null) {
                    criteriaBuilder.like(root.get("title").as(String.class),pro.getTitle());
                }
                if(type != null) {
                    if(type == 0) {
                        // 待抢购 开始时间 大于 当前时间 && 状态 == 0
                        criteriaBuilder.lt(root.get("startDate").as(Long.class),System.currentTimeMillis());
                        criteriaBuilder.equal(root.get("status").as(Integer.class),0);
                    }
                    else if(type == 1) {
                        // 抢购中 开始时间 小于 当前时间 && 结束时间 大于 当前时间 && 状态 == 0
                        criteriaBuilder.gt(root.get("startDate").as(Long.class),System.currentTimeMillis());
                        criteriaBuilder.lt(root.get("endDate").as(Long.class),System.currentTimeMillis());
                        criteriaBuilder.equal(root.get("status").as(Integer.class),0);
                    }
                    else {
                        // 已结束  结束时间 小于 当前时间  && 状态 == 1
                        criteriaBuilder.gt(root.get("endDate").as(Long.class),System.currentTimeMillis());
                        criteriaBuilder.equal(root.get("status").as(Integer.class),1);
                    }
                }
                return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
            }
        };
        return dao.findAll(spec,new PageRequest(pagenum - 1,pagesize, Sort.Direction.DESC,"id"));
    }

    @Override
    public Long findBuyCount(Long id) {
        String sql = "select count(t) from ProductBuyRecord t where t.productId = " + id;
        Query query = em.createQuery(sql,Long.class);
        return (Long) query.getSingleResult();
    }

    @Override
    public List<Product> findAll() {
        return null;
    }

    @Override
    public Page<Product> find(int pageNum, int pageSize) {
        return null;
    }

    @Override
    public Page<Product> find(int pageNum) {
        return null;
    }

    @Override
    public Product getById(Long id) {
        return dao.findOne(id);
    }

    @Override
    public Product deleteById(Long id) {
        Product info = dao.findOne(id);
        dao.delete(info);
        return null;
    }

    @Override
    public Product create(Product product) {
        return this.update(product);
    }

    /**
     * 新增/编辑
     * @param product
     * @return
     */
    @Transactional
    @Override
    public Product update(Product product) {
//        Set<Image> set = product.getList();
//        ProductImage pi = null;
//        for (Image image : set) {
//            pi = new ProductImage();
//            pi.setImageId(image.getId());
//            pi.setProductId(product.getId());
//            service.create(pi);
//        }
//        Set<com.leoman.entity.ProductService> set = product.getServiceList();
//
//        product.setServiceList(null);
//        product = dao.save(product);
//        for (com.leoman.entity.ProductService ps : set) {
//            ps.setProductId(product.getId());
//            psService.create(ps);
//        }
        return dao.save(product);
    }

    @Override
    public void deleteAll(Long[] ids) {
        for(Long id : ids) {
            deleteById(id);
        }
    }
}