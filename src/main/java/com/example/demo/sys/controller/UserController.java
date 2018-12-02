package com.example.demo.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.sys.entity.PageQuery;
import com.example.demo.sys.entity.User;
import com.example.demo.sys.mapper.UserMapper;
import com.example.demo.sys.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2018-12-01
 */
@RestController
@RequestMapping("/user")
@Api("用户测试类")
public class UserController {


    @Autowired
    private IUserService iUserService;

    @Autowired
    private UserMapper userMapper;


    @ApiOperation("查询所有用户接口")
    @PostMapping("/test")
    public List<User> queryUser() {


        return iUserService.list();

    }


    @ApiOperation("分页接口")
    @PostMapping("/page")
    public IPage<User> getUserPage(
            @RequestParam("pageSize") Integer pageSize,
            @RequestParam("currentPage") Integer currentPage
            , @RequestBody User user) {
        Page<User> page = new Page<User>(currentPage, pageSize);
        QueryWrapper<User> wrapper = new QueryWrapper<User>(user);
        return userMapper.selectPage(page, wrapper);
    }





}
