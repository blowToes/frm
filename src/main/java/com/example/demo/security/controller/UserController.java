package com.example.demo.security.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName GuestteController
 * @Description TO_DO
 * @Author ZHAAIKAIXUAN
 * @Date 2018/12/18 14:43
 * @Version 1.0
 */
@Api(description = "普通用户请求")
@RestController
@RequestMapping("/api/user")
public class UserController {

    @ApiOperation("普通用户请求测试")
    @GetMapping("/test/{str}")
    public String guest(@PathVariable String str) {
        return "普通用户输入内容：" + str;
    }
}
