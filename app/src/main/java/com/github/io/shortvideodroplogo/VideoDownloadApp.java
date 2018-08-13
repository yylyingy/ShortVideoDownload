package com.github.io.shortvideodroplogo;

import android.app.Application;

import com.github.io.shortvideodroplogo.network.Light;
import com.github.io.shortvideodroplogo.network.json.GsonEngine;
import com.github.io.shortvideodroplogo.network.json.JsonHelper;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class VideoDownloadApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        JsonHelper.getInstance().initJsonParser(GsonEngine.getInstance());
        Light.initOkBuild(new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10,TimeUnit.SECONDS)
        );

    }
}
