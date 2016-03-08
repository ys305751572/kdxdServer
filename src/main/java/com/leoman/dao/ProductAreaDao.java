package com.leoman.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leoman.entity.ProductArea;

/**
 * Created by wangbin on 2015/8/20.
 */
public interface ProductAreaDao extends JpaRepository<ProductArea, Integer> {
}
