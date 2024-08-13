package com.demo.common.algorithm;

public class BreathFirstSearch {

    public static void main(String[] args) {
        //构建一个人群，1表示有接触，0表示没接触
        int[] item0 = {1,1,0,1,0};
        int[] item1 = {1,1,0,0,0};
        int[] item2 = {0,0,1,0,1};
        int[] item3 = {1,0,0,1,0};
        int[] item4 = {0,0,1,0,1};
        int[][] map = {item0,item1,item2,item3,item4};
        bfs(map);
    }

    public static void bfs(int[][] map){
        //0:表示未被使用过；1：表示使用过，但是子节点还未使用；2：表示不需要使用了
        int[] pass = new int[map.length];
        for (int i=0; i< map.length; i++){
            if(pass[i] != 2) {
                bfsSelect(map, pass, i);
            }
        }

    }
    private static void bfsSelect(int[][] map, int[] pass, int i) {
        //输出第一排
        for (int j = 0; j < map.length; j++) {
            if(map[i][j] != 0 && pass[j] == 0){
                System.out.print(j + " ");
                pass[j] = 1;
            }
        }
        //输出第二排
        for (int j = 0; j < map.length; j++) {
            if(map[i][j] != 0 && pass[j] == 1){
                pass[j] = 2;
                bfsSelect(map,pass,j);
            }
        }
    }

}
