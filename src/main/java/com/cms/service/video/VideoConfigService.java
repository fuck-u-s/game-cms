package com.cms.service.video;

import com.cms.dao.video.VideoConfigDao;
import com.cms.model.video.VideoConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 方法描述: 视频参数设置
 * author ZELD、
 * version v1.0
 * date 2018/11/1
 */
@Service
public class VideoConfigService {

    @Autowired
    private VideoConfigDao videoConfigDao;

    // 获取配置参数
    public VideoConfig getConfigKey(String keys){

        return videoConfigDao.getConfigKey(keys);
    }

}
