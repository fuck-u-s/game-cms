package com.cms.service.video;

import com.cms.dao.video.VideoDao;
import com.cms.dao.video.VideoSubDao;
import com.cms.model.video.KuaiBao;
import com.cms.model.video.SubUser;
import com.cms.model.video.Video;
import com.cms.utils.Constants;
import com.cms.utils.aliyun.FileUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xuan.utils.Validators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 方法描述: 视频管理
 * author ZELD、
 * version v1.0
 * date 2018/9/29
 */
@Service
public class VideoService {

    @Autowired
    private VideoDao videoDao;
    @Autowired
    private VideoSubDao videoSubDao;

    // 视频管理
    public PageInfo<Video> videoList(String title,int state,int cate,int pageNum,int pageSize){
        Map<String,Object> map = new HashMap<>();
        map.put("title",title);
        map.put("state",state);
        map.put("cate",cate);
        PageHelper.startPage(pageNum,pageSize);
        List<Video> list = videoDao.videoList(map);
        PageInfo<Video> info = new PageInfo<>(list);
        return info;
    }

    // 新增发布者
    public int setVideo(Video video){

        return videoDao.setVideo(video);
    }

    // 视频信息
    public Video videoInfo(int id){

        return videoDao.videoInfo(id);
    }

    // 编辑视频
    public int updateVideo(Video video){

        return videoDao.updateVideo(video);
    }

    // 删除视频
    public int deleteVideo(int id,int state){
        Map<String,Object> map = new HashMap<>();
        map.put("id",id);
        map.put("state",state);
        return videoDao.deleteVideo(map);
    }

    // 视频列表
    public PageInfo<Video> vList(long user_id,int cate,int pageNum,int pageSize){
        Map<String,Object> map = new HashMap<>();
        map.put("cate",cate);
        PageHelper.startPage(pageNum,pageSize);
        List<Video> list = videoDao.videoList(map);
        if(user_id > 0){
            // 用户关注
            List<SubUser> subList = videoSubDao.userSubList(user_id);
            for(Video video : list){
                for(SubUser subUser : subList){
                    if(video.getIssuer() == subUser.getIssuer()){
                        video.setFlag(true);
                        break;
                    }
                }
            }
        }
        PageInfo<Video> info = new PageInfo<>(list);
        return info;
    }

    // 视频详情
    public Map<String,Object> videoDetail(int id){

        return videoDao.videoDetail(id);
    }

    // 发布者视频合集
    public PageInfo<Video> issuerVideoList(int issuer,int pageNum,int pageSize){
        Map<String,Object> map = new HashMap<>();
        map.put("issuer",issuer);
        PageHelper.startPage(pageNum,pageSize);
        List<Video> list = videoDao.videoList(map);
        PageInfo<Video> info = new PageInfo<>(list);
        return info;
    }

    // 待解析视频
    public List<Video> transList(){

        return videoDao.transList();
    }

    // 同步转码后的视频信息
    public int syncVideoInfo(Video video){

        return videoDao.syncVideoInfo(video);
    }

    // 拉取快报视频
    public int syncKuaiBaoList(KuaiBao kuaiBao){

        return videoDao.syncKuaiBaoList(kuaiBao);
    }

    // 快报视频
    public PageInfo<KuaiBao> kuaiBaoList(String id,String vid,int state,int pageNum,int pageSize){
        Map<String,Object> map = new HashMap<>();
        map.put("id",id);
        map.put("vid",vid);
        map.put("state",state);
        PageHelper.startPage(pageNum,pageSize);
        List<KuaiBao> list = videoDao.kuaiBaoList(map);
        for(KuaiBao kuaiBao:list){
            kuaiBao.setImg(Constants.IMAGEURL+"/"+kuaiBao.getVid()+".jpg");
        }
        PageInfo<KuaiBao> info = new PageInfo<>(list);
        return info;
    }

    // 删除快报视频
    public int deleteKuaiBao(String id,String vid){
        // 删除封面
        File file = new File(Constants.IMAGEPATH+vid+".jpg");
        if(file.exists()){
            file.delete();
        }
        return videoDao.deleteKuaiBao(id);
    }

    // 快报视频信息
    public KuaiBao kuaiBaoInfo(String id){
        KuaiBao info = videoDao.kuaiBaoInfo(id);
        // 视频封面
        info.setImg(Constants.IMAGEURL+"/"+info.getVid()+".jpg");
        // 是否有封面
        File file = new File(Constants.IMAGEPATH+info.getVid()+".jpg");
        if(file.exists()){
            info.setHasCover(true);
        }else {
            info.setHasCover(false);
        }
        return info;
    }

    // 同步快报视频到本地视频
    @Transactional
    public int syncToVideo(Video video) throws Exception {
        if(Validators.isEmpty(video.getCover())){
            File file = new File(Constants.IMAGEPATH+"/"+video.getVid()+".jpg");
            if(file.exists()){
                InputStream is = new FileInputStream(file);
                // 同步封面到OSS
                String filePath = FileUtil.putStream("miniapp/video/media/" + video.getVid() + ".jpg", is);
                video.setCover(filePath);
            }
        }

        // 快报视频信息
        KuaiBao info = videoDao.kuaiBaoInfo(video.getKid());
        video.setTitle(info.getTitle());
        video.setVtime(info.getDuration());
        video.setState(3);

        // 同步数据
        int i = videoDao.setVideo(video);
        // 同步快报状态
        resetKuaiBao(info.getId(),2);
        return i;
    }

    // 回执快报处理状态
    public int resetKuaiBao(String id,int state){
        Map<String,Object> map = new HashMap<>();
        map.put("id",id);
        map.put("state",state);
        return videoDao.resetKuaiBao(map);
    }

    // 待下发视频
    public KuaiBao waiteKuaiBaoList(){
        KuaiBao info = videoDao.waiteKuaiBaoList();
        Map<String,Object> map = new HashMap<>();
        map.put("id",info.getId());
        map.put("ispush",2);
        videoDao.setPush(map);
        return info;
    }
}
