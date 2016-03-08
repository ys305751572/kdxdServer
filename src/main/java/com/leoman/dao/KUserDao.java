package com.leoman.dao;

import com.leoman.entity.KUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Administrator on 2016/3/8.
 */
public interface KUserDao extends JpaRepository<KUser,Long>,JpaSpecificationExecutor<KUser>{

}
