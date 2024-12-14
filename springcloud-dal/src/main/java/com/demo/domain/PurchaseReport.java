package com.demo.domain;

import lombok.Data;

import java.math.BigDecimal;

/**
 * <p>
 * 发票-导出
 * </p>
 *
 * @author classgeng
 * @since 2024-11-30
 */
@Data
public class PurchaseReport {

    private String tenantId;

    private String systemSource;

    private int invoiceCompanyCount;

    private int cmsCompanyCount;

    private int invoiceCount;

    private int cmsCount;

    private int deductCount;

    private int accountCount;

    private BigDecimal invoiceAccRate;

    public String getTenantAndSystemSource(){
        return tenantId + "-" + systemSource;
    }

}
