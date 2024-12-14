package com.demo.common.excel;

import com.alibaba.excel.write.handler.RowWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteTableHolder;
import org.apache.poi.ss.usermodel.*;

/**
 * 自定义行拦截器
 * @author zj
 * @date 2021/07/08 19:08
 */
public class CustomRowWriteHandler implements RowWriteHandler {

    /**
     *  序号的样式，与其他列保持一样的样式
     */
    private CellStyle firstCellStyle;

    private static final String FIRST_CELL_NAME = "序号";
    /**
     * 列号
     */
    private int count = 0;

    @Override
    public void beforeRowCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Integer integer, Integer integer1, Boolean aBoolean) {

    }

    @Override
    public void afterRowCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Row row, Integer integer, Boolean aBoolean) {
        // 每一行首列单元格
        Cell cell = row.createCell(0);
        if (firstCellStyle == null) {
            Workbook workbook = writeSheetHolder.getSheet().getWorkbook();
            firstCellStyle = CellStyleUtil.firstCellStyle(workbook);
        }
        //设置列宽  0列 10个字符宽度
        writeSheetHolder.getSheet().setColumnWidth(0, 10 * 256);
        if (row.getRowNum() == 0) {
            cell.setCellValue(FIRST_CELL_NAME);
            cell.setCellStyle(firstCellStyle);
            return;
        }
        cell.setCellValue(++count);
    }
    @Override
    public void afterRowDispose(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Row row, Integer integer, Boolean aBoolean) {

    }
}
