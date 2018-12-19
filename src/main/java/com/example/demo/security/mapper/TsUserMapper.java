package com.example.demo.security.mapper;

import com.example.demo.security.dto.RolePermissions;
import com.example.demo.security.entity.TsUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2018-12-17
 */
public interface TsUserMapper extends BaseMapper<TsUser> {

        List<RolePermissions> queryRolePermissionsByUserName(String username);
}
