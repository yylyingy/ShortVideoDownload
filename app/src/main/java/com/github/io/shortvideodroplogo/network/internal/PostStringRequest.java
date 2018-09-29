package com.github.io.shortvideodroplogo.network.internal;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

public class PostStringRequest extends BaseLightRequest {
    private Map<String,String> mParams;
    public PostStringRequest(String url) {
        super(url);
    }

    public PostStringRequest addParams(String key, String value) {
        if (null == mParams) {
            mParams = new HashMap<>();
        }
        mParams.put(key,value);
        return this;
    }

    public PostStringRequest addParams(Map<String,String> param) {
        if (null == mParams) {
            mParams = param;
        } else {
            mParams.putAll(param);
        }
        return this;
    }
    @Override
    Request generateRequest() {
        String line;
        Iterator var12 = mParams.keySet().iterator();
        StringBuffer buffer = new StringBuffer();
        int size = mParams.size();
        int index = 0;
        while(var12.hasNext()) {
            line = (String)var12.next();
            String str = String.format("%s=%s", line, mParams.get(line));
            buffer.append(str);
            ++index;
            if (index != size) {
                buffer.append("&");
            }
        }
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType,buffer.toString());
        Request request = new Request.Builder()
                .url(getUrl())
                .post(body)
                .build();
        return request;
    }
}
