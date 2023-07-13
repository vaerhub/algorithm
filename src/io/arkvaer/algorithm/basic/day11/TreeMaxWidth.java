package io.arkvaer.algorithm.basic.day11;


import java.util.LinkedList;
import java.util.Queue;

/**
 * 求二叉树的最大宽度
 */
public class TreeMaxWidth {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int v) {
            value = v;
        }
    }

    public static int getTreeMaxWidth(Node root) {
        if (root == null) {
            return 0;
        }
        int max = 0;
        int cur = 0;
        Node curEnd = root;
        Node nextEnd = null;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            root = queue.poll();
            if (root.left != null) {
                queue.add(root.left);
                nextEnd = root.left;
            }
            if (root.right != null) {
                queue.add(root.right);
                nextEnd = root.right;
            }
            cur++;
            if (root == curEnd) {
                max = Math.max(max, cur);
                curEnd = nextEnd;
                cur = 0;
            }

        }
        return max;

    }


    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);
        head.left.right.left = new Node(8);
        head.left.right.right = new Node(9);
        head.left.left.left = new Node(10);
        head.left.left.right = new Node(11);

        head.right.right.left = new Node(12);
        head.right.right.right = new Node(13);
        head.right.left.left = new Node(14);
        head.right.left.right = new Node(15);
        int treeMaxWidth = getTreeMaxWidth(head);
        System.out.println(treeMaxWidth);
    }

}
