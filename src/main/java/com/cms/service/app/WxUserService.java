package com.cms.service.app;

import com.cms.dao.app.WxUserDao;
import com.cms.model.app.WxUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 方法描述: 微信用户
 * author ZELD、
 * version v1.0
 * date 2018/10/8
 */
@Service
public class WxUserService {

    @Autowired
    private WxUserDao wxUserDao;

    // 同步登录用户
    public WxUser saveWxUser(WxUser wxUser) {
        wxUserDao.saveWxUser(wxUser);
        return wxUser;
    }

    // 微信用户信息
    public WxUser wxUserInfo(String openId){
        Map<String,Object> map = new HashMap<>();
        map.put("open_id",openId);
        return wxUserDao.wxUserInfo(map);
    }
}
