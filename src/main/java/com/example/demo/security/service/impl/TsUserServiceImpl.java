package com.example.demo.security.service.impl;

import com.example.demo.security.dto.RolePermissions;
import com.example.demo.security.entity.TsUser;
import com.example.demo.security.mapper.TsUserMapper;
import com.example.demo.security.service.ITsUserService;
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
public class TsUserServiceImpl extends ServiceImpl<TsUserMapper, TsUser> implements ITsUserService {

    @Autowired
    private TsUserMapper userMapper;

    @Override
    public List<RolePermissions> queryRolePermissions(String username) {
        return userMapper.queryRolePermissionsByUserName(username);
    }
}
