package com.leoman.service;

import com.leoman.service.common.ICommonService;

import java.util.List;

/**
 * Created by Administrator on 2016/3/15.
 */
public interface PsService extends ICommonService<com.leoman.entity.ProductService> {

    // 根据商品id查询对应的服务周期
    public List<com.leoman.entity.ProductService> findListByProductId(Long productId);
}
