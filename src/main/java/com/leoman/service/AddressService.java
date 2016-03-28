package com.leoman.service;

import com.leoman.entity.Address;
import com.leoman.service.common.ICommonService;

import java.util.List;

/**
 * Created by Administrator on 2016/3/18.
 */
public interface AddressService extends ICommonService<Address> {

    public List<Address> findByUserId(Long userId);

    public void addressDefault(Long userId, Long addressId);

    // 获取用户的默认地址信息
    public Address findDefaultByUserId(Long userId);
}
