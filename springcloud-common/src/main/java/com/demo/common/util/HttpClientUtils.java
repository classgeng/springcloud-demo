package com.demo.common.util;

import com.alibaba.excel.util.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.nio.charset.StandardCharsets;
import java.util.Map;

public class HttpClientUtils {

    public static String ENCODING = "UTF-8";

    public static String doPost(String url, String params, Map<String, String> headers) {
        HttpPost httpPost = null;
        try {
            HttpClient httpClient = HttpClientBuilder.create().build();
            httpPost = new HttpPost(url);
            // 构造消息头
            httpPost.setHeader("Content-type", "application/json; charset=utf-8");
            RequestConfig config = RequestConfig.custom().setConnectionRequestTimeout(60000).setConnectTimeout(60000).setSocketTimeout(60000).build();
            httpPost.setConfig(config);
            // 设置header
            for (Map.Entry<String, String> header : headers.entrySet()) {
                httpPost.setHeader(header.getKey(), header.getValue());
            }
            // 构建消息实体
            params = StringUtils.isBlank(params) ? "": params;
            httpPost.setEntity(new StringEntity(params, StandardCharsets.UTF_8));
            HttpResponse response = httpClient.execute(httpPost);
            // 获取结果实体
            HttpEntity entity = response.getEntity();
            if (entity == null) {
                throw new RuntimeException("http request error");
            }
            // 按指定编码转换结果实体为String类型
            String result = EntityUtils.toString(entity, ENCODING);
            EntityUtils.consume(entity);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        } finally {
            if (httpPost != null) {
                httpPost.releaseConnection();
            }
        }
    }

    public static String httpGet(String url, Map<String, String> headers) {
        HttpGet httpGet = null;
        try {
            HttpClient httpClient = HttpClientBuilder.create().build();
            httpGet = new HttpGet(url);
            // 构造消息头
            httpGet.setHeader("Content-type", "application/json; charset=utf-8");
//            post.setHeader("Connection", "Close");
            RequestConfig config = RequestConfig.custom().setConnectionRequestTimeout(60000).setConnectTimeout(60000).setSocketTimeout(60000).build();
            httpGet.setConfig(config);
            // 设置header
            if(null != headers) {
                for (Map.Entry<String, String> header : headers.entrySet()) {
                    httpGet.setHeader(header.getKey(), header.getValue());
                }
            }
            HttpResponse response = httpClient.execute(httpGet);
            // 获取结果实体
            HttpEntity entity = response.getEntity();
            if (entity == null) {
                throw new RuntimeException("http request error");
            }
            // 按指定编码转换结果实体为String类型
            String result = EntityUtils.toString(entity, ENCODING);
            EntityUtils.consume(entity);
            return result;
        } catch (Exception e) {
            throw new RuntimeException("http request error");
        } finally {
            if (httpGet != null) {
                httpGet.releaseConnection();
            }
        }
    }

}
