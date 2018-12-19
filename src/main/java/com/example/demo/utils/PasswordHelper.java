package com.example.demo.utils;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName PasswordHelper
 * @Description TO_DO 密码加密工具类
 * @Author ZHAAIKAIXUAN
 * @Date 2018/12/18 13:52
 * @Version 1.0
 */
public class PasswordHelper {
    private final static Logger LOGGER = LoggerFactory.getLogger(PasswordHelper.class);



    private final static String algorithmName = "md5";
    private final static int hashIterations = 1024;


    // 加密
    public static String md5PasswordGenerator(String pwd,String salt){
        Md5Hash md5Hash = new Md5Hash(pwd, salt, hashIterations);
        String encodedPassword  = md5Hash.toHex();
        return encodedPassword ;
    }

    //解密

    public static void main(String[] args) {
        System.out.println(md5PasswordGenerator("admin","admin"));
    }

}
