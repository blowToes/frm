package com.example.demo.security.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.security.dto.RoleUrls;
import com.example.demo.security.dto.UrlRoles;
import com.example.demo.security.entity.TsPermissions;
import com.example.demo.security.entity.TsUser;
import com.example.demo.security.service.ITsRoleService;
import com.example.demo.security.service.ITsUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2018-12-17
 */
@RestController
@RequestMapping("/api/security/ts-user")
@Api(description = "用户管理")
public class TsUserController {

    @Autowired
    private ITsUserService iTsUserService;

    @Autowired
    private ITsRoleService roleService;


    @ApiOperation("查询所有的用户列表")
//    @RequiresPermissions("")
    @GetMapping("/query/users")
    public List<TsUser> queryUserList() {
        roleService.queryUrlRoles();
        return iTsUserService.list();
    }

    @ApiOperation("查询所有的用户列表")
    @PostMapping(value = "/query/page/users")
    public IPage<TsUser> queryUserListPage(TsPermissions tsPermissions) {
        IPage<TsUser> page = new Page<TsUser>();
        page.setCurrent(1);
        page.setSize(10);
        IPage<TsUser> pageUser = iTsUserService.page(page);
        return pageUser;
    }

    @ApiOperation("查询单个信息")
    @GetMapping("/query/user")
    public List<TsUser> queryUser(@RequestParam String account, @RequestParam String sex) {

        TsUser user = new TsUser();
        user.setAccount(account);
        user.setSex(sex);
        QueryWrapper wrapper = new QueryWrapper(user);
        return iTsUserService.list(wrapper);
    }


}
