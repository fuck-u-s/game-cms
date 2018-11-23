package com.cms.model.video;

/**
 * 方法描述: 视频参数设置
 * author ZELD、
 * version v1.0
 * date 2018/11/1
 */
public class VideoConfig {

    private int id;
    private String name;
    private String keys;
    private String vals;
    private String remark;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKeys() {
        return keys;
    }

    public void setKeys(String keys) {
        this.keys = keys;
    }

    public String getVals() {
        return vals;
    }

    public void setVals(String vals) {
        this.vals = vals;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
