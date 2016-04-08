package com.leoman.dao;

import com.leoman.entity.ProductService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/3/15.
 */
public interface PsDao extends JpaRepository<ProductService, Long>, JpaSpecificationExecutor<ProductService> {

    @Query("select a from ProductService a where a.productId = ?1")
    public List<ProductService> findListbyProductId(Long productId);

    @Transactional
    @Modifying
    @Query("delete from ProductService where productId = ?1")
    public void deleteByProductId(Long productId);
}
