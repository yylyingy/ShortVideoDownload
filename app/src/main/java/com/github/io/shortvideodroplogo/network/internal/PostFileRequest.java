package com.github.io.shortvideodroplogo.network.internal;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

public class PostFileRequest extends BaseLightRequest {
    private Map<String,String> mParams;
    private Map<String,File> mFileMap = new HashMap<>();
    public PostFileRequest(String url) {
        super(url);
    }

    public PostFileRequest addParams(String key, String value) {
        if (null == mParams) {
            mParams = new HashMap<>();
        }
        mParams.put(key,value);
        return this;
    }

    public PostFileRequest setFile(String key, File file) {
        if (mFileMap.size() > 0) {
            throw new IllegalStateException("Cannot set twice!");
        }
        mFileMap.put(key,file);
        return this;
    }

    public PostFileRequest addParams(Map<String,String> param) {
        if (null == mParams) {
            mParams = param;
        } else {
            mParams.putAll(param);
        }
        return this;
    }

    @Override
    Request generateRequest() {
        if (null == mParams) {
            mParams = new HashMap<>();
        }
        MultipartBody.Builder multipartBody = new MultipartBody.Builder()
                .setType(MultipartBody.ALTERNATIVE);
        Iterator<String> iterator = mParams.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            String value = mParams.get(key);
            RequestBody useNameBody = RequestBody.create(MediaType.parse("text/plain"),value);
            multipartBody.addPart(Headers.of(
                    "Content-Disposition",
                    "form-data; " + "name" + "=\""+ key + "\"")
                    ,useNameBody);
        }
        iterator = mFileMap.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            File value = mFileMap.get(key);
            Headers fileHead = Headers.of(
                    "Content-Disposition",
                    "form-data; name=\"file\"; filename=\"" + value.getName() + "\"");
            RequestBody fileBody = RequestBody.create(MultipartBody.FORM,value);
            multipartBody.addPart(fileHead,fileBody);
        }

        Request request = new Request.Builder()
                .url(getUrl())
                .post(multipartBody.build())
                .build();
        return request;
    }
}
