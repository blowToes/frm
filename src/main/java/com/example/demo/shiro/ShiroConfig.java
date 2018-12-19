package com.example.demo.shiro;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


@Configuration
public class ShiroConfig {

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
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        // 游客开发权限
        filterChainDefinitionMap.put("/api/guest/**", "anon");
        //开放登陆接口
        filterChainDefinitionMap.put("/api/guest/**", "anon");

        // 其余的一切端口都需要拦截
        filterChainDefinitionMap.put("/api/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
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
        return new ShiroRealm();
    }


    @Bean
    public TokenFilter tokenFilter() {
        return new TokenFilter();
    }
}
