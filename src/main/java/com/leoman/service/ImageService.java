package com.leoman.service;


import com.leoman.entity.Image;

import java.util.List;

/**
 * Created by wangbin on 2014/12/9.
 */
public interface ImageService {

    public Image getById(int id);

    public Image deleteById(int id);

    public Image create(Image image);
}
