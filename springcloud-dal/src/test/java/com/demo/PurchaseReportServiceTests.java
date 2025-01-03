package com.demo;

import com.demo.common.excel.EasyExcelUtils;
import com.demo.common.util.JdkStreamUtils;
import com.demo.constants.ReportConstants;
import com.demo.domain.PurchaseReport;
import com.demo.dto.InvoiceReport;
import com.demo.service.PurchaseReportService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PurchaseReportServiceTests {

    @Autowired
    private PurchaseReportService purchaseReportService;

    @Test
    public void testQueryInvoiceByPage() {
        // cms发票数统计
        List<PurchaseReport> purchaseReportList = purchaseReportService.queryPurchaseReport();
        // 精准配单率统计
        List<PurchaseReport> invoiceTicketStatisticsList = purchaseReportService.queryInvoiceTicketStatistics();
        // 自动抵扣统计
        List<PurchaseReport> invoiceDeductList = purchaseReportService.queryAutoDeductCount();
        // 自动记账统计
        List<PurchaseReport> invoiceAccountList = purchaseReportService.queryAutoAccountCount();

        System.out.println("purchaseReportList Size:" + purchaseReportList.size() +
                ", invoiceTicketStatisticsList Size:" + invoiceTicketStatisticsList.size() +
                ", invoiceDeductList Size:" + invoiceDeductList.size() +
                ", invoiceAccountList Size:" + invoiceAccountList.size());

        String sheetName = "进项十二月统计";
        String filePath = "D:\\test\\";
        String fileName = sheetName + ".xlsx";

        EasyExcelUtils.write(this.buildInvoiceReport(purchaseReportList, invoiceTicketStatisticsList, invoiceDeductList, invoiceAccountList, false), InvoiceReport.class, filePath+fileName, sheetName);
        System.out.println("exportExcel success");

    }

    /**
     * cms发票数统计
     * @param purchaseReportList
     * @param invoiceTicketStatisticsList
     * @param isCms
     * @return
     */
    private List<InvoiceReport> buildInvoiceReport(List<PurchaseReport> purchaseReportList,  List<PurchaseReport> invoiceTicketStatisticsList,
                                                   List<PurchaseReport> invoiceDeductList, List<PurchaseReport> invoiceAccountList, boolean isCms) {
        List<InvoiceReport> result = new ArrayList<>();

        Map<String, PurchaseReport> peportMap = JdkStreamUtils.toMap(purchaseReportList, PurchaseReport::getTenantAndSystemSource);

        Map<String, PurchaseReport> deductMap = JdkStreamUtils.toMap(invoiceDeductList, PurchaseReport::getTenantAndSystemSource);

        Map<String, PurchaseReport> accountMap = JdkStreamUtils.toMap(invoiceAccountList, PurchaseReport::getTenantAndSystemSource);


        AtomicInteger index = new AtomicInteger(1);
        invoiceTicketStatisticsList.forEach(item -> {
            PurchaseReport purchaseReport = peportMap.get(item.getTenantAndSystemSource());
            if(Objects.isNull(purchaseReport)){
                return;
            }
            PurchaseReport invoiceDeduct = deductMap.get(item.getTenantAndSystemSource());
            PurchaseReport invoiceAccount = accountMap.get(item.getTenantAndSystemSource());

            String companyName = ReportConstants.companyMap.get(item.getTenantId()) == null ?
                    item.getTenantId() : ReportConstants.companyMap.get(item.getTenantId());
            String systemSource = ReportConstants.systemSourceMap.get(item.getSystemSource()) == null ?
                    item.getSystemSource() : ReportConstants.systemSourceMap.get(item.getSystemSource());

            InvoiceReport invoiceReport = new InvoiceReport();
            invoiceReport.setIndex(index.getAndIncrement());
            invoiceReport.setCompanyName(companyName);
            invoiceReport.setSystemSource(systemSource);
            int cmsCount = purchaseReport.getCmsCount();
            int invoiceCount;

            if(isCms){  // 以cms同步为主
                String ownerCount = purchaseReport.getInvoiceCompanyCount()==0 ?
                        String.valueOf(purchaseReport.getCmsCompanyCount()) : purchaseReport.getInvoiceCompanyCount() + "/" + purchaseReport.getCmsCompanyCount();
                invoiceCount = purchaseReport.getInvoiceCount();
                invoiceReport.setOwnerCount(ownerCount);
                invoiceReport.setInvoiceCount(invoiceCount);
                invoiceReport.setCmsCount(cmsCount);
            } else {    // 以发票平台统计为主
                String ownerCount = item.getInvoiceCompanyCount()==0 ?
                        String.valueOf(purchaseReport.getCmsCompanyCount()) : item.getInvoiceCompanyCount() + "/" + purchaseReport.getCmsCompanyCount();
                invoiceCount = item.getInvoiceCount();
                invoiceReport.setOwnerCount(ownerCount);
                invoiceReport.setInvoiceCount(invoiceCount);
                invoiceReport.setCmsCount(cmsCount);
            }
            if(cmsCount != 0) {
                int rate = invoiceCount / cmsCount;
                BigDecimal bigDecimal = new BigDecimal(rate).setScale(2, RoundingMode.HALF_UP);
                invoiceReport.setInvoiceAndCms(bigDecimal);
            }
            if(Objects.nonNull(invoiceDeduct)) {
                invoiceReport.setAutoDeductCount(invoiceDeduct.getDeductCount());
            }
            if(Objects.nonNull(invoiceAccount)) {
                invoiceReport.setAutoAccountCount(invoiceAccount.getAccountCount());
            }
            //DecimalFormat decimalFormat = new DecimalFormat("#.##%");
            invoiceReport.setInvoiceAccRate(item.getInvoiceAccRate().divide(new BigDecimal(100)));
            result.add(invoiceReport);
        });
        return result;
    }

}
