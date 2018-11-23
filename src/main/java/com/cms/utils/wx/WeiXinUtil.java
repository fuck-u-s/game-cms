package com.cms.utils.wx;


import com.cms.model.app.AppInfo;
import com.cms.service.app.AppInfoService;
import com.cms.utils.SpringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Zephyr on 2016/12/28.
 */
public class WeiXinUtil {

    private static AppInfoService appInfoService = (AppInfoService) SpringUtils.getBean("appInfoService");

    private static Map<String, AppInfo> appInfoMap = new HashMap<>();

    public static AppInfo getAppInfo(String key) {
        AppInfo appInfo = appInfoMap.get(key);
        if(null == appInfo) {
            appInfo = appInfoService.appInfo(key);
            appInfoMap.put(key, appInfo);
        }
        return appInfo;
    }
}
