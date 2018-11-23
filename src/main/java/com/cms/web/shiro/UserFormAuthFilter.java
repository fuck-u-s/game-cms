package com.cms.web.shiro;

import com.alibaba.druid.util.StringUtils;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 基于几点修改：
 * 1、onLoginFailure 时 把异常添加到request attribute中 而不是异常类名
 * 2、登录成功时：成功页面重定向：
 * 2.1、如果前一个页面是登录页面，-->2.3
 * 2.2、如果有SavedRequest 则返回到SavedRequest
 * 2.3、否则根据当前登录的用户决定返回到管理员首页/前台首页
 * @author hanchaoyong
 */
public class UserFormAuthFilter extends FormAuthenticationFilter {

    /**
     * 重写认证，再验证授权信息前先添加验证码功能
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpSession session = httpServletRequest.getSession();
        //获取session中的验证码
        String validateCode = (String) session.getAttribute("captcha");
        //取出页面的验证码
        String captcha = httpServletRequest.getParameter("captchaCode");

        if(!StringUtils.isEmpty(captcha)) {
            if(StringUtils.isEmpty(validateCode) || !validateCode.equals(captcha)) {
                httpServletRequest.setAttribute("shiroLoginFailure", "captchaError");
                //拒绝访问，不再校验账号和密码
                return true;
            }
        }

    	//如果request里已经存在错误信息了，直接返回不再继续做验证
        if(request.getAttribute("shiroLoginFailure") != null) {
            return true;
        }
        return super.onAccessDenied(request, response);
    }
}
