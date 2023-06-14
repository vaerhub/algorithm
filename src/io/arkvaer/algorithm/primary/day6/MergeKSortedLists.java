package io.arkvaer.algorithm.primary.day6;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 合并多个有序的链表
 * 测试链接：<a href="https://leetcode.cn/problems/merge-k-sorted-lists/">https://leetcode.cn/problems/merge-k-sorted-lists/</a>
 *
 * @author waver
 * @date 2023/6/14 22:05
 */
public class MergeKSortedLists {

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    class MyListNodeComparator implements Comparator<ListNode> {
        @Override
        public int compare(ListNode o1, ListNode o2) {
            return o1.val - o2.val;
        }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> heap = new PriorityQueue<>(new MyListNodeComparator());
        for (ListNode list : lists) {
            if (list != null) {
                heap.add(list);
            }
        }
        if (heap.isEmpty()) {
            return null;
        }

        ListNode head = heap.poll();
        ListNode pre = head;
        if (pre.next != null) {
            heap.add(pre.next);
        }

        while (!heap.isEmpty()) {
            ListNode current = heap.poll();
            pre.next = current;
            pre = pre.next;
            if (current.next != null) {
                heap.add(current.next);
            }
        }
        return head;
    }

}
