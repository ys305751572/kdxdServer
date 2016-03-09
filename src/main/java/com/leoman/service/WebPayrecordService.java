package com.leoman.service;

import com.leoman.entity.WebPayrecord;
import com.leoman.service.common.ICommonService;
import org.springframework.data.domain.Page;

/**
 * Created by Administrator on 2016/3/9.
 */
public interface WebPayrecordService extends ICommonService<WebPayrecord>{

    public Page<WebPayrecord> findPage(WebPayrecord payrecord,Integer type,int pagenum,int pagesize);
}
