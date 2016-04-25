package com.leoman.service;

import com.leoman.entity.Information;
import com.leoman.service.common.ICommonService;
import org.springframework.data.domain.Page;

/**
 * Created by Administrator on 2016/3/9.
 */
public interface InfomationService extends ICommonService<Information> {

    public Page<Information> findPage(Information info, int pagenum, int pagesize,String sort,String column);

    public void publish(Long[] ids);

    public void unpublish(Long[] ids);

    public Page<Information> findList(Integer pageNum, Integer pageSize);
}
