package com.leoman.service.impl;

import com.leoman.dao.ProductImageDao;
import com.leoman.entity.ProductImage;
import com.leoman.service.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016/3/11.
 */
@Service
public class ProductImageServiceImpl implements ProductImageService {

    @Autowired
    private ProductImageDao dao;

    @Override
    public List<ProductImage> findAll() {
        return null;
    }

    @Override
    public Page<ProductImage> find(int pageNum, int pageSize) {
        return null;
    }

    @Override
    public Page<ProductImage> find(int pageNum) {
        return null;
    }

    @Override
    public ProductImage getById(Long id) {
        return null;
    }

    @Override
    public ProductImage deleteById(Long id) {
        return null;
    }

    @Override
    public ProductImage create(ProductImage productImage) {
        return dao.save(productImage);
    }

    @Override
    public ProductImage update(ProductImage productImage) {
        return null;
    }

    @Override
    public void deleteAll(Long[] ids) {

    }
}
