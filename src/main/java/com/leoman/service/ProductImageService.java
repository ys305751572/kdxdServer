package com.leoman.service;

import com.leoman.entity.ProductImage;
import com.leoman.service.common.ICommonService;

import java.util.List;

/**
 * Created by Administrator on 2016/3/11.
 */
public interface ProductImageService extends ICommonService<ProductImage>{

    public List<ProductImage> findListByProductId(Long productId);
}
