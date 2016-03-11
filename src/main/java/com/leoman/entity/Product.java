package com.leoman.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wangbin on 2015/8/10.
 */
@Entity
@Table(name = "tb_product")
public class Product extends BaseEntity {

    @Column(name = "title")
    private String title;

    @Column(name = "counts")
    private Integer counts;

    @Column(name = "coupons_counts")
    private Integer couponsCounts;

    @Column(name = "content")
    private String content;

    @Column(name = "status")
    private Integer status ;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private List<ProductService> serviceList;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tb_product_image",joinColumns = {@JoinColumn(name = "product_id",referencedColumnName = "id")},
    inverseJoinColumns = {@JoinColumn(name = "image_id",referencedColumnName = "id")})
    private List<Image> list;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<ProductService> getServiceList() {
        return serviceList;
    }

    public void setServiceList(List<ProductService> serviceList) {
        this.serviceList = serviceList;
    }

    public List<Image> getList() {
        return list;
    }

    public void setList(List<Image> list) {
        this.list = list;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCounts() {
        return counts;
    }

    public void setCounts(Integer counts) {
        this.counts = counts;
    }

    public Integer getCouponsCounts() {
        return couponsCounts;
    }

    public void setCouponsCounts(Integer couponsCounts) {
        this.couponsCounts = couponsCounts;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
