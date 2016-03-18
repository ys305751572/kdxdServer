package com.leoman.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "tb_coupons")
public class Coupon extends BaseEntity{

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "end_date")
    private Long endDate;

    @Column(name = "is_used")
    private Integer isUsed;

    @Transient
    private boolean isOverdue = false; // 是否过期 0:未过期 1:已过期

    public boolean isOverdue() {
        return isOverdue;
    }

    public void setOverdue(boolean overdue) {
        isOverdue = overdue;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getEndDate() {
        return endDate;
    }

    public void setEndDate(Long endDate) {
        this.endDate = endDate;
    }

    public Integer getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(Integer isUsed) {
        this.isUsed = isUsed;
    }
}
