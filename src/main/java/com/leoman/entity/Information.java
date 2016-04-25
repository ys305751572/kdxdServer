package com.leoman.entity;

import javax.persistence.*;

/**
 * Created by Administrator on 2016/3/9.
 */
@Entity
@Table(name = "tb_information")
public class Information extends BaseEntity{

    @Column(name = "title")
    private String title;

    @Column(name = "introduction")
    private String introduction;

    @Column(name = "content")
    private String content;

    @Column(name =  "is_list")
    private Integer isList;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "image_id")
    private Image image;

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getIsList() {
        return isList;
    }

    public void setIsList(Integer isList) {
        this.isList = isList;
    }
}
