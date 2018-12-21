package com.example.demo.security.service.impl;

import com.example.demo.security.dto.RoleUrls;
import com.example.demo.security.dto.UrlRoles;
import com.example.demo.security.entity.TsRole;
import com.example.demo.security.mapper.TsRoleMapper;
import com.example.demo.security.service.ITsRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2018-12-17
 */
@Service
public class TsRoleServiceImpl extends ServiceImpl<TsRoleMapper, TsRole> implements ITsRoleService {


    @Autowired
    private TsRoleMapper roleMapper;

    @Override
    public List<RoleUrls> queryRoleUrls() {
        return roleMapper.queryRoleUrls();
    }

    @Override
    public List<UrlRoles> queryUrlRoles() {
        return roleMapper.queryUrlRoles();
    }
}
