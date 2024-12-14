package com.demo.mapper;

import com.demo.domain.PurchaseReport;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author classgeng
 * @since 2024-10-13
 */
@Mapper
public interface PurchaseReportMapper {

    /**
     * cms发票数统计
     *
     * @return 发票数统计
     */
    List<PurchaseReport> selectPurchaseReport();


    /**
     * 精准配单率统计
     *
     * @return 发票数统计
     */
    List<PurchaseReport> selectInvoiceTicketStatistics();

    /**
     * 自动抵扣统计
     *
     * @return 发票数统计
     */
    List<PurchaseReport> selectAutoDeductCount();


    /**
     * 自动记账统计
     *
     * @return 发票数统计
     */
    List<PurchaseReport> selectAutoAccountCount();


}
