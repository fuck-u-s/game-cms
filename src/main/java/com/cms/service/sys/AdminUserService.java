package com.cms.service.sys;

import com.cms.dao.sys.AdminRoleDao;
import com.cms.dao.sys.AdminUserDao;
import com.cms.model.sys.AdminUser;
import com.cms.utils.Logger;
import com.cms.utils.PublicUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 方法描述:人员管理
 * <p>
 * author LZH
 * version v1.0
 * date 2016/6/14 09:55
 */
@Service
public class AdminUserService {

    @Autowired
    private AdminUserDao adminUserDao;
    @Autowired
    private AdminRoleDao adminRoleDao;

    // 根据用户名查询用户信息
    public AdminUser findByUserName(String username) {
        AdminUser user = adminUserDao.findByUserName(username);
        return user;
    }

    // 用户列表
    public PageInfo<AdminUser> userList(int pageNum, int pageSize){
        PageHelper.startPage((pageNum / pageSize) + 1,pageSize);
        List<AdminUser> list = adminUserDao.userList(null);
        PageInfo<AdminUser> info = new PageInfo<AdminUser>(list);
        return info;
    }

    // 冻结/解冻账户
    public int freezeUser(int status,long user_id){
        try {
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("status",status);
            map.put("user_id",user_id);
            int i = adminUserDao.freezeUser(map);
            return i;
        }catch (Exception e){
            e.printStackTrace();
            Logger.error(e);
        }
        return -1000;
    }

    // 新增用户
    @Transactional
    public int insert(AdminUser user) {
        // 用户是否存在
        int hasUserName = adminUserDao.hasUserName(user.getUsername());
        if(hasUserName > 0){
            return -1001;
        }else{
            //随机生成4位字符串
            String salt = PublicUtils.getRandomString(4);
            //重新生成MD5
            Md5Hash md5Hash = new Md5Hash(user.getPassword(),salt,1);
            String password = md5Hash.toString();
            user.setPassword(password);
            user.setSalt(salt);
            adminUserDao.insert(user);

            // 分配角色
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("user_id",user.getId());
            map.put("role_id",user.getRole_id());
            adminUserDao.saveRole(map);
        }
        return 0;
    }

    // 用户信息
    public AdminUser user(long id){
        // 人员信息
        AdminUser user = adminUserDao.user(id);
        return user;
    }

    // 更新人员信息
    @Transactional
    public int update(AdminUser user){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("user_id",user.getId());
        map.put("username",user.getUsername());
        map.put("role_id",user.getRole_id());
        // 用户是否存在
        int hasUserName = adminUserDao.hasUserNameByMe(map);
        if(hasUserName > 0){
            return -1001;
        }else{
            // 更改角色
            adminUserDao.updateRole(map);
            // 更新
            adminUserDao.update(user);
        }
        return 0;
    }

    // 删除人员
    @Transactional
    public int delete(long user_id){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("id",user_id);
        adminRoleDao.deleteUserRole(user_id);
        adminUserDao.delete(map);
        return 0;
    }

    // 修改密码
    public int setPassword(long user_id,String password){
        //随机生成4位字符串
        String salt = PublicUtils.getRandomString(4);
        //重新生成MD5
        Md5Hash md5Hash = new Md5Hash(password,salt,1);
        String pwd = md5Hash.toString();
        Map<String,Object> map = new HashMap<>();
        map.put("user_id",user_id);
        map.put("password",pwd);
        map.put("salt",salt);
        return adminUserDao.setPassword(map);
    }

    // 所有用户
    public List<AdminUser> allUsers(int type,int role_id,long user_id){
        Map<String,Object> map = new HashMap<>();
        map.put("type",type);
        map.put("role_id",role_id);
        map.put("user_id",user_id);
        return adminUserDao.allUsers(map);
    }

    // 未绑定代理的用户
    public List<AdminUser> freeUserList(){

        return adminUserDao.freeUserList();
    }

    public static void main(String[] args) {
        Md5Hash md5Hash = new Md5Hash("book201709","ow5a",1);
        System.out.println(md5Hash.toString());
    }
}
