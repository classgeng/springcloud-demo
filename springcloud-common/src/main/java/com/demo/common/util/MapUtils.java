package com.demo.common.util;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class MapUtils {

    public static void main(String[] args) {
        /*Map<String,Integer> map = new HashMap<>();
        map.put("dd",4);
        map.put("bb",2);
        map.put("cc",3);
        map.put("aa",1);
        System.out.println(map);
        System.out.println(sort(map));*/

        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("WAREHOUSEID", 1234);

        System.out.println(map.get("WAREHOUSEID"));
    }

    /**
     * map根据value排序
     * @param map
     * @return
     */
    public static Map<String,Integer> sortByValue(Map<String,Integer> map){
        Map<String,Integer> sortedMap = new HashMap<>();
        map.entrySet().stream().sorted(Map.Entry.comparingByValue()).
                forEach(item -> sortedMap.put(item.getKey(),item.getValue()));
        return sortedMap;
    }

}
