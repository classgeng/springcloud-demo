package com.demo.common.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpClientUtils {

    public static String ENCODING = "UTF-8";

    public static String doPost(String url, String str) {
        try {
            // 创建httpclient对象
            CloseableHttpClient client = HttpClients.createDefault();
            // 创建post方式请求对象
            HttpPost httpPost = new HttpPost(url);
            // 设置参数到请求对象中
            httpPost.setEntity(new StringEntity(str, ENCODING));
            // 设置header信息
            // 指定报文头【Content-type】、【User-Agent】
            httpPost.setHeader("Content-type", "application/json;charset=UTF-8");
            // 执行请求操作，并拿到结果（同步阻塞）
            HttpResponse response = client.execute(httpPost);
            // 获取结果实体
            HttpEntity entity = response.getEntity();
            if (entity == null) {
                throw new RuntimeException("http request error");
            }
            // 按指定编码转换结果实体为String类型
            String body = EntityUtils.toString(entity, ENCODING);
            EntityUtils.consume(entity);
            return body;
        } catch (Exception e) {
            throw new RuntimeException("http request error", e);
        }
    }


}
