package io.arkvaer.algorithm.basic.day02;

/**
 * 删除指定value的节点
 *
 * @author waver
 * @date 2023/6/23 下午10:16
 */
public class DeleteGivenValue {
    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    public static Node deleteGivenValue(Node node, int val) {

        // 如果链表头是需要删除的节点, 则将头移到下一个节点, 直到找到第一个不需要删除的节点
        while (node != null) {
            if (node.value != val) {
                break;
            }
            node = node.next;
        }

        if (node == null) {
            return node;
        }
        Node prev = node;
        Node current = node;
        while (current != null) {
            if (current.value == val) {
                prev.next = current.next;
            } else {
                prev = current;
            }
            current = current.next;
        }
        return node;
    }


    public static void printNode(Node node) {
        System.out.println("========================");
        while (node != null) {
            System.out.println(node.value);
            node = node.next;
        }

    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
//        printDoubleNode(node1);
        Node node = deleteGivenValue(node1, 4);
        printNode(node);

    }

}
