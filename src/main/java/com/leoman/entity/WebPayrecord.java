package com.leoman.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Administrator on 2016/3/9.
 */
@Entity
@Table(name = "tb_web_pay_record")
public class WebPayrecord extends BaseEntity{

    @Column(name = "record_code")
    private String recordCode;

    @Column(name = "money")
    private Double money;

    @Column(name = "plat")
    private Integer plat;

    public String getRecordCode() {
        return recordCode;
    }

    public void setRecordCode(String recordCode) {
        this.recordCode = recordCode;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Integer getPlat() {
        return plat;
    }

    public void setPlat(Integer plat) {
        this.plat = plat;
    }
}
