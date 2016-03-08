package com.leoman.service;

import com.leoman.entity.Product;
import com.leoman.service.common.ICommonService;

/**
 * Created by wangbin on 2015/8/17.
 */
public interface ProductService extends ICommonService<Product> {

    public void settingAdded(int id);

    public Product create(Product product,String imageIds);

    public Product update(Product product,String imageIds);
}
