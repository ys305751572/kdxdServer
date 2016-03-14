package com.leoman.entity;

import javax.persistence.*;

/**
 * Created by Administrator on 2016/3/11.
 */
@Entity
@Table(name = "tb_product_buy_record")
public class ProductBuyRecord extends BaseEntity{

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private KUser user;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "is_use_coupons")
    private Integer isUserCoupons;

    @Column(name = "result_status")
    private Integer resultStatus;

    @Column(name = "pay_money")
    private Double payMoney;

    @Column(name = "pay_days")
    private Integer payDays;

    @Column(name = "result")
    private String result;

    @Transient
    private String payResult;

    public String getPayResult() {
        if(payMoney == null || payMoney == 0) {
            return "未缴费";
        }
        payResult = "" + getPayDays() + "天" + ("￥" + getPayMoney());
        return payResult;
    }

    public void setPayResult(String payResult) {
        this.payResult = payResult;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public KUser getUser() {
        return user;
    }

    public void setUser(KUser user) {
        this.user = user;
    }

    public Integer getIsUserCoupons() {
        return isUserCoupons;
    }

    public void setIsUserCoupons(Integer isUserCoupons) {
        this.isUserCoupons = isUserCoupons;
    }

    public Integer getResultStatus() {
        return resultStatus;
    }

    public void setResultStatus(Integer resultStatus) {
        this.resultStatus = resultStatus;
    }

    public Double getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(Double payMoney) {
        this.payMoney = payMoney;
    }

    public Integer getPayDays() {
        return payDays;
    }

    public void setPayDays(Integer payDays) {
        this.payDays = payDays;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
