package io.arkvaer.algorithm.basic.day09;

import java.util.Stack;

/**
 * 判断单链表是否为回文
 */
public class IsPalindromeList {

    public static class Node {
        private int value;
        private Node next;

        public Node(int val) {
            this.value = val;
        }
    }

    /**
     * 额外空间复杂度 O(N)
     * @param head 头节点
     * @return 是否为回文结构
     */
    public static boolean isPalindrome1(Node head) {
        Stack<Node> stack = new Stack<>();
        Node cur = head;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        while (head != null) {
            if (head.value != stack.pop().value) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    /**
     * 额外空间复杂度 O(N/2)
     * @param head 头节点
     * @return 是否为回文结构
     */
    public static boolean isPalindrome2(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        Node fast = head;
        Node slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        Stack<Node> stack = new Stack<>();
        slow = slow.next;
        while (slow != null) {
            stack.push(slow);
            slow = slow.next;
        }
        while (!stack.isEmpty()) {
            if (stack.pop().value != head.value) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    /**
     * 额外空间复杂度 O(1)
     * @param head 头节点
     * @return 是否为回文结构
     */
    public static boolean isPalindrome3(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        Node slow = head;
        Node fast = head;
        // 找到链表的中点
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        // 将链表从重点到结尾进行反转
        fast = slow.next;
        slow.next = null;
        Node next;
        while (fast != null) {
            next = fast.next;
            fast.next = slow;
            slow = fast;
            fast = next;
        }

        // 对比链表数值, 判断是否为
        next = slow;
        fast = head;
        boolean result = true;
        while (slow != null && fast != null) {
            if (slow.value != fast.value) {
                result = false;
                break;
            }
            slow = slow.next;
            fast = fast.next;
        }

        slow = next;
        fast = slow.next;
        slow.next = null;
        while (fast != null) {
            next = fast.next;
            fast.next = slow;
            slow = fast;
            fast = next;
        }
        return result;
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

        Node head = null;
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(2);
        head.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(2);
        head.next.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

    }
}
