package com.cms.service.video;

import com.cms.dao.video.VideoCommentDao;
import com.cms.model.video.ReplyTop;
import com.cms.model.video.VComment;
import com.cms.utils.RelativeDateFormat;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 方法描述: 视频评论
 * author ZELD、
 * version v1.0
 * date 2018/10/15
 */
@Service
public class VideoCommentService {

    @Autowired
    private VideoCommentDao videoCommentDao;

    // 视频评论列表
    public PageInfo<VComment> commentList(long vid,long user_id,int pageNum,int pageSize){
        PageHelper.startPage((pageNum / pageSize) + 1,pageSize);
        List<VComment> list = videoCommentDao.commentList(vid);
        // 点赞
        List<ReplyTop> topList = userTopList(vid,user_id);
        for(VComment comment:list){
            comment.setDateTime(RelativeDateFormat.format(comment.getCreate_time()));
            for(ReplyTop replyTop : topList){
                if(comment.getId() == replyTop.getComment_id()){
                    comment.setFlag(true);
                }
            }
        }
        PageInfo<VComment> info = new PageInfo<>(list);
        return info;
    }

    // 评论视频
    public int videoReply(VComment comment){

        return videoCommentDao.videoReply(comment);
    }

    // 评论点赞
    @Transactional
    public int replayClick(long v_id,long comment_id,long user_id,long reply_id){
        Map<String,Object> map = new HashMap<>();
        map.put("v_id",v_id);
        map.put("comment_id",comment_id);
        map.put("user_id",user_id);
        map.put("reply_id",reply_id);
        videoCommentDao.replayClick(map);
        return videoCommentDao.addNum(map);
    }

    // 点赞列表
    public List<ReplyTop> userTopList(long v_id,long user_id){
        Map<String,Object> map = new HashMap<>();
        map.put("v_id",v_id);
        map.put("user_id",user_id);
        return videoCommentDao.userTopList(map);
    }

    // 删除评论
    public int deleteReply(long id){

        return videoCommentDao.deleteReply(id);
    }

    // 视频评论列表
    public PageInfo<VComment> replyList(long user_id,int pageNum,int pageSize){
        PageHelper.startPage((pageNum / pageSize) + 1,pageSize);
        List<VComment> list = videoCommentDao.replyList(user_id);
        for(VComment comment:list){
            comment.setDateTime(RelativeDateFormat.format(comment.getCreate_time()));
        }
        PageInfo<VComment> info = new PageInfo<>(list);
        return info;
    }
}
