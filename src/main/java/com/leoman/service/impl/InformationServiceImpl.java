package com.leoman.service.impl;

import com.leoman.dao.InfomationDao;
import com.leoman.entity.Information;
import com.leoman.service.InfomationService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/9.
 */
@Service
public class InformationServiceImpl implements InfomationService {

    @Autowired
    private InfomationDao dao;

    @Override
    public Page<Information> findPage(final Information info, int pagenum, int pagesize,String sort,String column) {
        Specification<Information> spec = new Specification<Information>() {
            @Override
            public Predicate toPredicate(Root<Information> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();
                if (info.getTitle() != null) {
                    criteriaBuilder.like(root.get("title").as(String.class), info.getTitle());
                }
                if (info.getIsList() != null) {
                    criteriaBuilder.equal(root.get("isList").as(Integer.class), info.getIsList());
                }

                return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
            }
        };
        if(StringUtils.isNotBlank(sort)) {
            if("asc".equals(sort)) {
                return dao.findAll(spec, new PageRequest(pagenum - 1, pagesize, Sort.Direction.ASC, column));
            }
            else {
                return dao.findAll(spec, new PageRequest(pagenum - 1, pagesize, Sort.Direction.DESC, column));
            }
        }
        return dao.findAll(spec, new PageRequest(pagenum - 1, pagesize, Sort.Direction.DESC, "id"));
    }

    // 发布
    @Override
    public void publish(Long[] ids) {
        for (Long id : ids) {
            publishInformation(id);
        }
    }

    @Override
    public Page<Information> findList(Integer pageNum, Integer pageSize) {
        PageRequest pageRequest = new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id");

        Page<Information> page = dao.findAll(new Specification<Information>() {
            @Override
            public Predicate toPredicate(Root<Information> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate result = null;
                List<Predicate> predicateList = new ArrayList<Predicate>();

                Predicate pre = cb.equal(root.get("isList").as(Integer.class), 1);
                predicateList.add(pre);

                Predicate pre1 = cb.notEqual(root.get("id").as(Integer.class), 0);
                predicateList.add(pre1);

                if (predicateList.size() > 0) {
                    result = cb.and(predicateList.toArray(new Predicate[]{}));
                }

                if (result != null) {
                    query.where(result);
                }
                return query.getGroupRestriction();
            }

        }, pageRequest);

        return page;
    }

    public void publishInformation(Long id) {
        Information info = dao.findOne(id);
        info.setIsList(1);
        dao.save(info);
    }

    @Override
    public List<Information> findAll() {
        return null;
    }

    @Override
    public Page<Information> find(int pageNum, int pageSize) {
        return null;
    }

    @Override
    public Page<Information> find(int pageNum) {
        return null;
    }

    @Override
    public Information getById(Long id) {
        return dao.findOne(id);
    }

    @Override
    public Information deleteById(Long id) {
        Information info = dao.findOne(id);
        dao.delete(info);
        return null;
    }

    @Override
    public Information create(Information information) {
        return dao.save(information);
    }

    @Override
    public Information update(Information information) {
        return dao.save(information);
    }

    @Override
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            deleteById(id);
        }
    }
}
