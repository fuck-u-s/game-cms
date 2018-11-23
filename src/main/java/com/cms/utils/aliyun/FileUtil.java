package com.cms.utils.aliyun;

import java.io.File;
import java.io.InputStream;

public class FileUtil {
	public static final String H5APPS_BUCKNAME = "xiaogg";
	public static final String DOMAIN = "d.8wj9.cn";

	/**
	 * 上传文件 以流的方式
	 * 
	 * @param path 路径，包括文件夹
	 * @param is 文件流
	 * @return
	 */
	public static String putStream(String path, InputStream is) {
		return OssPuter.putStream(DOMAIN, H5APPS_BUCKNAME, path, is);
	}
	
	/**
	 * 上传文件 
	 * 
	 * @param path 路径，包括文件夹
	 * @return
	 */
	public static String putFile(String path, File file) {
		return OssPuter.putFile(DOMAIN, H5APPS_BUCKNAME, path, file);
	}

	/**
	 * 上传applogo
	 * 
	 * @param fileName 文件名，不是全路径名称
	 * @param is 文件流
	 * @return
	 */
	public static String putAppLogo(String fileName, InputStream is) {
		String path = "app/log/" + "logo_" + System.currentTimeMillis() + fileName.substring(fileName.lastIndexOf("."));
		return putStream(path, is);
	}

}
