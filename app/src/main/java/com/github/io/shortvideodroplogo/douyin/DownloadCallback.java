package com.github.io.shortvideodroplogo.douyin;

/**
 * <br> ClassName:
 * <br> Description: todo(这里用一句话描述这个类的作用)
 * <br>
 * <br> Author:      Yangyl
 * <br> Date:        2018/6/27 0:51
 */
public interface DownloadCallback {
    void fail(String errStr);
    void success(String path);
}
