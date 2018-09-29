package com.github.io.shortvideodroplogo.network.internal;

import com.github.io.shortvideodroplogo.network.Light;
import com.github.io.shortvideodroplogo.network.callback.AbstractCallback;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public abstract class BaseLightRequest {
    private String mUrl;
    BaseLightRequest(String url) {
        mUrl = url;
    }
    public void asyncExecute(final AbstractCallback abstractCallback) {
        Call call = getOkClient().newCall(generateRequest());
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                abstractCallback.onFailure(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                abstractCallback.onSuccess(response);
            }
        });
    }

    public Response syncExecute() {
        Call call = getOkClient().newCall(generateRequest());
        try {
            return call.execute();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Deprecated
    public String syncExecuteWithStringResponse() {
        Call call = getOkClient().newCall(generateRequest());
        try {

            Response reponse = call.execute();
            if (reponse.isSuccessful()) {
                if (reponse.body() != null) {
                    return reponse.body().string();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    abstract Request generateRequest();

    protected OkHttpClient getOkClient() {
        return Light.getOkClient();
    }

    public String getUrl() {
        return mUrl == null ? "" : mUrl;
    }
}
