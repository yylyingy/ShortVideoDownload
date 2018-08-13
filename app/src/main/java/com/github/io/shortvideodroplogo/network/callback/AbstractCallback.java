package com.github.io.shortvideodroplogo.network.callback;

import okhttp3.Response;

public abstract class AbstractCallback {
    public abstract void onSuccess(Response response);
    public abstract void onFailure(Exception e);
}
