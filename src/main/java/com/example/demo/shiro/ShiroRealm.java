package com.example.demo.shiro;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.security.dto.RolePermissions;
import com.example.demo.security.entity.TsUser;
import com.example.demo.security.service.ITsUserService;
import com.google.common.collect.Sets;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

/**
 * @ClassName ShiroRealm
 * @Description TO_DO
 * @Author ZHAAIKAIXUAN
 * @Date 2018/12/18 9:50
 * @Version 1.0
 */
public class ShiroRealm extends AuthorizingRealm {
    private final static Logger LOGGER = LoggerFactory.getLogger(ShiroRealm.class);


    @Autowired
    private ITsUserService iTsUserService;


    /**
     * 授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 动态加载角色和权限
        LOGGER.info("授权方法 start = {}", "AuthorizationInfo");
        // 获取用户名对应用户名下的的角色和权限
        String username = (String) principalCollection.getPrimaryPrincipal();
        // 将角色和权限添加到SimpleAuthorizationInfo  中
        SimpleAuthorizationInfo authorizationInfo = getSimpleAuthorizationInfo(username);
        // 添加权限
        LOGGER.info("授权方法 end = {}", "AuthorizationInfo");
        return authorizationInfo;
    }


    /**
     * 认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        LOGGER.info(" ============ 认证 doGetAuthenticationInfo ====================");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        TsUser user = new TsUser();
        user.setAccount(username);
        QueryWrapper wrapper = new QueryWrapper(user);
        TsUser dataUser = iTsUserService.getOne(wrapper);
        if (null == token.getPassword()) throw new AccountException("密码不能为空");
        LOGGER.info(" ============ 认证 doGetAuthenticationInfo ====================");
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(dataUser.getAccount(), dataUser.getPwdword(), ByteSource.Util.bytes(dataUser.getAccount()), getName());
        return simpleAuthenticationInfo;
    }

    /**
     * 将角色和权限添加到SimpleAuthorizationInfo中
     *
     * @param username
     * @return
     */
    private SimpleAuthorizationInfo getSimpleAuthorizationInfo(String username) {
        LOGGER.info(" =================授权 getSimpleAuthorizationInfo start======================");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        // 添加角色
        Set<String> roles = Sets.newHashSet();
        Set<String> permissions = Sets.newHashSet();
        List<RolePermissions> rolePermissions = iTsUserService.queryRolePermissions(username);
        rolePermissions.forEach(rolePermission -> {
            roles.add(rolePermission.getRole_name());
            permissions.add(rolePermission.getPermission_name());
        });
        authorizationInfo.setRoles(roles);
        authorizationInfo.setStringPermissions(permissions);
        LOGGER.info(" =================授权 getSimpleAuthorizationInfo end======================");
        return authorizationInfo;
    }
}
