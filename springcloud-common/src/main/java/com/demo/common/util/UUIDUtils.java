package com.demo.common.util;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

public class UUIDUtils {

    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        String uuidStr = uuid.toString().replace("-", "");
        return uuidStr;
    }

    public static void main(String[] args) throws InterruptedException {
        /*int totalPage = (1001 + 100 - 1) / 100;
        System.out.println(totalPage);*/


        Instant begin = Instant.now();

        //前端传入的订单勾票金额总额
        BigDecimal orderTicketTotalAmount = new BigDecimal(103200);
        //前端传入的差价单勾票金额总额
        BigDecimal gapTicketTotalAmount = new BigDecimal(-28890);
        //前端传入的订单勾票金额总额
        BigDecimal ticketTotalAmount = orderTicketTotalAmount.add(gapTicketTotalAmount);
        System.out.println("ticketTotalAmount:"+ticketTotalAmount);


        //待勾发票含税总额
        BigDecimal invoiceTotalAmount = new BigDecimal(0);
        BigDecimal invoiceTotalAmountTax = new BigDecimal("74310.000000");
        BigDecimal ticketAmount = new BigDecimal("0.000000");
        invoiceTotalAmount = invoiceTotalAmount.add(invoiceTotalAmountTax.subtract(ticketAmount));
        System.out.println("invoiceTotalAmount:"+invoiceTotalAmount);

        int compareByWithTax = ticketTotalAmount.abs().compareTo(invoiceTotalAmount.abs());
        System.out.println("compareByWithTax:"+compareByWithTax);


        Thread.sleep(500);

        Instant end = Instant.now();
        Duration duration = Duration.between(begin, end);
        System.out.println(duration.toMillis());

    }


}
