package com.leoman.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Administrator on 2016/3/18.
 */

/**
 * 用户充值记录
 */
@Entity
@Table(name = "tb_coinlog")
public class Coinlog extends BaseEntity{

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "money")
    private Double money;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }
}
