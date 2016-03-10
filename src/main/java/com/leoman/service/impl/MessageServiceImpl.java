package com.leoman.service.impl;

import com.leoman.dao.MessageDao;
import com.leoman.entity.Message;
import com.leoman.service.MessageService;
import com.leoman.utils.MessageTimerWork;
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
 * Created by Administrator on 2016/3/10.
 */
@Service
public class MessageServiceImpl implements MessageService{

    @Autowired
    private MessageDao dao;

    /**
     * 翻页查询
     * @param message
     * @param type
     * @param pagenum
     * @param pagesize
     * @return
     */
    @Override
    public Page<Message> findPage(final Message message,final Integer type, int pagenum, int pagesize) {
        Specification<Message> spec = new Specification<Message>() {
            @Override
            public Predicate toPredicate(Root<Message> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();
                if(message.getTitle() != null) {
                    list.add(criteriaBuilder.like(root.get("title").as(String.class),"%" + message.getTitle() + "%"));
                }
                if(type != null) {
                    if(type == 0) {
                        list.add(criteriaBuilder.gt(root.get("sendDate").as(Long.class),System.currentTimeMillis()));
                    }
                    else {
                        list.add(criteriaBuilder.lt(root.get("sendDate").as(Long.class),System.currentTimeMillis()));
                    }
                }
                return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
            }
        };
        return dao.findAll(spec,new PageRequest(pagenum - 1,pagesize, Sort.Direction.DESC,"id"));
    }

    @Override
    public List<Message> findAll() {
        return null;
    }

    @Override
    public Page<Message> find(int pageNum, int pageSize) {
        return null;
    }

    @Override
    public Page<Message> find(int pageNum) {
        return null;
    }

    @Override
    public Message getById(Long id) {
        return dao.findOne(id);
    }

    @Override
    public Message deleteById(Long id) {
        Message msg = getById(id);
        dao.delete(msg);
        return null;
    }

    @Override
    public Message create(Message message) {
        new MessageTimerWork(message.getSendDate());
        return dao.save(message);
    }

    @Override
    public Message update(Message message) {
        return dao.save(message);
    }

    @Override
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            deleteById(id);
        }
    }
}