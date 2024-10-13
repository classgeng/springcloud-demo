package com.demo.service;

import com.demo.domain.Invoice;

import java.util.List;

public interface InvoiceService {

    /**
     * 根据条件分页查询发票列表
     *
     * @param invoice 发票信息
     * @return 发票信息集合
     */
    List<Invoice> queryInvoiceList(Invoice invoice);

}
