package com.leoman.service.impl;

import com.leoman.dao.KUserDao;
import com.leoman.entity.Address;
import com.leoman.entity.KUser;
import com.leoman.entity.WxUser;
import com.leoman.service.KUserService;
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
 * Created by Administrator on 2016/3/8.
 */
@Service
public class KUserServiceImpl implements KUserService {

    @Autowired
    private KUserDao dao;

    @Override
    public KUser modifyStatus(Long id, Integer status) {
        KUser _user = dao.findOne(id);
        _user.setStatus(status == 0 ? 1 : 0);
        return dao.save(_user);
    }

    @Override
    public Page<KUser> findPage(final KUser user, int pagenum, int pagesize) {
        Specification<KUser> spec = new Specification<KUser>() {
            @Override
            public Predicate toPredicate(Root<KUser> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();
                Predicate result = null;
                if (user.getMobile() != null) {
                    list.add(criteriaBuilder.like(root.get("mobile").as(String.class), '%' + user.getMobile() + '%'));
                }
                if (user.getNickname() != null) {
                    list.add(criteriaBuilder.like(root.get("nickname").as(String.class), '%' + user.getNickname() + '%'));
                }
                if (user.getPlat() != null) {
                    list.add(criteriaBuilder.equal(root.get("plat").as(Integer.class), user.getPlat()));
                }
                if (user.getStatus() != null) {
                    list.add(criteriaBuilder.equal(root.get("status").as(Integer.class), user.getStatus()));
                }
                if (list.size() > 0) {
                    result = criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
                }
                if (result != null) {
                    criteriaQuery.where(result);
                }
                return criteriaQuery.getRestriction();
            }
        };
        return dao.findAll(spec, new PageRequest(pagenum - 1, pagesize, Sort.Direction.DESC, "id"));
    }

    /**
     * 注册
     *
     * @param user
     * @return
     */
    @Override
    public KUser register(KUser user) {
        user.setNickname("");
        user.setPlat(1);
        user.setStatus(0);
        user.setMoney(0.0);
        return dao.save(user);
    }

    @Override
    public KUser findByMobile(String mobile) {
        return dao.findByMobile(mobile);
    }

    @Override
    public Address findDefaultAddressByUserId(Long userId) {
        return dao.findAddressByUserId(userId);
    }

    @Override
    public List<Address> findAllAddressByUserId(Long userId) {
        return dao.findAllAddressByUserId(userId);
    }

    @Override
    public KUser findUserByWxUser(WxUser wxUser) {
        return dao.findUserByWxUser(wxUser.getId());
    }

    @Override
    public List<KUser> findAll() {
        return null;
    }

    @Override
    public Page<KUser> find(int pageNum, int pageSize) {
        return null;
    }

    @Override
    public Page<KUser> find(int pageNum) {
        return null;
    }

    @Override
    public KUser getById(Long id) {
        return dao.findOne(id);
    }

    @Override
    public KUser deleteById(Long id) {
        KUser _user = dao.findOne(id);
        dao.delete(_user);
        return null;
    }

    @Override
    public KUser create(KUser kUser) {
        return dao.save(kUser);
    }

    @Override
    public KUser update(KUser kUser) {
        return dao.save(kUser);
    }

    @Override
    @Transactional
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            deleteById(id);
        }
    }
}
