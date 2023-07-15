package io.arkvaer.algorithm.basic.day12;

import io.arkvaer.algorithm.utils.AlgUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 判断给定二叉树是否为[搜索二叉树]
 *
 * @author waver
 * @date 2023/7/15 16:45
 */
public class IsBST {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    /**
     * 使用中序遍历方式校验是否为搜索二叉树
     *
     * @param head 给定的二叉树头节点
     * @return 是否为搜索二叉树
     */
    public static boolean isBST1(Node head) {
        if (head == null) {
            return true;
        }
        List<Node> nodeList = new ArrayList<>();
        inOrder(head, nodeList);
        for (int i = 1; i < nodeList.size(); i++) {
            if (nodeList.get(i).value <= nodeList.get(i - 1).value) {
                return false;
            }
        }
        return true;
    }

    public static void inOrder(Node head, List<Node> nodeList) {
        if (head == null) {
            return;
        }
        inOrder(head.left, nodeList);
        nodeList.add(head);
        inOrder(head.right, nodeList);
    }


    public static class Info {
        /**
         * 是否为搜索二叉树
         */
        boolean isSearch;
        /**
         * 最小值
         */
        int min;
        /**
         * 最大值
         */
        int max;

        public Info(boolean isSearch, int min, int max) {
            this.isSearch = isSearch;
            this.min = min;
            this.max = max;
        }
    }


    public static boolean isBST2(Node head) {
        if (head == null) {
            return true;
        }
        return process(head).isSearch;
    }

    public static Info process(Node head) {
        if (head == null) {
            return null;
        }
        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);
        int min = head.value;
        int max = head.value;
        boolean isSearch = true;
        if (leftInfo != null) {
            min = Math.min(min, leftInfo.min);
            max = Math.max(max, leftInfo.max);
        }
        if (rightInfo != null) {
            min = Math.min(min, rightInfo.min);
            max = Math.max(max, rightInfo.max);
        }
        if (leftInfo != null && !leftInfo.isSearch) {
            isSearch = false;
        }

        if (rightInfo != null && !rightInfo.isSearch) {
            isSearch = false;
        }

        if (leftInfo != null && leftInfo.max >= head.value) {
            isSearch = false;
        }

        if (rightInfo != null && rightInfo.min <= head.value) {
            isSearch = false;
        }

        return new Info(isSearch, min, max);

    }

    // for test
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node(AlgUtil.random.nextInt( maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (isBST1(head) != isBST2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }


}
