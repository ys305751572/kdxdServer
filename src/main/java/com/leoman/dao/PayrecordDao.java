package com.leoman.dao;

import com.leoman.entity.Payrecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Administrator on 2016/3/9.
 */
public interface PayrecordDao extends JpaRepository<Payrecord, Long>, JpaSpecificationExecutor<Payrecord> {

    @Query("select a from Payrecord a where a.sn = ?1")
    public Payrecord findOneBySn(String sn);
}
