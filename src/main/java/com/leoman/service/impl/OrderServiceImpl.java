package com.leoman.service.impl;

import com.leoman.core.Constant;
import com.leoman.dao.OrderDao;
import com.leoman.entity.Order;
import com.leoman.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by wangbin on 2015/8/28.
 */
@Service
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;


    @Override
    public List<Order> findAll() {
        return orderDao.findAll();
    }

    @Override
    public Page<Order> find(int pageNum, int pageSize) {

        return orderDao.findAll(new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id"));

    }

    @Override
    public Page<Order> find(int pageNum) {
        return find(pageNum, Constant.PAGE_DEF_SZIE);
    }

    @Override
    public Order getById(int id) {
        return orderDao.findOne(id);
    }

    @Override
    @Transactional
    public Order deleteById(int id) {
        Order order = getById(id);
        orderDao.delete(order);
        return order;
    }

    @Override
    @Transactional
    public Order create(Order order) {
        orderDao.save(order);
        return order;
    }

    @Override
    @Transactional
    public Order update(Order order) {
        return orderDao.save(order);
    }

    @Override
    @Transactional
    public void deleteAll(int[] ids) {
        for (int id : ids) {
            deleteById(id);
        }
    }

}