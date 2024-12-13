package com.demo.common.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@HeadRowHeight(20)//表头行高
@ContentRowHeight(20)//行高
@ColumnWidth(25)//列宽
public class UserInfo {

    @ExcelProperty({"用户信息", "编号"})
    private String id;

    /**
     * 姓名
     */
    @ExcelProperty({"用户信息", "姓名"})
    private String name;

    /**
     * 年龄
     */
    @ExcelProperty({"用户信息", "年龄"})
    private Integer age;

    /**
     * 金额
     */
    @ExcelProperty({"用户信息", "金额"})
    private BigDecimal amt;

}
