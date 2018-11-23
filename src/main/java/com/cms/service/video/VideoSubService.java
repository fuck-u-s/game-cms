package com.cms.service.video;

import com.cms.dao.video.VideoSubDao;
import com.cms.model.video.SubUser;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 方法描述: 关注
 * author ZELD、
 * version v1.0
 * date 2018/10/10
 */
@Service
public class VideoSubService {

    @Autowired
    private VideoSubDao videoSubDao;

    // 关注列表
    public PageInfo<SubUser> userSubList(long user_id,int pageNum,int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<SubUser> list = videoSubDao.userSubList(user_id);
        PageInfo<SubUser> info = new PageInfo<>(list);
        return info;
    }

    // 关注
    public int subscribe(long user_id,int issuer){
        Map<String,Object> map = new HashMap<>();
        map.put("user_id",user_id);
        map.put("issuer",issuer);
        int i = videoSubDao.isFollow(map);
        if(i == 0){
            videoSubDao.subscribe(map);
            return 1;
        }else {
            videoSubDao.deleSubscribe(map);
            return -1;
        }
    }

    // 取消关注
    public int deleSubscribe(long user_id,int issuer){
        Map<String,Object> map = new HashMap<>();
        map.put("user_id",user_id);
        map.put("issuer",issuer);
        return videoSubDao.deleSubscribe(map);
    }

    // 是否关注
    public int isFollow(int issuer,long user_id){
        Map<String,Object> map = new HashMap<>();
        map.put("user_id",user_id);
        map.put("issuer",issuer);
        return videoSubDao.isFollow(map);
    }

    // 关注列表
    public PageInfo<Map<String,Object>> subUserList(long user_id,int pageNum,int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Map<String,Object>> list = videoSubDao.subUserList(user_id);
        PageInfo<Map<String,Object>> info = new PageInfo<>(list);
        return info;
    }

}
