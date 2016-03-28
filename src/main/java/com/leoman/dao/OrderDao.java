package com.leoman.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import com.leoman.entity.Order;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by wangbin on 2015/8/28.
 */
public interface OrderDao extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {

    @Query("select a from Order a where a.sn = ?1")
    public Order findByOrderSn(String sn);

    @Query("select a from Order a where a.user.id = ?1")
    public List<Order> findListByUserId(Long userId);
}
