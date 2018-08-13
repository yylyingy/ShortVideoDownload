package com.github.io.shortvideodroplogo.network;

import com.github.io.shortvideodroplogo.network.internal.GetFileRequest;
import com.github.io.shortvideodroplogo.network.internal.GetRequest;
import com.github.io.shortvideodroplogo.network.internal.LightConfig;
import com.github.io.shortvideodroplogo.network.internal.PostFileRequest;
import com.github.io.shortvideodroplogo.network.internal.PostJsonRequest;
import com.github.io.shortvideodroplogo.network.internal.PostStringRequest;

import okhttp3.OkHttpClient;

public class Light {
    private static LightConfig mLightConfig = new LightConfig();

    private Light() {

    }

    public static void initOkBuild(OkHttpClient.Builder builder) {
        if (null == builder) {
            throw new IllegalArgumentException("Param builder must not be null!");
        }

        if (mLightConfig.isUsedCustom()) {
            throw new IllegalStateException("Cannot set twice okHttp builder");
        }
        mLightConfig.setBuilder(builder);
    }


    public static OkHttpClient getOkClient() {
        if (mLightConfig.getOkHttpClient() == null) {
            synchronized (Light.class) {
                if (mLightConfig.getOkHttpClient() == null) {
                    if (mLightConfig.getBuilder() != null) {
                        mLightConfig.setOkHttpClient(mLightConfig.getBuilder().build());
                    } else {
                        OkHttpClient.Builder mBuilder = new OkHttpClient.Builder();
                        mBuilder.retryOnConnectionFailure(false);
                        mLightConfig.setOkHttpClient(mBuilder.build());
                    }
                }
            }
        }
        return mLightConfig.getOkHttpClient();
    }

    /**
     * 向爱加密服务器上传文件
     * @param url 上传接口
     * @return PostFileRequest
     */
    public static PostFileRequest postFile(String url) {
        return new PostFileRequest(url);
    }

    /**
     * 爱加密请求格式
     * @param url 爱加密服务器地址
     * @return PostStringRequest
     */
    public static PostStringRequest postString(String url) {
        return new PostStringRequest(url);
    }

    /**
     * 下载文件
     * @param url 请求的地址
     * @param apkPath 保存的地址
     * @return GetFileRequest
     */
    public static GetFileRequest getFile(String url, String apkPath) {
        return new GetFileRequest(url,apkPath);
    }

    public static GetRequest getRequest(String url) {
        return new GetRequest(url);
    }
    /**
     * 请求提交json
     * @param url 请求的地址
     * @return PostJsonRequest
     */
    public static PostJsonRequest postJson(String url) {
        return new PostJsonRequest(url);
    }
}
