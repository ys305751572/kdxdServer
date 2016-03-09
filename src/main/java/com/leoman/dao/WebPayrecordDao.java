package com.leoman.dao;

import com.leoman.entity.WebPayrecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Administrator on 2016/3/9.
 */
public interface WebPayrecordDao extends JpaRepository<WebPayrecord,Long>,JpaSpecificationExecutor<WebPayrecord>{
}
