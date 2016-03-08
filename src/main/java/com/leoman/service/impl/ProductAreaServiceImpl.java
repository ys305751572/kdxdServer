package com.leoman.service.impl;

import com.leoman.core.Constant;
import com.leoman.dao.ProductAreaDao;
import com.leoman.entity.ProductArea;
import com.leoman.service.ProductAreaService;
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
public class ProductAreaServiceImpl implements ProductAreaService {

    @Autowired
    private ProductAreaDao productAreaDao;


    @Override
    public List<ProductArea> findAll() {
        return productAreaDao.findAll();
    }

    @Override
    public Page<ProductArea> find(int pageNum, int pageSize) {

        return productAreaDao.findAll(new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id"));

    }

    @Override
    public Page<ProductArea> find(int pageNum) {
        return find(pageNum, Constant.PAGE_DEF_SZIE);
    }

    @Override
    public ProductArea getById(int id) {
        return productAreaDao.findOne(id);
    }

    @Override
    @Transactional
    public ProductArea deleteById(int id) {
        ProductArea productArea = getById(id);
        productAreaDao.delete(productArea);
        return productArea;
    }

    @Override
    @Transactional
    public ProductArea create(ProductArea productArea) {
        productArea.setCreateDate(new Date());
        productAreaDao.save(productArea);
        return productArea;
    }

    @Override
    @Transactional
    public ProductArea update(ProductArea productArea) {
        ProductArea origProductArea = getById(productArea.getId());
        ClassUtil.copyProperties(origProductArea, productArea);
        origProductArea.setUpdateDate(new Date());
        return productAreaDao.save(origProductArea);
    }

    @Override
    @Transactional
    public void deleteAll(int[] ids) {
        for (int id : ids) {
            deleteById(id);
        }
    }

}