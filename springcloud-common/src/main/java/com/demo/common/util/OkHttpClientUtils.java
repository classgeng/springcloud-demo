package com.demo.common.util;

import com.alibaba.excel.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * http handler
 * @author classgeng
 */
@Slf4j
public class OkHttpClientUtils {

    private static final Integer connectTimeout = 20;
    private static final Integer readTimeout = 120;
    private static final Integer writeTimeout = 120;
    private static final Integer maxIdleConnections = 100;  //连接池中整体的空闲链接的最大数量
    private static final Long keepAliveDuration = 300L;

    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static final MediaType XML = MediaType.parse("application/xml; charset=utf-8");

    private static final OkHttpClient httpClient;

    static {
        // 自定义连接池最大空闲连接数和等待时间大小，否则默认最大10个空闲连接
        ConnectionPool connectionPool = new ConnectionPool(maxIdleConnections, keepAliveDuration, TimeUnit.MINUTES);
        httpClient = new OkHttpClient.Builder()
                .sslSocketFactory(Objects.requireNonNull(sslSocketFactory()), x509TrustManager())
                .retryOnConnectionFailure(false)  // 是否开启缓存
                .connectionPool(connectionPool)
                .connectTimeout(connectTimeout, TimeUnit.SECONDS)
                .readTimeout(readTimeout, TimeUnit.SECONDS)
                .writeTimeout(writeTimeout,TimeUnit.SECONDS)
                .hostnameVerifier((hostname, session) -> true)
                .build();
    }

    /**
     * 发送post请求
     * @param url
     * @param params
     * @param headers
     * @return
     */
    public static String doPostJson(String url, String params, Map<String, String> headers) {
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
    public static String doPostRequest(String url, String params, Map<String, String> headers, MediaType mediaType) {
        log.info("request url: {}, params: {}", url, params);
        params = StringUtils.isEmpty(params) ? "" : params;
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
    public static String doGetRequest(String url, Map<String, String> params) {
        return doGetRequest(url,params,null);
    }

    /**
     * get 请求
     * @param url  请求url地址
     * @param params 请求参数 map
     * @param headers 请求头字段
     * @return string
     * */
    public static String doGetRequest(String url, Map<String, String> params, Map<String, String> headers) {
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
    public static void doAsyncGetRequest(String url, Map<String, String> params, Map<String, String> headers, Consumer<Exception> failure, Consumer<Response> response) {
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
    public static void doAsyncPostRequest(String url, String params, Map<String, String> headers, MediaType mediaType, Consumer<Exception> failure, Consumer<Response> response) {
        log.info("request url: {}, params: {}", url, params);
        RequestBody requestBody = RequestBody.create(params, mediaType);
        Request.Builder builder = buildHeader(headers);
        Request request = builder.url(url).post(requestBody).build();
        asyncRequest(request,failure,response);
    }

    private static String buildUrl(String url, Map<String, String> params) {
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

    private static Request.Builder buildHeader(Map<String, String> headers) {
        Request.Builder builder = new Request.Builder();
        if (headers != null && !headers.isEmpty()) {
            for (String header:headers.keySet()){
                builder.addHeader(header, headers.get(header));
            }
        }
        return builder;
    }

    private static String request(Request request) {
        Response response = null;
        try {
            response = httpClient.newCall(request).execute();
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
    public static void asyncRequest(Request request, Consumer<Exception> failure, Consumer<Response> response) {
        httpClient.newCall(request).enqueue(new Callback() {
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


    public static X509TrustManager x509TrustManager() {
        return new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType)
                    throws CertificateException {
            }
            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType)
                    throws CertificateException {
            }
            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        };
    }

    public static SSLSocketFactory sslSocketFactory() {
        try {
            // 信任任何链接
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[]{x509TrustManager()}, new SecureRandom());
            return sslContext.getSocketFactory();
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            log.error(e.getMessage());
        }
        return null;
    }

}
