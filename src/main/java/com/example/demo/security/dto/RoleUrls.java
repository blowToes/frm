package com.example.demo.security.dto;

import com.example.demo.security.entity.TsPermissions;
import lombok.Data;

import java.util.List;

/**
 * @ClassName RoleUrls
 * @Description TO_DO
 * @Author ZHAAIKAIXUAN
 * @Date 2018/12/21 11:54
 * @Version 1.0
 */
@Data
public class RoleUrls {


    String id;

    /**
     * 角色名
     */
    String role_name;

    /**
     * 资源名
     */
    List<TsPermissions> permissions;

}
