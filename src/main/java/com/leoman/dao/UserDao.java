package com.leoman.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leoman.entity.User;

/**
 * Created by wangbin on 2015/9/1.
 */
public interface UserDao  extends JpaRepository<User, Integer> {
}
