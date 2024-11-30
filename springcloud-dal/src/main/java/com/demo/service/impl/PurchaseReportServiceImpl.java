package com.demo.service.impl;

import com.demo.domain.PurchaseReport;
import com.demo.mapper.PurchaseReportMapper;
import com.demo.service.PurchaseReportService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
public class PurchaseReportServiceImpl implements PurchaseReportService {

    @Resource
    private PurchaseReportMapper purchaseReportMapper;


    @Override
    public List<PurchaseReport> queryPurchaseReport() {
        return purchaseReportMapper.selectPurchaseReport();
    }

    @Override
    public List<PurchaseReport> queryInvoiceTicketStatistics() {
        return purchaseReportMapper.selectInvoiceTicketStatistics();
    }

    @Override
    public List<PurchaseReport> queryAutoDeductCount() {
        return purchaseReportMapper.selectAutoDeductCount();
    }

    @Override
    public List<PurchaseReport> queryAutoAccountCount() {
        return purchaseReportMapper.selectAutoAccountCount();
    }
}
