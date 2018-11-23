package com.cms.model.sys;

import java.io.Serializable;

/**
 * 方法描述:参数
 * <p>
 * author 小刘
 * version v1.0
 * date 2017/4/16 14:52
 */
public class SysConfig implements Serializable{

    private long id;
    private String name;
    private String key;
    private String val;

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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }
}
