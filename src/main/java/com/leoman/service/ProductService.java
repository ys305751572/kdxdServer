package com.leoman.service;

import com.leoman.entity.Product;
import com.leoman.service.common.ICommonService;
import org.springframework.data.domain.Page;

/**
 * Created by wangbin on 2015/8/17.
 */
public interface ProductService extends ICommonService<Product> {

    public Page<Product> findPage(Product project,Integer type, int pagenum, int pagesize);

    public Long findBuyCount(Long id);
}