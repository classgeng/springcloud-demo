package com.demo.controller;

import com.demo.config.AppConfig;
import com.demo.service.MicroService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author xfgeng
 * @date 2020-11-16 15:16
 */
@Slf4j
@RestController
@RequestMapping("/api/consumer")
public class ConsumerController {

    @Autowired
    private AppConfig appConfig;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private MicroService microService;

    @GetMapping("/ribbon/{name}")
    public String ribbon(@PathVariable String name) {
        log.info("ribbon:"+appConfig.getAppId()+appConfig.getAppName());
        return restTemplate.getForObject("http://nacos-service-provider/api/provider/sayHello?name=" +
                name, String.class);
    }

    @GetMapping("/feign/{name}")
    public String feign(@PathVariable String name) {
        log.info("feign:"+appConfig.getAppId()+appConfig.getAppName());
        return microService.sayHello(name);
    }

    @GetMapping("/echo/{name}")
    public List<String> echo(@PathVariable String name) {
        log.info("echo name:"+name);
        List list = null;
        return list;
    }

}
