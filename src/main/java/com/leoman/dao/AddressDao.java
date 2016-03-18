package com.leoman.dao;

import com.leoman.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2016/3/18.
 */
public interface AddressDao extends JpaRepository<Address,Long>,JpaSpecificationExecutor<Address>{

    @Query("select a from Address a where a.userId = ?1")
    public List<Address> findByUserId(Long userId);

    /**
     * 将用户地址改为非选择状态
     * @param userId
     */
    @Query("update Address a set a.isDefault = 1 where a.userId = ?1")
    public void modifyAddressByUserId(Long userId);
}
