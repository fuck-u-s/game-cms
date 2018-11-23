package com.cms.web.controller.sys;

import com.cms.model.sys.AdminRole;
import com.cms.model.sys.AdminUser;
import com.cms.model.sys.Dept;
import com.cms.service.sys.AdminRoleService;
import com.cms.service.sys.AdminUserService;
import com.cms.service.sys.DeptService;
import com.cms.utils.*;
import com.cms.web.bind.CurrentUser;
import com.cms.web.bind.LogWrite;
import com.github.pagehelper.PageInfo;
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
 * 方法描述:TODO
 * <p>
 * author LZH
 * version v1.0
 * date 2016/6/14 16:03
 */
@Controller
@RequestMapping("/user/*")
public class UserController {

    @Autowired
    private AdminUserService adminUserService;
    @Autowired
    private AdminRoleService adminRoleService;
    @Autowired
    private DeptService deptService;

    // 人员管理
    @RequestMapping("/userList")
    @RequiresPermissions("user:userList")
    public String userList(){
        return "/sys/user/userList";
    }

    // 人员管理数据
    @RequestMapping("/wsUserList")
    @ResponseBody
    public String wsUserList(@RequestParam(defaultValue = "0") int draw,
                             @RequestParam(defaultValue = "0") int start,
                             @RequestParam(defaultValue = "10") int length){
        Map<String,Object> map = new HashMap<String,Object>();
        PageInfo<AdminUser> pageInfo = adminUserService.userList(start,length);
        map.put("data",pageInfo.getList());
        map.put("draw",draw);
        map.put("recordsTotal",pageInfo.getTotal());
        map.put("recordsFiltered",pageInfo.getTotal());
        return ResultUtil.toJSON(map);
    }

    // 冻结/解冻
    @RequestMapping("/lock")
    @LogWrite(module = "人员管理-锁定/解锁人员")
    @RequiresPermissions("user:lock")
    @ResponseBody
    public String freezeUser(@RequestParam(required = true, defaultValue = "") int status,
                             @RequestParam(required = true, defaultValue = "0") long user_id){
        Map<String,Object> map = ResultUtil.result();
        int i = adminUserService.freezeUser(status,user_id);
        map.put("code",i);
        return ResultUtil.toJSON(map);
    }

    // 初始化人员
    @RequestMapping("/init")
    @RequiresPermissions("user:create")
    public String init(Model model){
        // 角色列表
        List<AdminRole> roles = adminRoleService.allRoles();
        // 部门列表
        List<Dept> deptList = deptService.list();
        model.addAttribute("roles",roles);
        model.addAttribute("deptList",deptList);
        return "sys/user/setUser";
    }

    // 创建人员
    @RequestMapping("/create")
    @RequiresPermissions("user:create")
    @LogWrite(module = "人员管理-创建人员")
    @ResponseBody
    public String create(@ModelAttribute AdminUser user){
        Map<String,Object> map = ResultUtil.result();
        try {
            // 设置用户类型
            user.setType(3);
            Map<String,Object> result = ValidatorUtils.checkUser(user);
            int code = Convert.strToInt(result.get("code")+"",0);
            if(code < 0){
                map.putAll(result);
            }else{
                int i = adminUserService.insert(user);
                map.put("code",i);
                if(i < 0){
                    map.put("msg","用户名已存在");
                }else{
                    map.put("msg","创建成功");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Logger.error(e);
            map.put("code",-1000);
            map.put("msg","创建失败,联系管理员");
        }
        return ResultUtil.toJSON(map);
    }

    // 人员信息
    @RequestMapping("/user")
    @RequiresPermissions("user:user")
    public String user(@RequestParam(required = true, defaultValue = "0") long user_id,
                       Model model){
        // 角色列表
        List<AdminRole> roles = adminRoleService.allRoles();
        // 部门列表
        List<Dept> deptList = deptService.list();
        // 人员信息
        AdminUser adminUser = adminUserService.user(user_id);
        model.addAttribute("user",adminUser);
        model.addAttribute("roles",roles);
        model.addAttribute("deptList",deptList);
        return "sys/user/view";
    }

    // 更新人员信息
    @RequestMapping("/update")
    @RequiresPermissions("user:update")
    @LogWrite(module = "人员管理-修改人员")
    @ResponseBody
    public String update(@ModelAttribute AdminUser user){
        Map<String,Object> map = ResultUtil.result();
        try {
            Map<String,Object> result = ValidatorUtils.checkUserInfo(user);
            int code = Convert.strToInt(result.get("code")+"",0);
            if(code < 0){
                map.putAll(result);
            }else{
                int i = adminUserService.update(user);
                map.put("code",i);
                if(i < 0){
                    map.put("msg","用户名已存在");
                }else{
                    map.put("msg","更新成功");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Logger.error(e);
            map.put("code",-1000);
            map.put("msg","更新失败,联系管理员");
        }
        return ResultUtil.toJSON(map);
    }

    // 删除人员
    @RequestMapping("/delete")
    @RequiresPermissions("user:delete")
    @LogWrite(module = "人员管理-删除人员")
    @ResponseBody
    public String delete(@RequestParam(required = true, defaultValue = "0") long user_id){
        Map<String,Object> map = ResultUtil.result();
        try {
            adminUserService.delete(user_id);
            map.put("msg","删除成功");
        }catch (Exception e){
            e.printStackTrace();
            Logger.error(e);
            map.put("code",-1000);
            map.put("msg","更新失败,联系管理员");
        }
        return ResultUtil.toJSON(map);
    }
}
