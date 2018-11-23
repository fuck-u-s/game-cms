package com.cms.dao.sys;

import com.cms.model.sys.SysConfig;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * 方法描述:参数设置
 * <p>
 * author 小刘
 * version v1.0
 * date 2017/4/16 15:01
 */
@Repository
public interface SysConfigDao {

    // 查看参数是否使用
    int isUsed(Map<String,Object> map);

    // 新增参数
    int thisConfig(SysConfig config);

    // 修改参数
    int setConfig(SysConfig config);

    // 参数修改
    int setConf(SysConfig config);

    // 获取参数
    SysConfig viewConfig(Map<String,Object> map);

    // 删除参数
    int deleConfig(Map<String,Object> map);
}
