package com.demo.common;

import sun.jvm.hotspot.opto.MachIfNode;

/**
 * 二分查找
 *
 * 孙悟空爱吃蟠桃，有一天趁着蟠桃园守卫不在来偷吃。已知蟠桃园有N颗桃树，每颗树上都有桃子，守卫将在H小时后回来。
 *
 * 孙悟空可以决定他吃蟠桃的速度K（个/小时），每个小时选一颗桃树，并从树上吃掉K个，如果树上的桃子少于K个，则全部吃掉，并且这一小时剩余的时间里不再吃桃。
 *
 * 孙悟空喜欢慢慢吃，但又想在守卫回来前吃完桃子。
 *
 * 请返回孙悟空可以在H小时内吃掉所有桃子的最小速度K（K为整数）。如果以任何速度都吃不完所有桃子，则返回0。
 */
public class MidSeclet {

    /**
     * 1、每小时只能吃一颗桃树，所以桃树的数量不能大约H
     * @param peaches
     * @param H
     * @return
     */
    public static int minSpeedK(int[] peaches, int H){
        if(peaches.length > H){
            return 0;
        }
        int leftK = 1, rightK = 1000000000;
        while (leftK <= rightK){
            // 每小时吃桃速度
            int midK = (leftK + rightK) >> 1;
            if(eatPeaches(peaches, midK, H)){
                rightK = midK - 1;
            } else{
                leftK = midK + 1;
            }
        }
        return leftK;
    }


    /**
     * 计算吃完所有桃树所发的时间是否 > H
     * @param peaches
     * @param K 每小时吃桃个数
     * @param H 守卫返回时间
     * @return 返回true，说明可以吃完，速度还可以放慢；返回false，说明吃不完，需要加快速度
     */
    public static boolean eatPeaches(int[] peaches, int K, int H){
        int totalTime = 0;
        for (int i =0; i<peaches.length; i++) {
            //吃完每课桃树所发的时间
            int t1 = peaches[i] / K; // 取整
            int t2 = peaches[i] % K > 0 ? 1 : 0;  // 取余
            totalTime += (t1 + t2);
            //System.out.println(totalTime);
            if(totalTime > H) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        int[] peaches = {2,3,4,5};
        int H = 4;
        int minK = minSpeedK(peaches, H);
        System.out.println(minK);

    }

}
