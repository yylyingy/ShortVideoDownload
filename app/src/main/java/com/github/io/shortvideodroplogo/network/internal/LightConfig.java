package com.github.io.shortvideodroplogo.network.internal;

import okhttp3.OkHttpClient;

public class LightConfig {
    boolean usedCustom = false;
    OkHttpClient.Builder mBuilder;
    OkHttpClient mOkHttpClient;

    public boolean isUsedCustom() {
        return usedCustom;
    }

    public void setUsedCustom(boolean usedCustom) {
        this.usedCustom = usedCustom;
    }

    public OkHttpClient.Builder getBuilder() {
        return mBuilder;
    }

    public void setBuilder(OkHttpClient.Builder builder) {
        mBuilder = builder;
    }

    public OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }

    public void setOkHttpClient(OkHttpClient okHttpClient) {
        mOkHttpClient = okHttpClient;
    }
}
