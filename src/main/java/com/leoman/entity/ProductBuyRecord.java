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

    @Column(name = "coupons_end_date")
    private Long couponsEndDate;

    @Column(name = "result_status")
    private Integer resultStatus;

    @Column(name = "pay_money")
    private Double payMoney;

    @Column(name = "pay_days")
    private Integer payDays;

    @Column(name = "result")
    private String result;

    @Column(name = "reset")
    private Integer reset = 0;

    @Transient
    private String payResult;

    public Integer getReset() {
        return reset;
    }

    public void setReset(Integer reset) {
        this.reset = reset;
    }

    public String getPayResult() {
        return payResult;
    }

    public void setPayResult(String payResult) {
        this.payResult = payResult;
    }

    public Long getCouponsEndDate() {
        return couponsEndDate;
    }

    public void setCouponsEndDate(Long couponsEndDate) {
        this.couponsEndDate = couponsEndDate;
    }

    public Integer getIsGetCoupons() {
        return isGetCoupons;
    }

    public void setIsGetCoupons(Integer isGetCoupons) {
        this.isGetCoupons = isGetCoupons;
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
