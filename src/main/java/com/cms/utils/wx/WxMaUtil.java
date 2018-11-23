package com.cms.utils.wx;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.config.WxMaInMemoryConfig;
import com.cms.model.app.AppInfo;

import java.util.HashMap;
import java.util.Map;

public class WxMaUtil {

    private static Map<String, WxMaInMemoryConfig> configMaMap = new HashMap<>();

    private static Map<String, WxMaService> serviceMaMap = new HashMap<>();

    // 小程序服务类
    public static WxMaService instenceWxMaService(String key) {
        WxMaService service = serviceMaMap.get(key + "Service");
        if(null == service) {
            WxMaInMemoryConfig config = getMaConfig(key);
            service = new WxMaServiceImpl();
            service.setWxMaConfig(config);
            serviceMaMap.put(key + "Service", service);
        }
        return service;
    }

    // 小程序配置信息
    public static WxMaInMemoryConfig getMaConfig(String key) {
        WxMaInMemoryConfig config = configMaMap.get(key + "Config");
        if(null == config) {
            AppInfo appInfo = WeiXinUtil.getAppInfo(key);
            config = getMaConfig(appInfo.getApp_id(), appInfo.getSecret(), appInfo.getToken(), appInfo.getAeskey(), "YYYY-MM-DD HH:mm:ss");
            configMaMap.put(key + "Config", config);
        }

        return config;
    }

    public static WxMaInMemoryConfig getMaConfig(String appID, String appsecret, String token, String EncodingAESKey, String format) {
        WxMaInMemoryConfig config = new WxMaInMemoryConfig();
        config.setAppid(appID);// 设置微信公众号的appid
        config.setSecret(appsecret);// 设置微信公众号的app corpSecret
        config.setToken(token);// 设置微信公众号的token
        config.setAesKey(EncodingAESKey);// 设置微信公众号的EncodingAESKey
        config.setMsgDataFormat(format);
        return config;
    }
}
