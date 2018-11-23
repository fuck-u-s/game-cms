package com.cms.service.sys;

import com.cms.dao.sys.SysConfigDao;
import com.cms.model.sys.SysConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 方法描述:参数管理
 * <p>
 * author 小刘
 * version v1.0
 * date 2017/4/16 15:05
 */
@Service
public class SysConfigService {

    @Autowired
    private SysConfigDao sysConfigDao;

    // 参数设置
    public int thisConfig(SysConfig config){
        return sysConfigDao.thisConfig(config);
    }

    // 设置参数
    public int setConfig(SysConfig config){
        return sysConfigDao.setConfig(config);
    }

    // 参数设置
    public int setConf(SysConfig config){
        return sysConfigDao.setConf(config);
    }

    // 获取参数
    public SysConfig getConfig(long id){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("id",id);
        return sysConfigDao.viewConfig(map);
    }

    // 删除参数
    public int deleteConfig(long id){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("id",id);
        return sysConfigDao.deleConfig(map);
    }
}
