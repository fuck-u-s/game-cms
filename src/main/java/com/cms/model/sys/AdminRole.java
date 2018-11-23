package com.cms.model.sys;

import com.xuan.utils.DateUtils;

import java.io.Serializable;
import java.util.Date;

// 角色
public class AdminRole implements Serializable {

    private Long id;

    private String name;

    private Byte status;

    private Date create_time;

    private int isAuth;

    private String createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public int getIsAuth() {
        return isAuth;
    }

    public void setIsAuth(int isAuth) {
        this.isAuth = isAuth;
    }

    public String getCreateTime() {
        createTime = DateUtils.date2String(getCreate_time(),"yyyy-MM-dd HH:mm:ss");
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}