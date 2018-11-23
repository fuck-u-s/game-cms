package com.cms.web.controller.video;

import com.cms.service.video.VideoIssuerService;
import com.cms.utils.Logger;
import com.cms.utils.ResultUtil;
import com.github.pagehelper.PageInfo;
import com.xuan.utils.Validators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 方法描述: 视频发布者
 * author ZELD、
 * version v1.0
 * date 2018/9/26
 */
@RequestMapping("/video/issuer/**")
@Controller
public class VideoIssuerController {

    @Autowired
    private VideoIssuerService videoIssuerService;

    // 发布者管理
    @RequestMapping("/issuerList")
    public String issuerList(){

        return "/video/issuer/list";
    }

    // 发布者管理列表
    @RequestMapping("/wsIssuerList")
    @ResponseBody
    public String wsIssuerList(@RequestParam(defaultValue = "") String name,
                               @RequestParam(defaultValue = "0") int state,
                               @RequestParam(defaultValue = "0") int draw,
                               @RequestParam(defaultValue = "1") int start,
                               @RequestParam(defaultValue = "10") int length){
        Map<String,Object> map = ResultUtil.result();
        try {
            PageInfo<Map<String,Object>> pageInfo = videoIssuerService.issuerList(name,state,start,length);
            map.put("data",pageInfo.getList());
            map.put("draw",draw);
            map.put("recordsTotal",pageInfo.getTotal());
            map.put("recordsFiltered",pageInfo.getTotal());
        }catch (Exception e){
            e.printStackTrace();
            Logger.error(e.getMessage());
            map.put("code",-1000);
            map.put("msg","请求异常...");
        }
        return ResultUtil.toJSON(map);
    }

    // 设置发布者
    @RequestMapping("/setIssuer")
    public String setIssuer(){

        return "/video/issuer/set";
    }

    // 保存发布者
    @RequestMapping("/saveIssuer")
    @ResponseBody
    public String saveIssuer(@RequestParam(defaultValue = "") String name,
                             @RequestParam(defaultValue = "") String cover){
        Map<String,Object> map = ResultUtil.result();
        try {
            if(Validators.isEmpty(name)){
                map.put("code",-1);
                map.put("msg","请填写发布者名称");
            }else if(Validators.isEmpty(cover)){
                map.put("code",-2);
                map.put("msg","请上传发布者封面");
            }else {
                int i = videoIssuerService.setIssuer(name,cover);
                map.put("code",i);
                map.put("msg",i > 0 ? "发布人保存成功":"发布人保存失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            Logger.error(e.getMessage());
            map.put("code",-1000);
            map.put("msg","发布者保存异常...");
        }
        return ResultUtil.toJSON(map);
    }

    // 发布者信息
    @RequestMapping("/issuerInfo")
    public String issuerInfo(@RequestParam(defaultValue = "0") int id, Model model){
        Map<String,Object> map = videoIssuerService.issuerInfo(id);
        model.addAttribute("info",map);
        return "/video/issuer/info";
    }

    // 修改发布者信息
    @RequestMapping("/updateIssuer")
    @ResponseBody
    public String updateIssuer(@RequestParam(defaultValue = "0") int id,
                               @RequestParam(defaultValue = "") String name,
                               @RequestParam(defaultValue = "") String cover){
        Map<String,Object> map = ResultUtil.result();
        try {
            if(Validators.isEmpty(name)){
                map.put("code",-1);
                map.put("msg","请填写发布者名称");
            }else if(Validators.isEmpty(cover)){
                map.put("code",-2);
                map.put("msg","请上传发布者封面");
            }else {
                int i = videoIssuerService.updateIssuer(id,name,cover);
                map.put("code",i);
                map.put("msg",i > 0 ? "发布人保存成功":"发布人保存失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            Logger.error(e.getMessage());
            map.put("code",-1000);
            map.put("msg","发布者保存异常...");
        }
        return ResultUtil.toJSON(map);
    }

    // 删除发布者
    @RequestMapping("/deleteIssuer")
    @ResponseBody
    public String deleteIssuer(@RequestParam(defaultValue = "0") int id){
        Map<String,Object> map = ResultUtil.result();
        try {
            int i = videoIssuerService.deleteIssuer(id,2);
            map.put("code",i);
            map.put("msg",i > 0 ? "删除成功":"删除失败");
        }catch (Exception e){
            e.printStackTrace();
            Logger.error(e.getMessage());
            map.put("code",-1000);
            map.put("msg","发布者删除出错了...");
        }
        return ResultUtil.toJSON(map);
    }
}
