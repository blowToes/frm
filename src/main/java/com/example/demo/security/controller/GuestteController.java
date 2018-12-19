package com.example.demo.security.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName GuestteController
 * @Description TO_DO
 * @Author ZHAAIKAIXUAN
 * @Date 2018/12/18 14:43
 * @Version 1.0
 */
@Api(description = "游客请求")
@RestController
@RequestMapping("/api/guest")
public class GuestteController {

    @ApiOperation("游客去测试")
    @GetMapping("/test/{str}")
    public String guest(@PathVariable String str) {
        return "游客输入内容：" + str;
    }
}
