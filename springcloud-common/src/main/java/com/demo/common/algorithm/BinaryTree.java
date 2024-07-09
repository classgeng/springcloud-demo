package com.demo.common.algorithm;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 二叉树
 */
public class BinaryTree {

    static class TreeNode {
        int value;
        TreeNode leftNode;
        TreeNode rightNode;
        int deep;

        public TreeNode(int value){
            this.value = value;
            this.leftNode = null;
            this.rightNode = null;
        }

        public TreeNode(int value, TreeNode leftNode, TreeNode rightNode){
            this.value = value;
            this.leftNode = leftNode;
            this.rightNode = rightNode;
        }
    }

    /**
     * 二叉树最小深度-深度（高度）优先
     * 递归，找到深度最小的节点返回
     * @param treeNode
     * @return
     */
    public static int minDepth(TreeNode treeNode) {
        if (null == treeNode) {
            return 0;
        }
        if (null == treeNode.leftNode && null == treeNode.rightNode) {
            return 1;
        }
        int leftMin = Integer.MAX_VALUE;
        int rightMin = Integer.MAX_VALUE;
        if(null != treeNode.leftNode){
            // 递归返回左节点深度
            leftMin = minDepth(treeNode.leftNode);
            System.out.println("leftNode:"+treeNode.leftNode.value+", leftMin:"+leftMin);
        }
        if(null != treeNode.rightNode){
            // 递归返回右节点深度
            rightMin = minDepth(treeNode.rightNode);
            System.out.println("rightNode:"+treeNode.rightNode.value+", rightMin:"+rightMin);
        }
        // 比较左节点跟右节点，取深度小的+1返回
        int min = Math.min(leftMin,rightMin);
        System.out.println("min:"+min);
        return min + 1;
    }

    /**
     * 二叉树最小深度-广度（宽度）优先
     * 采用队列（queue），先进先出，直到找到第一个叶子节点返回深度
     * 否则一直循环找左节点、右节点，deep+1，并加入队列
     * @param treeNode
     * @return
     */
    public static int minDepth1(TreeNode treeNode) {
        if (null == treeNode) {
            return 0;
        }
        treeNode.deep = 1;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(treeNode);
        while(!queue.isEmpty()){
            TreeNode currNode = queue.poll();
            if(null == currNode.leftNode && null == currNode.rightNode){
                return currNode.deep; // 已经找到最浅的节点，返回当前节点深度即可
            }
            if(currNode.leftNode != null){
                currNode.leftNode.deep = currNode.deep+1;
                queue.offer(currNode.leftNode);
            }
            if(currNode.rightNode != null){
                currNode.rightNode.deep = currNode.deep+1;
                queue.offer(currNode.rightNode);
            }
        }
        return 0;
    }

    /**
     * 构建满二叉
     * 特点：
     * 1、每层都是满的
     * 2、叶子节点全部在最底层
     * 3、节点顺序，从上到下，从左到右。
     * 满二叉树一定是一个完全二叉树，反之不一定
     * @param depth 树的深度
     * @param value 初始值
     * @return
     */
    public static TreeNode buildFullTree(int depth,int value) {
        if(depth < 1){
            return null;
        }
        TreeNode node = new TreeNode(value);
        node.leftNode = buildFullTree(depth-1,2*value);
        node.rightNode = buildFullTree(depth-1,2*value+1);
        return node;
    }

    /**
     * 二叉树遍历-递归
     * 前序（根左右）、中序（左根右）、后序（左右根）
     * @param root
     */
    public static void recursionOrder(TreeNode root){
        if(null == root){
            return;
        }
        // 前序遍历1245673
        // System.out.println(root.value);
        recursionOrder(root.leftNode);
        // 中序遍历4265713
        // System.out.println(root.value);
        recursionOrder(root.rightNode);
        // 后序遍历4675231
        System.out.println(root.value);

    }

    /**
     * 二叉树遍历-递归
     * 层序遍历
     * @param root
     */
    public static void levelOrder(TreeNode root, int index, List<Integer> list){
        if(null == root || index < 1){
            return;
        }
        int lenght = list.size();
        if(lenght <= index){
            for(int i=0; i<=index-lenght; i++){
                list.add(lenght+i,null);
            }
        }
        list.set(index,root.value);
        // 前序遍历1234567
        levelOrder(root.leftNode,2*index,list);
        levelOrder(root.rightNode,2*index+1,list);
    }

    /**
     * 二叉树遍历-迭代
     * 层序遍历
     * @param root
     */
    public static void levelOrder(TreeNode root){
        if(null == root){
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            TreeNode currNode = queue.poll();
            System.out.print(currNode.value + " ");
            if(null != currNode.leftNode){
                queue.offer(currNode.leftNode);
            }
            if(null != currNode.rightNode){
                queue.offer(currNode.rightNode);
            }
        }
    }


    public static void main(String[] args) {
        TreeNode node7 = new TreeNode(7, null, null);
        TreeNode node6 = new TreeNode(6, null, null);
        TreeNode node5 = new TreeNode(5, node6, node7);
        TreeNode node4 = new TreeNode(4, null, null);
        TreeNode node3 = new TreeNode(3, null, null);
        TreeNode node2 = new TreeNode(2, node4, node5);
        TreeNode node1 = new TreeNode(1, node2, node3);

        levelOrder(node1);

        /*List<Integer> list = new ArrayList<>();
        levelOrder(node1,1,list);
        list.removeIf(item -> item == null);
        System.out.println(Arrays.toString(list.toArray()));*/

        //TreeNode fullTrue = buildFullTree(3, 1);
        //System.out.println(minDepth(node1));
        //System.out.println(minDepth1(node1));

        // 生成4个4层的满二叉树
        /*TreeNode tree1 = buildFullTree(4,1);
        TreeNode tree2 = buildFullTree(4,1);
        TreeNode tree3 = buildFullTree(4,1);
        TreeNode tree4 = buildFullTree(4,1);
        // 把4棵树根节点串起来
        TreeNode[] treeList = {tree1, tree2, tree3, tree4};
        for (int i =0; i<treeList.length; i++){
            System.out.println("第"+i+"棵满二叉树开始遍历：");
            TreeNode tree = treeList[i];
            // TODO 如何遍历二叉树
            System.out.println(tree.value);
            System.out.println("第"+i+"棵满二叉树遍历结束");
            System.out.println(" ");
        }*/




    }



}
