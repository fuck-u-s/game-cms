package com.cms.dao.video;

import com.cms.model.video.VideoConfig;
import org.springframework.stereotype.Repository;

/**
 * 方法描述: 视频参数设置
 * author ZELD、
 * version v1.0
 * date 2018/11/1
 */
@Repository
public interface VideoConfigDao {

    // 获取配置参数
    VideoConfig getConfigKey(String keys);

}
