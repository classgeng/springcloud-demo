package com.demo.common.algorithm;

import java.util.Random;

/**
 * 深度优先
 */
public class DepthFirstSearch {

    public static void main(String[] args) {
        int[][] map = createMap(5);
        //构建一个人群，1表示有接触，0表示没接触
        /*int[] item0 = {1,1,0,1,0};
        int[] item1 = {1,1,0,0,0};
        int[] item2 = {0,0,1,0,1};
        int[] item3 = {1,0,0,1,0};
        int[] item4 = {0,0,1,0,1};
        int[][] map = {item0,item1,item2,item3,item4};*/
        dfs(map);
        System.out.println();
        BreathFirstSearch.bfs(map);
    }

    public static int[][] createMap(int num){
        int[][] map = new int[num][num];
        for (int i=0; i<num; i++){
            for (int j=0; j<num; j++){
                map[i][j] = new Random().nextInt(2);
                System.out.printf("%3d", map[i][j]);
            }
            System.out.println();
        }
        return map;
    }

    public static void dfs(int[][] map){
        boolean[] pass = new boolean[map.length];
        for (int i=0; i<map.length; i++){
            if(!pass[i]) {
                dfsDepth(map, pass, i);
            }
        }
    }
    private static void dfsDepth(int[][] map, boolean[] pass, int i) {
        System.out.print(i + " ");
        pass[i] = true;
        for (int j=0; j<map.length; j++){
            if(!pass[j] && map[i][j] != 0){
                dfsDepth(map,pass,j);
            }
        }
    }


}
