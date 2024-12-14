package com.demo.common.excel;

import org.apache.poi.ss.usermodel.*;

/**
 * excel 样式工具类
 * @author zj
 * @date 2021/07/08 19:08
 */
public class CellStyleUtil {

    /**
     * excel首列序号列样式
     * @param workbook
     * @return
     */
    public static CellStyle firstCellStyle(Workbook workbook) {
        CellStyle cellStyle = workbook.createCellStyle();
        //居中
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        // 灰色
        cellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        //设置边框
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        //文字
        Font font = workbook.createFont();
        font.setBold(Boolean.TRUE);
        cellStyle.setFont(font);
        return cellStyle;
    }
}

