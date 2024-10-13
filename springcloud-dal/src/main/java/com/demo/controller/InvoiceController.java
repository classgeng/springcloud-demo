package com.demo.controller;

import com.demo.config.AppConfig;
import com.demo.domain.Invoice;
import com.demo.page.TableDataInfo;
import com.demo.service.InvoiceService;
import com.demo.util.PageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xfgeng
 * @date 2020-11-16 15:16
 */
@Slf4j
@RestController
@RequestMapping("/api/invoice")
public class InvoiceController extends BaseController {

    @Resource
    private AppConfig appConfig;

    @Resource
    private InvoiceService invoiceService;

    /**
     * 测试服务
     * @return
     */
    @GetMapping(path="/sayHello")
    public String sayHello(String name) {
        log.info("provider-demo2: {} {} {}", appConfig.getAppId(), appConfig.getAppName(), name);
        return "provider-demo2：hello," + name + " [appId：" + appConfig.getAppId() + ",appName:" + appConfig.getAppName() + "]";
    }

    /**
     * 查询发票信息
     * @return
     */
    @RequestMapping(path="/query")
    public TableDataInfo query() {
        Invoice invoice = new Invoice();
        invoice.setPurchaserTaxNo("916501002286701741");
        invoice.setSellerTaxNo("915101005696858096");

        PageUtils.startPage();
        return getDataTable(invoiceService.queryInvoiceList(invoice));
    }


}
