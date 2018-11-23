package com.cms.web.controller.sys;

import com.cms.model.sys.Dept;
import com.cms.service.sys.DeptService;
import com.cms.utils.Logger;
import com.cms.utils.ResultUtil;
import com.cms.utils.XValidators;
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
 * 方法描述:部门管理
 *
 * @author 小刘
 * @version v1.0
 * @date 2016/7/6
 */
 @RequestMapping("/dept/*")
 @Controller
public class DeptController {
    @Autowired
    private DeptService deptService;

    // 列表
    @RequestMapping("/list")
    @RequiresPermissions("dept:list")
    public String list(Model model){
        List<Dept> list = deptService.list();
        model.addAttribute("list",list);
        return "sys/dept/deptList";
    }

    // 部门异步数据
    @RequestMapping("/wsList")
    @RequiresPermissions("dept:list")
    @ResponseBody
    public String wsList(@RequestParam(defaultValue = "0") int draw){
        Map<String,Object> map = new HashMap<String,Object>();
        List<Dept> list = deptService.list();
        map.put("data", list);
        map.put("draw",draw);
        map.put("recordsTotal", list.size());
        map.put("recordsFiltered", list.size());
        return ResultUtil.toJSON(map);
    }

    // 设置部门
    @RequestMapping("/setDept")
    @RequiresPermissions("dept:create")
    public String init(){
        return "sys/dept/setDept";
    }

    // 创建部门
    @RequestMapping("/create")
    @RequiresPermissions("dept:create")
    @LogWrite(module = "部门管理-创建部门")
    @ResponseBody
    public String create(@ModelAttribute Dept dept){
        Map<String,Object> map = ResultUtil.result();
        try {
            if(XValidators.isBlank(dept.getName())){
                map.put("code",-1002);
                map.put("msg","名称长度为1-10个字符");
            }else{
                int i = deptService.insert(dept);
                map.put("code",i);
                if(i > 0){
                    map.put("msg","创建成功");
                }else{
                    if(i == -1001){
                        map.put("msg","部门["+dept.getName()+"]已存在哦~");
                    }else{
                        map.put("msg","创建失败");
                    }
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

    // 查看
    @RequestMapping("/get")
    @RequiresPermissions("dept:get")
    public String get(@RequestParam(required = true, defaultValue = "0") long id,
                      Model model){
        Dept dept = deptService.get(id);
        model.addAttribute("dept",dept);
        return "sys/dept/view";
    }

    // 更新部门
    @RequestMapping("/update")
    @RequiresPermissions("dept:update")
    @LogWrite(module = "部门管理-修改部门")
    @ResponseBody
    public String update(@ModelAttribute Dept dept){
        Map<String,Object> map = ResultUtil.result();
        try {
            if(XValidators.isBlank(dept.getName())){
                map.put("code",-1002);
                map.put("msg","名称长度为1-10个字符");
            }else{
                int i = deptService.update(dept);
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

    // 删除
    @RequestMapping("/delete")
    @RequiresPermissions("dept:delete")
    @LogWrite(module = "部门管理-删除部门")
    @ResponseBody
    public String delete(@RequestParam(required = true, defaultValue = "0") long id){
        Map<String,Object> map = ResultUtil.result();
        int i = deptService.delete(id);
        map.put("code",i);
        if(i > 0){
            map.put("msg","删除成功");
        }else{
            if(i == -1001){
                map.put("msg","部门下存在人员不能删除");
            }else{
                map.put("msg","删除失败");
            }
        }
        return ResultUtil.toJSON(map);
    }
}
