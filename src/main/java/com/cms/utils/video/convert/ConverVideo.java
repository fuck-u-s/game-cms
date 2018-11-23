package com.cms.utils.video.convert;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
  
public class ConverVideo {
  
	private static Logger logger = LoggerFactory.getLogger(ConverVideo.class);
    private Date currentTime;
    private long begintime;
    private String filePath;
    private String filerealname; // 文件名 不包括扩展名
    private String filename; // 包括扩展名
    private String videofolder = "/home/resource/video/other/"; // 别的格式视频的目录
    private String flvfolder = "/home/resource/video/flv/"; // flv视频的目录
    private String swffolder = "/home/resource/video/swf/"; // swf视频的目录
    private String mp4folder = "/home/resource/video/mp4/"; // mp4视频的目录
    private String mencoderpath = "/home/resource/video/mencoder/"; // mencoder的目录
    private String videoRealPath = "/home/resource/video/flv/"; // 截图的视频目录;
    private String imageRealPath = "/home/resource/video/image/"; // 截图的存放目录

    public ConverVideo() {

    }
  
    public ConverVideo(String path) {

        filePath = path;
    }
  
    public String getPATH() {

        return filePath;
    }
  
    public void setPATH(String path) {

        filePath = path;
    }
  
    /**
     * 开始转换
     */
    public boolean beginConver() {
        File fi = new File(filePath);
        filename = fi.getName();
        filerealname = filename.substring(0, filename.lastIndexOf(".")).toLowerCase();
        logger.info("----接收到文件(" + filePath + ")需要转换-------------------------- ");
        if (!checkfile(filePath)) {
            logger.info(filePath + "文件不存在" + " ");
            return false;
        }
        currentTime = new Date();
        begintime = currentTime.getTime();
        logger.info("----开始转文件(" + filePath + ")-------------------------- ");
        if (process()) {
            Date dt2 = new Date();
            logger.info("转换成功 ");
            long endtime = dt2.getTime();
            long timecha = (endtime - begintime);
            String totaltime = sumTime(timecha);
            logger.info("共用了:" + totaltime + " ");
            if (processImg()) {
                logger.info("截图成功了 ");
            } else {
                logger.info("截图不成功了 ");
            }
            filePath = null;
            return true;
        } else {
            filePath = null;
            return false;
        }
    }
  
