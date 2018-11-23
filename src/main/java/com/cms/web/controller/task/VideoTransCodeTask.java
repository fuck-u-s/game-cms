package com.cms.web.controller.task;

import com.cms.model.video.Video;
import com.cms.service.video.VideoService;
import com.cms.utils.Convert;
import com.cms.utils.Logger;
import com.cms.utils.ResultUtil;
import com.cms.utils.aliyun.FileUtil;
import com.cms.utils.video.convert.ConverVideo;
import com.cms.utils.video.info.VideoInfo;
import com.cms.utils.video.info.VideoInfoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

/**
 * 方法描述: 视频转码
 * author ZELD、
 * version v1.0
 * date 2018/10/31
 */
@Component
public class VideoTransCodeTask {

    @Autowired
    private VideoService videoService;

    // 是否正在执行
    private boolean isRuning = false;

    // 视频转码
    public void videoTransCode(){
        // 判断任务是否执行
        if (!isRuning) {
            isRuning = true;
        } else {
            return;
        }
        try {
            // 待解码列表
            List<Video> list = videoService.transList();
            for(Video video:list){

                // 视频名称
                String uuid = UUID.randomUUID().toString().replace("-","");

                // 带解析视频信息
                VideoInfo videoInfo = VideoInfoUtils.getVideoInfo(video.getUrl());
                int rate = Convert.strToInt(videoInfo.getBitrateSize(),500);

                // 带解析的视频
                File file = new File(video.getUrl());

                if(file.exists()){

                    // 后缀名
                    String prefix = video.getUrl().substring(video.getUrl().lastIndexOf("."));

                    // 视频压缩
                    if(rate > 500){
                        ConverVideo cv = new ConverVideo(video.getUrl());
                        boolean flag = cv.beginConver();
                        if(flag){
                            String url = video.getUrl().replace("video/","video/mp4/");
                            file = new File(url);
                        }else {
                            Logger.error("视频转码失败:"+ResultUtil.toJSON(video));
                            return ;
                        }
                    }

                    InputStream is = new FileInputStream(file);

                    // 同步到阿里云
                    String result = FileUtil.putStream("miniapp/video/media/" + uuid + prefix, is);

                    // 同步视频URL
                    video.setUrl(result);
                    video.setState(3);
                    videoService.syncVideoInfo(video);

                    // 删除视频
                    file.delete();
                }else {
                    Logger.error("压缩目标视频不存在："+ResultUtil.toJSON(video));
                }
            }
        }catch (Exception e){
            isRuning = false;
            e.printStackTrace();
            Logger.error("视频解析出错",e);
        }
    }
}
