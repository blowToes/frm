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
import java.io.IOException;

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
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String ticket = request.getHeader("ticket");
        Subject subject = SecurityUtils.getSubject();
        //先判断是否是登陆请求
        if(isLoginRequest(servletRequest,servletResponse)){
            return true;
        }else{
            Session session = subject.getSession();
            // 获取令牌
           if(ticket != null){
               if(null != session.getAttribute(ticket)){
                   return true;
               }else{
                   throw new AuthenticationException("登陆失效，请重新登陆！");
               }
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

    @Override
    protected void saveRequestAndRedirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
        super.saveRequestAndRedirectToLogin(request, response);
    }
}
