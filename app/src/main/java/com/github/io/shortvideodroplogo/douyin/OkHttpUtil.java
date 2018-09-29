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
//        Request request = new Request.Builder()
//                .url(url)
//                .removeHeader("User-Agent").addHeader("User-Agent",
//                        "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.92 Safari/537.36")
//                .addHeader("Upgrade-Insecure-Requests","1")
//                .addHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
//                .removeHeader("Accept-Encoding")
//                .addHeader("Accept-Encoding","gzip, deflate, br")
////                .addHeader("Accept-Language","zh-CN,zh;q=0.9")
//                .get()
//                .build();
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        okHttpClient.newCall(request)
                .enqueue(callback);
    }
}
