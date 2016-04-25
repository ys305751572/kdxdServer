package com.leoman.dao;

import com.leoman.entity.SalemanRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2016/4/21.
 */
public interface SalemanRecordDao extends JpaSpecificationExecutor<SalemanRecord>,JpaRepository<SalemanRecord,Long>{

    @Query("select a from SalemanRecord a where a.kuser.id = ?1")
    public List<SalemanRecord> findByUserId(Long userId);
}
