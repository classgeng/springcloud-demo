package com.demo.redis;

import com.demo.config.AppConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xfgeng
 * @date 2020-11-16 15:16
 */
@Slf4j
@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private AppConfig appConfig;

    /**
     * 测试服务
     * @return
     */
    @GetMapping(path="/sayHello")
    public String sayHello(String name) {
        return "provider-demo2：hello," + name + " [appId：" + appConfig.getAppId() + ",appName:" + appConfig.getAppName() + "]";
    }

}