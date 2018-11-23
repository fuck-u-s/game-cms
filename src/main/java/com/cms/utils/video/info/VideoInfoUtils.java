package com.cms.utils.video.info;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.xuan.utils.Validators;
import org.apache.log4j.Logger;
import org.apache.oro.text.regex.MalformedPatternException;
import org.apache.oro.text.regex.MatchResult;
import org.apache.oro.text.regex.Pattern;
import org.apache.oro.text.regex.PatternCompiler;
import org.apache.oro.text.regex.PatternMatcher;
import org.apache.oro.text.regex.Perl5Compiler;
import org.apache.oro.text.regex.Perl5Matcher;

/**
 * 获取视频音频的各项属性帮助类 如果需要修改或者添加属性，只要扩展下面的二维数组和修改下面getVideoInfo()方法
 * @author chifangxiong
 */
public class VideoInfoUtils {

	protected static final Logger logger = Logger.getLogger(VideoInfoUtils.class);

	// FFMPEG环境变量
	public static final String ffmpegPath = "D:\\ffmpeg\\bin\\ffmpeg.exe";

	// 视频地址
	private static final String filePath = "D:\\in.mp4";

	/**
	 * 根据属性获取视频信息
	 * @param videoPath
	 */
	public static VideoInfo getVideoInfo(String videoPath) {
		VideoInfo videoInfo = new VideoInfo();
		File file = new File(videoPath);
		if (file.exists()) {
			if (checkContentType() < 2) {
				String result = processVideo(videoPath);
				if (!Validators.isEmpty(result)) {
                    System.out.println("视频信息："+result);
					PatternCompiler compiler = new Perl5Compiler();
					try {
						String regexDuration = "Duration: (.*?), start: (.*?), bitrate: (\\d*) kb\\/s";
						String regexVideo = "Video: (.*?), (.*?), (.*?)[,\\s]";
						String regexAudio = "Audio: (\\w*), (\\d*) Hz";

						Pattern patternDuration = compiler.compile(regexDuration, Perl5Compiler.CASE_INSENSITIVE_MASK);
						PatternMatcher matcherDuration = new Perl5Matcher();
						if (matcherDuration.contains(result, patternDuration)) {
							MatchResult re = matcherDuration.getMatch();
							videoInfo.setPlayingAllTime(re.group(1));
							videoInfo.setPlayingStartTime(re.group(2));
							videoInfo.setBitrateSize(re.group(3));
						}

						Pattern patternVideo = compiler.compile(regexVideo, Perl5Compiler.CASE_INSENSITIVE_MASK);
						PatternMatcher matcherVideo = new Perl5Matcher();

						if (matcherVideo.contains(result, patternVideo)) {
							MatchResult re = matcherVideo.getMatch();
							videoInfo.setCodeFormat(re.group(1));
							videoInfo.setVideoFormat(re.group(2));
							videoInfo.setResolution(re.group(3));
						}

						Pattern patternAudio = compiler.compile(regexAudio, Perl5Compiler.CASE_INSENSITIVE_MASK);
						PatternMatcher matcherAudio = new Perl5Matcher();

						if (matcherAudio.contains(result, patternAudio)) {
							MatchResult re = matcherAudio.getMatch();
							videoInfo.setAudioCoding(re.group(1));
							videoInfo.setAudioFrequency(re.group(2));
						}
					} catch (MalformedPatternException e) {
						logger.error("获取【" + videoPath + "】视频信息失败!");
					}
					logger.info("获取【" + videoPath + "】视频信息成功!");
				} else {
					logger.info("执行成功！但未获取到【" + videoPath + "】视频信息!");
				}
			} else {
				logger.debug("【" + videoPath + "】文件格式不支持！");
			}
		}

		return videoInfo;
	}

	/**
	 * ffmpeg能解析的格式：（asx，asf，mpg，wmv，3gp，mp4，mov，avi，flv等）
	 */
	private static String processVideo(String filePath) {
		List<String> commend = new ArrayList<>();
        // 可以设置环境变量从而省去这行
		//commend.add(ffmpegPath);
        commend.add("ffmpeg");
        commend.add("-i");
		commend.add(filePath);
		try {
			ProcessBuilder builder = new ProcessBuilder();
			builder.command(commend);
			builder.redirectErrorStream(true);
			Process p = builder.start();
			BufferedReader buf; // 保存ffmpeg的输出结果流
			String line;
			buf = new BufferedReader(new InputStreamReader(p.getInputStream()));
			StringBuffer sb = new StringBuffer();
			while ((line = buf.readLine()) != null) {
				sb.append(line);
				continue;
			}
			p.waitFor();// 这里线程阻塞，将等待外部转换进程运行成功运行结束后，才往下执行
			return sb.toString();
		} catch (Exception e) {
			logger.error("ffmpeg解析视频文件【" + filePath + "】失败!");
			return null;
		}
	}

	private static int checkContentType() {
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
		return 2;
	}

	public static void main(String[] args) {
		System.out.println(getVideoInfo(filePath));
	}

}
