package com.cms.web.controller.sys;

import com.cms.model.sys.LoginError;
import com.cms.model.sys.SysConfig;
import com.cms.model.sys.TreeObject;
import com.cms.service.sys.AdminMenuService;
import com.cms.service.sys.SysConfigService;
import com.cms.utils.*;
import com.xuan.utils.Validators;
import com.cms.model.sys.AdminUser;
import com.cms.service.sys.AdminUserService;
import com.cms.web.bind.CurrentUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.hyperic.sigar.Sigar;
import org.patchca.utils.encoder.EncoderHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * 方法描述:登录界面
 * <p>
 * author LZH
 * version v1.0
 * date 2016/6/13 10:31
 */
@Controller
public class IndexController {

    @Autowired
    private AdminUserService adminUserService;
    @Autowired
    private SysConfigService sysConfigService;
    @Autowired
    private AdminMenuService adminMenuService;

    // 登录
    @RequestMapping("/login")
    public String login(HttpServletRequest request,Model model) {
        //如果登陆失败从request中获取认证异常信息，shiroLoginFailure就是shiro异常类的全限定名
        String exceptionClassName = (String) request.getAttribute("shiroLoginFailure");
        Subject subject = SecurityUtils.getSubject();
        if(subject != null && subject.isAuthenticated()) {
            return "redirect:/main";
        }
        LoginError error = new LoginError();
        if(!Validators.isEmpty(exceptionClassName)) {
            if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
                error.setCode(1000);
                error.setMsg("账号不存在");
            } else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
                error.setCode(1001);
                error.setMsg("登录密码错误");
            } else if (AuthenticationException.class.getName().equals(exceptionClassName)) {
                error.setCode(1002);
                error.setMsg("登录密码错误");
            }else if (LockedAccountException.class.getName().equals(exceptionClassName)) {
                error.setCode(1003);
                error.setMsg("用户已被禁止登录");
            }else if("captchaError".equals(exceptionClassName)) {
                error.setCode(1004);
                error.setMsg("验证码错误");
            }
        }
        model.addAttribute("error", ResultUtil.toJSON(error));
        return "/login";
    }

    /**
     * 登录成功后进入后台主页面
     */
    @RequestMapping("/main")
    public String main(HttpServletRequest request,@CurrentUser AdminUser user,Model model) {
        // 获取权限菜单
        List<TreeObject> menuList = adminMenuService.roleMenuList(user.getRole().getId());
        user.setUserMenuList(menuList);
        model.addAttribute("user",user);
        return "/Hmain";
    }

    //生成验证码
    @RequestMapping("/captcha")
    public void code(HttpServletRequest request, HttpServletResponse response)throws Exception {
        response.setContentType("image/png");
        response.setHeader("cache", "no-cache");
        HttpSession session = request.getSession(true);
        OutputStream os = response.getOutputStream();
        String captcha = EncoderHelper.getChallangeAndWriteImage(Patchca.createImage(), "png", os);
        session.setAttribute("captcha", captcha);
        os.flush();
        os.close();
    }

    // 退出登录
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() throws IOException {
        Subject user = SecurityUtils.getSubject();
        user.logout();
        return "redirect:/login";
    }

    // 修改密码
    @RequestMapping("/password")
    public String password(){
        return "sys/user/password";
    }

    // 设置密码
    @RequestMapping("/setPassword")
    @ResponseBody
    public String setPassword(@CurrentUser AdminUser user,
                              @RequestParam(required = true, defaultValue = "") String oldPwd,
                              @RequestParam(required = true, defaultValue = "") String password,
                              @RequestParam(required = true, defaultValue = "") String confirm){
        Map<String,Object> map = ResultUtil.result();
        String md5 = new Md5Hash(oldPwd,user.getSalt()).toString();
        if(!md5.equals(user.getPassword())){
            map.put("code",-1001);
            map.put("msg","旧密码错误");
        }else if(!XValidators.checkPassword(password)){
            map.put("code",-1002);
            map.put("msg","新密码由a-z,0-9的6-16位字符组成");
        }else if(!XValidators.checkPassword(confirm)){
            map.put("code",-1003);
            map.put("msg","新密码由a-z,0-9的6-16位字符组成");
        }else if(!password.equals(confirm)){
            map.put("code",-1004);
            map.put("msg","输入的新密码不匹配");
        }else{
            int i = adminUserService.setPassword(user.getId(),password);
            map.put("code",i);
            if(i > 0){
                map.put("msg","密码修改成功");
            }else{
                map.put("msg","密码修改失败");
            }
        }
        return ResultUtil.toJSON(map);
    }

    // 被T下线
    @RequestMapping("/ws/ticket")
    public String ticket(){
        return "ticket";
    }

    // 没有权限
    @RequestMapping("/refuse")
    public String refuse(){
        return "/refuse";
    }

    // 系统监控
    @RequestMapping("/sysInfo")
    public String monitor(Model model){
        // CPU
        SysConfig cpu = sysConfigService.getConfig(1);
        // 内存
        SysConfig ram = sysConfigService.getConfig(2);
        // JVM
        SysConfig jvm = sysConfigService.getConfig(3);
        // 邮箱
        SysConfig email = sysConfigService.getConfig(4);
        // 系统参数
        model.addAttribute("systemInfo", SystemInfo.SystemProperty());
        model.addAttribute("cpu",cpu);
        model.addAttribute("ram",ram);
        model.addAttribute("jvm",jvm);
        model.addAttribute("email",email);
        return "/sys/monitor/sysinfo";
    }

    // 修改配置
    @RequestMapping("/setConfig")
    @ResponseBody
    public String setConfig(String key,String value) throws Exception{
        Map<String, Object> map = ResultUtil.result();
        try {
            SysConfig config = new SysConfig();
            config.setKey(key);
            config.setVal(value);
            int i = sysConfigService.setConf(config);
            map.put("code",i);
            map.put("msg",i>0?"设置成功":"设置失败");
        } catch (Exception e) {
            e.printStackTrace();
            Logger.error(e);
            map.put("code",-1000);
            map.put("msg","参数设置出错啦...");
        }
        return ResultUtil.toJSON(map);
    }

    // 系统监控
    @ResponseBody
    @RequestMapping("/usage")
    public Map<String,Object> usage() throws Exception {
        return SystemInfo.usage(new Sigar());
    }

}
