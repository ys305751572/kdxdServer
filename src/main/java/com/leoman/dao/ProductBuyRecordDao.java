package com.leoman.dao;

import com.leoman.entity.ProductBuyRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Administrator on 2016/3/11.
 */
public interface ProductBuyRecordDao extends JpaRepository<ProductBuyRecord,Long>,JpaSpecificationExecutor<ProductBuyRecord>{

    /**
     * 已抢数
     * @param id
     * @return
     */
    @Query("select count(a) from ProductBuyRecord a where a.productId = ?1")
    public Integer findCountByProductId(Long id);
}
