package io.arkvaer.algorithm.basic.day12;

/**
 * 判断二叉树是否为平衡二叉树
 *
 * @author waver
 */
public class IsBalanced {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }


    public static boolean isBalanced1(Node head) {
        boolean[] ans = new boolean[1];
        ans[0] = true;
        process1(head, ans);
        return ans[0];
    }

    public static int process1(Node head, boolean[] isBalanced) {
        if (head == null) {
            return 0;
        }
        int leftHeight = process1(head.left, isBalanced);
        int rightHeight = process1(head.right, isBalanced);
        if (Math.abs(leftHeight - rightHeight) > 1) {
            isBalanced[0] = false;
        }
        return Math.max(leftHeight, rightHeight) + 1;
    }

    public static class Info {
        private int height;
        private boolean isBalanced;

        public Info(int height, boolean isBalanced) {
            this.height = height;
            this.isBalanced = isBalanced;
        }
    }

    public static boolean isBalanced2(Node head) {
        return process2(head).isBalanced;
    }

    public static Info process2(Node head) {
        if (head == null) {
            return new Info(0, true);
        }
        Info leftInfo = process2(head.left);
        Info rightInfo = process2(head.right);
        boolean isBalanced = true;
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        if (!leftInfo.isBalanced || !rightInfo.isBalanced) {
            isBalanced = false;
        } else {
            if (Math.abs(leftInfo.height - rightInfo.height) > 1) {
                isBalanced = false;
            }
        }
        return new Info(height, isBalanced);
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
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void main(String[] args) {
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (isBalanced1(head) != isBalanced2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
