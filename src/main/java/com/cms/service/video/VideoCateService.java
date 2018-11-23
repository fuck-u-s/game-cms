package com.cms.service.video;

import com.cms.dao.video.VideoCateDao;
import com.cms.model.video.Video;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 方法描述: 类型管理
 * author ZELD、
 * version v1.0
 * date 2018/9/30
 */
@Service
public class VideoCateService {

    @Autowired
    private VideoCateDao videoCateDao;

    // 类型管理
    public PageInfo<Map<String,Object>> cateList(String title,int pageNum,int pageSize){
        PageHelper.startPage((pageNum / pageSize) + 1,pageSize);
        List<Map<String,Object>> list = videoCateDao.cateList();
        PageInfo<Map<String,Object>> info = new PageInfo<>(list);
        return info;
    }

    // 设置类型
    public int setCate(String name){
        Map<String,Object> map = new HashMap<>();
        map.put("name",name);
        return videoCateDao.setCate(map);
    }

    // 类型信息
    public Map<String,Object> cateInfo(int id){

        return videoCateDao.cateInfo(id);
    }

    // 编辑信息
    public int updateCate(int id,String name){
        Map<String,Object> map = new HashMap<>();
        map.put("id",id);
        map.put("name",name);
        return videoCateDao.updateCate(map);
    }

    // 删除信息
    public int deleteCate(int id){

        return videoCateDao.deleteCate(id);
    }

    // 类型列表
    public List<Map<String,Object>> cateList(){

        return videoCateDao.cateList();
    }
}
