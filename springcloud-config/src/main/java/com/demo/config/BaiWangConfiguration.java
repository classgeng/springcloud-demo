package com.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author classgeng
 * RefreshScope   //开启动态刷新功能
 */
@Data
@Component
@ConfigurationProperties(prefix = "bw")
public class BaiWangConfiguration {

    public String akId;

    public String akSecret;

    public String fpUrl;

}
