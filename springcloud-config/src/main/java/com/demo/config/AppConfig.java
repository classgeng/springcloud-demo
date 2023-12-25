package com.demo.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

/**
 * @author xfgeng
 */
@Data
@RefreshScope   //开启动态刷新功能
@Configuration
public class AppConfig {

    @Value("${app.id}")
    String appId;
    @Value("${app.name}")
    String appName;

}
