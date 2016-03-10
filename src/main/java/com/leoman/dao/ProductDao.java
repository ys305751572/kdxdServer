package com.leoman.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.leoman.entity.Image;
import com.leoman.entity.Product;

import java.util.List;

/**
 * Created by wangbin on 2015/8/17.
 */
public interface ProductDao extends JpaRepository<Product, Long>,JpaSpecificationExecutor<Product> {


    Product findOne(Long id);
}
