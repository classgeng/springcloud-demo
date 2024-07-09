package com.demo.common.util;

import java.util.concurrent.ConcurrentHashMap;

public class JvmCache {

    private static ConcurrentHashMap<String, Object> jvmCache = new ConcurrentHashMap<>();

    public static void put(String key, Object value){
        jvmCache.put(key,value);
    }

    public static Object get(String key){
        return jvmCache.get(key);
    }

    public static int size(){
        return jvmCache.size();
    }

}
