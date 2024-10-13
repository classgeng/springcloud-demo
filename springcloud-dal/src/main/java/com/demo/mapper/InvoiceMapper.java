package com.demo.mapper;

import com.demo.domain.Invoice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
public interface InvoiceMapper {

    /**
     * 根据条件分页查询发票列表
     *
     * @param invoice 发票信息
     * @return 发票信息集合
     */
    List<Invoice> selectInvoiceList(@Param("request") Invoice invoice);


}
