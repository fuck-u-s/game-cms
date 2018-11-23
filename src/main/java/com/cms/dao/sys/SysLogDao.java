package com.cms.dao.sys;

import com.cms.model.sys.LoginLog;
import com.cms.model.sys.SysLog;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 方法描述:TODO
 * <p>
 * author lzh
 * version v1.0
 * date 2016/7/9 15:58
 */
@Repository
public interface SysLogDao {
    // 操作日志
    int insert(SysLog sysLog);

    // 登陆日志
    int insertLoginLog(LoginLog loginLog);

    // 操作日志记录
    List<SysLog> sysLogList(Map<String, Object> map);

    // 操作日志记录
    List<LoginLog> loginLogList(Map<String, Object> map);

    // 系统资源报警记录
    void serverLog(Map<String,Object> map);
}
