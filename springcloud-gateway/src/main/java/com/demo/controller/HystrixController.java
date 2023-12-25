package com.demo.controller;

import com.demo.config.AppConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xfgeng
 * @date 2020-11-16 15:16
 */
@Slf4j
@RestController
@RequestMapping("/hystrix")
public class HystrixController {

    @Autowired
    private AppConfig appConfig;

    /**
     * 熔断服务
     * @return
     */
    @GetMapping(path="/fallback")
    public Map<String,String> fallback() {
        System.out.println(appConfig.getAppId() + " -> " + appConfig.getAppName());
        Map<String,String> result = new HashMap<>();
        result.put("3000","服务暂时不可用，请稍后再试！");
        return result;
    }


}
