package com.leoman.service.impl;

import com.leoman.dao.PsDao;
import com.leoman.entity.ProductService;
import com.leoman.service.PsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016/3/15.
 */
@Service
public class PsServiceImpl implements PsService{

    @Autowired
    private PsDao dao;

    @Override
    public List<ProductService> findAll() {
        return null;
    }

    @Override
    public Page<ProductService> find(int pageNum, int pageSize) {
        return null;
    }

    @Override
    public Page<ProductService> find(int pageNum) {
        return null;
    }

    @Override
    public ProductService getById(Long id) {
        return null;
    }

    @Override
    public ProductService deleteById(Long id) {
        return null;
    }

    @Override
    public ProductService create(ProductService productService) {
        return dao.save(productService);
    }

    @Override
    public ProductService update(ProductService productService) {
        return null;
    }

    @Override
    public void deleteAll(Long[] ids) {

    }
}
