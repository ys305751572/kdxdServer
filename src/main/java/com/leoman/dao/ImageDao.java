package com.leoman.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leoman.entity.Image;

/**
 * Created by 涂奕恒 on 2014/12/4 16:16.
 */
public interface ImageDao extends JpaRepository<Image, Integer> {

}