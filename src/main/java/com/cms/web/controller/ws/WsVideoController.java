package com.cms.web.controller.ws;

import com.cms.model.video.KuaiBao;
import com.cms.model.video.Video;
import com.cms.service.video.VideoService;
import com.cms.utils.Constants;
import com.cms.utils.FileUtils;
import com.cms.utils.Logger;
import com.cms.utils.ResultUtil;
import com.cms.utils.aliyun.FileUtil;
import com.xuan.utils.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;


/**
 * 方法描述: 视频下发处理
 * author ZELD、
 * version v1.0
 * date 2018/11/2
 */
@RequestMapping("/ws/video/**")
@Controller
public class WsVideoController {

    @Autowired
    private VideoService videoService;

    // 快报视频下发
    @RequestMapping("/pull")
    @ResponseBody
    public String pull(){
//        Map<String,Object> map = new HashMap<>();
//        try {
//            KuaiBao info = videoService.waiteKuaiBaoList();
//            map.put("kid",info.getId());
//            map.put("vid",info.getVid());
//            map.put("url",info.getUrl());
//            return ResultUtil.toJSON(map);
//        }catch (Exception e){
//            e.printStackTrace();
//            Logger.error(e.getMessage());
//        }
        return "";
    }

    // 上报视频
    @RequestMapping("/push")
    @ResponseBody
    public void push(@RequestParam(defaultValue = "") String kid,
                       @RequestParam(defaultValue = "") String vid,
                       @RequestParam(defaultValue = "") String url){
        // 新线程处理视频
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // 快报信息
                    KuaiBao info = videoService.kuaiBaoInfo(kid);
                    if(info != null){
                        // 视频封面
                        File image = new File(Constants.IMAGEPATH+"/"+vid+".jpg");
                        if(image.exists()){
                            // 视频封面
                            InputStream imageIs = new FileInputStream(image);
                            // 更新到OSS
                            String resImage = FileUtil.putStream("miniapp/video/cover/" + vid+".jpg", imageIs);

                            // 下载视频
                            FileUtils.downloadByNIO2(url,Constants.VIDEOPATH,vid+".mp4");
                            // 下载完成的视频
                            File file = new File(Constants.VIDEOPATH+"/"+vid+".mp4");

                            if(file.exists()){

                                // 更新到OSS
                                InputStream videoIs = new FileInputStream(file);
                                String resVideo = FileUtil.putStream("miniapp/video/media/" + vid+".mp4", videoIs);

                                Video video = new Video();
                                video.setTitle(info.getTitle());
                                video.setCover(resImage);
                                video.setUrl(resVideo);
                                video.setVtime(info.getDuration());
                                video.setHot(RandomUtils.getRandomInt(1000,5000));
                                video.setCate(1);    // 推荐
                                video.setIssuer(2);  // 经典音乐
                                video.setState(3);   // 发布状态

                                videoService.setVideo(video);

                                videoService.resetKuaiBao(kid,2);

                                file.delete();
                            }else {
                                Logger.error("视频上报错误：没有获取到视频文件{kid: "+kid+",vid: "+vid+",url: "+url+"}");
                            }
                        }else {
                            Logger.error("视频上报错误：没有获取到视频封面{kid: "+kid+",vid: "+vid+",url: "+url+"}");
                        }
                    }else {
                        Logger.error("视频上报错误：没有获取到视频信息{kid: "+kid+",vid: "+vid+",url: "+url+"}");
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    Logger.error(e.getMessage());
                }
            }
        });
        t.start();
    }
}
