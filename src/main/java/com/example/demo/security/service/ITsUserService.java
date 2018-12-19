package com.example.demo.security.service;

import com.example.demo.security.dto.RolePermissions;
import com.example.demo.security.entity.TsUser;
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
public interface ITsUserService extends IService<TsUser> {

    TsUser register(TsUser tsUser);

    List<RolePermissions> queryRolePermissions(String username);
}
