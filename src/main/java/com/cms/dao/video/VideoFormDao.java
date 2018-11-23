package com.cms.dao.video;

import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * 方法描述: 模板编号记录
 * author ZELD、
 * version v1.0
 * date 2018/10/18
 */
@Repository
public interface VideoFormDao {

    // 记录模板
    int setFormId(Map<String,Object> map);

}
