package com.leoman.service;


import com.leoman.entity.Image;
import com.leoman.entity.ProductImages;
import com.leoman.service.common.ICommonService;

import java.util.List;

/**
 * Created by wangbin on 2015/4/16.
 */
public interface ProductImagesService extends ICommonService<ProductImages> {


    public void deleteByProductId(Integer productId);

    public void delete(Integer productId, Integer imageId);


    public List<Image> findImageListByProduct(Integer productId);
}
