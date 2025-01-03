package com.demo.controller;

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
@RequestMapping("/api/metrics")
public class MetricsController {

    @Autowired
    private AppConfig appConfig;

    /**
     * 测试服务
     * @return
     */
    @GetMapping(path="/health")
    public String health() {
        log.info("metrics-demo: {} {}", appConfig.getAppId(), appConfig.getAppName());
        return "ok";
    }

    /**
     * 测试服务
     * @return
     */
    @GetMapping(path="/sayHello")
    public String sayHello(String name) {
        log.info("metrics-demo: {} {} {}", appConfig.getAppId(), appConfig.getAppName(), name);
        return "metrics-demo：hello," + name + " [appId：" + appConfig.getAppId() + ",appName:" + appConfig.getAppName() + "]";
    }

}
