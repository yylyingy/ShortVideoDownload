package com.github.io.shortvideodroplogo.network.internal;

import okhttp3.Request;

public class GetRequest extends BaseLightRequest {
    public GetRequest(String url) {
        super(url);
    }

    @Override
    Request generateRequest() {
        return new Request.Builder()
                .url(getUrl())
                .get()
                .build();
    }
}
