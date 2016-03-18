package com.leoman.service;

import com.leoman.entity.Coinlog;
import com.leoman.service.common.ICommonService;
import org.springframework.data.domain.Page;

/**
 * Created by Administrator on 2016/3/18.
 */
public interface CoinlogService extends ICommonService<Coinlog>{

    public Page<Coinlog> findPageByUserId(Long id,int pagenum,int pagesize);
}
