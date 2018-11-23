package com.cms.dao.app;

import com.cms.model.app.WxUser;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * 方法描述: 微信用户
 * author ZELD、
 * version v1.0
 * date 2018/10/8
 */
@Repository
public interface WxUserDao {

    // 微信用户信息
    WxUser wxUserInfo(Map<String,Object> map);

    // 新增微信用户
    int saveWxUser(WxUser wxUser);
}
