package com.cms.service.video;

import com.cms.dao.video.VideoFormDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 方法描述: 模板记录管理
 * author ZELD、
 * version v1.0
 * date 2018/10/18
 */
@Service
public class VideoFormService {

    @Autowired
    private VideoFormDao videoFormDao;

    // 记录模板编号
    public int setFormId(long user_id,String form_id){
        Map<String,Object> map = new HashMap<>();
        map.put("user_id",user_id);
        map.put("form_id",form_id);
        return videoFormDao.setFormId(map);
    }

}
