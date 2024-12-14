package com.demo.controller;

import com.demo.common.I18N.I18NUtils;
import com.demo.common.http.OkHttpClientHandler;
import com.demo.config.AppConfig;
import com.demo.config.BaiWangConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

/**
 * @author classgeng
 * @date 2020-11-16 15:16
 */
@Slf4j
@RestController
@RequestMapping("/api/provider")
public class ProviderController {

    @Resource
    private AppConfig appConfig;

    @Resource
    private BaiWangConfiguration baiWangConfiguration;

    @Resource
    private OkHttpClientHandler okHttpClientHandler;

    /**
     * 测试服务
     * @return
     */
    @GetMapping(path="/sayHello")
    public String sayHello(String name) {
        log.info("provider-demo1: {} {} {}", appConfig.getAppId(), appConfig.getAppName(), name);
        return "provider-demo1：hello," + name + " [appId：" + appConfig.getAppId() + ",appName:" + appConfig.getAppName() + "]";
    }

    /**
     * 测试服务
     * @return
     */
    @GetMapping(path="/sayNo")
    public String sayNo(String name) {
        return "no，" + name;
    }

    /**
     * 测试服务
     * @return
     */
    @GetMapping(path="/http")
    public String http(String url) {
        return okHttpClientHandler.doGetRequest(url,null);
    }


    @RequestMapping(value = "/echo/{str}", method = RequestMethod.POST)
    public String i18nEcho(@RequestBody RequestEntity requestEntity, @PathVariable String str) {
        String language = requestEntity.getHeaders().getFirst("Language");
        String contentLanguage = requestEntity.getHeaders().getContentLanguage().getLanguage();

        Object[] args = {str,language,contentLanguage};
        return I18NUtils.msg("tsf.gateway.create", args, language);
    }

    /**
     * 获取配置信息
     * @return
     */
    @GetMapping(path="/getBwConfig")
    public String getBwConfig() {
        String config = String.format("AkId:%s AkSecret:%s FpUrl:%s", baiWangConfiguration.getAkId(), baiWangConfiguration.getAkSecret(), baiWangConfiguration.getFpUrl());
        log.info(config);
        return config;
    }

}
