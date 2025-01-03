package com.demo;

import com.demo.domain.Invoice;
import com.demo.domain.RebateWriteoffOrder;
import com.demo.mapper.RebateWriteoffOrderMapper;
import com.demo.service.InvoiceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class InvoiceServiceTests {

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private RebateWriteoffOrderMapper rebateWriteoffOrderMapper;

    @Test
    public void testQueryInvoiceByPage() {
        Invoice invoice = new Invoice();
        invoice.setInvoiceNo("24502000000024651849");
        invoice.setPurchaserTaxNo("911101016343030544");
        List<Invoice> list = invoiceService.queryInvoiceByPage(invoice);
        System.out.println("invoice size:" + list.size());
    }


    @Test
    public void testQueryRebateWriteoffOrderList() {
        List<RebateWriteoffOrder> list = rebateWriteoffOrderMapper.queryRebateWriteoffOrderList(List.of(1895347485514813440L));
        System.out.println("rebateWriteoffOrder size:" + list.size());
    }


}
