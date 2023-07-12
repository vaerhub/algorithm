package io.arkvaer.algorithm.basic.day09;

import io.arkvaer.algorithm.utils.AlgUtil;

/**
 * 将链表按给定的值[pivot]进行分区,
 * 小于[pivot]的在前面
 * 等于[pivot]的在中间
 * 大于[pivot]的在后边
 */
public class SmallerEqualBigger {
    public static class Node {
        private int value;
        private Node next;

        public Node(int val) {
            this.value = val;
        }
    }

    public static Node listPartition1(Node head, int pivot) {
        if (head == null) {
            return null;
        }
        int i = 0;
        Node cur = head;
        while (cur != null) {
            i++;
            cur = cur.next;
        }
        Node[] nodes = new Node[i];
        cur = head;
        for (i = 0; i < nodes.length; i++) {
            nodes[i] = cur;
            cur = cur.next;
        }
        nodeArrPartition(nodes, pivot);
        for (i = 1; i < nodes.length; i++) {
            nodes[i - 1].next = nodes[i];
        }
        // 断开最后一个节点
        nodes[i - 1].next = null;
        return nodes[0];
    }

    /**
     * 利用荷兰国旗算法对节点进行分区
     * 对数组进行分区
     *
     * @param nodes 节点数组
     * @param pivot 分界值
     */
    public static void nodeArrPartition(Node[] nodes, int pivot) {
        int s = -1;
        int m = nodes.length;
        int index = 0;
        while (index != m) {
            if (nodes[index].value < pivot) {
                AlgUtil.swap(nodes, index++, ++s);
            } else if (nodes[index].value == pivot) {
                index++;
            } else {
                AlgUtil.swap(nodes, index, --m);
            }

        }
    }


    public static Node listPartition2(Node head, int pivot) {
        if (head == null) {
            return null;
        }
        Node smallHead = null;
        Node smallTail = null;
        Node equalHead = null;
        Node equalTail = null;
        Node biggerHead = null;
        Node biggerTail = null;
        Node next;
        while (head != null) {
            next = head.next;
            head.next = null;
            if (head.value < pivot) {
                if (smallHead == null) {
                    smallHead = head;
                    smallTail = head;
                } else {
                    smallTail.next = head;
                    smallTail = smallTail.next;
                }
            } else if (head.value == pivot) {
                if (equalHead == null) {
                    equalHead = head;
                    equalTail = head;
                } else {
                    equalTail.next = head;
                    equalTail = equalHead.next;
                }
            } else {
                if (biggerHead == null) {
                    biggerHead = head;
                    biggerTail = head;
                } else {
                    biggerTail.next = head;
                    biggerTail = biggerTail.next;
                }
            }
            head = next;
        }
        // 小于区域的尾巴，连等于区域的头，等于区域的尾巴连大于区域的头
        // 如果有小于区域
        if (smallTail != null) {
            smallTail.next = equalHead;
            // 下一步，谁去连大于区域的头，谁就变成equalTail
            equalTail = equalTail == null ? smallTail : equalTail;
        }

        // 下一步，一定是需要用 equalTail 去接 大于区域的头
        // 有等于区域，equalTail -> 等于区域的尾结点
        // 无等于区域，equalTail -> 小于区域的尾结点
        // equalTail 尽量不为空的尾巴节点
        if (equalTail != null) {
            equalTail.next = biggerHead;
        }
        if (smallHead != null) {
            return smallHead;
        } else {
            return equalHead != null ? equalHead : biggerHead;
        }
    }

    public static void printLinkedList(Node node) {
        System.out.print("Linked List: ");
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head1 = new Node(7);
        head1.next = new Node(9);
        head1.next.next = new Node(1);
        head1.next.next.next = new Node(8);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(2);
        head1.next.next.next.next.next.next = new Node(5);
        printLinkedList(head1);
        head1 = listPartition1(head1, 4);
//        head1 = listPartition2(head1, 5);
        printLinkedList(head1);

    }

}
