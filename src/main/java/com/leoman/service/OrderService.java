package com.leoman.service;

import com.leoman.entity.Order;
import com.leoman.service.common.ICommonService;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by wangbin on 2015/8/28.
 */
public interface OrderService extends ICommonService<Order> {

    public Order modifyStatus(Long id, Integer status);

    public Page<Order> findPage(Order user, int pagenum, int pagesize);

    // 根据订单流水号查询订单详情
    public Order findByOrderSn(String sn);

    // 根据用户id查询对应用户的订单列表
    public List<Order> findListByUserId(Long userId);

    // 根据用户id查询对应用户的订单列表（分页）
    public Page<Order> pageByUserId(Long userId, Integer pageNum, Integer pageSize);

    // 获取当天最新的订单信息
    public List<Order> findNewOne();

    // 用户余额支付订单
    public void payOrder(Long orderId, Long userId);
}
