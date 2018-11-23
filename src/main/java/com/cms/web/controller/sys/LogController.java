package com.cms.web.controller.sys;

import com.cms.model.sys.AdminUser;
import com.cms.model.sys.LoginLog;
import com.cms.model.sys.SysLog;
import com.cms.service.sys.AdminUserService;
import com.cms.service.sys.LogService;
import com.cms.utils.ResultUtil;
import com.cms.web.bind.CurrentUser;
import com.cms.web.bind.LogWrite;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 方法描述:日志管理
 * <p>
 * author lzh
 * version v1.0
 * date 2016/7/10 18:19
 */
@RequestMapping("/log/*")
@Controller
public class LogController {
    @Autowired
    private LogService logService;
    @Autowired
    private AdminUserService adminUserService;

    // 操作日志列表
    @RequestMapping("/sysLogList")
    @RequiresPermissions("log:sysLogList")
    public String sysLogList(@CurrentUser AdminUser user,Model model){
        List<AdminUser> allUsers = adminUserService.allUsers(user.getType(),0,user.getId());
        model.addAttribute("allUsers",allUsers);
        return "sys/log/sysLogList";
    }

    // 操作日志数据
    @RequestMapping("/wsSysLogList")
    @RequiresPermissions("log:sysLogList")
    @ResponseBody
    public String wsSysLogList(@RequestParam(defaultValue = "0") int draw,
                               @RequestParam(defaultValue = "0") int start,
                               @RequestParam(defaultValue = "10") int length,
                               @RequestParam(defaultValue = "0") long user_id,
                               @RequestParam(defaultValue = "2") int status,
                               @RequestParam(defaultValue = "") String start_time,
                               @RequestParam(defaultValue = "") String end_time){
        Map<String,Object> map = new HashMap<String,Object>();
        PageInfo<SysLog> pageInfo = logService.sysLogList(start,length,user_id,status,start_time,end_time);
        map.put("data",pageInfo.getList());
        map.put("draw",draw);
        map.put("recordsTotal",pageInfo.getTotal());
        map.put("recordsFiltered",pageInfo.getTotal());
        return ResultUtil.toJSON(map);
    }

    // 登陆日志列表
    @RequestMapping("/loginLogList")
    @RequiresPermissions("log:loginLogList")
    public String loginLogList(@CurrentUser AdminUser user,Model model){
        List<AdminUser> allUsers = adminUserService.allUsers(user.getType(),0,user.getId());
        model.addAttribute("allUsers",allUsers);
        return "sys/log/loginLogList";
    }

    // 登录日志数据
    @RequestMapping("/wsLoginLogList")
    @RequiresPermissions("log:loginLogList")
    @LogWrite(module = "日志管理-登录日志")
    @ResponseBody
    public String wsLoginLogList(@RequestParam(defaultValue = "0") int draw,
                                 @RequestParam(defaultValue = "0") int start,
                                 @RequestParam(defaultValue = "10") int length,
                                 @RequestParam(defaultValue = "0") long user_id,
                                 @RequestParam(defaultValue = "") String start_time,
                                 @RequestParam(defaultValue = "") String end_time){
        Map<String,Object> map = new HashMap<String,Object>();
        PageInfo<LoginLog> pageInfo = logService.loginLogList(start,length,user_id,start_time,end_time);
        map.put("data",pageInfo.getList());
        map.put("draw",draw);
        map.put("recordsTotal",pageInfo.getTotal());
        map.put("recordsFiltered",pageInfo.getTotal());
        return ResultUtil.toJSON(map);
    }
}
