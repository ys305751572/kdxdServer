package com.leoman.dao;

import com.leoman.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/3/11.
 */
public interface ProductImageDao extends JpaRepository<ProductImage,Long>,JpaSpecificationExecutor<ProductImage>{

    @Query("select a from ProductImage a where a.productId = ?1")
    public List<ProductImage> findListByProductId(Long productId);

    @Query("delete from ProductImage where productId = ?1 and imageId = ?2")
    @Modifying
    @Transactional
    public void deleteProductImageByProductIdAndImageId(Long productId,Integer imageId);
}
