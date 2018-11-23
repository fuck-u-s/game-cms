package com.cms.web.shiro;


import com.cms.model.sys.AdminUser;
import com.cms.utils.Constants;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 方法描述:绑定登录用户信息
 * <p>
 * author LZH
 * version v1.0
 * date 2016/6/13 10:31
 */
public class SysUserFilter extends AccessControlFilter {
	
    /**用户删除了后重定向的地址*/
    private String userDeletedUrl;
    
    /**用户锁定后重定向的地址*/
    private String userLockedUrl;
    
    /**未知错误*/
    private String userUnknownErrorUrl;

	@Override
	protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject = getSubject(request, response);
        if (subject == null) {
            return true;
        }
        //String username = (String) subject.getPrincipal();
        // 此处注意缓存 防止大量的查询db
        //SysUser user = sysUserService.findByUsername(username);

        AdminUser user = (AdminUser)subject.getPrincipal();

        // 把当前用户放到session中
        request.setAttribute(Constants.CURRENT_USER, user);
        //druid监控需要
        //((HttpServletRequest)request).getSession().setAttribute(Constants.CURRENT_USERNAME, username);
        return true;
	}

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object object) throws Exception {
        AdminUser user = (AdminUser) request.getAttribute(Constants.CURRENT_USER);
        if (user == null) {
            return true;
        }
        return true;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		 getSubject(request, response).logout();
	     redirectToLogin(request, response);
	     return true;
	}
}
