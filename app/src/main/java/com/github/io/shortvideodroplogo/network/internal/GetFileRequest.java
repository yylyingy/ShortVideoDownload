package com.github.io.shortvideodroplogo.network.internal;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

public class GetFileRequest extends BaseLightRequest {
    private String mSavePath;
    public GetFileRequest(String url, String savePath) {
        super(url);
        mSavePath = savePath;
    }

    @Override
    public String syncExecuteWithStringResponse(){
        Call call = getOkClient().newCall(generateRequest());
        try {
            Response response = call.execute();
            if (response.isSuccessful()) {
                if (response.body() != null) {
                    byte[] buf = new byte[4096];
                    InputStream inputStream = response.body().byteStream();
                    int index;
                    String path = mSavePath;
                    File file = new File(path);
                    FileOutputStream mDstOutputStream = new FileOutputStream(file);
                    while ((index = inputStream.read(buf)) != -1) {
                        mDstOutputStream.write(buf,0,index);
                    }
                    mDstOutputStream.close();
                    response.close();
                }
            } else {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return mSavePath;
    }

    @Override
    Request generateRequest() {
        Request request = new Request.Builder()
                .url(getUrl())
                .get()
                .build();
        return request;
    }
}
