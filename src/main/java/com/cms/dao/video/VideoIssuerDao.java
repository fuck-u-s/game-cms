package com.cms.dao.video;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 方法描述: 视频发布人
 * author ZELD、
 * version v1.0
 * date 2018/9/26
 */
@Repository
public interface VideoIssuerDao {

    // 视频发布者管理
    List<Map<String,Object>> issuerList(Map<String,Object> map);

    // 所有发布者
    List<Map<String,Object>> issList();

    // 新增发布者
    int setIssuer(Map<String,Object> map);

    // 发布者信息
    Map<String,Object> issuerInfo(int id);

    // 信息修改
    int updateIssuer(Map<String,Object> map);

    // 删除发布者
    int deleteIssuer(Map<String,Object> map);

    // 视频人气/数量
    Map<String,Object> videoHot(int issuer);
}
