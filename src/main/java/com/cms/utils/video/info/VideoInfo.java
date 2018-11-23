package com.cms.utils.video.info;

import java.io.Serializable;

public class VideoInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    // 播放时长
    private String playingAllTime;
    // 开始播放时间
    private String playingStartTime;
    // 比特率
    private String bitrateSize;
    // 编码类型
    private String codeFormat;
    // 视频格式
    private String videoFormat;
    private String resolution;
    private String audioCoding;
    private String audioFrequency;

    public String getPlayingAllTime() {
        return playingAllTime;
    }

    public void setPlayingAllTime(String playingAllTime) {
        this.playingAllTime = playingAllTime;
    }

    public String getPlayingStartTime() {
        return playingStartTime;
    }

    public void setPlayingStartTime(String playingStartTime) {
        this.playingStartTime = playingStartTime;
    }

    public String getBitrateSize() {
        return bitrateSize;
    }

    public void setBitrateSize(String bitrateSize) {
        this.bitrateSize = bitrateSize;
    }

    public String getCodeFormat() {
        return codeFormat;
    }

    public void setCodeFormat(String codeFormat) {
        this.codeFormat = codeFormat;
    }

    public String getVideoFormat() {
        return videoFormat;
    }

    public void setVideoFormat(String videoFormat) {
        this.videoFormat = videoFormat;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getAudioCoding() {
        return audioCoding;
    }

    public void setAudioCoding(String audioCoding) {
        this.audioCoding = audioCoding;
    }

    public String getAudioFrequency() {
        return audioFrequency;
    }

    public void setAudioFrequency(String audioFrequency) {
        this.audioFrequency = audioFrequency;
    }

    @Override
    public String toString() {
        return "VideoInfo [playingAllTime=" + playingAllTime + ", playingStartTime=" + playingStartTime
                + ", bitrateSize=" + bitrateSize + ", codeFormat=" + codeFormat + ", videoFormat=" + videoFormat
                + ", resolution=" + resolution + ", audioCoding=" + audioCoding + ", audioFrequency=" + audioFrequency
                + "]";
    }


}
