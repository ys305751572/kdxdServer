package com.leoman.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.leoman.entity.WxUser;

/**
 * Created by wangbin on 2015/7/8.
 */
public interface WxUserDao extends JpaRepository<WxUser, Long> {


    @Query("select a from  WxUser a where a.openId = ?1")
    public WxUser findByOpenId(String openId);

}
