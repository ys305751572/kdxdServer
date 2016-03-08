package com.leoman.core.bean;

import java.io.Serializable;

/**
 * Created by wangbin on 2015/4/1.
 */
public class Response implements Serializable {

    private int statusCode;

    private Boolean status;

    private String body;

    private String error;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String content) {
        this.body = content;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
