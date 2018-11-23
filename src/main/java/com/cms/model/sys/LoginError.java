package com.cms.model.sys;

/**
 * 方法描述:错误对象
 * <p>
 * author 小刘
 * version v1.0
 * date 2017/3/30 17:41
 */
public class LoginError {
    // 错误码
    private int code = 0;
    // 错误消息
    private String msg = "";

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
