package com.cms.service.video;

import com.cms.dao.video.VideoIssuerDao;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 方法描述: 视频发布者
 * author ZELD、
 * version v1.0
 * date 2018/9/26
 */
@Service
public class VideoIssuerService {

    @Autowired
    private VideoIssuerDao videoIssuerDao;

    // 视频发布者
    public PageInfo<Map<String,Object>> issuerList(String name,int state,int pageNum,int pageSize){
        Map<String,Object> map = new HashMap<>();
        map.put("name",name);
        map.put("state",state);
        PageHelper.startPage((pageNum / pageSize) + 1,pageSize);
        List<Map<String,Object>> list = videoIssuerDao.issuerList(map);
        PageInfo<Map<String,Object>> info = new PageInfo<>(list);
        return info;
    }

    // 所有发布者
    public List<Map<String,Object>> issList(){

        return videoIssuerDao.issList();
    }

    // 新增发布者
    public int setIssuer(String name,String cover){
        Map<String,Object> map = new HashMap<>();
        map.put("name",name);
        map.put("cover",cover);
        return videoIssuerDao.setIssuer(map);
    }

    // 发布者信息
    public Map<String,Object> issuerInfo(int id){

        return videoIssuerDao.issuerInfo(id);
    }

    // 修改信息
    public int updateIssuer(int id,String name,String cover){
        Map<String,Object> map = new HashMap<>();
        map.put("id",id);
        map.put("name",name);
        map.put("cover",cover);
        return videoIssuerDao.updateIssuer(map);
    }

    // 删除发布者
    public int deleteIssuer(int id,int state){
        Map<String,Object> map = new HashMap<>();
        map.put("id",id);
        map.put("state",state);
        return videoIssuerDao.deleteIssuer(map);
    }

    // 视频数量/人气
    public Map<String,Object> videoHot(int issuer){

        return videoIssuerDao.videoHot(issuer);
    }
}
