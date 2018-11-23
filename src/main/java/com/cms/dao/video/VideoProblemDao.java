package com.cms.dao.video;

import com.cms.model.video.Problems;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 方法描述: 意见管理
 * author ZELD、
 * version v1.0
 * date 2018/10/11
 */
@Repository
public interface VideoProblemDao {

    // 意见列表
    List<Problems> problemList(Map<String,Object> map);

    // 记录时间
    int lastProblem(long user_id);

    // 记录意见
    int newProblem(Problems problems);
}
