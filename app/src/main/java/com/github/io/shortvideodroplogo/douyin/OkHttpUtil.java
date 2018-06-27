package com.github.io.shortvideodroplogo.douyin;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * <br> ClassName:
 * <br> Description: todo(这里用一句话描述这个类的作用)
 * <br>
 * <br> Author:      Yangyl
 * <br> Date:        2018/6/26 23:18
 */

public class OkHttpUtil {
    private static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .followRedirects(true)
            .followSslRedirects(true)
            .build();

    public static void get(String url, Callback callback) {
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        okHttpClient.newCall(request)
                .enqueue(callback);
    }
}
