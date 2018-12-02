package com.example.demo.sys.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("登陆控制器")
@RestController
public class LoginController {



    @ApiOperation("未登录的资源跳转")
    @GetMapping("/notLogin")
    public String notLogin(){
        return "您尚未登陆";
    }


    @ApiOperation("/notRole")
    @GetMapping("/nnotRole")
    public String notRole(){
        return "您没有权限";
    }


    @ApiOperation("注销")
    @GetMapping("/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "成功注销";
    }

    @ApiOperation("登陆页面")
    @PostMapping("/login")
    public String login(String username,String password){

        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        return "欢迎登陆";
    }


}
