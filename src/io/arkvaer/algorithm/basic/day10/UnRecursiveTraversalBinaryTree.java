package io.arkvaer.algorithm.basic.day10;

import java.util.Stack;

/**
 * 非递归方式遍历二叉树
 * @author arkvaer
 */
public class UnRecursiveTraversalBinaryTree {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int v) {
            value = v;
        }
    }

    public static void pre(Node head) {
        System.out.print("pre-order: ");
        if (head == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(head);
        while (!stack.isEmpty()) {
            head = stack.pop();
            System.out.print(head.value + " ");
            if (head.right != null) {
                stack.push(head.right);
            }
            if (head.left != null) {
                stack.push(head.left);
            }
        }
        System.out.println();
    }


    public static void in(Node head) {
        System.out.print("in-order: ");
        if (head != null) {
            Stack<Node> stack = new Stack<>();
            while (!stack.isEmpty() || head != null) {
                if (head != null) {
                    stack.push(head);
                    head = head.left;
                } else {
                    head = stack.pop();
                    System.out.print(head.value + " ");
                    head = head.right;
                }
            }

        }
        System.out.println();
    }

    /**
     * 使用两个栈实现后序遍历
     *
     * @param head 给定二叉树头节点
     */
    public static void pos1(Node head) {
        System.out.print("pos-order: ");
        if (head == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        Stack<Node> prt = new Stack<>();
        stack.push(head);
        while (!stack.isEmpty()) {
            head = stack.pop();
            prt.push(head);
            if (head.left != null) {
                stack.push(head.left);
            }
            if (head.right != null) {
                stack.push(head.right);
            }
        }
        while (!prt.isEmpty()) {
            System.out.print(prt.pop().value + " ");
        }
        System.out.println();
    }

    /**
     * 使用一个栈实现二叉树的后序遍历
     *
     * @param head 二叉树的头节点
     */
    public static void pos2(Node head) {
        System.out.print("pos-order: ");
        if (head == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(head);
        Node cur;
        while (!stack.isEmpty()){
            cur = stack.peek();
            if (cur.left != null && head != cur.left && head != cur.right) {
                stack.push(cur.left);
            } else if (cur.right != null && head != cur.right) {
                stack.push(cur.right);
            } else {
                System.out.print(stack.pop().value + " ");
                head = cur;
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);
        pre(head);
        System.out.println("========");
        in(head);
        System.out.println("========");
        pos1(head);
        System.out.println("========");
        pos2(head);
        System.out.println("========");
    }
}
