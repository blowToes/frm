package com.example.demo.shiro;

import com.example.demo.security.entity.TsUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class TokenFilter extends AccessControlFilter {

    private final static Logger logger = LoggerFactory.getLogger(TokenFilter.class);

    /**
     * 是否允许访问
     *
     * @param servletRequest
     * @param servletResponse
     * @param o
     * @return
     * @throws Exception
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        logger.info("============== TokenFilter 是否允许登陆过滤器 token 令牌验证 ========================");
        Subject subject = SecurityUtils.getSubject();
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        //获取toen 令牌
        String ticket = request.getHeader("ticket");
        //判断是否为空
        if (null != ticket) {
            Session session = SecurityUtils.getSubject().getSession();
            TsUser user = (TsUser) session.getAttribute(ticket);
            if (null == user) {
                return false;
            } else {
                UsernamePasswordToken token = new UsernamePasswordToken(user.getAccount(), user.getPwdword() );
                try {
                    // 判断用户名和 密码是否正确
                    subject.login(token);
                } catch (AuthenticationException e) {
                    return false;
                }
                return true;
            }
        }

        logger.info("============== isAccessAllowed end  ===========================");
        return false;
    }

    /**
     * 当访问拒绝时如何处理
     *
     * @param servletRequest
     * @param servletResponse
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        logger.info("=========onAccessDenied 当访问拒绝时如何处理=============");

        logger.info("=========onAccessDenied end=============");
        return false;
    }


    @Override
    public boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        logger.info("============== onPreHandle ===================");
        return isAccessAllowed(request, response, mappedValue) || onAccessDenied(request, response, mappedValue);
    }


    @Override
    protected boolean isLoginRequest(ServletRequest request, ServletResponse response) {
        return super.isLoginRequest(request, response);
    }
}
