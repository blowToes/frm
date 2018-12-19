package com.example.demo.security.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName LoginController
 * @Description TO_DO
 * @Author ZHAAIKAIXUAN
 * @Date 2018/12/18 10:21
 * @Version 1.0
 */
@Api(description = "登陆模块")
@RestController
@RequestMapping("/login")
public class LoginController {
    @ApiOperation("登陆接口")
    @GetMapping("/user")
    public String loginAccount(String account,String pwd){
        UsernamePasswordToken token = new UsernamePasswordToken(account, pwd);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
        return "登陆成功";
    }

}
