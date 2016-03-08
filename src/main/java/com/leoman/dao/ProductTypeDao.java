package com.leoman.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leoman.entity.ProductType;

/**
 * Created by wangbin on 2015/8/20.
 */
public interface ProductTypeDao  extends JpaRepository<ProductType, Integer> {
}
