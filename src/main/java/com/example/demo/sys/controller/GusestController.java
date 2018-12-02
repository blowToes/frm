package com.example.demo.sys.controller;


import com.example.demo.sys.entity.User;
import com.example.demo.sys.mapper.UserMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api("游客登陆接口")
@RestController
@RequestMapping("/guest")
public class GusestController {

    @Autowired
    private UserMapper userMapper;

    @ApiOperation("游客进入 页面")
    @GetMapping("/enter")
    public String enterGuestPage(){
        return "游客进入页面";
    }



    @ApiOperation("测试")
    @GetMapping("test")
    public List<User> queryUser(){
        return userMapper.queryUserList();
    }

}
