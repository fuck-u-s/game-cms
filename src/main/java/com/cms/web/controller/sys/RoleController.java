package com.cms.web.controller.sys;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.xuan.utils.Validators;
import com.cms.model.sys.AdminRole;
import com.cms.model.sys.AdminUser;
import com.cms.model.sys.TreeObject;
import com.cms.service.sys.AdminMenuService;
import com.cms.service.sys.AdminRoleService;
import com.cms.utils.Logger;
import com.cms.utils.ResultUtil;
import com.cms.web.bind.CurrentUser;
import com.cms.web.bind.LogWrite;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 方法描述:角色
 * <p>
 * author LZH
 * version v1.0
 * date 2016/6/14 16:00
 */
@Controller
@RequestMapping("/role/*")
public class RoleController {

    @Autowired
    private AdminRoleService adminRoleService;
    @Autowired
    private AdminMenuService adminMenuService;

    // 角色列表
    @RequestMapping("/roleList")
    @RequiresPermissions("role:roleList")
    public String roleList(){
        return "sys/role/roleList";
    }

    // 异步数据请求
    @RequestMapping("/wsRoleList")
    @RequiresPermissions("role:roleList")
    @ResponseBody
    public String wsRoleList(@RequestParam(defaultValue = "0") int draw,
                             @RequestParam(defaultValue = "0") int start,
                             @RequestParam(defaultValue = "10") int length){
        Map<String,Object> map = new HashMap<String,Object>();
        PageInfo<AdminRole> pageInfo = adminRoleService.roleList(start,length);
        map.put("data",pageInfo.getList());
        map.put("draw",draw);
        map.put("recordsTotal",pageInfo.getTotal());
        map.put("recordsFiltered",pageInfo.getTotal());
        return ResultUtil.toJSON(map);
    }

    // 新增角色
    @RequestMapping("/init")
    @RequiresPermissions("role:create")
    public String init(){
        return "sys/role/setRole";
    }

    // 创建角色
    @RequestMapping("/create")
    @LogWrite(module = "角色管理-创建角色")
    @RequiresPermissions("role:create")
    @ResponseBody
    public String create(@ModelAttribute AdminRole role){
        Map<String,Object> map = ResultUtil.result();
        int i;
        try {
            if(Validators.isBlank(role.getName())){
                i = -1002;
                map.put("msg","角色名称不合法");
            }else{
                // 标识是否管理员角色
                role.setIsAuth(2);
                i = adminRoleService.insert(role);
                if(i < 0){
                    map.put("msg","角色名称已存在");
                }else{
                    map.put("msg","角色创建成功");
                }
            }
            map.put("code",i);
        }catch (Exception e){
            e.printStackTrace();
            Logger.error(e);
            map.put("code",-1000);
            map.put("msg","创建角色失败,请联系管理员");
        }
        return ResultUtil.toJSON(map);
    }

    // 锁定/激活
    @RequestMapping("/lock")
    @RequiresPermissions("role:lock")
    @LogWrite(module = "角色管理-锁定角色")
    @ResponseBody
    public String lock(@RequestParam(required = true, defaultValue = "0") long id,
                       @RequestParam(required = true, defaultValue = "") int status){
        Map<String,Object> map = ResultUtil.result();
        int i = adminRoleService.lock(id,status);
        map.put("code",i);
        return ResultUtil.toJSON(map);
    }

    // 角色信息
    @RequestMapping("/role")
    @RequiresPermissions("role:role")
    public String role(@RequestParam(required = true, defaultValue = "0") long id,Model model){
        AdminRole role = adminRoleService.role(id);
        model.addAttribute("role",role);
        return "sys/role/view";
    }

    // 修改角色
    @RequestMapping("/update")
    @RequiresPermissions("role:update")
    @LogWrite(module = "角色管理-修改角色")
    @ResponseBody
    public String update(@ModelAttribute AdminRole role){
        Map<String,Object> map = ResultUtil.result();
        int i;
        try {
            if(Validators.isBlank(role.getName())){
                i = -1002;
                map.put("msg","角色名称不合法");
            }else{
                i = adminRoleService.update(role);
                if(i > 0){
                    map.put("msg","角色更新成功");
                }else{
                    map.put("msg","角色更新失败");
                }
            }
            map.put("code",i);
        }catch (Exception e){
            e.printStackTrace();
            Logger.error(e);
            map.put("code",-1000);
            map.put("msg","更新角色失败,请联系管理员");
        }
        return ResultUtil.toJSON(map);
    }

    // 删除角色
    @RequestMapping("/delete")
    @RequiresPermissions("role:delete")
    @LogWrite(module = "角色管理-删除角色")
    @ResponseBody
    public String delete(@RequestParam(required = true, defaultValue = "0") long id){
        Map<String,Object> map = ResultUtil.result();
        try {
            int i = adminRoleService.deleteRole(id);
            if(i > 0){
                map.put("code",i);
                map.put("msg","角色已删除!");
            }else{
                map.put("code",i);
                map.put("msg","该角色正在使用,不能删除!");
            }
        }catch (Exception e){
            e.printStackTrace();
            Logger.error(e);
            map.put("code",-1000);
            map.put("msg","更新失败,联系管理员");
        }
        return ResultUtil.toJSON(map);
    }

    // 权限设置
    @RequestMapping("/roleRootList")
    @RequiresPermissions("role:permission")
    public String roleRootList(@CurrentUser AdminUser user,Model model,long role_id){
        List<TreeObject> list = adminMenuService.roleRootMenuList(user,role_id);
        model.addAttribute("role_id",role_id);
        model.addAttribute("treeList",ResultUtil.toJSON(list));
        return "sys/role/root";
    }

    //角色菜单授权
    @RequestMapping("/roleRoot")
    @RequiresPermissions("role:permission")
    @LogWrite(module = "角色管理-角色菜单授权")
    @ResponseBody
    public String roleRoot(long role_id,String treeList){
        Map<String,Object> map = ResultUtil.result();
        try{
            if(treeList != null && !treeList.equals("")){
                treeList = treeList.substring(0,treeList.length()-1);
                adminRoleService.roleMenuRoot(treeList,role_id);
                map.put("msg","权限分配成功");
            }else{
                map.put("code",1);
                map.put("msg","请为角色分配权限");
            }
        }catch (Exception e){
            e.printStackTrace();
            Logger.error(e);
            map.put("code",-1);
            map.put("msg","权限分配失败,联系管理员");
        }
        return JSON.toJSONString(map);
    }
}
