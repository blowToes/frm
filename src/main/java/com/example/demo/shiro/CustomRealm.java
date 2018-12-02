package com.example.demo.shiro;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.sys.entity.Role;
import com.example.demo.sys.entity.User;
import com.example.demo.sys.mapper.RoleMapper;
import com.example.demo.sys.mapper.UserMapper;
import com.example.demo.sys.mapper.UserRoleMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CustomRealm extends AuthorizingRealm {


    @Autowired
    private UserMapper userMapper;


    /**
     * 权限认证
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        String username = (String)SecurityUtils.getSubject().getPrincipal();
        // 获取该用户角色
        List<Role> roles = userMapper.queryRoleByUserName(username);
        Set<String> set = new HashSet<String>();
        for(Role role : roles){
            set.add(role.getRoleName());
        }
        info.setRoles(set);
        return info;
    }


    /**
     * 获取身份验证信息
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        User userQuery = new User();
        userQuery.setName(token.getUsername());

        QueryWrapper<User> queryWrapper = new QueryWrapper<User>(userQuery);
        User user = userMapper.selectOne(queryWrapper);
        if(null == user.getPassword()){
            throw new AccountException("密码不能为空！");
        }
        if(!user.getPassword().equals(new String((char[]) token.getCredentials()))){
            throw new AccountException("密码或密码不正确！");
        }

        return new SimpleAuthenticationInfo(token.getPrincipal(),user.getPassword(),user.getName());
    }
}
