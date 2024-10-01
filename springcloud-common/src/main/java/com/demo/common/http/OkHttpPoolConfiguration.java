package com.demo.common.http;

import lombok.extern.slf4j.Slf4j;
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
import java.util.concurrent.TimeUnit;
/**
 * @author classgeng
 */
@Slf4j
@Configuration
public class OkHttpPoolConfiguration {

    @Value("${ok.http.connect-timeout:30}")
    private Integer connectTimeout;
    @Value("${ok.http.read-timeout:60}")
    private Integer readTimeout;
    @Value("${ok.http.write-timeout:60}")
    private Integer writeTimeout;
    @Value("${ok.http.max-idle-connections:100}")
    private Integer maxIdleConnections;  //连接池中整体的空闲链接的最大数量
    @Value("${ok.http.keep-alive-duration:300}")
    private Long keepAliveDuration;     //链接空闲时间

    @Bean
    public OkHttpClient okHttpClient() {
        // 自定义连接池最大空闲连接数和等待时间大小，否则默认最大10个空闲连接
        ConnectionPool connectionPool = new ConnectionPool(maxIdleConnections, maxIdleConnections, TimeUnit.MINUTES);
        return new OkHttpClient.Builder()
                .sslSocketFactory(sslSocketFactory(), x509TrustManager())
                .retryOnConnectionFailure(false)  // 是否开启缓存
                .connectionPool(connectionPool)
                .connectTimeout(connectTimeout, TimeUnit.SECONDS)
                .readTimeout(readTimeout, TimeUnit.SECONDS)
                .writeTimeout(writeTimeout,TimeUnit.SECONDS)
                .hostnameVerifier((hostname, session) -> true)
                .build();
    }

    @Bean
    public X509TrustManager x509TrustManager() {
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

    @Bean
    public SSLSocketFactory sslSocketFactory() {
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