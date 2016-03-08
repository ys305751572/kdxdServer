package com.leoman.service;

import com.leoman.entity.KUser;
import com.leoman.service.common.ICommonService;
import org.springframework.data.domain.Page;

/**
 * Created by Administrator on 2016/3/8.
 */
public interface KUserService extends ICommonService<KUser>{

    public KUser modifyStatus(Long id,Integer status);

    public Page<KUser> findPage(KUser user,int pagenum,int pagesize);
}
