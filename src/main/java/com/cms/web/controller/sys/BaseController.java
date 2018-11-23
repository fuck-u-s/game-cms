package com.cms.web.controller.sys;

import com.cms.utils.ResultUtil;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 方法描述: DEMO
 * author ZELD、
 * version v1.0
 * date 2018/10/8
 */
public class BaseController {

    /**
     * 获取IP地址
     * @param request
     * @return
     */
    public String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public String result(Map<String,Object> data) {

        return ResultUtil.toJSON(data);
    }

    public Map<String,Object> resPage(PageInfo pageInfo) {
        Map<String, Object> map = new HashMap<>();
        map.put("data",pageInfo.getList());
        map.put("recordsTotal",pageInfo.getTotal());
        map.put("recordsFiltered",pageInfo.getTotal());
        map.put("cur_page", pageInfo.getPageNum());
        map.put("total_page", pageInfo.getPages());
        map.put("hasNextPage", pageInfo.isHasNextPage());
        return map;
    }

}
