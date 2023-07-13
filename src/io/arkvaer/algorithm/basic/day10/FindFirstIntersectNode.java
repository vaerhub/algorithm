package io.arkvaer.algorithm.basic.day10;

/**
 * 给定两个单链表, 求:
 * 如果两个链表相交, 则返回第一个相交的节点;
 * 若不相交, 则返回null
 */
public class FindFirstIntersectNode {

    public static class Node {
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node getIntersectNode(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node loop1 = getLoopNode(head1);
        Node loop2 = getLoopNode(head2);
        if (loop1 == null && loop2== null) {
            return noLoop(head1, head2);
        }
        if (loop1 != null && loop2 != null) {
            return bothLoop(head1, loop1, head2, loop2);
        }
        return null;
    }

    /**
     * 找到链表第一个入环节点，如果无环，返回null
     *
     * @param head 给定的链表头节点
     * @return 第一个入换的节点或null
     */
    public static Node getLoopNode(Node head) {
        Node slow = head.next;
        Node fast = head.next.next;
        while (fast != slow) {
            if (fast.next == null || fast.next.next == null) {
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
        }

        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }

        return slow;
    }

    /**
     * 如果两个链表都无环，返回第一个相交节点，如果不想交，返回null
     *
     * @param head1 链表1
     * @param head2 链表2
     * @return 返回第一个相交节点，如果不想交，返回null
     */
    public static Node noLoop(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        int len = 0;
        Node cur1 = head1;
        Node cur2 = head2;
        while (cur1.next != null) {
            len++;
            cur1 = cur1.next;
        }
        while (cur2.next != null) {
            len --;
            cur2 = cur2.next;
        }
        if (cur1 != cur2) {
            return null;
        }
        cur1 = len > 0 ? head1 : head2;
        cur2 = cur1 == head1 ? head2 : head1;
        len = Math.abs(len);
        while (len != 0) {
            len--;
            cur1 = cur1.next;
        }

        while (cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }

    /**
     * 两个有环链表，返回第一个相交节点，如果不想交返回null
     *
     * @param head1 链表1
     * @param loop1 链表1的第一个入环节点
     * @param head2 链表2
     * @param loop2 链表2的第一个入环节点
     * @return 返回第一个相交节点，如果不想交返回null
     */
    public static Node bothLoop(Node head1, Node loop1, Node head2, Node loop2) {
        Node cur1;
        Node cur2;
        if (loop1 != loop2) {
            cur1 = loop1.next;
            while (cur1 != loop1) {
                if (cur1 == loop2) {
                    return loop1;
                }
                cur1 = cur1.next;
            }
        } else {
            int len = 0;
            cur1 = head1;
            cur2 = head2;
            while (cur1  != loop1) {
                len++;
                cur1 = cur1.next;
            }
            while (cur2 != loop2) {
                len--;
                cur2 = cur2.next;
            }
            // 将较长的链表赋给cur1, 较短的给cur2
            cur1 = len > 0 ? head1 : head2;
            cur2 = cur1 == head1 ? head2 : head1;
            len = Math.abs(len);
            while (len != 0) {
                len--;
                cur1 = cur1.next;
            }

            while (cur1 != cur2) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        }
        return null;
    }

    public static void main(String[] args) {
        // 1->2->3->4->5->6->7->null
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);

        // 0->9->8->6->7->null
        Node head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value);

        // 1->2->3->4->5->6->7->4...
        head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);
        head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

        // 0->9->8->2...
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next; // 8->2
        System.out.println(getIntersectNode(head1, head2).value);

        // 0->9->8->6->4->5->6..
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value);

    }

}
