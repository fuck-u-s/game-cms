package com.cms.dao.video;

import com.cms.model.video.ReplyTop;
import com.cms.model.video.VComment;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 方法描述: 视频评论
 * author ZELD、
 * version v1.0
 * date 2018/10/15
 */
@Repository
public interface VideoCommentDao {

    // 视频评论
    List<VComment> commentList(long vid);

    // 用户评论
    int videoReply(VComment comment);

    // 评论点赞
    int replayClick(Map<String,Object> map);

    // 点赞数量
    int addNum(Map<String,Object> map);

    // 点赞列表
    List<ReplyTop> userTopList(Map<String,Object> map);

    // 删除评论
    int deleteReply(long id);

    // 我的评论
    List<VComment> replyList(long user_id);
}
