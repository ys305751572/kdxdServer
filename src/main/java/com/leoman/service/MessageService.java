package com.leoman.service;

import com.leoman.entity.Message;
import com.leoman.service.common.ICommonService;
import org.springframework.data.domain.Page;

/**
 * Created by Administrator on 2016/3/10.
 */
public interface MessageService extends ICommonService<Message>{

    public Page<Message> findPage(Message message,Integer type,int pagenum,int pagesize);
}
