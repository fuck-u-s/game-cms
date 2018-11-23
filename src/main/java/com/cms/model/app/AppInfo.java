package com.cms.model.app;

/**
 * 方法描述:小程序配置信息
 * <p>
 * author 小刘
 * version v1.0
 * date 2017/8/9 17:08
 */
public class AppInfo {
    private long id;
    private String name;
    private String appid;
    private String secret;
    private String token;
    private String aeskey;
    private String match_id;
    private String mchappid;
    private String match_password;
    private String cert;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApp_id() {
        return appid;
    }

    public void setApp_id(String app_id) {
        this.appid = app_id;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAeskey() {
        return aeskey;
    }

    public void setAeskey(String aeskey) {
        this.aeskey = aeskey;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMatch_id() {
        return match_id;
    }

    public void setMatch_id(String match_id) {
        this.match_id = match_id;
    }

    public String getMchappid() {
        return mchappid;
    }

    public void setMchappid(String mchappid) {
        this.mchappid = mchappid;
    }

    public String getMatch_password() {
        return match_password;
    }

    public void setMatch_password(String match_password) {
        this.match_password = match_password;
    }

    public String getCert() {
        return cert;
    }

    public void setCert(String cert) {
        this.cert = cert;
    }
}
