package com.cms.dao.sys;

import com.cms.model.sys.AdminRole;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 方法描述:角色
 * <p>
 * author LZH
 * version v1.0
 * date 2016/6/14 10:25
 */
@Repository
public interface AdminRoleDao {

    // 查询用户角色
    AdminRole findRoleByUserId(long user_id);

    // 所有角色
    List<AdminRole> allRoles();

    // 角色数量
    int roleCount(Map<String, Object> map);

    // 角色列表
    List<AdminRole> roleList();

    // 角色是否存在
    int hasRole(String name);

    // 新增角色
    int insert(AdminRole role);

    // 删除人员所属角色
    int deleteUserRole(long user_id);

    // 删除角色所有人员
    int deleteRoleUser(long role_id);

    // 锁定/激活角色
    int lock(Map<String, Object> map);

    // 角色信息
    AdminRole role(Map<String, Object> map);

    // 修改角色
    int update(AdminRole role);

    // 删除角色
    int deleteRole(Map<String, Object> map);

    // 角色是否使用
    int useRole(Map<String, Object> map);

    // 删除角色菜单
    int deleteRoleMenu(long role_id);

    // 教授菜单授权
    int roleMenuRoot(Map<String, Object> map);

    // 所有管理角色
    List<AdminRole> allAdminRoles();
}
