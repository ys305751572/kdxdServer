package com.leoman.dao;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import com.leoman.entity.Order;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by wangbin on 2015/8/28.
 */
public interface OrderDao extends JpaRepository<Order, Long>,JpaSpecificationExecutor<Order> {

}
