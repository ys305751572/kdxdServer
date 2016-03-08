package com.leoman.common.factory;

import org.springframework.stereotype.Component;

import com.leoman.core.bean.Menus;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by wangbin on 2015/8/12.
 */
@Component
public class PermissionFactory {

    private Map<String, List<String>> permissionUrlMap = new ConcurrentHashMap<String, List<String>>();

    private Map<String, List<Menus>> permissionMenusMap = new ConcurrentHashMap<String, List<Menus>>();


    public void putUrlMap(String key,List<String> urlList){
        permissionUrlMap.put(key,urlList);
    }

    public List<String> getUrlList(String key){
        return permissionUrlMap.get(key);
    }


    public void putMenusMap(String key,List<Menus> menuList){
        permissionMenusMap.put(key,menuList);
    }

    public List<Menus> getMenuList(String key){
        return permissionMenusMap.get(key);
    }

}
