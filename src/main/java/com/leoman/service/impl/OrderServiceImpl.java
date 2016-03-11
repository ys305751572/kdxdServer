package com.leoman.service.impl;

import com.leoman.dao.KUserDao;
import com.leoman.dao.OrderDao;
import com.leoman.entity.KUser;
import com.leoman.entity.Order;
import com.leoman.service.KUserService;
import com.leoman.service.OrderService;
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
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/3/8.
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao dao;

    @Override
    public Order modifyStatus(Long id, Integer status) {
        Order _order = dao.findOne(id);
        switch (status){
            case 0:
                _order.setStatus(1);
                break;
            case 1:
                _order.setStatus(2);
                break;
        }
        return dao.save(_order);
    }

    @Override
    public Page<Order> findPage(final Order order, int pagenum, int pagesize) {
        Specification<Order> spec = new Specification<Order>() {
            @Override
            public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();
                Predicate result = null;
                if(StringUtils.isNotBlank(order.getSn())) {
                    list.add(criteriaBuilder.like(root.get("sn").as(String.class), '%' + order.getSn() + '%'));
                }
                if(order.getProduct() != null && StringUtils.isNotBlank(order.getProduct().getTitle())) {
                    list.add(criteriaBuilder.equal(root.get("product").get("title").as(String.class), '%' + order.getProduct().getTitle() + '%'));
                }
                if(StringUtils.isNotBlank(order.getUser().getNickname())) {
                    list.add(criteriaBuilder.equal(root.get("user").get("nickname").as(String.class), order.getUser().getNickname()));
                }
                if(order.getCreateDate() != null) {
                    list.add(criteriaBuilder.equal(root.get("createDate").as(Long.class), order.getCreateDate()));
                }
                if(order.getStatus() != null) {
                    list.add(criteriaBuilder.equal(root.get("status").as(Integer.class), order.getStatus()));
                }
                return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
            }
        };
        return dao.findAll(spec,new PageRequest(pagenum - 1,pagesize, Sort.Direction.DESC,"id"));
    }

    @Override
    public List<Order> findAll() {
        return null;
    }

    @Override
    public Page<Order> find(int pageNum, int pageSize) {
        return null;
    }

    @Override
    public Page<Order> find(int pageNum) {
        return null;
    }

    @Override
    public Order getById(Long id) {
        return dao.findOne(id);
    }

    @Override
    public Order deleteById(Long id) {
        Order _user = dao.findOne(id);
        dao.delete(_user);
        return null;
    }

    @Override
    public Order create(Order Order) {
        return null;
    }

    @Override
    public Order update(Order Order) {
        return null;
    }

    @Override
    public void deleteAll(Long[] ids) {

    }
}
