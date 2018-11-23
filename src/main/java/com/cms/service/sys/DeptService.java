package com.cms.service.sys;

import com.cms.dao.sys.DeptDao;
import com.cms.model.sys.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 方法描述:描述
 *
 * @author 小刘
 * @version v1.0
 * @date 2016/7/6
 */
 @Service
public class DeptService {

    @Autowired
    private DeptDao deptDao;

    // 部门列表
    public List<Dept> list(){
        List<Dept> list = deptDao.list();
        return list;
    }

    // 创建部门
    public int insert(Dept dept){
        int hasDept = deptDao.hasDept(dept);
        if(hasDept > 0){
            return -1001;
        }else{
            return deptDao.insert(dept);
        }
    }

    // 删除部门
    public int delete(long id){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("id",id);
        // 部门下是否存在人员
        int hasUser = deptDao.hasUser(id);
        if(hasUser > 0){
            return -1001;
        }else{
            return deptDao.delete(map);
        }
    }

    // 修改部门
    public int update(Dept dept){
        int hasDept = deptDao.hasDept(dept);
        if(hasDept > 0){
            return -1001;
        }else{
            return deptDao.update(dept);
        }
    }

    // 查看部门
    public Dept get(long id){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("id",id);
        return deptDao.get(map);
    }
}
