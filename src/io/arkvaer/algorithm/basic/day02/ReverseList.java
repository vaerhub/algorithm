package io.arkvaer.algorithm.basic.day02;

import io.arkvaer.algorithm.utils.AlgUtil;

/**
 * 反转链表
 *
 * @author waver
 * @date 2023/6/23 下午3:52
 */
public class ReverseList {
    public static class Node {
        private int val;
        private Node next;

        public Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }
    }

    public static class DoubleNode {
        private int val;
        private DoubleNode prev;
        private DoubleNode next;

        public DoubleNode(int val) {
            this.val = val;
        }

        public DoubleNode(int val, DoubleNode prev, DoubleNode next) {
            this.val = val;
            this.prev = prev;
            this.next = next;
        }
    }


    public Node reverse(Node node) {
        Node prev = null;
        Node next = null;
        while (node != null) {
            next = node.next;
            node.next = prev;
            prev = node;
            node = next;
        }
        return prev;
    }


    public static DoubleNode reverseDouble(DoubleNode node) {
        DoubleNode prev = null;
        DoubleNode next;
//        DoubleNode head = node;
        while (node != null) {
            next = node.next;
            node.next = prev;
            node.prev = next;
            prev = node;
            node = next;
        }
        return prev;
    }


    public static void printDoubleNode(DoubleNode node) {
        System.out.println("========================");
        while (node != null) {
            System.out.println(node.val);
            node = node.prev;
        }

    }

    public static void main(String[] args) {
        DoubleNode node1 = new DoubleNode(1);
        DoubleNode node2 = new DoubleNode(2);
        DoubleNode node3 = new DoubleNode(3);
        DoubleNode node4 = new DoubleNode(4);
        DoubleNode node5 = new DoubleNode(5);
        DoubleNode node6 = new DoubleNode(6);
        node1.next = node2;
        node2.prev = node1;
        node2.next = node3;
        node3.prev = node2;
        node3.next = node4;
        node4.prev = node3;
        node4.next = node5;
        node5.prev = node4;
        node5.next = node6;
        node6.prev = node5;
        // printDoubleNode(node1);
        DoubleNode node = reverseDouble(node1);
        printDoubleNode(node);

    }
}
