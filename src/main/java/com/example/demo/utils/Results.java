package com.example.demo.utils;

import com.example.demo.common.ResponseMessageEntity;
import com.example.demo.enums.ResponseEnum;

/**
 * @ClassName Results
 * @Description TO_DO
 * @Author ZHAAIKAIXUAN
 * @Date 2018/12/19 11:22
 * @Version 1.0
 */
public class Results {
    // 成功返回
    public static ResponseMessageEntity success() {
        return new ResponseMessageEntity(ResponseEnum.SUCCESS.getCode(), ResponseEnum.SUCCESS.getMsg(), ResponseEnum.SUCCESS.getStatus());
    }

    public static ResponseMessageEntity success(String msg) {
        return new ResponseMessageEntity(ResponseEnum.SUCCESS.getCode(), msg, ResponseEnum.SUCCESS.getStatus());
    }

    public static ResponseMessageEntity success(String code,String msg) {
        return new ResponseMessageEntity(code, msg, ResponseEnum.SUCCESS.getStatus());
    }


    // 失败返回
    public static ResponseMessageEntity error() {
        return new ResponseMessageEntity(ResponseEnum.ERROR.getCode(), ResponseEnum.ERROR.getMsg(), ResponseEnum.ERROR.getStatus());
    }
    public static ResponseMessageEntity error(String msg) {
        return new ResponseMessageEntity(ResponseEnum.ERROR.getCode(), msg, ResponseEnum.ERROR.getStatus());
    }
}
