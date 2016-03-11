package com.leoman.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Administrator on 2016/3/11.
 */
@Entity
@Table(name = "tb_product_service")
public class ProductService extends BaseEntity{

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "days")
    private Integer days;

    @Column(name = "money")
    private Double money;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }
}
