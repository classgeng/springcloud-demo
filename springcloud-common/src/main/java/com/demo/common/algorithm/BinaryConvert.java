package com.demo.common.algorithm;

/**
 * 进制转换
 */
public class BinaryConvert {


    /**
     * 将字符串 "12345678" 转换为 ASCII 16 进制表示
     * 1、先将字符转为单个字符，再将单个字符转为ASCII码
     * 2、再将每个ASCII码转为16进制，拼成字符串返回
     * @param str
     * @return
     */
    public static String stringToAsc16(String str){
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<str.length(); i++){
            // 1、先将字符转为单个字符
            char c = str.charAt(i);
            // 2、再将单个字符转为ASCII码
            int acsii = (int) c;
            // 3、再将每个ASCII码转为16进制
            String asc16 = Integer.toHexString(acsii);
            sb.append(asc16);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(stringToAsc16("12345678"));
    }

}
