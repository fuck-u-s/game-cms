package com.cms.utils;

import com.xuan.utils.Validators;

/**
 * 方法描述:工具类
 * 继承xutils.Validators 扩展验证方法
 * <p>
 * author LZH
 * version v1.0
 * date 2016/6/16 09:51
 */
public class XValidators extends Validators{

    // 验证用户名
    public static boolean checkUserName(String userName){
        // 验证是否符合正则表达式
        String reg = "^[0-9a-zA-Z_]{5,16}$";
        if(!isRegexMatch(userName,reg)) {
            return false;
        }

        // 验证第一个字符是否字母开头
        String c = userName.substring(0,1);
        if(!isRegexMatch(c,"^[A-Za-z]{1}$")) {
            return false;
        }
        return true;
    }

    // 验证密码
    public static boolean checkPassword(String password){
        String reg = "^[0-9a-zA-Z`~!@#$%&*(),.<>:;\\{\\}\\[\\]]{6,16}";
        if(!isRegexMatch(password,reg)){
            return false;
        }
        return true;
    }
}
