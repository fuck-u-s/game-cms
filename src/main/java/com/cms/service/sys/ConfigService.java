package com.cms.service.sys;

import com.cms.dao.sys.ConfigDao;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 方法描述:参数配置
 * <p>
 * author 小刘
 * version v1.0
 * date 2017/4/5 23:47
 */
@Service
public class ConfigService {

    @Autowired
    private ConfigDao configDao;

    // 参数列表
    public PageInfo<Map<String,Object>> configList(int pageNum, int pageSize){
        PageHelper.startPage((pageNum / pageSize) + 1,pageSize);
        List<Map<String,Object>> list = configDao.configList();
        PageInfo<Map<String,Object>> info = new PageInfo<Map<String,Object>>(list);
        return info;
    }

    // 设置参数
    public int setConfig(long id,String name,String val,String remark){
        Map<String,Object> map = new HashMap();
        map.put("id",id);
        map.put("name",name);
        map.put("val",val);
        map.put("remark",remark);
        if(id > 0){
            return configDao.updateConfig(map);
        }else{
            return configDao.newConfig(map);
        }
    }

    // 查看参数
    public Map<String,Object> config(long id){
        return configDao.config(id);
    }

    // 修改参数值
    public int setVal(long id,String val){
        Map<String,Object> map = new HashMap<>();
        map.put("id",id);
        map.put("val",val);
        return configDao.setVal(map);
    }

    // 获取参数值
    public String getVal(long id){
        return configDao.getVal(id);
    }
}
