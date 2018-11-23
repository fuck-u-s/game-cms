package com.cms.service.sys;

import com.cms.dao.sys.AdminRoleDao;
import com.cms.model.sys.AdminRole;
import com.cms.web.shiro.UserRealm;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 方法描述:角色
 * <p>
 * author LZH
 * version v1.0
 * date 2016/6/14 10:27
 */
@Service
public class AdminRoleService {

    @Autowired
    private UserRealm realm;
    @Autowired
    private AdminRoleDao adminRoleDao;

    // 查询角色
    public AdminRole findRoleByUserId(long user_id) {
        AdminRole role = adminRoleDao.findRoleByUserId(user_id);
        return role;
    }

    // 所有角色
    public List<AdminRole> allRoles(){
        return adminRoleDao.allRoles();
    }

    // 所有管理角色
    public List<AdminRole> allAdminRoles(){
        return adminRoleDao.allAdminRoles();
    }

    // 角色列表
    public PageInfo<AdminRole> roleList(int pageNum, int pageSize) {
        PageHelper.startPage((pageNum / pageSize) + 1,pageSize);
        List<AdminRole> list = adminRoleDao.roleList();
        PageInfo<AdminRole> info = new PageInfo<AdminRole>(list);
        return info;
    }

    // 新增角色
    public int insert(AdminRole role){
        // 角色名是否存在
        int hasRole = adminRoleDao.hasRole(role.getName());
        if(hasRole > 0){
            return -1001;
        }else{
            return adminRoleDao.insert(role);
        }
    }

    // 激活/锁定角色
    public int lock(long id,int status){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("id",id);
        map.put("status",status);
        return adminRoleDao.lock(map);
    }

    // 角色信息
    public AdminRole role(long id){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("id",id);
        return adminRoleDao.role(map);
    }

    // 修改角色
    public int update(AdminRole role){
        return adminRoleDao.update(role);
    }

    // 删除角色
    @Transactional
    public int deleteRole(long id){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("id",id);
        // 判断角色是否使用
        int useRole = adminRoleDao.useRole(map);
        if(useRole > 0){
            return -1001;
        }
        // 删除关联菜单
        adminRoleDao.deleteRoleMenu(id);
        // 删除人员关系
        adminRoleDao.deleteRoleUser(id);
        return adminRoleDao.deleteRole(map);
    }

    // 角色授权
    @Transactional
    public void roleMenuRoot(String treeList,long role_id){
        // 删除角色菜单关联
        adminRoleDao.deleteRoleMenu(role_id);
        // 角色绑定菜单
        String idArr[] = treeList.split(",");
        for(int i=0;i<idArr.length;i++){
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("role_id",role_id);
            map.put("menu_id",idArr[i]);
            adminRoleDao.roleMenuRoot(map);
        }
        //清除角色缓存
        realm.clearCached();
    }
}
