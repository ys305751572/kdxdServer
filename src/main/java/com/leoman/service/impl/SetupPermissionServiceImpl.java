//package com.leoman.service.impl;
//package com.bluemobi.winetast.service.impl;
//
//import com.bluemobi.winetast.common.factory.PermissionFactory;
//import com.bluemobi.winetast.common.logger.Logger;
//import com.bluemobi.winetast.core.Configue;
//import com.bluemobi.winetast.core.bean.Menu;
//import com.bluemobi.winetast.core.bean.Menus;
//import com.bluemobi.winetast.core.bean.Role;
//import com.bluemobi.winetast.service.SetupPermissionService;
//import org.dom4j.Document;
//import org.dom4j.DocumentException;
//import org.dom4j.Element;
//import org.dom4j.io.SAXReader;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//
///**
// * Created by wangbin on 2015/8/12.
// */
//@Service
//public class SetupPermissionServiceImpl implements SetupPermissionService {
//
//    @Autowired
//    private PermissionFactory permissionFactory;
//
//    @Override
//    public void initPermission(){
//        final InputStream in = Configue.class.getResourceAsStream("/permission.xml");
//        Document doc = null;
//        try {
//            SAXReader reader = new SAXReader();
//            doc = reader.read(in);
//        } catch (DocumentException e) {
//            throw new RuntimeException(e.getMessage());
//        }
//
//
//        List<Menus> menusList = readMenus(doc);
//
//
//        System.out.println(menusList);
//        Logger.info("读取菜单信息成功。");
//
//        //添加权限URL
//        List<Role> roleList=readRole(doc);
//        roleList.forEach((role -> {
//            String roleName = role.getName();
//            List<String> urlList = new ArrayList<String>();
//            menusList.stream().filter((menus -> menus.getRole().equals(roleName)))
//                    .forEach(menus1 -> {
//                        if (menus1.getPath() != null) {
//                            urlList.add(menus1.getPath());
//                        }
//                        if (menus1.getHasSon()) {
//                            menus1.getMenus().stream().filter(menu -> menu.getRole().equals(roleName))
//                                    .forEach(menu1 -> {
//                                        if (menu1.getPath() != null) {
//                                            urlList.add(menu1.getPath());
//                                        }
//                                    });
//                        }
//
//                    });
//            permissionFactory.putUrlMap(roleName, urlList);
//
//        }));
//        Logger.info("添加权限URL");
//
//
//        roleList.forEach((role -> {
//            String roleName = role.getName();
//            List<Menus> menusList1 = new ArrayList<Menus>();
//            menusList.stream().filter(menus -> menus.getRole().equals(roleName))
//                    .forEach(menus1 -> {
//                        menusList1.add(menus1);
//                    });
//            permissionFactory.putMenusMap(roleName, menusList1);
//        }));
//        Logger.info("添加权限菜单成功");
//
//    }
//
//
//
//    public List<Role> readRole(Document doc){
//        List<Role> roleList=new ArrayList<Role>();
//
//        Element rolesElement = (Element) doc.selectSingleNode("//permission/roles");
//        if (rolesElement.hasContent()){
//            for (Iterator<?> it = rolesElement.elementIterator(); it.hasNext();) {
//                Element roleElement = (Element) it.next();
//                Role role=new Role();
//                String name=roleElement.attributeValue("name");
//                String description=roleElement.attributeValue("description");
//                role.setName(name);
//                role.setDescription(description);
//                roleList.add(role);
//            }
//        }
//        return roleList;
//    }
//
//
//    public List<Menus> readMenus(Document doc){
//        List<Menus> menusList=new ArrayList<Menus>();
//        Element menusgroupsElement = (Element) doc.selectSingleNode("//permission/menusgroups");
//        if(menusgroupsElement.hasContent()){
//            for (Iterator<?> it = menusgroupsElement.elementIterator(); it.hasNext();) {
//                Element menusElement = (Element) it.next();
//                Menus menus = new Menus();
//                String name =menusElement.attributeValue("name");
//                String role = menusElement.attributeValue("role");
//                String path = menusElement.attributeValue("path");
//                Boolean hasSon =  Boolean.valueOf(menusElement.attributeValue("hasSon"));
//
//                menus.setName(name);
//                menus.setRole(role);
//                menus.setPath(path);
//                menus.setHasSon(hasSon);
//
//                if(hasSon){
//                    menus.setMenus(new ArrayList<>());
//                    for (Iterator<?> moduleIt = menusElement.elementIterator(); moduleIt.hasNext();) {
//                        Element moduleElement = (Element) moduleIt.next();
//                        Menu menu=readMenu(moduleElement);
//                        menus.getMenus().add(menu);
//                    }
//                }
//
//                menusList.add(menus);
//            }
//        }
//        return menusList;
//    }
//
//
//    Menu readMenu(Element menuElement){
//        Menu menu = new Menu();
//        String name =menuElement.attributeValue("name");
//        String role = menuElement.attributeValue("role");
//        String path = menuElement.attributeValue("path");
//        menu.setName(name);
//        menu.setRole(role);
//        menu.setPath(path);
//        return menu;
//    }
//
//
//
//}
