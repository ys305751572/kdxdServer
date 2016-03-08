package com.leoman.service.impl;

import com.leoman.common.exception.GeneralExceptionHandler;
import com.leoman.core.Constant;
import com.leoman.dao.WxUserDao;
import com.leoman.entity.WxUser;
import com.leoman.service.WxUserService;
import com.leoman.utils.ClassUtil;

import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Created by wangbin on 2015/7/8.
 */
@Service
@Transactional(readOnly = true)
public class WxUserServiceImpl implements WxUserService {

    @Autowired
    private WxUserDao wxUserDao;

    @Autowired
    private WxMpService wxMpService;


    @Override
    public List<WxUser> findAll() {
        return wxUserDao.findAll();
    }

    @Override
    public Page<WxUser> find(int pageNum, int pageSize) {
        return wxUserDao.findAll(new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id"));
    }

    @Override
    public Page<WxUser> find(int pageNum) {
        return find(pageNum, Constant.PAGE_DEF_SZIE);
    }

    @Override
    public WxUser getById(int id) {
        return wxUserDao.findOne(id);
    }

    @Override
    @Transactional
    public WxUser deleteById(int id) {
        WxUser wxUser = getById(id);
        wxUserDao.delete(wxUser);
        return wxUser;
    }

    @Override
    @Transactional
    public WxUser create(WxUser wxUser) {
        wxUser.setCreateDate(new Date());
        wxUserDao.save(wxUser);
        return wxUser;
    }

    @Override
    @Transactional
    public WxUser update(WxUser wxUser) {
        return wxUserDao.save(wxUser);
    }

    @Override
    @Transactional
    public void deleteAll(int[] ids) {
        for (int id : ids) {
            deleteById(id);
        }
    }

    @Override
    @Transactional
    public WxUser create(WxMpUser wxMpUser) {

        WxUser wxUser = wxUserDao.findByOpenId(wxMpUser.getOpenId());
        if(wxUser!=null){
            return  wxUser;
        }else{
            wxUser = new WxUser();
            ClassUtil.copyProperties(wxUser, wxMpUser);
            return create(wxUser);
        }

    }

    @Override
    public WxUser findByOpenId(String openid) {
        return wxUserDao.findByOpenId(openid);
    }

    @Override
    @Transactional
    public WxUser getWxUserByToken(WxMpOAuth2AccessToken wxMpOAuth2AccessToken){
        WxUser wxUser = wxUserDao.findByOpenId(wxMpOAuth2AccessToken.getOpenId());
        if(wxUser!=null){
            return  wxUser;
        }else{
            try {
                wxUser = new WxUser();
                WxMpUser wxMpUser = wxMpService.oauth2getUserInfo(wxMpOAuth2AccessToken, null);
                ClassUtil.copyProperties(wxUser, wxMpUser);
                return create(wxUser);
            }catch (Exception e){
                GeneralExceptionHandler.log(e);
                return null;
            }

        }
    }

    @Override
    public WxUser getWXUserByRequest(HttpServletRequest request) {
        WxUser wxUser = (WxUser)request.getSession().getAttribute(Constant.SESSION_WEIXIN_USER);
        return wxUser;
    }


}