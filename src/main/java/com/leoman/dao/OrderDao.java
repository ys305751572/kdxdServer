package com.leoman.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leoman.entity.Order;

/**
 * Created by wangbin on 2015/8/28.
 */
public interface OrderDao extends JpaRepository<Order, Integer> {
}
