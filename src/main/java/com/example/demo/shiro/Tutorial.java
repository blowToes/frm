package com.example.demo.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Tutorial {

    private final static Logger logger = LoggerFactory.getLogger(Tutorial.class);

    public static void main(String[] args) {
        logger.info("My First Apache Shiro Application");
//        1 启动shiro
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro/shiro.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);


        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        session.setAttribute("someKey", "aValue");


        try {
            UsernamePasswordToken token = new UsernamePasswordToken("lonestarr", "vespa");
            token.setRememberMe(true);
            subject.login(token);
        } catch (UnknownAccountException uae) {
            uae.printStackTrace();
        } catch (IncorrectCredentialsException ice) {
            ice.printStackTrace();
        } catch (LockedAccountException lae) {
            lae.printStackTrace();
        }
        System.exit(0);
    }
}
