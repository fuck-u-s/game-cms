package com.cms.web.shiro;

import com.cms.model.sys.AdminMenu;
import com.cms.model.sys.AdminRole;
import com.cms.model.sys.AdminUser;
import com.cms.model.sys.LoginLog;
import com.cms.service.sys.AdminMenuService;
import com.cms.service.sys.AdminRoleService;
import com.cms.service.sys.AdminUserService;
import com.cms.service.sys.LogService;
import com.cms.utils.IPUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    private AdminUserService adminUserService;
    @Autowired
    private AdminRoleService adminRoleService;
    @Autowired
    private AdminMenuService adminMenuService;
    @Autowired
    private LogService logService;
	
	/**
	 * shiro.realm分配权限
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 登录用户信息
        AdminUser user = (AdminUser) principals.getPrimaryPrincipal();
        // 获取权限菜单
        List<AdminMenu> menuList = adminMenuService.userAuthMenuList(user.getRole().getId());
        //单独定一个集合对象
        List<String> permissions = new ArrayList<String>();
        if(menuList != null && menuList.size() > 0){
            // 遍历父节点
            for(AdminMenu menu:menuList){
                //将数据库中的权限标签 符放入集合
                permissions.add(menu.getPermission());
            }
        }
        // 异步登陆日志
        Thread t = new Thread(){
            public void run(){
                // 登陆日志
                LoginLog log = new LoginLog();
                String ip = SecurityUtils.getSubject().getSession().getHost();
                log.setUser_id(user.getId());
                log.setIp(ip);
                log.setAreaName(IPUtils.getIpInfo(ip));
                logService.insertLoginLog(log);
            }
        };
        t.start();
        // 认证
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addStringPermissions(permissions);
        return authorizationInfo;
	}

	/**
	 * shiro.realm登录凭证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 用户输入的登录信息
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = upToken.getUsername().trim();
        AdminUser user = null;
        try{
            user = adminUserService.findByUserName(username);
        }catch (Exception e) {
            e.printStackTrace();
        }
        // 账号不存在
        if(user == null) {
            throw new UnknownAccountException();
        }
        // 1.启用2.禁用
        if(user.getStatus() == 2) {
            throw new LockedAccountException();
        }

        // 用户角色
        AdminRole role = adminRoleService.findRoleByUserId(user.getId());
        if(role == null || role.getStatus() == 2){
            throw new LockedAccountException();
        }
        user.setRole(role);

        // 获取权限菜单
//        List<TreeObject> menuList = adminMenuService.roleMenuList(role.getId());
//        user.setUserMenuList(menuList);

        // 盐
        String salt = user.getSalt().trim();
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), ByteSource.Util.bytes(salt), getName());

        // 当验证都通过后，把用户信息放在session里
        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute("userSession", user);
        session.setAttribute("userSessionId", user.getId());
        return info;
	}

    /**
     * 更新用户授权信息缓存.
     */
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }
    /**
     * 更新用户信息缓存.
     */
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    /**
     * 清除用户授权信息缓存.
     */
    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    /**
     * 清除用户信息缓存.
     */
    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    /**
     * 清空所有缓存
     */
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }


    /**
     * 清空所有认证缓存
     */
    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }

    // 清楚认证缓存
    public void clearCached() {
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        super.clearCache(principals);
    }
}
