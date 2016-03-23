package com.leoman.service.impl;

import com.leoman.dao.AddressDao;
import com.leoman.entity.Address;
import com.leoman.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/3/18.
 */
@Service
public class AddressServiceImpl implements AddressService{

    @Autowired
    private AddressDao dao;

    @Override
    public List<Address> findAll() {
        return null;
    }

    @Override
    public Page<Address> find(int pageNum, int pageSize) {
        return null;
    }

    @Override
    public Page<Address> find(int pageNum) {
        return null;
    }

    @Override
    public Address getById(Long id) {
        return null;
    }

    @Override
    public Address deleteById(Long id) {
        return null;
    }

    @Override
    public Address create(Address address) {
        return dao.save(address);
    }

    @Override
    public Address update(Address address) {
        return dao.save(address);
    }

    @Override
    public void deleteAll(Long[] ids) {

    }

    @Override
    public List<Address> findByUserId(Long userId) {
        return dao.findByUserId(userId);
    }

    /**
     * 选择默认地址
     * @param userId
     * @param addressId
     */
    @Transactional
    @Override
    public void addressDefault(Long userId, Long addressId) {
        dao.modifyAddressByUserId(userId);
        Address address = dao.findOne(addressId);
        address.setIsDefault(0);
        dao.save(address);
    }
}
