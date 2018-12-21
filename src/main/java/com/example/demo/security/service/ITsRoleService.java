package com.example.demo.security.service;

import com.example.demo.security.dto.RoleUrls;
import com.example.demo.security.dto.UrlRoles;
import com.example.demo.security.entity.TsRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2018-12-17
 */
public interface ITsRoleService extends IService<TsRole> {
    List<RoleUrls> queryRoleUrls();

    List<UrlRoles> queryUrlRoles();
}
