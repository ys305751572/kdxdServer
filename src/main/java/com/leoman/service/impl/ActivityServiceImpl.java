package com.leoman.service.impl;

import com.leoman.dao.ActivityDao;
import com.leoman.entity.Activity;
import com.leoman.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016/3/10.
 */
@Service
public class ActivityServiceImpl implements ActivityService{

    @Autowired
    private ActivityDao dao;

    @Override
    public List<Activity> findAll() {
        return dao.findAll();
    }

    @Override
    public Page<Activity> find(int pageNum, int pageSize) {
        return null;
    }

    @Override
    public Page<Activity> find(int pageNum) {
        return null;
    }

    @Override
    public Activity getById(Long id) {
        return null;
    }

    @Override
    public Activity deleteById(Long id) {
        return null;
    }

    @Override
    public Activity create(Activity activity) {
        return null;
    }

    @Override
    public Activity update(Activity activity) {
        return dao.save(activity);
    }

    @Override
    public void deleteAll(Long[] ids) {

    }
}
