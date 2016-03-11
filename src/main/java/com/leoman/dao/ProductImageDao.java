package com.leoman.dao;

import com.leoman.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Administrator on 2016/3/11.
 */
public interface ProductImageDao extends JpaRepository<ProductImage,Long>,JpaSpecificationExecutor<ProductImage>{
}
