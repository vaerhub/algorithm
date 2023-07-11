package io.arkvaer.algorithm.basic.day09;

import java.util.Stack;

/**
 * 是否为回文
 */
public class IsPalindromeList {

    public static class Node {
        private int val;
        private Node next;
    }

    public static boolean isPalindrome1(Node head) {
        Stack<Node> stack = new Stack<>();
        Node cur = head;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        while (head != null) {
            if (head.val != stack.pop().val) {
                return false;
            }
            head = head.next;
        }
        return true;
    }
    public static boolean isPalindrome2(Node head) {
        return false;
    }

    public static boolean isPalindrome3(Node head) {
        return false;
    }

}
