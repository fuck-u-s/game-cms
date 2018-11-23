package com.cms.dao.sys;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 方法描述:参数设置
 * <p>
 * author 小刘
 * version v1.0
 * date 2017/4/5 23:45
 */
@Repository
public interface ConfigDao {

    // 参数列表
    List<Map<String,Object>> configList();

    // 新增参数
    int newConfig(Map<String, Object> map);

    // 修改参数
    int updateConfig(Map<String, Object> map);

    // 查询参数
    Map<String,Object> config(long id);

    // 修改参数值
    int setVal(Map<String, Object> map);

    // 获取参数值
    String getVal(long id);
}
