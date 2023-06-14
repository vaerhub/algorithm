package io.arkvaer.algorithm.primary.day4;

/**
 * @author waver
 * @date 2023/6/11 下午11:45
 * 测试链接：https://leetcode.cn/problems/add-two-numbers/
 */
public class AddTwoNumber {
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

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int l1Len = getListNodeLength(l1);
        int l2Len = getListNodeLength(l2);
        ListNode longer = l1Len > l2Len ? l1 : l2;
        ListNode shorter = longer == l1 ? l2 : l1;
        ListNode last = longer;
        ListNode currentLonger = longer;
        ListNode currentShorter = shorter;

        int carry = 0;
        while (currentShorter != null) {
            int num = currentLonger.val + currentShorter.val + carry;
            currentLonger.val = num % 10;
            carry = num / 10;
            last = currentLonger;
            currentLonger = currentLonger.next;
            currentShorter = currentShorter.next;
        }
        while (currentLonger != null) {
            int num = currentLonger.val + carry;
            currentLonger.val = num % 10;
            carry = num / 10;
            last = currentLonger;
            currentLonger = currentLonger.next;
        }
        if (carry != 0) {
            last.next = new ListNode(1);
        }
        return longer;
    }

    public int getListNodeLength(ListNode listNode) {
        int length = 0;
        while (listNode.next != null) {
            length++;
            listNode = listNode.next;
        }
        return length;
    }
}
