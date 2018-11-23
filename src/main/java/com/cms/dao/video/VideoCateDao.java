package com.cms.dao.video;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 方法描述: 类型
 * author ZELD、
 * version v1.0
 * date 2018/9/30
 */
@Repository
public interface VideoCateDao {

    // 类型管理
    List<Map<String,Object>> cateList();

    // 设置类型
    int setCate(Map<String,Object> map);

    // 类型信息
    Map<String,Object> cateInfo(long id);

    // 编辑类型
    int updateCate(Map<String,Object> map);

    // 删除类型
    int deleteCate(long id);
}
