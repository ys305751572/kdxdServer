package com.leoman.service;

import com.leoman.entity.Product;
import com.leoman.entity.ProductBuyRecord;
import com.leoman.service.common.ICommonService;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by wangbin on 2015/8/17.
 */
public interface ProductService extends ICommonService<Product> {

    public Page<Product> findPage(Product project, Integer type, int pagenum, int pagesize);

    public Long findBuyCount(Long id);

    public Product reduceInventory(Long id);

    public ProductBuyRecord createProductByRecord(HttpServletResponse response, Long id, Boolean isUsed, Long userId);

    public void createOrder(Long productId, Long pbrId, Long serviceId, HttpServletRequest request, HttpServletResponse response);

    public Page<Product> findList(int pageNum, int pageSize);

    public void deleteImages(Long product,Integer imageId);
}