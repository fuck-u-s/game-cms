package com.cms.dao.sys;

import com.cms.model.sys.Dept;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 方法描述:部门管理
 *
 * @author 小刘
 * @version v1.0
 * @date 2016/7/6
 */
 @Repository
public interface DeptDao {
    // 创建
    int insert(Dept dept);
    // 删除
    int delete(Map<String, Object> map);
    // 修改
    int update(Dept dept);
    // 查询
    Dept get(Map<String, Object> map);
    // 列表
    List<Dept> list();
    // 是否存在相同部门
    int hasDept(Dept dept);
    // 部门下是否存在人员
    int hasUser(long id);
}
