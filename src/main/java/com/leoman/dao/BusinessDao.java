package com.leoman.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leoman.entity.Business;

/**
 * Created by wangbin on 2015/8/10.
 */
public interface BusinessDao extends JpaRepository<Business, Long> {
}
