package com.demo.common.http;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;
import java.util.function.Consumer;

/**
 * http handler
 * @author classgeng
 */
@Slf4j
@Component
public class OkHttpClientHandler {

    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static final MediaType XML = MediaType.parse("application/xml; charset=utf-8");

    // 为使用静态调用异步注入
    @Resource
    private OkHttpClient okHttpClient;

    /**
     * 发送post请求
     *
     * @param url
     * @param params
     * @param headers
     * @return
     */
    public String doPostXml(String url, String params, Map<String, String> headers) {
        return doPostRequest(url, params, headers, XML);
    }

    /**
     * 发送post请求
     * @param url
     * @param params
     * @param headers
     * @return
     */
    public String doPostJson(String url, String params, Map<String, String> headers) {
        return doPostRequest(url, params, headers, JSON);
    }

    /**
     * 发送post请求
     * @param url
     * @param params
     * @param headers
     * @param mediaType
     * @return
     */
    public String doPostRequest(String url, String params, Map<String, String> headers, MediaType mediaType) {
        log.info("request url: {}, params: {}", url, params);
        RequestBody requestBody = RequestBody.create(params, mediaType);
        Request.Builder builder = buildHeader(headers);
        Request request = builder.url(url).post(requestBody).build();
        return request(request);
    }

    /**
     * get 请求
     * @param url  请求url地址
     * @param params 请求参数 map
     * @return string
     * */
    public String doGetRequest(String url, Map<String, String> params) {
        return doGetRequest(url,params,null);
    }

    /**
     * get 请求
     * @param url  请求url地址
     * @param params 请求参数 map
     * @param headers 请求头字段
     * @return string
     * */
    public String doGetRequest(String url, Map<String, String> params, Map<String, String> headers) {
        String reqUrl = buildUrl(url, params);
        Request.Builder builder = buildHeader(headers);
        Request request = builder.url(reqUrl).build();
        log.info("request url: {}", reqUrl);
        return request(request);
    }

    /**
     * 异步 get 请求
     * @param url  请求url地址
     * @param params 请求参数 map
     * @param headers 请求头字段
     * @return string
     * */
    public void doAsyncGetRequest(String url, Map<String, String> params, Map<String, String> headers, Consumer<Exception> failure, Consumer<Response> response) {
        String reqUrl = buildUrl(url, params);
        Request.Builder builder = buildHeader(headers);
        Request request = builder.url(reqUrl).build();
        log.info("request url: {}", reqUrl);
        asyncRequest(request, failure, response);
    }

    /**
     * 异步发送post请求
     * @param url
     * @param params
     * @param headers
     * @param mediaType
     * @return
     */
    public void doAsyncPostRequest(String url, String params, Map<String, String> headers, MediaType mediaType, Consumer<Exception> failure, Consumer<Response> response) {
        log.info("request url: {}, params: {}", url, params);
        RequestBody requestBody = RequestBody.create(params, mediaType);
        Request.Builder builder = buildHeader(headers);
        Request request = builder.url(url).post(requestBody).build();
        asyncRequest(request,failure,response);
    }

    private String buildUrl(String url, Map<String, String> params) {
        StringBuilder sb = new StringBuilder(url);
        if (params != null && !params.keySet().isEmpty()) {
            boolean firstFlag = true;
            for (String key : params.keySet()) {
                if (firstFlag) {
                    sb.append("?").append(key).append("=").append(params.get(key));
                    firstFlag = false;
                } else {
                    sb.append("&").append(key).append("=").append(params.get(key));
                }
            }
        }
        return sb.toString();
    }

    private Request.Builder buildHeader(Map<String, String> headers) {
        Request.Builder builder = new Request.Builder();
        if (headers != null && !headers.isEmpty()) {
            for (String header:headers.keySet()){
                builder.addHeader(header, headers.get(header));
            }
        }
        return builder;
    }

    private String request(Request request) {
        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
            if (response.body() != null) {
                String result = response.body().string();
                log.info("response body: {}", result);
                return result;
            } else {
                throw new RuntimeException("http request error");
            }
        } catch (Exception e) {
            log.error("http request error", e);
            throw new RuntimeException("http request error", e);
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }


    /**
     * 开启异步线程访问网络,不需要返回结果( Callback 返回为空)
     */
    public void asyncRequest(Request request, Consumer<Exception> failure, Consumer<Response> response) {
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                log.error("async request error: {} ", e.getMessage());
                failure.accept(e);
            }
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response res)
                    throws IOException {
                log.info("async request success: {}", res.body().string());
                response.accept(res);
            }
        });
    }

}
