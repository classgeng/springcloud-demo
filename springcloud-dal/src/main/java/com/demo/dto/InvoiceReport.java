package com.demo.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.converters.WriteConverterContext;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.demo.common.excel.BigDecimalTwoDecimalConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * <p>
 * 发票-导出
 * </p>
 *
 * @author classgeng
 * @since 2024-11-30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@HeadRowHeight(16)//表头行高
@ContentRowHeight(16)//行高
@ColumnWidth(20)//列宽
public class InvoiceReport {

    @ExcelProperty(value = "序号", index = 0)
    private Integer index;

    @ExcelProperty(value = "子公司名称", index = 1)
    private String companyName;

    @ExcelProperty(value = "对接系统", index = 2)
    private String systemSource;

    @ExcelProperty(value = "使用货主数", index = 3)
    private String ownerCount;

    @ExcelProperty(value = {"<使用货主>勾票数统计", "发票平台张数"}, index = 4)
    private int invoiceCount;

    @ExcelProperty(value = {"<使用货主>勾票数统计", "CMS张数"}, index = 5)
    private int cmsCount;

    @ExcelProperty(value = {"<使用货主>勾票数统计", "占比CMS"}, index = 6)
    private BigDecimal invoiceAndCms;

    @ExcelProperty(value = "自动抵扣发票数(张)", index = 7)
    private int autoDeductCount;

    @ExcelProperty(value = "自动记账发票数(张)", index = 8)
    private int autoAccountCount;

    @ExcelProperty(value = "精准配单率(%)", converter = BigDecimalTwoDecimalConverter.class, index = 9)
    private BigDecimal invoiceAccRate;

}
