package io.arkvaer.algorithm.day4;

import io.arkvaer.algorithm.utils.AlgUtil;

/**
 *
 */
public class ReverseLinkedList {
    public static class Node {
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static class DoubleNode {
        public int value;
        private DoubleNode prev;

        public DoubleNode next;

        public DoubleNode(int value) {
            this.value = value;
        }
    }

    public static Node reverseLinkedList(Node node) {
        Node prev = null;
        Node next;
        while (node != null) {
            next = node.next;
            node.next = prev;
            prev = node;
            node = next;
        }
        return prev;
    }

    public static DoubleNode reverseDoubleLinkedList(DoubleNode node) {
        DoubleNode prev = null;
        DoubleNode next;
        while (node != null) {
            next = node.next;
            node.prev = next;
            node.next = prev;
            prev = node;
            node = next;
        }
        return prev;
    }

    public static void main(String[] args) {
        Node node = new Node(1);
        node.next = new Node(2);
        node.next.next = new Node(3);
        node = reverseLinkedList(node);
        while (node != null){
            AlgUtil.console(node.value);
            node = node.next;
        }

        AlgUtil.console("========================");
        DoubleNode node1 = new DoubleNode(1);

        DoubleNode node2 = new DoubleNode(2);

        DoubleNode node3 = new DoubleNode(3);

        node1.prev = null;
        node1.next = node2;
        node2.prev = node1;
        node2.next = node3;
        node3.prev = node2;
        node3.next = null;
        node1 = reverseDoubleLinkedList(node1);
        DoubleNode node4 = node1.next.next;
        while (node4 != null){
            AlgUtil.console(node4.value);
            node4 = node4.prev;
        }


    }
}
