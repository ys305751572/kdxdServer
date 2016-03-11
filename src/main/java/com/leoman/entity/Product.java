package com.leoman.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

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

    @Column(name = "start_date")
    private Long startDate;

    @Column(name = "service_start_date")
    private Long serviceStartDate;

    @OneToOne
    @JoinColumn(name = "cover_image_id")
    private Image coverImage; // 封面图片

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Set<ProductService> serviceList;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tb_product_image",joinColumns = {@JoinColumn(name = "product_id",referencedColumnName = "id")},
    inverseJoinColumns = {@JoinColumn(name = "image_id",referencedColumnName = "id")})
    private Set<Image> list;

    public Set<ProductService> getServiceList() {
        return serviceList;
    }

    public Set<Image> getList() {
        return list;
    }

    public Image getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(Image coverImage) {
        this.coverImage = coverImage;
    }

    public void setServiceList(Set<ProductService> serviceList) {
        this.serviceList = serviceList;
    }

    public void setList(Set<Image> list) {
        this.list = list;
    }

    public Long getStartDate() {
        return startDate;
    }

    public void setStartDate(Long startDate) {
        this.startDate = startDate;
    }

    public Long getServiceStartDate() {
        return serviceStartDate;
    }

    public void setServiceStartDate(Long serviceStartDate) {
        this.serviceStartDate = serviceStartDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
