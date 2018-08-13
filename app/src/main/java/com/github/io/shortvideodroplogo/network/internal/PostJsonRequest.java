package com.github.io.shortvideodroplogo.network.internal;

import com.github.io.shortvideodroplogo.network.json.JsonHelper;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

public class PostJsonRequest extends BaseLightRequest {
    private Map<String,String> mParams;
    private Object mJsonObj;
    public PostJsonRequest(String url) {
        super(url);
    }


    public PostJsonRequest addParams(String key, String value) {
        if (null == mParams) {
            mParams = new HashMap<>();
        }
        mParams.put(key,value);
        return this;
    }

    public PostJsonRequest addParams(Map<String,String> param) {
        if (null == mParams) {
            mParams = param;
        } else {
            mParams.putAll(param);
        }
        return this;
    }

    /**
     * addJson和addParams一个对象只能调用同一个，两个都调用的话默认只使用json对象
     * @param json
     * @param <T>
     * @return
     */
    public <T> PostJsonRequest addJson(T json) {
        mJsonObj = json;
        return this;
    }


    @Override
    Request generateRequest() {
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        RequestBody requestBody = null;
        if (mJsonObj != null) {
            requestBody = RequestBody.create(mediaType, mJsonObj instanceof String ?
                    mJsonObj.toString() : JsonHelper.getInstance().toJson(mJsonObj));
        } else {
            requestBody = RequestBody.create(mediaType, JsonHelper.getInstance().toJson(mParams));
        }
        return new Request.Builder()
                .url(getUrl())
                .post(requestBody)
                .build();
    }
}
