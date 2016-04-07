package com.leoman.dao;

import com.leoman.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import com.leoman.entity.Image;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by 涂奕恒 on 2014/12/4 16:16.
 */
public interface ImageDao extends JpaRepository<Image, Integer> {

    @Query("select a from Image a where a.id = ?1")
    public Image findOneInfo(Integer id);
}