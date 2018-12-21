package com.example.demo.common;

import lombok.Data;

/**
 * @ClassName ResponseMessageEntity
 * @Description TO_DO
 * @Author ZHAAIKAIXUAN
 * @Date 2018/12/19 11:20
 * @Version 1.0
 */
@Data
public class ResponseMessageEntity<T> {
    /**
     * 用户令牌
     */
    String token;


    /**
     * 响应吗
     */
    String code;

    /**
     * 消息
     */
    String message;

    /**
     * 数据
     */
    T data;

    public ResponseMessageEntity() {
    }

    public ResponseMessageEntity(String token, String code, String message) {
        this.token = token;
        this.code = code;
        this.message = message;
    }

    public ResponseMessageEntity(String token, String code, String message, T data) {
        this.token = token;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResponseMessageEntity(T data) {
        this.data = data;
    }
}
