package com.demo.common.algorithm;

import java.util.HashSet;
import java.util.Set;

/**
 * 单向链表
 */
public class LinkedList {

    /**
     * 单向链表节点属性
     */
    static class LinkedNode {
        public LinkedNode(int value, LinkedNode next){
            this.value = value;
            this.next = next;
        }
        int value;
        LinkedNode next;
    }

    /**
     * 迭代，反转
     * @param currNode
     * @return
     */
    public static LinkedNode reversal(LinkedNode currNode){
        LinkedNode next,prev = null;
        while (currNode != null){
            next = currNode.next;
            currNode.next = prev;
            prev = currNode;
            currNode = next;
        }
        return prev;
    }

    /**
     * 递归，反转
     * @param currNode
     * @return
     */
    public static LinkedNode recursion(LinkedNode currNode){
        if(null == currNode || null == currNode.next){
            return currNode;
        }
        LinkedNode prev = recursion(currNode.next);
        currNode.next.next = currNode;
        currNode.next = null;
        return prev;
    }

    /**
     * Set集合的 add() 判断是否有重复元素
     * @param currNode
     * @return
     */
    public static boolean hasCycle(LinkedNode currNode){
        Set<LinkedNode> set = new HashSet<>();
        while (null != currNode){
            if(!set.add(currNode)){
                return true;
            }
            currNode = currNode.next;
        }
        return false;
    }

    /**
     * 双指针判断，时间空间复杂度更低
     * @param currNode
     * @return
     */
    public static boolean hasCycle1(LinkedNode currNode){
        if(null == currNode || currNode.next == null){
            return false;
        }
        LinkedNode next1 = currNode;
        LinkedNode next2 = currNode.next;
        while (next1 != next2){
            if(next2 == null || next2.next == null){
                return false;
            }
            next1 = next1.next;
            next2 = next2.next.next;
        }
        return true;
    }




    public static void main(String[] args) {
        LinkedNode node5 = new LinkedNode(5,null);
        LinkedNode node4 = new LinkedNode(4,node5);
        LinkedNode node3 = new LinkedNode(3,node4);
        LinkedNode node2 = new LinkedNode(2,node3);
        LinkedNode node1 = new LinkedNode(1,node2);

        /*LinkedNode prev = recursion(node1);
        while (null != prev){
            System.out.println(prev.value);
            prev = prev.next;
        }*/

        //node5.next = node3;
        System.out.println(hasCycle1(node1));


    }


}
