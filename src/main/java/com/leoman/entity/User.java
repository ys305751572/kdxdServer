package com.leoman.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by wangbin on 2015/9/1.
 */
@Entity
@Table(name = "user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;


    @OneToOne
    @JoinColumn(name = "wxuser_id")
    private WxUser wxUser;

    public WxUser getWxUser() {
        return wxUser;
    }

    public void setWxUser(WxUser wxUser) {
        this.wxUser = wxUser;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
