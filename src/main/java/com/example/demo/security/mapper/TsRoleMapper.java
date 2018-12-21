package com.example.demo.security.mapper;

import com.example.demo.security.dto.RoleUrls;
import com.example.demo.security.dto.UrlRoles;
import com.example.demo.security.entity.TsRole;
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
public interface TsRoleMapper extends BaseMapper<TsRole> {

    // 查询所有的角色拦截的URL
    List<RoleUrls> queryRoleUrls();

    List<UrlRoles> queryUrlRoles();


}
