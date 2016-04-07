package com.leoman.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Created by Administrator on 2016/3/11.
 */
@Entity
@Table(name = "tb_product_image")
public class ProductImage extends BaseEntity{

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "image_id")
    private Integer imageId;

    private String path;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    @Transient
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
