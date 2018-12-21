package com.example.demo.security.dto;

import com.example.demo.security.entity.TsRole;
import lombok.Data;

import java.util.List;

/**
 * @ClassName UrlRoles
 * @Description TO_DO
 * @Author ZHAAIKAIXUAN
 * @Date 2018/12/21 13:15
 * @Version 1.0
 */
@Data
public class UrlRoles {
    String permissionName;
    String permissionUrl;
    List<TsRole> roles;
}
