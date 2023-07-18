package io.arkvaer.algorithm.basic.day13;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 判断二叉树是否为完全二叉树
 *
 * @author waver
 * @date 2023/7/15 15:52
 */
public class IsCBT {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    /**
     * 使用按层遍历的方式判断是否为完全二叉树
     *
     * @return 是否为完全二叉树
     */
    public static boolean isCbt1(Node head) {
        // 空树时认为是完全二叉树
        if (head == null) {
            return true;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        // 是否已经遇到左右孩子不全的节点?
        boolean isLeaf = false;
        Node cur;
        while (!queue.isEmpty()) {
            cur = queue.poll();
            // 当遇到过左右孩子不双全的节点后再出现左右孩子双全节点则不是完全二叉树
            if ((isLeaf && (cur.left != null || cur.right != null)) ||
                    // 当某个节点的左孩子为空, 右孩子不为空时, 则不是完全二叉树
                    (cur.left == null && cur.right != null)) {

                return false;
            }
            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
            if (cur.left == null || cur.right == null) {
                isLeaf = true;
            }
        }
        return true;
    }

    public static class Info {
        /**
         * 是否为满二叉树
         */
        boolean isFull;
        /**
         * 是否为完全二叉树
         */
        boolean isComplete;
        /**
         * 树的高度
         */
        int height;

        public Info(boolean isFull, boolean isComplete, int height) {
            this.isFull = isFull;
            this.isComplete = isComplete;
            this.height = height;
        }
    }

    public static boolean isCbt2(Node head) {
        if (head == null) {
            return true;
        }
        return process(head).isComplete;
    }

    public static Info process(Node head) {
        if (head == null) {
            return new Info(true, true, 0);
        }
        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);
        boolean isFull = false;
        boolean isComplete = false;
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        // 左子树和右子树都是满二叉树, 且左右子树高度相等, 则当前树也是满二叉树, 当然也是完全二叉树
        if (leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height) {
            isFull = true;
            isComplete = true;
        } else {
            // 若左子树和右子树都是满二叉树, 且左子树的高度 == 右子树的高度 + 1, 则该二叉树是完全二叉树
            if (leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height + 1) {
                isComplete = true;
            }

            // 若左子树是满二叉树, 右子树是完全二叉树(不是满二叉树) 且左子树高度==右子树高度, 则该树为完全二叉树
            if (leftInfo.isFull && rightInfo.isComplete && leftInfo.height == rightInfo.height) {
                isComplete = true;
            }

            // 若左子树为完全二叉树(不是满二叉树), 且右子树是满二叉树时, 左子树高度 == 右子树高度 + 1, 则该树为完全二叉树
            if (leftInfo.isComplete && rightInfo.isFull && leftInfo.height == rightInfo.height + 1) {
                isComplete = true;
            }
        }

        return new Info(isFull, isComplete, height);
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
            if (isCbt1(head) != isCbt2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

}
