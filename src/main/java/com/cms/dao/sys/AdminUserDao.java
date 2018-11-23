package com.cms.dao.sys;

import com.cms.model.sys.AdminUser;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 方法描述:人员管理
 * <p>
 * author LZH
 * version v1.0
 * date 2016/6/13 15:30
 */
@Repository
public interface AdminUserDao {

    // 用户列表
    List<AdminUser> userList(Map<String, Object> map);

    // 冻结/解冻账户
    int freezeUser(Map<String, Object> map);

    // 新增用户
    void insert(AdminUser user);

    // 根据用户名获取用户信息
    AdminUser findByUserName(String username);

    // 根据用户名获取用户信息
    AdminUser selectUserName(String username);

    // 用户是否存在
    int hasUserName(String username);

    // 分配角色
    void saveRole(Map<String, Object> map);

    // 修改角色
    int updateRole(Map<String, Object> map);

    // 人员信息
    AdminUser user(long id);

    // 用户是否存在
    int hasUserNameByMe(Map<String, Object> map);

    // 更新人员信息
    int update(AdminUser user);

    // 删除人员
    int delete(Map<String, Object> map);

    // 管理员数量
    int adminUserCount(Map<String, Object> map);

    // 管理员列表
    List<AdminUser> adminUserList(Map<String, Object> map);

    // 修改密码
    int setPassword(Map<String, Object> map);

    // 所有用户
    List<AdminUser> allUsers(Map<String,Object> map);

    // 未绑定代理的用户
    List<AdminUser> freeUserList();
}
