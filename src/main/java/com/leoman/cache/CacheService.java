package com.leoman.cache;


/**
 * Created by wangbin on 2015/4/22.
 */
public interface CacheService<T> {
    public void put(String key, T value);
    public T get(String key);
    public boolean isExist(String key);
    public void remove(String key);

}
