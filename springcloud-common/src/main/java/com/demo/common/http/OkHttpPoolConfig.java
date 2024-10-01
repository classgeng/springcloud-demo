package com.demo.common.http;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author classgeng
 */
public class OkHttpPoolConfig {

    private static final Integer connectTimeout = 10;
    private static final Integer readTimeout = 30;
    private static final Integer writeTimeout = 30;
    private static final Integer maxIdleConnections = 100;  //连接池中整体的空闲链接的最大数量
    private static final Long keepAliveDuration = 300l;     //链接空闲时间

    private static OkHttpClient okHttpClient = null;

    public static OkHttpClient getOkHttpClient(){
        if (okHttpClient != null){
            return okHttpClient;
        }
        synchronized (OkHttpPoolConfig.class){
            if (okHttpClient != null){
                return okHttpClient;
            }
            // 自定义连接池最大空闲连接数和等待时间大小，否则默认最大10个空闲连接
            ConnectionPool connectionPool = new ConnectionPool(maxIdleConnections, maxIdleConnections, TimeUnit.MINUTES);
            okHttpClient = new OkHttpClient.Builder()
                    .sslSocketFactory(Objects.requireNonNull(sslSocketFactory()), x509TrustManager())
                    .connectTimeout(connectTimeout, TimeUnit.SECONDS) // 连接超时
                    .writeTimeout(writeTimeout,TimeUnit.SECONDS)  // 写入超时
                    .readTimeout(readTimeout,TimeUnit.SECONDS) // 读取超时
                    .connectionPool(connectionPool)
                    .retryOnConnectionFailure(false) // 是否开启缓存
                    .build();
        }
        return okHttpClient;
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
            e.printStackTrace();
        }
        return null;
    }
}