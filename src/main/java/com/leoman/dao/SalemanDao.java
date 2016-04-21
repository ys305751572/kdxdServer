package com.leoman.dao;

import com.leoman.entity.Saleman;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Administrator on 2016/4/21.
 */
public interface SalemanDao extends JpaRepository<Saleman,Long>,JpaSpecificationExecutor<Saleman>{

}
