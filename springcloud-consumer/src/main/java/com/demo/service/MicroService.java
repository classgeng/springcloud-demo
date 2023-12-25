package com.demo.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author xfgeng
 */
@FeignClient("nacos-service-provider")
public interface MicroService {

    /**
     * 测试openfeign方式调用
     * @param name
     * @return
     */
    @GetMapping("/api/provider/sayHello")
    String sayHello(@RequestParam("name")String name);

}
