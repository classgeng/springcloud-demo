package com.demo.common.I18N;

import java.util.Locale;

public class I18NUtils {

    public static String msg(String code, Object[] args, String language) {
        // 简单对语言进行直接处理，Language可以通过tsf-common的拦截器自动注入到线程Context中，也可以直接从Body里获取
        if (language.equals("zh-CN")) {
            return SpringContextHolder.getApplicationContext().getMessage(code, args, Locale.SIMPLIFIED_CHINESE);
        } else if (language.equals("en-US")) {
            return SpringContextHolder.getApplicationContext().getMessage(code, args, Locale.US);
        } else {
            throw new RuntimeException("not found expect language");
        }
    }



}