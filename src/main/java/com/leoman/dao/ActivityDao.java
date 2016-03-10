package com.leoman.dao;

import com.leoman.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Administrator on 2016/3/10.
 */
public interface ActivityDao extends JpaRepository<Activity,Long>,JpaSpecificationExecutor<Activity>{


}
