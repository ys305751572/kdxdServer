package com.leoman.dao;

import com.leoman.entity.Coinlog;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2016/3/18.
 */
public interface CoinlogDao extends JpaRepository<Coinlog,Long>,JpaSpecificationExecutor<Coinlog>{

}
