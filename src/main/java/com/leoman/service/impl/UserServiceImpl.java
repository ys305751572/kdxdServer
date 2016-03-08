package com.leoman.service.impl;

import com.leoman.core.Constant;
import com.leoman.dao.UserDao;
import com.leoman.entity.User;
import com.leoman.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by wangbin on 2015/9/1.
 */
@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public Page<User> find(int pageNum, int pageSize) {

        return userDao.findAll(new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id"));

    }

    @Override
    public Page<User> find(int pageNum) {
        return find(pageNum, Constant.PAGE_DEF_SZIE);
    }

    @Override
    public User getById(int id) {
        return userDao.findOne(id);
    }

    @Override
    @Transactional
    public User deleteById(int id) {
        User user = getById(id);
        userDao.delete(user);
        return user;
    }

    @Override
    @Transactional
    public User create(User user) {
        userDao.save(user);
        return user;
    }

    @Override
    @Transactional
    public User update(User user) {
        return userDao.save(user);
    }

    @Override
    @Transactional
    public void deleteAll(int[] ids) {
        for (int id : ids) {
            deleteById(id);
        }
    }

}