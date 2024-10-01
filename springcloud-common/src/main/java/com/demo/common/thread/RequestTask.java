package com.demo.common.thread;

import com.demo.common.http.OkHttpClientHandler;
import java.util.concurrent.Callable;

public class RequestTask implements Callable<Object>{

    private String url;
    private String params;

    public RequestTask(String url, String params) {
        this.url = url;
        this.params = params;
    }

    @Override
    public Object call() {
        return OkHttpClientHandler.doGetRequest(url, null);
    }
}
