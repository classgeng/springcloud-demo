package com.demo.common;

/**
 * 冒泡排序
 */
public class BubbleSoft {

    public static void main(String[] args) {
        int[] arr = {10,4,6,7,5,3,8};
        bubbleSoft(arr);
        for (int aa: arr) {
            System.out.print(aa + " ");
        }
    }

    public static void bubbleSoft(int[] arr){
        int temp;
        for(int i=0; i< arr.length; i++){
            for(int j=0; j< arr.length-i-1; j++){
                if(arr[j] > arr[j+1]){
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }


    }


}
