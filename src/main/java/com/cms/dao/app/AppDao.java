package com.cms.dao.app;

import com.cms.model.app.AppInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 方法描述:小程序信息
 * <p>
 * author 小刘
 * version v1.0
 * date 2017/8/9 17:23
 */
@Repository
public interface AppDao {

    // 小程序列表
    List<AppInfo> appList();

    AppInfo appInfo(String appid);
}
