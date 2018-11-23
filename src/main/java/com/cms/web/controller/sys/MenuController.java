package com.cms.web.controller.sys;

import com.cms.model.sys.AdminMenu;
import com.cms.model.sys.AdminUser;
import com.cms.model.sys.TreeObject;
import com.cms.service.sys.AdminMenuService;
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
 * date 2016/6/14 15:49
 */
@Controller
@RequestMapping("/menu/*")
public class MenuController {

    @Autowired
    private AdminMenuService adminMenuService;

    // 菜单列表
    @RequestMapping("/menuList")
    @RequiresPermissions("menu:menuList")
    public String menuList(){
        return "/sys/menu/menuList";
    }

    // 菜单数据
    @RequestMapping("/wsMenuList")
    @RequiresPermissions("menu:menuList")
    @ResponseBody
    public String wsMenuList(@RequestParam(defaultValue = "0") int draw,
                             @RequestParam(defaultValue = "0") int start,
                             @RequestParam(defaultValue = "10") int length){
        Map<String,Object> map = new HashMap<String,Object>();
        PageInfo<AdminMenu> pageInfo = adminMenuService.menuList(start,length);
        map.put("data",pageInfo.getList());
        map.put("draw",draw);
        map.put("recordsTotal",pageInfo.getTotal());
        map.put("recordsFiltered",pageInfo.getTotal());
        return ResultUtil.toJSON(map);
    }

    // 子菜单
    @RequestMapping("/childList")
    @RequiresPermissions("menu:menuList")
    public String childList(@RequestParam(defaultValue = "0") long parent_id,
                            Model model){
        model.addAttribute("parent_id",parent_id);
        return "/sys/menu/childList";
    }

    // 子菜单数据
    @RequestMapping("/wsChildList")
    @RequiresPermissions("menu:menuList")
    @ResponseBody
    public String wsChildList(@RequestParam(defaultValue = "0") long parent_id,
                              @RequestParam(defaultValue = "0") int draw,
                              @RequestParam(defaultValue = "0") int start,
                              @RequestParam(defaultValue = "10") int length){
        Map<String,Object> map = new HashMap<String,Object>();
        PageInfo<AdminMenu> pageInfo = adminMenuService.childList(parent_id,start,length);
        map.put("data",pageInfo.getList());
        map.put("draw",draw);
        map.put("recordsTotal",pageInfo.getTotal());
        map.put("recordsFiltered",pageInfo.getTotal());
        return ResultUtil.toJSON(map);
    }

    // 初始化菜单
    @RequestMapping("/init")
    @RequiresPermissions("menu:create")
    public String init(Model model){
        // 菜单列表
        List<TreeObject> treeList = adminMenuService.authMenuList();
        model.addAttribute("treeList",treeList);
        return "/sys/menu/setMenu";
    }

    // 创建菜单
    @RequestMapping("/create")
    @RequiresPermissions("menu:create")
    @LogWrite(module = "菜单管理-创建菜单")
    @ResponseBody
    public String create(@CurrentUser AdminUser user, @ModelAttribute AdminMenu menu){
        Map<String,Object> map = ResultUtil.result();
        try {
            Map<String,Object> result = ValidatorUtils.checkCreateMenu(menu);
            int code = Convert.strToInt(result.get("code")+"",0);
            if(code < 0){
                map.putAll(result);
            }else{
                int i = adminMenuService.insert(menu,user);
                map.put("code",i);
                if(i < 0){
                    map.put("msg","菜单名已存在");
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

    // 查看菜单
    @RequestMapping("/menu")
    @RequiresPermissions("menu:menu")
    public String menu(Model model,
                       @RequestParam(required = false, defaultValue = "0") long id,
                       @RequestParam(required = false, defaultValue = "1") int lookType){
        AdminMenu menu = adminMenuService.menu(id);
        // 菜单列表
        List<TreeObject> treeList = adminMenuService.authMenuList();
        model.addAttribute("treeList",treeList);
        model.addAttribute("menu",menu);
        model.addAttribute("lookType",lookType);
        return "/sys/menu/viewMenu";
    }

    // 删除菜单
    @RequestMapping("/delete")
    @RequiresPermissions("menu:delete")
    @LogWrite(module = "菜单管理-删除菜单")
    @ResponseBody
    public String delete(@RequestParam(required = false, defaultValue = "0") long id){
        Map<String,Object> map = ResultUtil.result();
        int i = adminMenuService.delete(id);
        map.put("code",i);
        if(i > 0){
            map.put("msg","删除成功");
        }else{
            map.put("msg","删除失败");
        }
        return ResultUtil.toJSON(map);
    }

    // 修改菜单
    @RequestMapping("/update")
    @RequiresPermissions("menu:update")
    @LogWrite(module = "菜单管理-修改菜单")
    @ResponseBody
    public String update(@ModelAttribute AdminMenu menu){
        Map<String,Object> map = ResultUtil.result();
        try {
            Map<String,Object> result = ValidatorUtils.checkCreateMenu(menu);
            int code = Convert.strToInt(result.get("code")+"",0);
            if(code < 0){
                map.putAll(result);
            }else{
                int i = adminMenuService.update(menu);
                map.put("code",i);
                if(i > 0){
                    map.put("msg","更新成功");
                }else{
                    map.put("msg","更新失败");
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
}
