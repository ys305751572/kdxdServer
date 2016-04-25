package com.leoman.dao;

import com.leoman.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2016/3/18.
 */
public interface AddressDao extends JpaRepository<Address, Long>, JpaSpecificationExecutor<Address> {

    @Query("select a from Address a where a.userId = ?1")
    public List<Address> findByUserId(Long userId);

    @Query("select a from Address a where a.userId = ?1 and a.isDefault = 0")
    public Address findDefaultByUserId(Long userId);

    @Query("select a from Address a where a.userId = ?1 and a.id <> ?2")
    public List<Address> findByUserIdNoDefault(Long userId, Long addressId);
}
