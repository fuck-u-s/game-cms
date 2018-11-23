package com.cms.dao.video;

import com.cms.model.video.SubUser;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 方法描述: 用户关注
 * author ZELD、
 * version v1.0
 * date 2018/10/10
 */
@Repository
public interface VideoSubDao {

    // 用户关注列表
    List<SubUser> userSubList(long user_id);

    // 是否关注
    int isFollow(Map<String,Object> map);

    // 关注
    int subscribe(Map<String,Object> map);

    // 取消关注
    int deleSubscribe(Map<String,Object> map);

    // 我的关注
    List<Map<String,Object>> subUserList(long user_id);
}
