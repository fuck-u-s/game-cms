package com.cms.dao.video;

import com.cms.model.video.KuaiBao;
import com.cms.model.video.Video;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 方法描述: 视频
 * author ZELD、
 * version v1.0
 * date 2018/9/26
 */
@Repository
public interface VideoDao {

    // 视频管理
    List<Video> videoList(Map<String,Object> map);

    // 新增视频
    int setVideo(Video video);

    // 视频信息
    Video videoInfo(long id);

    // 编辑视频
    int updateVideo(Video video);

    // 删除视频
    int deleteVideo(Map<String,Object> map);

    // 视频详情
    Map<String,Object> videoDetail(int id);

    // 发布者视频
    List<Map<String,Object>> issuerVideoList(Map<String,Object> map);

    // 待解析视频
    List<Video> transList();

    // 同步转码后的视频信息
    int syncVideoInfo(Video video);

    // 拉取快报视频
    int syncKuaiBaoList(KuaiBao kuaiBao);

    // 待处理快报视频
    List<KuaiBao> kuaiBaoList(Map<String,Object> map);

    // 删除快报视频
    int deleteKuaiBao(String id);

    // 回执快报处理状态
    int resetKuaiBao(Map<String,Object> map);

    // 快报视频信息
    KuaiBao kuaiBaoInfo(String id);

    // 待处理视频下发
    KuaiBao waiteKuaiBaoList();

    // 设置下发
    int setPush(Map<String,Object> map);
}
