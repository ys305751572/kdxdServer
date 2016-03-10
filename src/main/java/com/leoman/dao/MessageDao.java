package com.leoman.dao;

import com.leoman.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Administrator on 2016/3/10.
 */
public interface MessageDao extends JpaRepository<Message,Long>,JpaSpecificationExecutor<Message>{
}
