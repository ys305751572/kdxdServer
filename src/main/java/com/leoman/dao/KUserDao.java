package com.leoman.dao;

import com.leoman.entity.Address;
import com.leoman.entity.KUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2016/3/8.
 */
public interface KUserDao extends JpaRepository<KUser,Long>,JpaSpecificationExecutor<KUser>{

    @Query("select a from KUser a where a.mobile = ?1")
    public KUser findByMobile(String mobile);

    @Query("select a from Address a where a.userId = ?1 and a.isDefault = 0")
    public Address findAddressByUserId(Long userId);

    @Query("select a from Address a where a.userId = ?1")
    public List<Address> findAllAddressByUserId(Long id);
}
