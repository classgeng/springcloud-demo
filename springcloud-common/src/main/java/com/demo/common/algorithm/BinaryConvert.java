package com.demo.common.algorithm;

import java.sql.SQLOutput;
import java.util.Scanner;

/**
 * 进制转换
 */
public class BinaryConvert {

    public static void main(String[] args) {
       /* System.out.println(stringToAsc16("12345678"));

        binaryConvert();
        hexToBinary();*/

        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入一个16进制数字（例如：0xAF或#AF）: ");
        /*String hexStr = scanner.nextLine();
        int hexNumber = Integer.parseInt(hexStr,16);*/

        while(scanner.hasNext()){
            int hexNumber = scanner.nextInt(16);
            System.out.println("你输入的10进制数字是: " + hexNumber);
        }
        scanner.close();
    }


    public static void strToHex(int hex){
        System.out.println(Integer.parseInt(String.valueOf(hex)));
    }

    public static void hexToTen(String hex){
        System.out.println(Integer.parseInt(hex));
    }


    /**
     * 进制转换
     */
    public static void binaryConvert(){
        int ten = 78;
        System.out.println("十进制转二进制：" + Integer.toBinaryString(ten));
        System.out.println("十进制转八进制：" + Integer.toOctalString(ten));
        System.out.println("十进制转十六进制：" + Integer.toHexString(ten));

        int binary = 0b11101;
        System.out.println("二进制转十进制：" + Integer.parseInt(String.valueOf(binary)));
        int octal = 035;
        System.out.println("八进制转十进制：" + Integer.parseInt(String.valueOf(octal)));
        int hex = 0x1d;
        System.out.println("十六进制转十进制：" + Integer.parseInt(String.valueOf(hex)));
    }


    /**
     * 十六进制转成二进制
     * 1、先将十六进制转出十进制
     * 2、再将十进制转出二进制
     */
    public static void hexToBinary(){
        int hex = 0xbd;
        int ten = Integer.parseInt(String.valueOf(hex));
        System.out.println("先将十六进制转出十进制:"+ten);
        System.out.println("再将十进制转出二进制:"+ Integer.toBinaryString(ten));
    }


    /**
     * 二进制转十进制
     *
     * @return
     */
    public static int twoToTen(int binary){
        return Integer.parseInt(String.valueOf(binary));
    }


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



}
