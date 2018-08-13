package com.github.io.shortvideodroplogo.network.json;




/**
 * @author yangyinglong on 2017/4/27 11:24.
 * @FileName:
 * @Description
 * @Copyright Copyright (c) 2017 Tuandai Inc. All Rights Reserved.
 */

public class JsonHelper implements IJsonParser{
    private static IJsonParser mIJsonParser;
    public static JsonHelper getInstance() {
        return SingleTon.INSTANCE.getJsonParser();
    }

    private JsonHelper() {
    }

    public String toJson( Object object) {
        return mIJsonParser.toJson(object);
    }

    public <T> T fromJson( String json,Class<T> classOfT) {
        return mIJsonParser.fromJson(json,classOfT);
    }

    public void initJsonParser(IJsonParser jsonParser) {
        this.mIJsonParser = jsonParser;
    }

    /**
     *
     */
    private enum SingleTon {
        INSTANCE;
        private JsonHelper mJsonParser;
        SingleTon() {
            mJsonParser = new JsonHelper();
        }

        public JsonHelper getJsonParser() {
            return mJsonParser;
        }
    }
}
