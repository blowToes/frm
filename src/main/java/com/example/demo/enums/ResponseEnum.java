package com.example.demo.enums;


import lombok.Data;

public enum ResponseEnum {

    SUCCESS("200","请求成功！","SUCCESS"),
    ERROR("400","系统内部错误！","ERROR");

    String code;
    String msg;
    String status;

    ResponseEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    ResponseEnum(String code, String msg, String status) {
        this.code = code;
        this.msg = msg;
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
