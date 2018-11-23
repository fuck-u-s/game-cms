package com.cms.model.video;

/**
 * 方法描述: 视频点赞
 * author ZELD、
 * version v1.0
 * date 2018/10/15
 */
public class ReplyTop {

    private long id;
    private long v_id;
    private long comment_id;
    private long user_id;
    private long reply_id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getV_id() {
        return v_id;
    }

    public void setV_id(long v_id) {
        this.v_id = v_id;
    }

    public long getComment_id() {
        return comment_id;
    }

    public void setComment_id(long comment_id) {
        this.comment_id = comment_id;
    }

    public long getReply_id() {
        return reply_id;
    }

    public void setReply_id(long reply_id) {
        this.reply_id = reply_id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }
}
