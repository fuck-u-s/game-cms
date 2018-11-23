package com.cms.web.controller.video;

import com.cms.model.video.KuaiBao;
import com.cms.model.video.Video;
import com.cms.model.video.VideoConfig;
import com.cms.service.video.VideoCateService;
import com.cms.service.video.VideoConfigService;
import com.cms.service.video.VideoIssuerService;
import com.cms.service.video.VideoService;
import com.cms.utils.Logger;
import com.cms.utils.ResultUtil;
import com.github.pagehelper.PageInfo;
import com.xuan.utils.Validators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 方法描述: 视频管理
 * author ZELD、
 * version v1.0
 * date 2018/9/29
 */
@RequestMapping("/video/**")
@Controller
public class VideoController {

    @Autowired
    private VideoService videoService;
    @Autowired
    private VideoCateService videoCateService;
    @Autowired
    private VideoIssuerService videoIssuerService;
    @Autowired
    private VideoConfigService videoConfigService;

    // 视频管理
    @RequestMapping("/list")
    public String videoList(Model model){
        List<Map<String,Object>> cateList = videoCateService.cateList();
        model.addAttribute("cateList",cateList);
        return "video/video/list";
    }

    // 发布者管理列表
    @RequestMapping("/wsList")
    @ResponseBody
    public String wsList(@RequestParam(defaultValue = "") String title,
                         @RequestParam(defaultValue = "0") int state,
                         @RequestParam(defaultValue = "0") int cate,
                         @RequestParam(defaultValue = "0") int draw,
                         @RequestParam(defaultValue = "1") int start,
                         @RequestParam(defaultValue = "10") int length){
        Map<String,Object> map = ResultUtil.result();
        try {
            PageInfo<Video> pageInfo = videoService.videoList(title,state,cate,start,length);
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
    @RequestMapping("/setVideo")
    public String setVideo(Model model){
        List<Map<String,Object>> cateList = videoCateService.cateList();
        List<Map<String,Object>> issList = videoIssuerService.issList();
        model.addAttribute("cateList",cateList);
        model.addAttribute("issList",issList);
        return "/video/video/set";
    }

    // 保存发布者
    @RequestMapping("/saveVideo")
    @ResponseBody
    public String saveVideo(@ModelAttribute Video video){
        Map<String,Object> map = ResultUtil.result();
        try {
            if(Validators.isEmpty(video.getTitle())){
                map.put("code",-1);
                map.put("msg","请填写视频标题");
            }else if(Validators.isEmpty(video.getUrl())){
                map.put("code",-2);
                map.put("msg","请上传视频");
            }else if(Validators.isEmpty(video.getVtime())){
                map.put("code",-3);
                map.put("msg","请填写视频时间");
            }else {
                VideoConfig config = videoConfigService.getConfigKey("transcode");
                if(config.getVals().equals("1")){
                    video.setState(2);
                }else {
                    video.setState(3);
                }
                int i = videoService.setVideo(video);
                map.put("code",i);
                map.put("msg",i > 0 ? "视频保存成功":"视频保存失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            Logger.error(e.getMessage());
            map.put("code",-1000);
            map.put("msg","发布者保存异常...");
        }
        return ResultUtil.toJSON(map);
    }

    // 视频信息
    @RequestMapping("/videoInfo")
    public String videoInfo(@RequestParam(defaultValue = "0") int id, Model model){
        List<Map<String,Object>> cateList = videoCateService.cateList();
        List<Map<String,Object>> issList = videoIssuerService.issList();
        Video video = videoService.videoInfo(id);
        model.addAttribute("info",video);
        model.addAttribute("cateList",cateList);
        model.addAttribute("issList",issList);
        return "video/video/info";
    }

    // 修改发布者信息
    @RequestMapping("/updateVideo")
    @ResponseBody
    public String updateVideo(@ModelAttribute Video video){
        Map<String,Object> map = ResultUtil.result();
        try {
            if(Validators.isEmpty(video.getTitle())){
                map.put("code",-1);
                map.put("msg","请填写视频标题");
            }else if(Validators.isEmpty(video.getUrl())){
                map.put("code",-2);
                map.put("msg","请上传视频");
            }else if(Validators.isEmpty(video.getVtime())){
                map.put("code",-3);
                map.put("msg","请填写视频时间");
            }else if(Validators.isEmpty(video.getCover())){
                map.put("code",-4);
                map.put("msg","请上传封面");
            }else {
                int i = videoService.updateVideo(video);
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
    @RequestMapping("/deleteVideo")
    @ResponseBody
    public String deleteVideo(@RequestParam(defaultValue = "0") int id,
                              @RequestParam(defaultValue = "0") int state){
        Map<String,Object> map = ResultUtil.result();
        try {
            int i = videoService.deleteVideo(id,state);
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

    // 快报视频管理
    @RequestMapping("/kuaiBaoList")
    public String kuaiBaoList(){

        return "video/kuaibao/list";
    }

    // 快报视频数据
    @RequestMapping("/wsKuaiBaoList")
    @ResponseBody
    public String wsKuaiBaoList(@RequestParam(defaultValue = "") String id,
                                @RequestParam(defaultValue = "") String vid,
                                @RequestParam(defaultValue = "1") int state,
                                @RequestParam(defaultValue = "0") int draw,
                                @RequestParam(defaultValue = "1") int start,
                                @RequestParam(defaultValue = "10") int length){
        Map<String,Object> map = ResultUtil.result();
        try {
            PageInfo<KuaiBao> pageInfo = videoService.kuaiBaoList(id,vid,state,start,length);
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

    // 删除快报视频
    @RequestMapping("/deleteKuaiBao")
    @ResponseBody
    public String deleteKuaiBao(@RequestParam(defaultValue = "") String id,
                                @RequestParam(defaultValue = "") String vid){
        Map<String,Object> map = ResultUtil.result();
        try {
            int i = videoService.deleteKuaiBao(id,vid);
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

    // 快报视频信息
    @RequestMapping("/kuaiBaoInfo")
    public String kuaiBaoInfo(@RequestParam(defaultValue = "") String id,Model model){
        List<Map<String,Object>> cateList = videoCateService.cateList();
        List<Map<String,Object>> issList = videoIssuerService.issList();
        KuaiBao info = videoService.kuaiBaoInfo(id);
        model.addAttribute("info",info);
        model.addAttribute("cateList",cateList);
        model.addAttribute("issList",issList);
        return "/video/kuaibao/info";
    }

    // 同步快到视频到本地视频
    @RequestMapping("/syncToVideo")
    @ResponseBody
    public String syncToVideo(@ModelAttribute Video video){
        Map<String,Object> map = ResultUtil.result();
        try {
            if(video.isHasCover() == false && Validators.isEmpty(video.getCover())){
                map.put("code",-1);
                map.put("msg","未发现可用视频封面,请上传!");
            }else if(Validators.isEmpty(video.getUrl())){
                map.put("code",-2);
                map.put("msg","请上传视频");
            }else {
                int i = videoService.syncToVideo(video);
                map.put("code",i);
                map.put("msg",i > 0 ? "转化成功":"转化失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            Logger.error(e.getMessage());
            map.put("code",-1000);
            map.put("msg","发布者删除出错了...");
        }
        return ResultUtil.toJSON(map);
    }
}
