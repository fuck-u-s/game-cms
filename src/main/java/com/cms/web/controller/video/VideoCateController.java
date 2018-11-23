package com.cms.web.controller.video;

import com.cms.service.video.VideoCateService;
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
 * 方法描述: 视频类型
 * author ZELD、
 * version v1.0
 * date 2018/10/17
 */
@RequestMapping("/video/cate/**")
@Controller
public class VideoCateController {

    @Autowired
    private VideoCateService videoCateService;

    // 视频类型管理
    @RequestMapping("/list")
    public String cateList(){

        return "/video/cate/list";
    }

    // 视频类型数据
    @RequestMapping("/wsList")
    @ResponseBody
    public String wsList(@RequestParam(defaultValue = "") String title,
                         @RequestParam(defaultValue = "0") int draw,
                         @RequestParam(defaultValue = "1") int start,
                         @RequestParam(defaultValue = "10") int length){
        Map<String,Object> map = ResultUtil.result();
        try {
            PageInfo<Map<String,Object>> pageInfo = videoCateService.cateList(title,start,length);
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

    // 设置类型
    @RequestMapping("/setCate")
    public String setCate(){

        return "/video/cate/set";
    }

    // 保存视频类型
    @RequestMapping("/saveCate")
    @ResponseBody
    public String saveCate(@RequestParam(defaultValue = "") String name){
        Map<String,Object> map = ResultUtil.result();
        try {
            if(Validators.isEmpty(name)){
                map.put("code",-1);
                map.put("msg","请填写名称");
            }else {
                int i = videoCateService.setCate(name);
                map.put("code",i);
                map.put("msg",i > 0 ? "保存成功":"保存失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            Logger.error(e.getMessage());
            map.put("code",-1000);
            map.put("msg","保存类型出错了...");
        }
        return ResultUtil.toJSON(map);
    }

    // 类型信息
    @RequestMapping("/cateInfo")
    public String cateInfo(@RequestParam(defaultValue = "0") int id, Model model){
        Map<String,Object> cate = videoCateService.cateInfo(id);
        model.addAttribute("cate",cate);
        return "/video/cate/info";
    }

    // 修改类型信息
    @RequestMapping("/updateCate")
    @ResponseBody
    public String updateCate(@RequestParam(defaultValue = "0") int id,
                             @RequestParam(defaultValue = "") String name){
        Map<String,Object> map = ResultUtil.result();
        try {
            if(Validators.isEmpty(name)){
                map.put("code",-1);
                map.put("msg","请填写视频标题");
            }else {
                int i = videoCateService.updateCate(id,name);
                map.put("code",i);
                map.put("msg",i > 0 ? "操作成功":"操作失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            Logger.error(e.getMessage());
            map.put("code",-1000);
            map.put("msg","类型修改出错了...");
        }
        return ResultUtil.toJSON(map);
    }

    // 删除类型
    @RequestMapping("/deleteCate")
    @ResponseBody
    public String deleteCate(@RequestParam(defaultValue = "0") int id){
        Map<String,Object> map = ResultUtil.result();
        try {
            int i = videoCateService.deleteCate(id);
            map.put("code",i);
            map.put("msg",i > 0 ? "删除成功":"删除失败");
        }catch (Exception e){
            e.printStackTrace();
            Logger.error(e.getMessage());
            map.put("code",-1000);
            map.put("msg","删除出错了...");
        }
        return ResultUtil.toJSON(map);
    }
}
