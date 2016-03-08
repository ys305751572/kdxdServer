package com.leoman.service.impl;

import com.leoman.core.Constant;
import com.leoman.dao.ProductDao;
import com.leoman.entity.Image;
import com.leoman.entity.Product;
import com.leoman.entity.ProductImages;
import com.leoman.service.ProductImagesService;
import com.leoman.service.ProductService;
import com.leoman.utils.ClassUtil;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by wangbin on 2015/8/17.
 */
@Service
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private ProductImagesService productImagesService;

    @Override
    public List<Product> findAll() {
        return productDao.findAll();
    }

    @Override
    public Page<Product> find(int pageNum, int pageSize) {

        return productDao.findAll(new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id"));

    }

    @Override
    public Page<Product> find(int pageNum) {
        return find(pageNum, Constant.PAGE_DEF_SZIE);
    }

    @Override
    public Product getById(int id) {
        return productDao.findOne(id);
    }

    @Override
    @Transactional
    public Product deleteById(int id) {
        Product product = getById(id);
        productImagesService.deleteByProductId(id);
        productDao.delete(product);
        return product;
    }

    @Override
    @Transactional
    public Product create(Product product) {
        product.setIsAdded(false);
        product.setCreateDate(new Date());
        productDao.save(product);
        return product;
    }

    @Override
    @Transactional
    public Product update(Product product) {
        Product origProduct = getById(product.getId());
        ClassUtil.copyProperties(origProduct, product);
        return productDao.save(origProduct);
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
    public Product create(Product product,String imageIds) {
        if(StringUtils.isNotBlank(imageIds)){
            String[] ids = imageIds.split(",");
            for(String id :ids){
                Image image = new Image();
                Integer imageId =  Integer.valueOf(id);
                image.setId(imageId);
                ProductImages productImages = new ProductImages();
                productImages.setImage(image);
                productImages.setProduct(product);
                productImagesService.create(productImages);
            }
        }
        return create(product);
    }


    @Override
    @Transactional
    public Product update(Product product, String imageIds) {
        //productImagesService.deleteByProductId(product.getId());
        if(StringUtils.isNotBlank(imageIds)){
            String[] ids = imageIds.split(",");
            for(String id :ids){
                Image image = new Image();
                Integer imageId =  Integer.valueOf(id);
                image.setId(imageId);
                ProductImages productImages = new ProductImages();
                productImages.setImage(image);
                productImages.setProduct(product);
                productImagesService.create(productImages);
            }
        }
        return update(product);
    }

    @Override
    @Transactional
    public void settingAdded(int id) {
        Product product = getById(id);
        if (product.getIsAdded()) {
            product.setIsAdded(false);
        } else {
            product.setUpdateDate(new Date());
            product.setIsAdded(true);
        }

        productDao.save(product);
    }


}