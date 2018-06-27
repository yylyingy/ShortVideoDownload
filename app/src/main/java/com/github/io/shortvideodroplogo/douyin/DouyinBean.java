package com.github.io.shortvideodroplogo.douyin;

/**
 * <br> ClassName:
 * <br> Description: todo(这里用一句话描述这个类的作用)
 * <br>
 * <br> Author:      Yangyl
 * <br> Date:        2018/6/26 0:04
 */

public class DouyinBean {

    /**
     * hasData : 1
     * videoWidth : 720
     * videoHeight : 640
     * playAddr : https://aweme.snssdk.com/aweme/v1/playwm/?video_id=v0200fbc0000bcmd1nnrri6cjkpfu05g&line=0
     * cover : https://p3.pstatp.com/large/915800139280e601bbba.jpg
     */

    private int hasData;
    private int videoWidth;
    private int videoHeight;
    private String playAddr;
    private String cover;

    public int getHasData() {
        return hasData;
    }

    public void setHasData(int hasData) {
        this.hasData = hasData;
    }

    public int getVideoWidth() {
        return videoWidth;
    }

    public void setVideoWidth(int videoWidth) {
        this.videoWidth = videoWidth;
    }

    public int getVideoHeight() {
        return videoHeight;
    }

    public void setVideoHeight(int videoHeight) {
        this.videoHeight = videoHeight;
    }

    public String getPlayAddr() {
        return playAddr;
    }

    public void setPlayAddr(String playAddr) {
        this.playAddr = playAddr;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }
}
