package com.leoman.service;

import com.leoman.entity.Information;
import com.leoman.service.common.ICommonService;
import org.springframework.data.domain.Page;

/**
 * Created by Administrator on 2016/3/9.
 */
public interface InfomationService extends ICommonService<Information>{

    public Page<Information> findPage(Information info,int pagenum,int pagesize);

    public void publish(Long[] ids);
}
