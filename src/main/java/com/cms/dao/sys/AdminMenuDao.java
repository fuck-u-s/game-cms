package com.cms.dao.sys;

import com.cms.model.sys.AdminMenu;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 方法描述:TODO
 * <p>
 * author LZH
 * version v1.0
 * date 2016/6/14 10:38
 */
@Repository
public interface AdminMenuDao {
    // 角色菜单列表
    List<AdminMenu> roleMenuList(long role_id);

    // 角色登录菜单
    List<Map<String,Object>> roleMenuAllList(long role_id);

    // 角色权限菜单
    List<AdminMenu> userAuthMenuList(long role_id);

    // 菜单列表
    List<AdminMenu> menuList();

    // 子菜单
    List<AdminMenu> childList(Map<String,Object> map);

    // 创建菜单
    int insert(AdminMenu menu);

    // 修改菜单
    int update(AdminMenu menu);

    // 菜单名是否存在
    int hasMenuName(String name);

    // 一级/二级菜单列表
    List<AdminMenu> authMenuList();

    // 菜单信息
    AdminMenu menu(long id);

    // 添加角色菜单关系
    int roleMenu(Map<String, Object> map);

    // 删除级联菜单
    int deleteRoleMenu(long id);

    // 删除菜单
    int delete(long id);

    // 查询所有菜单列表
    List<AdminMenu> allMenuList();

    // 菜单排序最大值
    int sort(long parent_id);
}
