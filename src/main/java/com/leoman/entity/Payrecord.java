package com.leoman.entity;

import javax.persistence.*;

/**
 * Created by Administrator on 2016/3/9.
 */
@Entity
@Table(name = "tb_pay_record")
public class Payrecord extends BaseEntity{

    @OneToOne
    @JoinColumn(name = "user_id")
    private KUser user;

    @Column(name = "start_date")
    private Long startDate;

    @Column(name = "end_date")
    private Long endDate;

    public Long getStartDate() {
        return startDate;
    }

    public void setStartDate(Long startDate) {
        this.startDate = startDate;
    }

    public Long getEndDate() {
        return endDate;
    }

    public void setEndDate(Long endDate) {
        this.endDate = endDate;
    }

    public KUser getUser() {
        return user;
    }

    public void setUser(KUser user) {
        this.user = user;
    }

    @Column(name = "money")
    private Double money;

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }
}
