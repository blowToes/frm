package com.example.demo.shiro;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;


@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 必须设置SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //设置登录连接
        shiroFilterFactoryBean.setLoginUrl("/notLogin");
        // 设置无权限跳转连接
        shiroFilterFactoryBean.setUnauthorizedUrl("/notRole");

        // 设置拦截器
        Map<String,String> filterChainDefinitionMap = new HashMap<String,String>();
        // 游客开发权限
        filterChainDefinitionMap.put("/guest/**","anon");
        // 用户 需要user的权限
        filterChainDefinitionMap.put("/user/**","roles[user]");
        // 管理员配置
        filterChainDefinitionMap.put("/admin/**","roles[admin]");
        //开放登陆接口
        filterChainDefinitionMap.put("/login","anon");

        // 其余的一切端口都需要拦截
        filterChainDefinitionMap.put("/api/**","authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }


    // 设置SecurityManager
    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(customRealm());
        return securityManager;
    }



    // 自定义认证的Realm
    @Bean
    public CustomRealm customRealm(){
        return new CustomRealm();
    }
}
