package com.leoman.service.impl;

import com.leoman.dao.ImageDao;
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

    @Autowired
    private ImageDao imageDao;

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

    @Override
    public List<ProductImage> findListByProductId(Long productId) {
        List<ProductImage> list = dao.findListByProductId(productId);
        for (ProductImage productImage : list) {
            productImage.setPath(imageDao.findOneInfo(productImage.getImageId()).getPath());
        }

        return list;
    }

    @Override
    public void deleteProductImageByProductIdAndImageId(Long productId, Integer imageId) {
        dao.deleteProductImageByProductIdAndImageId(productId,imageId);
    }
}
