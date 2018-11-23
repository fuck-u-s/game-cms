package com.cms.service.sys;

import com.cms.dao.sys.SysLogDao;
import com.cms.model.sys.LoginLog;
import com.cms.model.sys.SysLog;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xuan.utils.Validators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 方法描述:TODO
 * <p>
 * author lzh
 * version v1.0
 * date 2016/7/9 13:36
 */

@Service
public class LogService {
    @Autowired
    private SysLogDao sysLogDao;

    // 操作日志
    public int insert(SysLog sysLog){
        return sysLogDao.insert(sysLog);
    }

    // 登陆日志
    public int insertLoginLog(LoginLog log){
        return sysLogDao.insertLoginLog(log);
    }

    // 操作日志记录
    public PageInfo<SysLog> sysLogList(int pageNum, int pageSize,long user_id,int status,String start_time,String end_time){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("user_id",user_id);
        map.put("status",status);
        map.put("start_time", Validators.isEmpty(start_time) == true ? null : start_time+":00");
        map.put("end_time",Validators.isEmpty(end_time) == true ? null : end_time+":59");
        PageHelper.startPage((pageNum / pageSize) + 1,pageSize);
        List<SysLog> list = sysLogDao.sysLogList(map);
        PageInfo<SysLog> page = new PageInfo<SysLog>(list);
        return page;
    }

    // 登陆日志记录
    public PageInfo<LoginLog> loginLogList(int pageNum, int pageSize,long user_id,String start_time,String end_time){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("user_id",user_id);
        map.put("start_time", Validators.isEmpty(start_time) == true ? null : start_time+":00");
        map.put("end_time",Validators.isEmpty(end_time) == true ? null : end_time+":59");
        PageHelper.startPage((pageNum / pageSize) + 1,pageSize);
        List<LoginLog> list = sysLogDao.loginLogList(map);
        PageInfo<LoginLog> page = new PageInfo<LoginLog>(list);
        return page;
    }

    // 写入系统资源报警记录
    public void serverLog(Map<String,Object> map){
        sysLogDao.serverLog(map);
    }
}
