package com.demo.service.impl;

import com.demo.domain.Invoice;
import com.demo.mapper.InvoiceMapper;
import com.demo.service.InvoiceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Resource
    private InvoiceMapper invoiceMapper;

    @Override
    public List<Invoice> queryInvoiceList(Invoice invoice) {
        return invoiceMapper.selectInvoiceList(invoice);
    }
}
