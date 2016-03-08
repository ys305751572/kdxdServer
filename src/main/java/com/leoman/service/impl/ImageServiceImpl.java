package com.leoman.service.impl;


import com.leoman.dao.ImageDao;
import com.leoman.entity.Image;
import com.leoman.service.ImageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by wangbin on 14-10-16.
 */
@Service
@Transactional(readOnly = true)
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageDao imageDao;

    @Override
    public Image getById(int id) {
        return imageDao.findOne(id);
    }

    @Override
    @Transactional
    public Image deleteById(int id) {
        Image image = getById(id);
        imageDao.delete(image);
        return image;
    }

    @Override
    @Transactional
    public Image create(Image image) {
        image.setCreateDate(new Date());
        return imageDao.save(image);
    }
}
