
package com.demo.common.algorithm;

/**
 * 求素数
 * 0、1除外，既不是素数也不是合数
 * 能被1和本身整除除外的数，称为素数
 */
public class PrimeNumber {

    /**
     * 暴力算法，
     */
    public static int blsf(int number){
        int count = 0;
        for(int n =2; n<number; n++){
            boolean flag = true;
            for(int j=2; j<n; j++){
                if(n % j == 0){
                    flag = false;
                    break;
                }
            }
            count += flag ? 1:0;
        }
        return count;
    }


    // 埃筛法
    public static int asf(int number){
        int count = 0;
        boolean[] isPrime = new boolean[number]; // false代表素数
        for(int i =2; i<number; i++){
            if(!isPrime[i]){
                count++;
                for(int j=i*i; j<number; j+=i){
                    isPrime[j] = true; // 合数
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(blsf(100));
        System.out.println(asf(100));
    }

}
