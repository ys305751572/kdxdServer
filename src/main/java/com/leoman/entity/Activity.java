package com.leoman.entity;

import javax.persistence.*;

/**
 * Created by Administrator on 2016/3/10.
 */
@Entity
@Table(name = "tb_activity")
public class Activity extends BaseEntity{

    @Column(name = "title")
    private String title;

    @OneToOne
    @JoinColumn(name = "image_id")
    private Image image;

    @Column(name = "content")
    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
