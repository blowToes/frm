package com.example.demo.common;


import lombok.Data;

@Data
public class BaseEntity<T> {

    /**
     * 用户令牌
     */
    String token;

    T t;

}
