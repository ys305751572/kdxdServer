package com.leoman.service;

import com.leoman.entity.Payrecord;
import com.leoman.service.common.ICommonService;
import org.springframework.data.domain.Page;

/**
 * Created by Administrator on 2016/3/9.
 */
public interface PayrecordService extends ICommonService<Payrecord>{

    public Page<Payrecord> findPage(Payrecord payrecord,Integer isEnd,int pagenum,int pagesize);
}
