package com.slyang.test.algorithm.binaryTree;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Description:
 * Created by slyang <slyang520@yeah.net>
 * Copyright (c) 2019, All Rights Reserved.
 */
public class TreeNode {

    int val;
    String tag;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
        tag = "tag "+x;
    }

    public TreeNode(int x, TreeNode left, TreeNode right) {
        val = x;
        tag = "tag "+x;
        this.left = left;
        this.right = right;
    }

    public static void main(String[] args) {
        /**
         * 如果二叉树每个节点都具有相同的值，那么该二叉树就是单值二叉树。
         * 只有给定的树是单值二叉树时，才返回 true；否则返回 false。
         */
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        node1.left = node2;
        node1.right = node3;

        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node7 = new TreeNode(7);
        node2.left = node4;
        node2.right = node5;
        node3.right = node7;

        //preOrderTraverse1(node1);
        //preOrderTraverse2(node1);
        //preOrderTraverse3(node1);
        System.out.println(" --- --- --- ");
        //iterativeTraversal1(node1);
        //iterativeTraversal2(node1);

        levelOrder(node1);


        System.out.println( node1.binaryTreePaths(node1) );


    }

    public static TreeNode  arrayToBinaryTree(Integer[] array,int index){

            TreeNode tn = null;
            if (index<array.length) {
                Integer value = array[index];
                if (value == null) {
                    return null;
                }
                tn = new TreeNode(value);
                tn.left = arrayToBinaryTree(array, 2*index+1);
                tn.right = arrayToBinaryTree(array, 2*index+2);
                return tn;
            }
            return null;

    }

//    前序遍历
//    中序遍历
//    后序遍历
//    这三种遍历顺序，都是属于二叉树中的深度优先搜索(DFS)

//    而层序遍历(即从左到右一层层的遍历)又属于二叉树中的广度优先搜索(BFS)。



    // 前序遍历
    public static void preOrderTraverse1(TreeNode root) {
        if (root != null) {
            System.out.println(root.tag + "  ");
            preOrderTraverse1(root.left);
            preOrderTraverse1(root.right);
        }
    }

    // 中序遍历
    public static void preOrderTraverse2(TreeNode root) {
        if (root != null) {
            preOrderTraverse1(root.left);
            System.out.println(root.tag + "  ");
            preOrderTraverse1(root.right);
        }
    }

    // 后序遍历
    public static void preOrderTraverse3(TreeNode root) {
        if (root != null) {
            preOrderTraverse1(root.left);
            preOrderTraverse1(root.right);
            System.out.println(root.tag + "  ");
        }
    }

    // 迭代遍历(前序)
    public static void iterativeTraversal1(TreeNode root) {
        Stack<TreeNode> nodeStack = new Stack<>();
        nodeStack.push(root);
        while (nodeStack.size() != 0) {
            TreeNode node = nodeStack.pop();
            System.out.println(node.tag + "  ");

            if (node.right != null){
                nodeStack.push(node.right);
            }
            if (node.left != null) {
                nodeStack.push(node.left);
            }
            // 先遍历左 再遍历右
         }
    }

    // 迭代遍历(前序)
    public static void iterativeTraversal2(TreeNode root) {
        Stack<TreeNode> nodeStack = new Stack<>();
        nodeStack.push(root);
        while (nodeStack.size() != 0) {
            TreeNode node = nodeStack.pop();
            System.out.println(node.tag + "  ");

            if (node.left != null) {
                nodeStack.push(node.left);
            }
            if (node.right != null){
                nodeStack.push(node.right);
            }
            // 先遍历右 再遍历左
        }
    }


    // 层序遍历
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result=new ArrayList<>();

        List<TreeNode> list=new ArrayList<>(); // 记录当前层级的节点
        if(root==null) return result;

        list.add(root);

        while(!list.isEmpty()){
            List<Integer> curList=new ArrayList<>();
            List<TreeNode> nextList=new ArrayList<>();

            for(TreeNode cur:list){
                curList.add(cur.val);
                if(cur.left!=null) nextList.add(cur.left);
                if(cur.right!=null) nextList.add(cur.right);
            }
            list=nextList;
            result.add(curList);
        }

       //  [[1],[2,3],[4,5,7]]

        System.out.println(JSON.toJSON(result));
        return result;
    }

    //  层序遍历 02   深度优先搜索
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> levelOrder02(TreeNode root) {
        DFS(root, 0);
        return res;
    }


    private void DFS(TreeNode root, int level) {
        //递归终止条件
        if (root == null) {
            return;
        }
        if (level >= res.size()) {
            //如果是新的一层,就创建
            res.add(new ArrayList<>());
        }
        //添加当前的元素
        res.get(level).add(root.val);
        //遍历左节点
        DFS(root.left, level + 1);
        //遍历又节点
        DFS(root.right, level + 1);
    }


    // 获取所有路径
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> ret = new ArrayList<>();
        if(root==null) return ret;
        solvePath(root, "", ret);
        return ret;
    }

    public void solvePath(TreeNode node,String prePath,List<String> ret){
        if(node==null){
            return;
        }

        prePath =  "".equals(prePath)?String.valueOf(node.val): ( prePath+ ";"+node.val );

        if(node.left== null && node.right == null){
            // 叶子节点
            ret.add(prePath);
        }else{
            if(node.left!=null){
                solvePath(node.left,prePath,ret);
            }
            if(node.right!=null){
                solvePath(node.right,prePath,ret);
            }
        }





    }


}
