package com.github.io.shortvideodroplogo.douyin;

import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * <br> ClassName:
 * <br> Description: todo(这里用一句话描述这个类的作用)
 * <br>
 * <br> Author:      Yangyl
 * <br> Date:        2018/6/24 23:41
 */

public class DouyinGet {
//    String douyin = "https://aweme.snssdk.com/aweme/v1/play/?video_id=v0200fbc0000bcmd1nnrri6cjkpfu05g&line=0&ratio=720p&media_type=4&vr_type=0&test_cdn=None&improve_bitrate=0&device_platform=android&device_type=STF-AL10&version_code=190&device_id=46014508360&channel=update";
    static String douyin = "https://aweme.snssdk.com/aweme/v1/play/?video_id=";
    static String config = "&line=0&ratio=720p&media_type=4&vr_type=0&test_cdn=None&improve_bitrate=0&device_platform=android&device_type=STF-AL10&version_code=190&device_id=46014508360&channel=update";
    public static void download(final String videoId,final DownloadCallback downloadCallback) {
       OkHttpUtil.get(douyin + videoId + config,new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                downloadCallback.fail("解析成功，但下载视频失败");
                System.out.println();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println();
                Integer mTotal = Integer.valueOf(response.header("Content-Length"));
                String path = Environment.getExternalStorageDirectory().toString() + File.separator
                        + "videonologo";
                FileOutputStream mDstOutputStream = new FileOutputStream(getFileDir(path) + File.separator
                        + videoId + ".mp4");
                final byte[] bytes = response.body().bytes();
                mDstOutputStream.write(bytes);
                mDstOutputStream.close();
                downloadCallback.success(getFileDir(path) + File.separator
                        + videoId + ".mp4");
            }
        });
    }

    private static File getFileDir(String dirName) {
        File file = new File(dirName);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }
}
