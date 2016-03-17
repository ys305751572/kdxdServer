package com.leoman.cache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Created by wangbin on 2015/4/22.
 */
@Service
public  class CacheTempCodeServiceImpl implements CacheService<String> {

    private Cache cache;

    private static final String KEY="CACHE_CODE_KEY";

    @Autowired
    private EhCacheCacheManager cacheManager;


    protected String getKey(String key) {
        return KEY+">"+key;
    }

    @PostConstruct
    protected void cacheInit(){
        this.cache = cacheManager.getCacheManager().getCache("TEMP.CACHE");
    }

    @Override
    public void put(String key, String value) {
        cache.put(new Element(getKey(key),value));
    }

    @Override
    public String get(String key) {
        if(isExist(key)){
            return cache.get(getKey(key)).getObjectValue().toString();
        }else{
            return null;
        }

    }


    @Override
    public boolean isExist(String key) {
        return cache.isKeyInCache(getKey(key));
    }

    @Override
    public void remove(String key) {
        cache.remove(getKey(key));
    }


}
