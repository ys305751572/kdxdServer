package com.leoman.service;

import com.leoman.entity.Saleman;
import com.leoman.service.common.ICommonService;
import org.springframework.data.domain.Page;

/**
 * Created by Administrator on 2016/4/21.
 */
public interface SalemanService extends ICommonService<Saleman>{

    public Page<Saleman> findPage(String mobile,String name,int pagenum,int pagesize);


}
