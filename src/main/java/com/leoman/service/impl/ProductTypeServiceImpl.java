package com.leoman.service.impl;

import com.leoman.core.Constant;
import com.leoman.dao.ProductTypeDao;
import com.leoman.entity.ProductType;
import com.leoman.service.ProductTypeService;
import com.leoman.utils.ClassUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by wangbin on 2015/8/20.
 */
@Service
@Transactional(readOnly = true)
public class ProductTypeServiceImpl implements ProductTypeService {

    @Autowired
    private ProductTypeDao productTypeDao;


    @Override
    public List<ProductType> findAll() {
        return productTypeDao.findAll();
    }

    @Override
    public Page<ProductType> find(int pageNum, int pageSize) {
        return productTypeDao.findAll(new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id"));
    }

    @Override
    public Page<ProductType> find(int pageNum) {
        return find(pageNum, Constant.PAGE_DEF_SZIE);
    }

    @Override
    public ProductType getById(int id) {
        return productTypeDao.findOne(id);
    }

    @Override
    @Transactional
    public ProductType deleteById(int id) {
        ProductType productType = getById(id);
        productTypeDao.delete(productType);
        return productType;
    }

    @Override
    @Transactional
    public ProductType create(ProductType productType) {
        productType.setCreateDate(new Date());
        productTypeDao.save(productType);
        return productType;
    }

    @Override
    @Transactional
    public ProductType update(ProductType productType) {
        ProductType origProjectType = getById(productType.getId());
        ClassUtil.copyProperties(origProjectType, productType);
        origProjectType.setUpdateDate(new Date());
        return productTypeDao.save(origProjectType);
    }

    @Override
    @Transactional
    public void deleteAll(int[] ids) {
        for (int id : ids) {
            deleteById(id);
        }
    }

}