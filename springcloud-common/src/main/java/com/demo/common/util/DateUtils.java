package com.demo.common.util;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期转换工具类
 */
public class DateUtils {

    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private static final SimpleDateFormat simpleDateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    /**
     * 当前时间加减（n为负数就是减）
     * @param n
     * @return
     */
    public static Date plusDateTime(int n) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,n);
        return calendar.getTime();
    }

    public static Date plusDate(int n) {
        LocalDate localDateTime = LocalDate.now().plusDays(n);
        Instant instant  =  localDateTime.atTime(LocalTime.MIDNIGHT).atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);

    }

    public static String dateFormat(Date date) {
        return simpleDateFormat.format(date);
    }

    public static String dateTimeFormat(Date date) {
        return simpleDateTimeFormat.format(date);
    }

    public static void main(String[] args) {
        Date date = plusDate(-10);
        System.out.println(dateTimeFormat(date));
    }

}
