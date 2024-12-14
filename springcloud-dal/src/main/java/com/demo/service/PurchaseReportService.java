package com.demo.service;

import com.demo.domain.PurchaseReport;
import java.util.List;

public interface PurchaseReportService {

    /**
     * cms发票数统计
     *
     * @return 发票数统计
     */
    List<PurchaseReport> queryPurchaseReport();

    /**
     * 精准配单率统计
     *
     * @return 发票数统计
     */
    List<PurchaseReport> queryInvoiceTicketStatistics();

    /**
     * 自动抵扣统计
     *
     * @return 发票数统计
     */
    List<PurchaseReport> queryAutoDeductCount();


    /**
     * 自动记账统计
     *
     * @return 发票数统计
     */
    List<PurchaseReport> queryAutoAccountCount();

}
