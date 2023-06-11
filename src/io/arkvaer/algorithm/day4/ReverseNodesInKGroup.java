package io.arkvaer.algorithm.day4;

/**
 * 反转K范围内的节点
 * 测试链接：https://leetcode.com/problems/reverse-nodes-in-k-group/
 */
public class ReverseNodesInKGroup {
    // 不要提交这个类
    public static class ListNode {
        public int val;
        public ListNode next;
    }


    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode start = head;
        ListNode end = getKGroupEnd(head, k);
        if (end == null) {
            return head;
        }
        // 第一组数凑齐了
        head = end;
        reverse(start, end);
        ListNode lastEnd = start;
        while (lastEnd.next != null) {
            start = lastEnd.next;
            end = getKGroupEnd(start, k);
            if (end == null) {
                return head;
            }
            reverse(start, end);
            lastEnd.next = end;
            lastEnd = start;
        }
        return head;
    }


    public ListNode getKGroupEnd(ListNode start, int k) {
        while (--k != 0 && start != null) {
            start = start.next;
        }
        return start;
    }

    public void reverse(ListNode start, ListNode end) {
        end = end.next;
        ListNode prev = null;
        ListNode current = start;
        ListNode next = null;
        while (current != end) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        start.next = end;
    }


}
