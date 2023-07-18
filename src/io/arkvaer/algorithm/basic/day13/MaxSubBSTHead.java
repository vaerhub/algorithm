package io.arkvaer.algorithm.basic.day13;

import java.util.ArrayList;
import java.util.List;

/**
 * 找到二叉树中最大搜索子树的头节点
 */
public class MaxSubBSTHead {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static Node maxSubBSTHead1(Node head) {
        if (head == null) {
            return null;
        }
        if (getMaxBSTSize(head) != 0) {
            return head;
        }
        Node leftAns = maxSubBSTHead1(head.left);
        Node rightAns = maxSubBSTHead1(head.right);
        // 若左右两边子树最大搜索子树的大小相等, 则返回左树的
        return getMaxBSTSize(leftAns) >= getMaxBSTSize(rightAns) ? leftAns : rightAns;
    }

    public static int getMaxBSTSize(Node head) {
        if (head == null) {
            return 0;
        }
        List<Node> nodeList = new ArrayList<>();
        inOrder(head, nodeList);
        for (int i = 1; i < nodeList.size(); i++) {
            if (nodeList.get(i).value <= nodeList.get(i - 1).value) {
                return 0;
            }
        }
        return nodeList.size();
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
        public Node maxSubBSTHead;
        public int maxSubBSTSize;
        public int min;
        public int max;

        public Info(Node maxSubBSTHead, int maxSubBSTSize, int min, int max) {
            this.maxSubBSTHead = maxSubBSTHead;
            this.maxSubBSTSize = maxSubBSTSize;
            this.min = min;
            this.max = max;
        }
    }

    public static Node maxSubBSTHead2(Node head) {
        if (head == null) {
            return null;
        }
        return process(head).maxSubBSTHead;
    }

    public static Info process(Node head) {
        if (head == null) {
            return null;
        }
        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);
        int min = head.value;
        int max = head.value;
        Node maxSubBSTHead = null;
        int maxSubBSTSize = 0;
        if (leftInfo != null) {
            min = Math.min(min, leftInfo.min);
            max = Math.max(max, leftInfo.max);
            maxSubBSTHead = leftInfo.maxSubBSTHead;
            maxSubBSTSize = leftInfo.maxSubBSTSize;
        }

        if (rightInfo != null) {
            min = Math.min(min, rightInfo.min);
            max = Math.max(max, rightInfo.max);
            if (rightInfo.maxSubBSTSize > maxSubBSTSize) {
                maxSubBSTHead = rightInfo.maxSubBSTHead;
                maxSubBSTSize = rightInfo.maxSubBSTSize;
            }
        }

        if ((leftInfo == null|| (leftInfo.maxSubBSTHead == head.left && leftInfo.max < head.value))
                && (rightInfo == null|| (rightInfo.maxSubBSTHead == head.right && rightInfo.min > head.value))
        ) {
            maxSubBSTSize = (leftInfo == null ? 0 : leftInfo.maxSubBSTSize) + (rightInfo == null ? 0 : rightInfo.maxSubBSTSize) + 1;
            maxSubBSTHead = head;
        }
        return new Info(maxSubBSTHead, maxSubBSTSize, min, max);

    }


    // region Test
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
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
            if (maxSubBSTHead1(head) != maxSubBSTHead2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
    // endregion
}
