package com.leoman.core.bean;

import java.io.Serializable;

/**
 * Created by wangbin on 14-10-17.
 */
public class Result implements Serializable {

    private String status;

    private String msg ="";


    private Object data = new Object();


    public Result(String status) {
        this.status = status;
    }

    public Result(Boolean success) {
        if(success){
            this.status = "0";
        }else{
            this.status = "1";
        }
    }


    public Result msg(String msg) {
        this.msg = msg;
        return this;
    }


    public Result data(Object data) {
        this.data = data;
        return this;
    }


    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
