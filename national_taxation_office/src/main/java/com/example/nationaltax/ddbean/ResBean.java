package com.example.nationaltax.ddbean;

public class ResBean {
    private int statusCode;
    private String msg;
    private Object data;

    public ResBean() {
    }

    public ResBean(int statusCode, String msg) {
        this.statusCode = statusCode;
        this.msg = msg;
    
    }

    public ResBean(int statusCode, String msg, Object data) {
        this.statusCode = statusCode;
        this.msg = msg;
        this.data = data;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    
}
