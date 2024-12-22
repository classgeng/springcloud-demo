package com.demo.controller;

import com.demo.common.domain.Response;
import com.demo.service.IDemoService;
import lombok.extern.slf4j.Slf4j;
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
    private IDemoService IDemoService;

    /**
     * 测试服务
     * @return
     */
    @PostMapping(path="/exportExcel")
    public Response exportExcel(String tenantId,String systemSource) {
        IDemoService.exportExcel(tenantId, systemSource);
        return Response.success("正在导出......,最大导出前30000条,请您稍后到消息中心查看!");
    }


}
