package com.leoman.service.impl;

import com.leoman.dao.KUserDao;
import com.leoman.dao.OrderDao;
import com.leoman.entity.KUser;
import com.leoman.entity.Order;
import com.leoman.service.KUserService;
import com.leoman.service.OrderService;
import com.leoman.utils.DateUtils;
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
import java.text.ParseException;
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
        _order.setStatus(_order.getStatus() + 1);
        return dao.save(_order);
    }

    @Override
    public Page<Order> findPage(final Order order, int pagenum, int pagesize) {
        Specification<Order> spec = new Specification<Order>() {
            @Override
            public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();
                Predicate result = null;
                if (StringUtils.isNotBlank(order.getSn())) {
                    list.add(criteriaBuilder.like(root.get("sn").as(String.class), '%' + order.getSn() + '%'));
                }
                if (StringUtils.isNotBlank(order.getProductName())) {
                    list.add(criteriaBuilder.like(root.get("product").get("title").as(String.class), '%' + order.getProductName() + '%'));
                }
                if (StringUtils.isNotBlank(order.getUserName())) {
                    list.add(criteriaBuilder.like(root.get("user").get("nickname").as(String.class), order.getUserName()));
                }
                if (order.getCreateDate() != null) {
                    list.add(criteriaBuilder.equal(root.get("createDate").as(Long.class), order.getCreateDate()));
                }
                if (order.getStatus() != null) {
                    list.add(criteriaBuilder.equal(root.get("status").as(Integer.class), order.getStatus()));
                }
//                if (order.getUser() != null && order.getUser().getId() != null) {
//                    list.add(criteriaBuilder.equal(root.get("user").get("id").as(Long.class), order.getUser().getId()));
//                }
                return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
            }
        };
        return dao.findAll(spec, new PageRequest(pagenum - 1, pagesize, Sort.Direction.DESC, "id"));
    }

    @Override
    public Order findByOrderSn(String sn) {
        return dao.findByOrderSn(sn);
    }

    @Override
    public List<Order> findListByUserId(Long userId) {
        return dao.findListByUserId(userId);
    }

    @Override
    public Page<Order> pageByUserId(Long userId, Integer pageNum, Integer pageSize) {
        return dao.pageByUserId(userId, new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id"));
    }

    @Override
    public List<Order> findNewOne() {
        List<Order> list = new ArrayList<Order>();
        try {
            String today = DateUtils.longToString(System.currentTimeMillis(), "yyyy-MM-dd");
            Long startTime = DateUtils.stringToLong(today + " 00:00:00", "yyyy-MM-dd hh:mm:ss");
            Long endTime = DateUtils.stringToLong(today + " 23:59:59", "yyyy-MM-dd hh:mm:ss");

            list = dao.findNewOne(startTime, endTime);
            return list;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return list;
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
    public Order create(Order order) {
        return dao.save(order);
    }

    @Override
    public Order update(Order order) {
        return dao.save(order);
    }

    @Override
    public void deleteAll(Long[] ids) {

    }
}
