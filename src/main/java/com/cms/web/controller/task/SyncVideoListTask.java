package com.cms.web.controller.task;

import com.cms.model.video.KuaiBao;
import com.cms.service.video.VideoService;
import com.cms.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 方法描述: 第三方视频同步
 * author ZELD、
 * version v1.0
 * date 2018/10/31
 */
@Component
public class SyncVideoListTask {

    @Autowired
    private VideoService videoService;

    // 同步视频
    public void syncKuaiBaoList(){
        for(int i=1;i <= 2;i++){
            String url = "http://kuaibao.qq.com/getAggrHotVideos?page="+i;
            try {
                String res = HttpUtils.GET(url);
                Map<String,Object> map = ResultUtil.fromJSON(res);
                double ret = Convert.strToDouble(map.get("ret")+"",2);
                if(ret == 0){
                    List<Map<String,Object>> list = (List<Map<String, Object>>) map.get("newslist");
                    for(Map<String,Object> obj:list){
                        // 视频信息
                        Map<String,Object> video_channel = (Map<String, Object>) obj.get("video_channel");
                        Map<String,Object> video = (Map<String, Object>) video_channel.get("video");
                        KuaiBao kuaiBao = new KuaiBao();
                        kuaiBao.setId(obj.get("id")+"");
                        kuaiBao.setTitle(obj.get("title")+"");
                        kuaiBao.setSource(obj.get("source")+"");
                        kuaiBao.setUrl(obj.get("url")+"");
                        kuaiBao.setVid(video.get("vid")+"");
                        kuaiBao.setDesc(video.get("desc")+"");
                        kuaiBao.setImg(video.get("img")+"");
                        kuaiBao.setWidth(video.get("width")+"");
                        kuaiBao.setHeight(video.get("height")+"");
                        kuaiBao.setPlayurl(video.get("playurl")+"");
                        kuaiBao.setDuration(video.get("duration")+"");
                        videoService.syncKuaiBaoList(kuaiBao);

                        // 下载图片
                        FileUtils.downloadByNIO2(video.get("img")+"",Constants.IMAGEPATH,video.get("vid")+".jpg");
                    }
                }else {
                    System.out.println("快报视频数据拉取失败...:"+res);
                }
            } catch (Exception e) {
                e.printStackTrace();
                Logger.error(e);
            }
        }
    }

    public static void main(String[] args){
        String url = "http://inews.gtimg.com/newsapp_ls/0/1078769330_640480/0";
        try {
            FileUtils.downloadByNIO2(url,"/home/resource/image","111.jpg");
        } catch (Exception e) {
            e.printStackTrace();
            Logger.error(e);
        }
    }
}
