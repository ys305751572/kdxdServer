package com.leoman.service.common;

import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by wangbin on 14-10-16.
 */
public interface ICommonService<T> {

    public List<T> findAll();

    public Page<T> find(int pageNum, int pageSize);

    public Page<T> find(int pageNum);

    public T getById(int id);

    public T deleteById(int id);

    public T create(T t);

    public T update(T t);

    public void deleteAll(int[] ids);


}
