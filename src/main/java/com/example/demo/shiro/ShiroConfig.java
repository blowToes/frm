package com.example.demo.shiro;

import com.example.demo.security.entity.TsPermissions;
import com.example.demo.security.service.ITsPermissionsService;
import com.example.demo.security.service.ITsUserService;
import com.google.common.cache.CacheBuilder;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@Configuration
public class ShiroConfig {

    @Autowired
    private ITsPermissionsService permissionsService;


    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 必须设置SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // url拦截 跳转和拦截器的设置
        urlFilterMethod(shiroFilterFactoryBean);
        return shiroFilterFactoryBean;
    }

    private void urlFilterMethod(ShiroFilterFactoryBean shiroFilterFactoryBean) {
        Map<String, Filter> mapFilter = new HashMap<>();
        mapFilter.put("authc", tokenFilter());
//        shiroFilterFactoryBean.setFilters(mapFilter);
        //设置登录连接
        shiroFilterFactoryBean.setLoginUrl("/notLogin");
        // 设置无权限跳转连接
        shiroFilterFactoryBean.setUnauthorizedUrl("/notRole");
        // 设置拦截器

        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        filterChainDefinitionMap.put("/query/users","perms[管理员权限]");
        // 游客开发权限
        filterChainDefinitionMap.put("/guest/**", "anon");
        //开放登陆接口
        filterChainDefinitionMap.put("/user/**", "anon");

        // 自定义权限连
        custormPermission(filterChainDefinitionMap);
        // 其余的一切端口都需要拦截
        filterChainDefinitionMap.put("/api/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
    }

    private void custormPermission(LinkedHashMap<String, String> filterChainDefinitionMap) {
        // 获取指定缓存
//        CacheBuilder<Object, Object> cacheBuilder = CacheBuilder.newBuilder()
//                .expireAfterAccess(30, TimeUnit.DAYS);
        //获取所有权限限制的URL
        List<TsPermissions> list = permissionsService.list();
        list.forEach(tsPermissions -> {
            filterChainDefinitionMap.put(tsPermissions.getPermissionUrl(), "perms[" + tsPermissions.getPermissionName() + "]");
        });
    }

    // 设置SecurityManager
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(customRealm());
        return securityManager;
    }

    // 自定义认证的Realm
    @Bean
    public ShiroRealm customRealm() {
        ShiroRealm shiroRealm = new ShiroRealm();
        shiroRealm.setCredentialsMatcher(credentialsMatcher());
        return shiroRealm;
    }

    @Bean
    public TokenFilter tokenFilter() {
        return new TokenFilter();
    }

    @Bean
    public CredentialsMatcher credentialsMatcher() {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("md5");
        matcher.setHashIterations(1024);
        return matcher;
    }
}
