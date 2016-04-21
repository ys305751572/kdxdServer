package com.leoman.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "tb_user_coupons")
public class Coupon extends BaseEntity{

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "end_date")
    private Long endDate;

    @Column(name = "status")
    private Integer status;

    @Column(name = "is_used")
    private Integer isUsed;

    @Column(name = "is_changed")
    private Integer isChanged;

    @Transient
    private boolean isOverdue = false; // 是否过期 0:未过期 1:已过期

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

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

    public Integer getIsChanged() {
        return isChanged;
    }

    public void setIsChanged(Integer isChanged) {
        this.isChanged = isChanged;
    }
}
