package io.arkvaer.algorithm.day4;

/**
 * 反转K范围内的节点
 */
public class ReverseNodesInKGroup {
    // 不要提交这个类
    public static class ListNode {
        public int val;
        public ListNode next;
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
