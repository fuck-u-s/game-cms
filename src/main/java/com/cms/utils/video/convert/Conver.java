package com.cms.utils.video.convert;

public class Conver {
	  
    public void run() {
        try {
            // 转换并截图
            String filePath = "D:\\in.mp4";
            ConverVideo cv = new ConverVideo(filePath);
            cv.beginConver();
  
            // 仅截图
            // ProcessFlvImg pfi = new ProcessFlvImg();
            // pfi.processImg("D:\\video\\old\\test.avi");
  
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
  
    public static void main(String args[]) {
        Conver c = new Conver();
        c.run();
    }
}
