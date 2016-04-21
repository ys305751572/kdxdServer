package com.leoman.entity;

import javax.persistence.*;

/**
 * Created by Administrator on 2016/4/21.
 */
@Entity
@Table(name = "tb_salesman_record")
public class SalemanRecord extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "saleman_id")
    private Saleman saleman;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private KUser kuser;

    @Column(name = "content")
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Saleman getSaleman() {
        return saleman;
    }

    public void setSaleman(Saleman saleman) {
        this.saleman = saleman;
    }

    public KUser getKuser() {
        return kuser;
    }

    public void setKuser(KUser kuser) {
        this.kuser = kuser;
    }
}
