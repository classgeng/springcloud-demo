package com.demo.common.util;

import java.util.UUID;

public class UUIDUtils {

    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        String uuidStr = uuid.toString().replace("-", "");
        return uuidStr;
    }

    public static void main(String[] args) {
        int totalPage = (1001 + 100 - 1) / 100;
        System.out.println(totalPage);

    }

}
