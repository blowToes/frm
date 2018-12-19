package com.example.demo.utils;

import java.util.UUID;

/**
 * @ClassName UUIDGenerator
 * @Description TO_DO
 * @Author ZHAAIKAIXUAN
 * @Date 2018/12/19 10:50
 * @Version 1.0
 */
public class UUIDGenerator {

    // 生成32的主键
    public static String generatorKey_32(){
        return UUID.randomUUID().toString().replace("-","");
    }

}
