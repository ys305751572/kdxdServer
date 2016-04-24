package com.leoman.service.impl;

import com.leoman.dao.AddressDao;
import com.leoman.entity.Address;
import com.leoman.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/18.
 */
@Service
public class AddressServiceImpl implements AddressService {

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
        return dao.findOne(id);
    }

    @Override
    @Transactional
    public Address deleteById(Long id) {
        Address address = dao.findOne(id);
        if (null != address && address.getIsDefault() == 0) {
            // 如果是默认地址，则将该用户的第一个地址设置为默认地址
            List<Address> list = dao.findByUserIdNoDefault(address.getUserId(), id);
            if (null != list && list.size() > 0) {
                Address address1 = list.get(0);
                address1.setIsDefault(0);
                dao.save(address1);
            }
        }

        dao.delete(id);

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
     *
     * @param userId
     * @param addressId
     */
    @Transactional
    @Override
    public void addressDefault(Long userId, Long addressId) {
        // 先将该用户的所有地址更改为非默认地址
        List<Address> list = dao.findByUserId(userId);
        for (Address temp : list) {
            temp.setIsDefault(1);
            dao.save(temp);
        }
        Address address = dao.findOne(addressId);
        address.setIsDefault(0);
        dao.save(address);
    }

    @Override
    public Address findDefaultByUserId(Long userId) {
        return dao.findDefaultByUserId(userId);
    }

    @Override
    public Page<Address> pageByUserId(final Long userId, Integer pageNum, Integer pageSize) {
        Specification<Address> spec = new Specification<Address>() {
            @Override
            public Predicate toPredicate(Root<Address> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();
                list.add(criteriaBuilder.equal(root.get("userId").as(Long.class), userId));
                return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
            }
        };
        return dao.findAll(spec, new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id"));
    }
}
