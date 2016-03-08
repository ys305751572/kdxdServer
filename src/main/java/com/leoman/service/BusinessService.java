package com.leoman.service;

import com.leoman.entity.Business;
import com.leoman.service.common.ICommonService;

/**
 * Created by wangbin on 2015/8/10.
 */
public interface BusinessService extends ICommonService<Business>{


    public Business optionsBusiness(String username,String password,Long businessId);


    public Business update(Long businessId,Business business);

}