    /**
     * 转换成功之后截图
     * @return
     */
    public boolean processImg() {
        List command = new ArrayList();
        command.add("ffmpeg");
        command.add("-i");
        command.add(filePath);
        command.add("-y");
        command.add("-f");
        command.add("image2");
        command.add("-ss");
        command.add("2");
        command.add("-t");
        command.add("0.001");
        command.add("-s");
        command.add("420x220");
        command.add(imageRealPath + filerealname + ".jpg");
        try {
            ProcessBuilder builder = new ProcessBuilder();
            builder.command(command);
            builder.start();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
  
    /**
     * 
     * @return
     */
    private boolean process() {
        int type = checkContentType();
        boolean status = false;
        if (type == 0) {
            // 直接将文件转为mp4文件
            status = processMP4(filePath);
        } else if (type == 1) {
            String avifilepath = processAVI(type);
            if (avifilepath == null){
            	return false;
            } else {
                // avi文件没有得到
                logger.info("----成功转换成avi，即将转avi转出flv-------------------------");
                status = processFLV(avifilepath);// 将avi转为flv
            }
        }
        return status;
    }
  
    private int checkContentType() {
        String type = filePath.substring(filePath.lastIndexOf(".") + 1, filePath.length()).toLowerCase();
        // ffmpeg能解析的格式：（asx，asf，mpg，wmv，3gp，mp4，mov，avi，flv等）
        if (type.equals("avi")) {
            return 0;
        } else if (type.equals("mpg")) {
            return 0;
        } else if (type.equals("wmv")) {
            return 0;
        } else if (type.equals("3gp")) {
            return 0;
        } else if (type.equals("mov")) {
            return 0;
        } else if (type.equals("mp4")) {
            return 0;
        } else if (type.equals("asf")) {
            return 0;
        } else if (type.equals("asx")) {
            return 0;
        } else if (type.equals("flv")) {
            return 0;
        }
        // 对ffmpeg无法解析的文件格式(wmv9，rm，rmvb等),
        // 可以先用别的工具（mencoder）转换为avi(ffmpeg能解析的)格式.
        else if (type.equals("wmv9")) {
            return 1;
        } else if (type.equals("rm")) {
            return 1;
        } else if (type.equals("rmvb")) {
            return 1;
        }
        return 9;
    }
  
    /**
     * 检查文件是否存在
     * @param path
     * @return
     */
    private boolean checkfile(String path) {
        File file = new File(path);
        if (!file.isFile()) {
            return false;
        } else {
            return true;
        }
    }
  
    /**
     * 对ffmpeg无法解析的文件格式(wmv9，rm，rmvb等), 可以先用别的工具（mencoder）转换为avi(ffmpeg能解析的)格式
     * @param type
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private String processAVI(int type) {
        List command = new ArrayList();
        command.add(mencoderpath);
        command.add(filePath);
        command.add("-oac");
        command.add("mp3lame");
        command.add("-lameopts");
        command.add("preset=64");
        command.add("-ovc");
        command.add("xvid");
        command.add("-xvidencopts");
        command.add("bitrate=600");
        command.add("-of");
        command.add("avi");
        command.add("-o");
        command.add(videofolder + filerealname + ".avi");
        // 命令类型：mencoder 1.rmvb -oac mp3lame -lameopts preset=64 -ovc xvid
        // -xvidencopts bitrate=600 -of avi -o rmvb.avi
        try {
            ProcessBuilder builder = new ProcessBuilder();
            builder.command(command);
            Process p = builder.start();
  
            doWaitFor(p);
            return videofolder + filerealname + ".avi";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
  
    /**
     * ffmpeg能解析的格式：（asx，asf，mpg，wmv，3gp，mp4，mov，avi，flv等）
     * @param oldfilepath
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	private boolean processFLV(String oldfilepath) {
  
        if (!checkfile(filePath)) {
            logger.info(oldfilepath + " is not file");
            return false;
        }
        File file = new File(flvfolder);
    	if(!file.exists()){
    		file.mkdirs();
    	}
        List command = new ArrayList();
        command.add("ffmpeg");
        command.add("-i");
        command.add(oldfilepath);
        command.add("-ab");
        command.add("64");
        command.add("-acodec");
        command.add("mp3");
        command.add("-ac");
        command.add("2");
        command.add("-ar");
        command.add("22050");
        command.add("-b");
        command.add("230");
        command.add("-r");
        command.add("24");
        command.add("-y");
        command.add(flvfolder + filerealname + ".flv");
        try {
            ProcessBuilder builder = new ProcessBuilder();
            //String cmd = command.toString();
            builder.command(command);
            Process p = builder.start();
            doWaitFor(p);
            p.destroy();
            deleteFile(oldfilepath);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked", "unused" })
    private boolean processSWF(String oldfilepath) {
    	
    	if (!checkfile(filePath)) {
    		logger.info(oldfilepath + " is not file");
    		return false;
    	}
    	File file = new File(swffolder);
    	if(!file.exists()){
    		file.mkdirs();
    	}
    	List command = new ArrayList();
    	command.add("ffmpeg");
        command.add("-i");
        command.add(oldfilepath);
        command.add("-ab");
        command.add("64");
        command.add("-acodec");
        command.add("mp3");
        command.add("-ac");
        command.add("2");
        command.add("-ar");
        command.add("22050");
        command.add("-b");
        command.add("230");
        command.add("-r");
        command.add("24");
        command.add("-y");
        command.add(swffolder + filerealname + ".swf");
    	try {
    		ProcessBuilder builder = new ProcessBuilder();
    		//String cmd = command.toString();
    		builder.command(command);
    		Process p = builder.start();
    		doWaitFor(p);
    		p.destroy();
    		deleteFile(oldfilepath);
    		return true;
    	} catch (Exception e) {
    		e.printStackTrace();
    		return false;
    	}
    }

    // 转换MP4
    private boolean processMP4(String oldfilepath) {

        if (!checkfile(filePath)) {
            System.out.println(oldfilepath + " is not file");
            return false;
        }
        File file = new File(mp4folder);
    	if(!file.exists()){
    		file.mkdirs();
    	}
        List<String> command = new ArrayList<String>();
        command.add("ffmpeg");
        command.add("-i");//-i 源文件
        command.add(oldfilepath);
        command.add("-ab");//-ab <比特率> 设定声音比特率
        command.add("128");
        command.add("-ar");//-ar <采样率> 设定声音采样率
        command.add("22050");
        command.add("-qscale");//-qscale <数值> 以<数值>质量为基础的VBR，取值0.01-255，越小质量越好
        command.add("8");
        command.add("-r");//-r 29.97 桢速率（可以改，确认非标准桢率会导致音画不同步，所以只能设定为15或者29.97）
        command.add("29.97");
        command.add("-b:v");
        command.add("500k");
        //command.add("-s");//-s 320x240 指定分辨率
        //command.add("1024x768");
        command.add(mp4folder + filerealname + ".mp4");

        try {
        	ProcessBuilder builder = new ProcessBuilder();
    		builder.command(command);
    		Process p = builder.start();
    		doWaitFor(p);
    		p.destroy();

    		//删除旧文件
    		deleteFile(oldfilepath);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
  
    @SuppressWarnings("static-access")
	public int doWaitFor(Process p) {
        InputStream in = null;
        InputStream err = null;
        int exitValue = -1; // returned to caller when p is finished
        try {
            logger.info("comeing................");
            in = p.getInputStream();
            err = p.getErrorStream();
            boolean finished = false; // Set to true when p is finished
  
            while (!finished) {
                try {
                    while (in.available() > 0) {
                        Character c = new Character((char) in.read());
                        System.out.print(c);
                    }
                    while (err.available() > 0) {
                        Character c = new Character((char) err.read());
                        System.out.print(c);
                    }
  
                    exitValue = p.exitValue();
                    finished = true;
  
                } catch (IllegalThreadStateException e) {
                    Thread.currentThread().sleep(500);
                }
            }
        } catch (Exception e) {
            System.err.println("doWaitFor();: unexpected exception - " + e.getMessage());
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
  
            } catch (IOException e) {
                logger.info(e.getMessage());
            }
            if (err != null) {
                try {
                    err.close();
                } catch (IOException e) {
                    logger.info(e.getMessage());
                }
            }
        }
        return exitValue;
    }
  
    /**
     * 删除文件
     * @param filepath
     */
    public void deleteFile(String filepath) {
        File file = new File(filepath);
        if (filePath.equals(filepath)) {
            if (file.delete()) {
                logger.info("文件" + filepath + "已删除");
            }
        } else {
            if (file.delete()) {
                logger.info("文件" + filepath + "已删除 ");
            }
            File filedelete2 = new File(filePath);
            if (filedelete2.delete()) {
                logger.info("文件" + filePath + "已删除");
            }
        }
    }
  
    /**
     * 计算所花时间
     * @param ms
     * @return
     */
    public String sumTime(long ms) {
        int ss = 1000;
        long mi = ss * 60;
        long hh = mi * 60;
        long dd = hh * 24;
  
        long day = ms / dd;
        long hour = (ms - day * dd) / hh;
        long minute = (ms - day * dd - hour * hh) / mi;
        long second = (ms - day * dd - hour * hh - minute * mi) / ss;
        long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;
  
        String strDay = day < 10 ? "0" + day + "天" : "" + day + "天";
        String strHour = hour < 10 ? "0" + hour + "小时" : "" + hour + "小时";
        String strMinute = minute < 10 ? "0" + minute + "分" : "" + minute + "分";
        String strSecond = second < 10 ? "0" + second + "秒" : "" + second + "秒";
        String strMilliSecond = milliSecond < 10 ? "0" + milliSecond : "" + milliSecond;
        strMilliSecond = milliSecond < 100 ? "0" + strMilliSecond + "毫秒" : "" + strMilliSecond + " 毫秒";
        return strDay + " " + strHour + ":" + strMinute + ":" + strSecond + " " + strMilliSecond;
  
    }
    
}
