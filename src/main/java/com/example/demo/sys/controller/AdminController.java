package com.example.demo.sys.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("管理员信息管理器")
@RestController
@RequestMapping("/admin")
public class AdminController {

    @ApiOperation("管理员资源测试")
    @GetMapping("/enter")
    public String adminPage(){
        return "管理员资源测试";
    }
}
