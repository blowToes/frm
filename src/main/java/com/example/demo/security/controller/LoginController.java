package com.example.demo.security.controller;

import com.example.demo.common.ResponseMessageEntity;
import com.example.demo.security.entity.TsUser;
import com.example.demo.security.message.MessageUtil;
import com.example.demo.security.service.ITsUserService;
import com.example.demo.utils.Results;
import com.example.demo.utils.UUIDGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private ITsUserService iTsUserService;

    @ApiOperation("登陆接口")
    @GetMapping("/user")
    public ResponseMessageEntity<String> loginAccount(String account, String pwd) {
        UsernamePasswordToken token = new UsernamePasswordToken(account, pwd);
        Subject subject = SecurityUtils.getSubject();
        String ticket = "";
        try {
            subject.login(token);
            TsUser user = iTsUserService.queryUserByWrraper(account);
            Session session = subject.getSession();
            ticket = UUIDGenerator.generatorKey_32();
            session.setAttribute(ticket, user);
            session.setTimeout(30000);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return Results.error();
        }
        new ResponseMessageEntity<String>(ticket, "200", MessageUtil.LOGIN_SUCCESS);
        return Results.success(MessageUtil.LOGIN_SUCCESS);
    }

    @ApiOperation("注销")
    @GetMapping("/logout")
    public ResponseMessageEntity<String> logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return Results.success(MessageUtil.LOGOUT_SUCCESS);
    }

    @ApiOperation("注册")
    @PostMapping(value = "/register")
    public ResponseMessageEntity<String> register(@RequestBody TsUser user) {

        iTsUserService.save(user);
        iTsUserService.register(user);
        return Results.success(MessageUtil.REGISTER_SUCCESS);
    }

}
