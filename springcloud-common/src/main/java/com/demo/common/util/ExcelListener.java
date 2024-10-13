package com.demo.common.util;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelListener extends AnalysisEventListener {

    private List<Object> dataList = new ArrayList<>();

    private static Map<Integer,String> containColumnMap = new HashMap<>();

    //初始化
    static {
        containColumnMap.put(1,"类型");
        containColumnMap.put(2,"发票号码");
        containColumnMap.put(3,"发票代码");
        containColumnMap.put(4,"销方名称");
        containColumnMap.put(5,"销售方税号");
        containColumnMap.put(6,"开票日期");
        containColumnMap.put(7,"购方名称");
        containColumnMap.put(8,"购方税号");
        containColumnMap.put(9,"无税金额");
        containColumnMap.put(10,"税额");
        containColumnMap.put(11,"含税金额");
        containColumnMap.put(12,"税率");
        containColumnMap.put(13,"币种");
        containColumnMap.put(14,"货物及服务名称");
        containColumnMap.put(15,"规格型号");
        containColumnMap.put(16,"单位");
        containColumnMap.put(17,"数量");
        containColumnMap.put(18,"单价");
        containColumnMap.put(19,"无税金额");
        containColumnMap.put(20,"税额");
        containColumnMap.put(21,"含税金额");
    }

    @Override
    public void invoke(Object object, AnalysisContext context) {
        dataList.add(object);
        //业务
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
    }


    @Override
    public void invokeHeadMap(Map headMap, AnalysisContext context) {

        for (Integer columnIndex : containColumnMap.keySet()) {
            if (!headMap.containsKey(columnIndex)){
                throw new RuntimeException("模板错误");
            }
            if (!containColumnMap.get(columnIndex).equals(headMap.get(columnIndex))){
                throw new RuntimeException("模板错误");
            }
        }

    }


    public List<Object> getDataList() {
        return dataList;
    }

    public void setDataList(List<Object> dataList) {
        this.dataList = dataList;
    }
}
