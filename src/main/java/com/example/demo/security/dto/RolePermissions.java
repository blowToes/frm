package com.example.demo.security.dto;

import lombok.Data;

/**
 * @ClassName RolePermissions
 * @Description TO_DO
 * @Author ZHAAIKAIXUAN
 * @Date 2018/12/18 14:25
 * @Version 1.0
 */
@Data
public class RolePermissions {
    // 用户名
    private String account;
    // 用户昵称
    private String nick_name;
    // 角色名
    private String role_name;
    // 权限名
    private String permission_name;
    // 权限url资源定义
    private String permission_url;
}
