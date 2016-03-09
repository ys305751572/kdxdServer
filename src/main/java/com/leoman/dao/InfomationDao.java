package com.leoman.dao;

import com.leoman.entity.Information;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Administrator on 2016/3/9.
 */
public interface InfomationDao extends JpaRepository<Information,Long>,JpaSpecificationExecutor<Information>{
}
