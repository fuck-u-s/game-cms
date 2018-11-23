package com.cms.web.controller.video;

import com.cms.model.video.VideoConfig;
import com.cms.service.video.VideoConfigService;
import com.cms.utils.Constants;
import com.cms.utils.Logger;
import com.cms.utils.ResultUtil;
import com.cms.utils.aliyun.FileUtil;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 方法描述: 文件管理
 * author ZELD、
 * version v1.0
 * date 2018/10/17
 */
@RequestMapping("/upload/video/**")
@Controller
public class VideoUploadController {

    @Autowired
    private VideoConfigService videoConfigService;

    // 视频
    @RequestMapping("/subVideo")
    @ResponseBody
    public String subVideo(@RequestParam(value = "file", required = false) MultipartFile file){
        Map<String,Object> resMap = ResultUtil.result();
        Map<String,Object> data = new HashMap<>();
        try{

            // 配置参数-是否转码
            VideoConfig config = videoConfigService.getConfigKey("transcode");

            // 临时文件
            File tempFile;
            // 临时文件地址
            String filePath;

            // 视频转码
            if(config.getVals().equals("1")){
                // 定义文件名
                String uuid = UUID.randomUUID().toString().replace("-","");
                // 获取文件名
                String fileName = file.getOriginalFilename();
                // 获取文件后缀
                String prefix = fileName.substring(fileName.lastIndexOf("."));
                // 临时文件地址
                filePath = Constants.VIDEOPATH +uuid+prefix;
                // 转存文件
                file.transferTo(new File(filePath));
                // 临时文件
                tempFile = new File(filePath);
            }else {
                // 临时视频
                CommonsMultipartFile commonsmultipartfile = (CommonsMultipartFile) file;
                DiskFileItem diskFileItem = (DiskFileItem) commonsmultipartfile.getFileItem();
                tempFile = diskFileItem.getStoreLocation();

                // 文件后缀名
                String prefix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

                // 提交到OSS
                String uuid = UUID.randomUUID().toString().replace("-","");
                filePath = FileUtil.putStream("miniapp/video/media/" + uuid + prefix, file.getInputStream());
            }

            // 视频信息
           /* Encoder encoder = new Encoder();
            MultimediaInfo m = encoder.getInfo(tempFile);*/
//            long ls = m.getDuration()/1000;
            long ls = 1000/1000;
            int minute = (int) ls/60;
            int second = (int) (ls%60);

            // 回执信息
            data.put("name",file.getOriginalFilename());
            data.put("url",filePath);
            data.put("time",(minute < 10 ? "0"+minute:minute)+":"+(second < 10 ? "0"+second:second));
            resMap.put("msg","上传成功");
            resMap.put("data",data);

            // 非转码删除视频
           if(config.getVals().equals("2")){
               tempFile.delete();
           }
        }catch (Exception e){
            e.printStackTrace();
            Logger.error(e);
            resMap.put("code",-1000);
            resMap.put("msg","上传失败");
        }
        return ResultUtil.toJSON(resMap);
    }

    // 视频
    @RequestMapping("/syncVideo")
    @ResponseBody
    public String syncVideo(@RequestParam(value = "file", required = false) MultipartFile file){
        Map<String,Object> resMap = ResultUtil.result();
        Map<String,Object> data = new HashMap<>();
        try{

            // 定义文件名
            String uuid = UUID.randomUUID().toString().replace("-","");

            // 文件后缀名
            String prefix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

            String filePath = FileUtil.putStream("miniapp/video/media/" + uuid + prefix, file.getInputStream());

            // 回执信息
            data.put("name",file.getOriginalFilename());
            data.put("url",filePath);

            resMap.put("msg","上传成功");
            resMap.put("data",data);
        }catch (Exception e){
            e.printStackTrace();
            Logger.error(e);
            resMap.put("code",-1000);
            resMap.put("msg","上传失败");
        }
        return ResultUtil.toJSON(resMap);
    }

    // 图片
    @RequestMapping("/subCover")
    @ResponseBody
    public String subAvatar(@RequestParam(value = "file", required = false) MultipartFile file){
        Map<String,Object> resMap = ResultUtil.result();
        Map<String,Object> data = new HashMap<>();
        try{
            String uuid = UUID.randomUUID().toString().replace("-","");
            String result = FileUtil.putStream("miniapp/video/cover/" + uuid+ file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")), file.getInputStream());
            data.put("name",file.getOriginalFilename());
            data.put("url",result);
            resMap.put("msg","上传成功");
            resMap.put("data",data);
        }catch (Exception e){
            e.printStackTrace();
            Logger.error(e);
            resMap.put("code",-1000);
            resMap.put("msg","上传失败");
        }
        return ResultUtil.toJSON(resMap);
    }
}
