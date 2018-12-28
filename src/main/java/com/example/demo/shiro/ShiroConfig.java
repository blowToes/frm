package com.example.demo.shiro;

import com.example.demo.security.dto.UrlRoles;
import com.example.demo.security.entity.TsPermissions;
import com.example.demo.security.entity.TsRole;
import com.example.demo.security.service.ITsPermissionsService;
import com.example.demo.security.service.ITsRoleService;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class ShiroConfig {
    private final static Logger LOGGER = LoggerFactory.getLogger(ShiroConfig.class);
    @Autowired
    private ITsPermissionsService permissionsService;
    @Autowired
    private ITsRoleService roleService;

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
        // 自定义拦截器的配置
        Map<String, Filter> mapFilter = new LinkedHashMap <>();
        mapFilter.put("ticket", tokenFilter());
        shiroFilterFactoryBean.setFilters(mapFilter);
        //设置登录连接
        shiroFilterFactoryBean.setLoginUrl("/notLogin");
        // 设置无权限跳转连接
        shiroFilterFactoryBean.setUnauthorizedUrl("/notRole");
        // 设置拦截器
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        // 游客开发权限
        filterChainDefinitionMap.put("/guest/**", "anon");
        // 添加角色权限
        customeRoles(filterChainDefinitionMap);
        // 自定义权限连
        custormPermission(filterChainDefinitionMap);

        // 其余的一切端口都需要拦截
        filterChainDefinitionMap.put("/api/**", "ticket");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

    }

    private void customeRoles(LinkedHashMap<String, String> filterChainDefinitionMap) {
        List<UrlRoles> urlRoles = roleService.queryUrlRoles();
        urlRoles.forEach(urlRole -> {
            //拼接roles的字符串
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("roles[");
            List<TsRole> roles = urlRole.getRoles();
            for (int i = 0; i < roles.size(); i++) {
                stringBuffer.append(roles.get(i).getRoleName());
                if (i != (roles.size() - 1)) stringBuffer.append(",");
            }
            stringBuffer.append("]");
            filterChainDefinitionMap.put(urlRole.getPermissionUrl(), stringBuffer.toString());
            LOGGER.info("roles = [{}]", stringBuffer.toString());
        });
    }

    private void custormPermission(LinkedHashMap<String, String> filterChainDefinitionMap) {
        //获取所有权限限制的URL
        List<TsPermissions> list = permissionsService.list();
        list.forEach(tsPermissions -> {
                    filterChainDefinitionMap.put(tsPermissions.getPermissionUrl(), "perms[" + tsPermissions.getPermissionName() + ":*]");
                }
        );
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
