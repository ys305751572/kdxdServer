package com.leoman.service;

import com.leoman.entity.SalemanRecord;
import com.leoman.service.common.ICommonService;
import org.springframework.data.domain.Page;

/**
 * Created by Administrator on 2016/4/21.
 */
public interface SalamanRecordService extends ICommonService<SalemanRecord>{

    public Page<SalemanRecord> findPage(String salemanName,int pagenum,int pagesize);
}
