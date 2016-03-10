package com.leoman.service;

import com.leoman.entity.KUser;
import com.leoman.entity.Order;
import com.leoman.service.common.ICommonService;
import org.springframework.data.domain.Page;

/**
 * Created by wangbin on 2015/8/28.
 */
public interface OrderService extends ICommonService<Order> {

    public Order modifyStatus(Long id, Integer status);

    public Page<Order> findPage(Order user, int pagenum, int pagesize);
}
