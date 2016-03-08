package com.leoman.service;

import com.leoman.entity.Admin;
import com.leoman.service.common.ICommonService;

/**
 * Created by Administrator on 2016/3/8.
 */
public interface AdminService extends ICommonService<Admin>{

    public Admin findByUsername(String username);
}
