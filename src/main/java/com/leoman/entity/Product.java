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
    private Image coverImage;

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Set<ProductService> serviceList;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tb_product_image",joinColumns = {@JoinColumn(name = "product_id",referencedColumnName = "id")},
    inverseJoinColumns = {@JoinColumn(name = "image_id",referencedColumnName = "id")})
    private Set<Image> list;

    @Transient
    private Long buyCount = 0L;

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

    public Long getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(Long buyCount) {
        this.buyCount = buyCount;
    }

    public Image getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(Image coverImage) {
        this.coverImage = coverImage;
    }

    public Integer getStatus() {
        if(status != 2) {
            if(System.currentTimeMillis() > getStartDate()) {
                status = 1;
            }
            if(System.currentTimeMillis() <= getStartDate()) {
                status = 0;
            }
            if(getBuyCount() >= getCounts()) {
                status = 2;
            }
        }
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Set<ProductService> getServiceList() {
        return serviceList;
    }

    public void setServiceList(Set<ProductService> serviceList) {
        this.serviceList = serviceList;
    }

    public Set<Image> getList() {
        return list;
    }

    public void setList(Set<Image> list) {
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
