package com.leoman.service;

import com.leoman.entity.ProductBuyRecord;
import com.leoman.service.common.ICommonService;
import org.springframework.data.domain.Page;

/**
 * Created by Administrator on 2016/3/11.
 */
public interface ProductBuyRecordService extends ICommonService<ProductBuyRecord>{

    public Page<ProductBuyRecord> findPage(ProductBuyRecord record,Integer isPay,Integer isUserVouchers,int pagenum, int pagesize);
}
