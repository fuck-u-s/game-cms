package com.cms.service.video;

import com.cms.dao.video.VideoProblemDao;
import com.cms.model.video.Problems;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 方法描述: 意见管理
 * author ZELD、
 * version v1.0
 * date 2018/10/11
 */
@Service
public class VideoProblemService {

    @Autowired
    private VideoProblemDao videoProblemDao;

    // 意见管理
    public PageInfo<Problems> problemList(long user_id,int pageNum,int pageSize){
        Map<String,Object> map = new HashMap<>();
        map.put("user_id",user_id);
        PageHelper.startPage((pageNum / pageSize) + 1,pageSize);
        List<Problems> list = videoProblemDao.problemList(map);
        PageInfo<Problems> info = new PageInfo<>(list);
        return info;
    }

    // 记录意见
    public int newProblem(long user_id,String content){
        int i = videoProblemDao.lastProblem(user_id);
        if(i > 0){
            return -1;
        }else {
            Problems problems = new Problems();
            problems.setUser_id(user_id);
            problems.setContent(content);
            return videoProblemDao.newProblem(problems);
        }
    }
}
