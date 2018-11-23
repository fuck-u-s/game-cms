package com.cms.service.app;

import com.cms.dao.app.AppDao;
import com.cms.model.app.AppInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 方法描述:小程序信息
 * <p>
 * author 小刘
 * version v1.0
 * date 2017/8/9 17:27
 */
@Service
public class AppInfoService {

    @Autowired
    private AppDao appDao;

    // 小程序信息列表
    @Cacheable(key="'appInfoList'", value="appInfoList")
    public List<AppInfo> appList(){
        List<AppInfo> list = appDao.appList();
        return list;
    }

    // 小程序信息
    @Cacheable(key="'appInfo_'+#appid", value="appInfo", unless="#result == null")
    public AppInfo appInfo(String appid){
        AppInfo appInfo = appDao.appInfo(appid);
        return appInfo;
    }
}
