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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "is_use_coupons")
    private Integer isUserCoupons;

    @Column(name = "is_get_coupons")
    private Integer isGetCoupons;

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

    public Integer getIsGetCoupons() {
        return isGetCoupons;
    }

    public void setIsGetCoupons(Integer isGetCoupons) {
        this.isGetCoupons = isGetCoupons;
    }

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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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
