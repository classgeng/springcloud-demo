package com.demo.service.impl;

import com.demo.domain.Invoice;
import com.demo.mapper.InvoiceMapper;
import com.demo.service.InvoiceService;
import com.demo.util.PageUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Resource
    private InvoiceMapper invoiceMapper;

    @Override
    public List<Invoice> queryInvoiceByPage(Invoice invoice) {
        PageUtils.startPage();
        return invoiceMapper.selectInvoiceByPage(invoice);
    }
}
