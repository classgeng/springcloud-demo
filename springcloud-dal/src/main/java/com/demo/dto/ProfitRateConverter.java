package com.demo.dto;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

public class ProfitRateConverter implements Converter<InvoiceReport> {

    @Override
    public WriteCellData<?> convertToExcelData(InvoiceReport value, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        return new WriteCellData<>(value.getInvoiceAndCms());
    }

}
