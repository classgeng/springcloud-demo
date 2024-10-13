package com.demo.common.util;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.write.metadata.WriteSheet;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Java解析、生成Excel比较有名的框架有Apache poi、jxl。
 * 但他们都存在一个严重的问题就是非常的耗内存，
 * poi有一套SAX模式的API可以一定程度的解决一些内存溢出的问题，但POI还是有一些缺陷，
 * 比如07版Excel解压缩以及解压后存储都是在内存中完成的，内存消耗依然很大。
 *
 * easyexcel重写了poi对07版Excel的解析，一个3M的excel用POI sax解析依然需要100M左右内存，
 * 改用easyexcel可以降低到几M，并且再大的excel也不会出现内存溢出；
 * 03版依赖POI的sax模式，在上层做了模型转换的封装，让使用者更加简单方便
 */
public class EasyExcelUtils {

    public static void write(List<?> list, Class<?> objectClass, String fileName, String sheetName, HttpServletResponse response) {
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        try {
            EasyExcel.write(response.getOutputStream(), objectClass).sheet(sheetName).doWrite(list);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] write(List<?> list, Class<?> objectClass, String sheetName) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        EasyExcel.write(out, objectClass).sheet(sheetName).doWrite(list);
        byte[] bytes = out.toByteArray(); // 获取字节数组
        out.close();
        return bytes;
    }

    public static void write(List<?> list, Class<?> objectClass, String fileName, String sheetName, OutputStream outputStream) {
        EasyExcel.write(outputStream, objectClass).sheet(sheetName).doWrite(list);
    }

    public static void write(List<?> list, Class<?> objectClass, String fileName, String sheetName, OutputStream outputStream, int row) {
        EasyExcel.write(outputStream, objectClass).sheet(sheetName).relativeHeadRowIndex(row).doWrite(list);
    }

    public static void write(List<?> list, Class<?> objectClass, String filePath, String sheetName) {
        EasyExcel.write(filePath, objectClass).sheet(sheetName).doWrite(list);
    }


    /**
     * 读取Excel（多个sheet可以用同一个实体类解析）
     * @param excel 文件
     * @param clazz 实体类
     * @return 返回实体列表(需转换)
     */
    public static <T> List<T> readExcel(MultipartFile excel, Class<T> clazz) {

        ExcelListener excelListener = new ExcelListener();
        ExcelReader excelReader = getReader(excel,clazz,excelListener);

        if (excelReader == null) {
            return new ArrayList<>();
        }

        List<ReadSheet> readSheetList = excelReader.excelExecutor().sheetList();

        for (ReadSheet readSheet:readSheetList){
            excelReader.read(readSheet);
        }
        excelReader.finish();

        return BeanConvert.objectConvertBean(excelListener.getDataList(), clazz);
    }


    /**
     * 返回ExcelReader
     * @param excel         文件
     * @param clazz         实体类
     * @param excelListener
     */
    private static <T> ExcelReader getReader(MultipartFile excel, Class<T> clazz, ExcelListener excelListener) {
        String filename = excel.getOriginalFilename();

        try {
            if (filename == null || (!filename.toLowerCase().endsWith(".xls") && !filename.toLowerCase().endsWith(".xlsx"))) {
                return null;
            }

            InputStream inputStream = new BufferedInputStream(excel.getInputStream());

            ExcelReader excelReader = EasyExcel.read(inputStream, clazz, excelListener).build();

            inputStream.close();

            return excelReader;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



}
