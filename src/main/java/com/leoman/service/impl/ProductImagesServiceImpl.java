package com.leoman.service.impl;

import com.leoman.core.Constant;
import com.leoman.dao.ProductImagesDao;
import com.leoman.entity.Image;
import com.leoman.entity.ProductImages;
import com.leoman.service.ImageService;
import com.leoman.service.ProductImagesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by wangbin on 2015/4/16.
 */
@Service
@Transactional(readOnly = true)
public class ProductImagesServiceImpl implements ProductImagesService {

    @Autowired
    private ProductImagesDao productImagesDao;
    @Autowired
    private ImageService imageService;


    @Override
    public List<ProductImages> findAll() {
        return productImagesDao.findAll();
    }

    @Override
    public Page<ProductImages> find(int pageNum, int pageSize) {

        return productImagesDao.findAll(new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id"));

    }

    @Override
    public Page<ProductImages> find(int pageNum) {
        return find(pageNum, Constant.PAGE_DEF_SZIE);
    }

    @Override
    public ProductImages getById(int id) {
        return productImagesDao.findOne(id);
    }

    @Override
    @Transactional
    public ProductImages deleteById(int id) {
        ProductImages productImages = getById(id);
        productImagesDao.delete(productImages);
        return productImages;
    }

    @Override
    @Transactional
    public ProductImages create(ProductImages productImages) {
        productImagesDao.save(productImages);
        return productImages;
    }

    @Override
    @Transactional
    public ProductImages update(ProductImages productImages) {
        return productImagesDao.save(productImages);
    }

    @Override
    @Transactional
    public void deleteAll(int[] ids) {
        for (int id : ids) {
            deleteById(id);
        }
    }

    @Override
    @Transactional
    public void deleteByProductId(Integer productId) {
        productImagesDao.deleteByProductId(productId);
    }

    @Override
    @Transactional
    public void delete(Integer productId,Integer imageId) {
        imageService.deleteById(imageId);
        productImagesDao.delete(productId,imageId);
    }

    @Override
    public List<Image> findImageListByProduct(Integer productId) {
        return productImagesDao.findImagesByProduct(productId);
    }



}