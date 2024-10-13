package com.demo.controller;

import com.demo.common.I18N.I18NUtils;
import com.demo.common.domain.Response;
import com.demo.common.http.OkHttpClientHandler;
import com.demo.config.AppConfig;
import com.demo.config.BaiWangConfiguration;
import com.demo.service.DemoService;
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
@RequestMapping("/api/demo")
public class DemoController {

    @Resource
    private DemoService demoService;

    /**
     * 测试服务
     * @return
     */
    @PostMapping(path="/exportExcel")
    public Response exportExcel(String tenantId,String systemSource) {
        demoService.exportExcel(tenantId, systemSource);
        return Response.success("正在导出......,最大导出前30000条,请您稍后到消息中心查看!");
    }


}
