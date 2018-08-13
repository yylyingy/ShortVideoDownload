package com.github.io.shortvideodroplogo.network.json;


import com.google.gson.Gson;

/**
 * @author yangyinglong on 2017/8/3 11:10.
 * @Description: todo(这里用一句话描述这个类的作用)
 * @Copyright Copyright (c) 2017 Tuandai Inc. All Rights Reserved.
 */

public class GsonEngine implements IJsonParser {
    private Gson mGson;
    public static GsonEngine getInstance() {
        return SingleTon.INSTANCE.getJsonParser();
    }

    private GsonEngine() {
        mGson = new Gson();
    }

    @Override
    public String toJson(Object object) {
        return mGson.toJson(object);
    }

    @Override
    public <T> T fromJson(String json,Class<T> classOfT) {
        return mGson.fromJson(json,classOfT);
    }

    /**
     *
     */
    private enum SingleTon {
        INSTANCE;
        private GsonEngine mJsonParser;
        SingleTon() {
            mJsonParser = new GsonEngine();
        }

        public GsonEngine getJsonParser() {
            return mJsonParser;
        }
    }
}
