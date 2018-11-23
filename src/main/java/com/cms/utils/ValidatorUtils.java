package com.cms.utils;

import com.cms.model.sys.AdminMenu;
import com.cms.model.sys.AdminUser;
import com.xuan.utils.Validators;

import java.util.Map;

/**
 * 方法描述:验证处理类
 * <p>
 * author LZH
 * version v1.0
 * date 2016/6/16 11:01
 */
public class ValidatorUtils {

    // 创建用户验证
    public static Map<String,Object> checkUser(AdminUser user){
        Map<String,Object> map = ResultUtil.result();
        if(!XValidators.checkUserName(user.getUsername())){
            map.put("code",-1002);
            map.put("msg","账号由a-z,0-9的6-16位字符组成");
        }else if(!XValidators.checkPassword(user.getPassword())){
            map.put("code",-1003);
            map.put("msg","密码由a-z,0-9的6-16位字符组成");
        }else if(Validators.isBlank(user.getName()) || user.getName().length() > 10){
            map.put("code",-1004);
            map.put("msg","用户名长度为1-10个字符");
        }else if(user.getType() == 3 && user.getDept_id() < 0){
            map.put("code",-1007);
            map.put("msg","请选择部门");
        }else{
            map.put("code",0);
            map.put("msg","验证通过");
        }
        return map;
    }

    // 修改用户验证
    public static Map<String,Object> checkUserInfo(AdminUser user){
        Map<String,Object> map = ResultUtil.result();
        if(!XValidators.checkUserName(user.getUsername())){
            map.put("code",-1002);
            map.put("msg","账号由a-z,0-9的6-16位字符组成");
        }else if(Validators.isBlank(user.getName()) || user.getName().length() > 10){
            map.put("code",-1004);
            map.put("msg","用户名长度为1-10个字符");
        }else if(Validators.isBlank(user.getMobile())){
            map.put("code",-1005);
            map.put("msg","请输入用户电话");
        }else if(!Validators.isEmail(user.getEmail())){
            map.put("code",-1006);
            map.put("msg","请输入用户邮箱");
        }else{
            map.put("code",0);
            map.put("msg","验证通过");
        }
        return map;
    }

    // 验证菜单
    public static Map<String,Object> checkCreateMenu(AdminMenu menu){
        Map<String,Object> map = ResultUtil.result();
        if(XValidators.isBlank(menu.getName()) || menu.getName().length() > 10){
            map.put("code",-1002);
            map.put("msg","菜单名长度为1-10个字符");
        }else if(Validators.isBlank(menu.getPermission()) || menu.getPermission().length() > 50){
            map.put("code",-1003);
            map.put("msg","菜单标识长度为1-20个字符");
        }else if(Validators.isBlank(menu.getDescription()) || menu.getDescription().length() > 15){
            map.put("code",-1006);
            map.put("msg","菜单描述长度为1-15个字符");
        }else{
            map.put("code",0);
            map.put("msg","验证通过");
        }
        return map;
    }
}
