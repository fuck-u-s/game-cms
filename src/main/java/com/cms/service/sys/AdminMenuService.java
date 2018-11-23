package com.cms.service.sys;

import com.cms.dao.sys.AdminMenuDao;
import com.cms.model.sys.AdminMenu;
import com.cms.model.sys.AdminUser;
import com.cms.model.sys.TreeObject;
import com.cms.utils.PublicUtils;
import com.cms.utils.TreeUtil;
import com.cms.web.shiro.UserRealm;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 方法描述:菜单
 * <p>
 * author LZH
 * version v1.0
 * date 2016/6/14 10:56
 */
@Service
public class AdminMenuService {

    @Autowired
    private AdminMenuDao adminMenuDao;
    @Autowired
    private UserRealm userRealm;

    // 角色菜单列表
    public List<TreeObject> roleMenuList(long role_id) {
        List<Map<String,Object>> menuList = adminMenuDao.roleMenuAllList(role_id);
        List<TreeObject> list = new ArrayList<TreeObject>();
        for(int i=0;i<menuList.size();i++){
            Map<String,Object> mMap = menuList.get(i);
            TreeObject treeObject = PublicUtils.map2Bean(mMap, TreeObject.class);
            list.add(treeObject);
        }
        TreeUtil treeUtil = new TreeUtil();
        List<TreeObject> ns = treeUtil.getChildTreeObjects(list, 0);
        return ns;
    }

    // 角色权限菜单列表
    public List<AdminMenu> userAuthMenuList(long role_id) {
        List<AdminMenu> menuList = adminMenuDao.userAuthMenuList(role_id);
        return menuList;
    }

    // 菜单列表
    public PageInfo<AdminMenu> menuList(int pageNum, int pageSize){
        PageHelper.startPage((pageNum / pageSize) + 1,pageSize);
        // 列表
        List<AdminMenu> list = adminMenuDao.menuList();
        PageInfo<AdminMenu> info = new PageInfo<AdminMenu>(list);
        return info;
    }

    // 子菜单列表
    public PageInfo<AdminMenu> childList(long parent_id,int pageNum,int pageSize){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("parent_id",parent_id);
        PageHelper.startPage((pageNum / pageSize) + 1,pageSize);
        List<AdminMenu> list = adminMenuDao.childList(map);
        PageInfo<AdminMenu> info = new PageInfo<AdminMenu>(list);
        return info;
    }

    // 一级/二级菜单列表
    public List<TreeObject> authMenuList(){
        // 菜单列表
        List<AdminMenu> mps = adminMenuDao.authMenuList();
        // 生成菜单关系
        List<TreeObject> list = new ArrayList<TreeObject>();
        for(int i=0;i<mps.size();i++){
            AdminMenu menu = mps.get(i);
            TreeObject treeObject = new TreeObject();
            treeObject.setId(menu.getId());
            treeObject.setName(menu.getName());
            treeObject.setPermission(menu.getPermission());
            treeObject.setParent_id(menu.getParent_id());
            treeObject.setUrl(menu.getUrl());
            treeObject.setSort(menu.getSort());
            treeObject.setType(menu.getType()+"");
            treeObject.setDescription(menu.getDescription());
            treeObject.setIcon(menu.getIcon());
            treeObject.setIshide(menu.getIshide());
            list.add(treeObject);
        }
        TreeUtil treeUtil = new TreeUtil();
        List<TreeObject> ns = treeUtil.getChildTreeObjects(list, 0, "&nbsp;&nbsp;&nbsp;");
        return ns;
    }

    // 创建菜单
    @Transactional
    public int insert(AdminMenu menu, AdminUser user){
        // 菜单名唯一
        int hasName = adminMenuDao.hasMenuName(menu.getName());
        if(hasName > 0){
            return -1001;
        }

        // 上级菜单
        AdminMenu parent = adminMenuDao.menu(menu.getParent_id());

        // 地址链
        String parent_ids = "0";
        if(menu.getParent_id() != 0){
            parent_ids = parent.getParent_ids()+"/"+menu.getParent_id();
        }
        menu.setParent_ids(parent_ids);

        // 排序
        int sort = menu.getType() == 3?0:adminMenuDao.sort(menu.getParent_id());
        menu.setSort(sort);

        int i = adminMenuDao.insert(menu);

        // 超级管理员添加默认菜单
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("role_id",1);
        map.put("menu_id",menu.getId());
        adminMenuDao.roleMenu(map);

        //删除缓存
        userRealm.clearCached();
        return i;
    }

    // 菜单信息
    public AdminMenu menu(long id){
        return adminMenuDao.menu(id);
    }

    // 删除菜单
    @Transactional
    public int delete(long id){
        adminMenuDao.deleteRoleMenu(id);
        return adminMenuDao.delete(id);
    }

    // 修改菜单
    public int update(AdminMenu menu){
        return adminMenuDao.update(menu);
    }

    // 角色权限关系列表
    public List<TreeObject> roleRootMenuList(AdminUser user,long role_id){
        // 菜单结果集
        List<TreeObject> list = new ArrayList<TreeObject>();
        // 全部菜单
        List<AdminMenu> menuList;
        if(role_id == 1){
            menuList = adminMenuDao.allMenuList();
        }else{
            menuList = adminMenuDao.roleMenuList(user.getRole().getId());
        }
        // 角色菜单
        List<AdminMenu> roleMenuList = adminMenuDao.roleMenuList(role_id);
        for(int i=0;i<menuList.size();i++){
            AdminMenu menu = menuList.get(i);
            long mRes_id = menu.getId();
            for(int j=0;j<roleMenuList.size();j++){
                AdminMenu rMap = roleMenuList.get(j);
                if(mRes_id == rMap.getId()){
                    menu.setChecked(true);
                    break;
                }
            }
            TreeObject treeObject = new TreeObject();
            treeObject.setId(menu.getId());
            treeObject.setName(menu.getName());
            treeObject.setPermission(menu.getPermission());
            treeObject.setParent_id(menu.getParent_id());
            treeObject.setUrl(menu.getUrl());
            treeObject.setSort(menu.getSort());
            treeObject.setType(menu.getType()+"");
            treeObject.setDescription(menu.getDescription());
            //treeObject.setIcon(menu.getIcon());
            treeObject.setIshide(menu.getIshide());
            treeObject.setChecked(menu.isChecked());
            list.add(treeObject);
        }
        TreeUtil treeUtil = new TreeUtil();
        List<TreeObject> ns = treeUtil.getChildTreeObjects(list, 0, "");
        return ns;
    }
}
