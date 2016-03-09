package com.leoman.dao;

import com.leoman.entity.Payrecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Administrator on 2016/3/9.
 */
public interface PayrecordDao extends JpaRepository<Payrecord,Long>,JpaSpecificationExecutor<Payrecord>{
}
