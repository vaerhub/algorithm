package io.arkvaer.algorithm.basic.day12;

import java.util.ArrayList;

/**
 * 查找最大的搜索二叉子树的大小
 * 在线测试链接 : <a href="https://leetcode.cn/problems/largest-bst-subtree">最大BST子树</a>
 *
 * @author waver
 */
public class MaxSubBSTSize {


    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static class Info {
        /**
         * 最小值
         */
        public int min;
        /**
         * 最大值
         */
        public int max;

        /**
         * 最大搜索子树大小
         */
        public int maxSize;
        /**
         * 树节点总个数
         */
        public int allSize;

        public Info(int min, int max, int maxSize, int allSize) {
            this.min = min;
            this.max = max;
            this.maxSize = maxSize;
            this.allSize = allSize;
        }
    }

    public static int maxSubBSTSize(Node head) {
        if (head == null) {
            return 0;
        }
        return process(head).maxSize;

    }

    public static Info process(Node head) {
        if (head == null) {
            return null;
        }
        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);
        int min = head.value;
        int max = head.value;
        int maxSize = 1;
        int allSize = 1;
        int leftMaxSize = -1;
        if (leftInfo != null) {
            min = Math.min(min, leftInfo.min);
            max = Math.max(max, leftInfo.max);
            allSize += leftInfo.allSize;
            leftMaxSize = leftInfo.maxSize;
        }
        int rightMaxSize = -1;
        if (rightInfo != null) {
            min = Math.min(min, rightInfo.min);
            max = Math.max(max, rightInfo.max);
            allSize += rightInfo.allSize;
            rightMaxSize = rightInfo.maxSize;
        }
        int curMaxSize = -1;
        boolean leftBst = leftInfo == null || leftInfo.allSize == leftInfo.maxSize;
        boolean rightBst = rightInfo == null || rightInfo.allSize == rightInfo.maxSize;
        if (leftBst && rightBst) {
            boolean leftMaxLessThanCur = leftInfo == null || leftInfo.max < head.value;
            boolean rightMinGraterThanCur = rightInfo == null || rightInfo.min > head.value;
            if (leftMaxLessThanCur && rightMinGraterThanCur) {
                int leftSize = leftInfo == null ? 0 : leftInfo.allSize;
                int rightSize = rightInfo == null ? 0 : rightInfo.allSize;
                curMaxSize = leftSize + rightSize + 1;
            }
        }

        maxSize = Math.max(curMaxSize, Math.max(leftMaxSize, rightMaxSize));
        return new Info(min, max, maxSize, allSize);
    }


    // 为了验证
    // 对数器方法
    public static int right(Node head) {
        if (head == null) {
            return 0;
        }
        int h = getBSTSize(head);
        if (h != 0) {
            return h;
        }
        return Math.max(right(head.left), right(head.right));
    }

    // 为了验证
    // 对数器方法
    public static int getBSTSize(Node head) {
        if (head == null) {
            return 0;
        }
        ArrayList<Node> arr = new ArrayList<>();
        in(head, arr);
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i).value <= arr.get(i - 1).value) {
                return 0;
            }
        }
        return arr.size();
    }

    // 为了验证
    // 对数器方法
    public static void in(Node head, ArrayList<Node> arr) {
        if (head == null) {
            return;
        }
        in(head.left, arr);
        arr.add(head);
        in(head.right, arr);
    }

    // 为了验证
    // 对数器方法
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // 为了验证
    // 对数器方法
    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    // 为了验证
    // 对数器方法
    public static void main(String[] args) {
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (maxSubBSTSize(head) != right(head)) {
                System.out.println("出错了！");
            }
        }
        System.out.println("测试结束");
    }
}
