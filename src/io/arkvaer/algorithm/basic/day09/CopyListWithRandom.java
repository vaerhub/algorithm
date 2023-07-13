package io.arkvaer.algorithm.basic.day09;

import java.util.HashMap;

/**
 * 复制带随机指针的链表
 * 测试链接 : https://leetcode.com/problems/copy-list-with-random-pointer/
 */
public class CopyListWithRandom {
    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }


    /**
     * 使用容器的方法
     *
     * @param head 需要克隆的链表
     * @return 拷贝完成的链表
     */
    public Node copyRandomList1(Node head) {
        // key 老节点
        // value 新节点
        HashMap<Node, Node> map = new HashMap<>();
        Node cur = head;
        while (cur != null) {
            map.put(cur, new Node(cur.val));
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            // cur 老
            // map.get(cur) 新
            // 新.next ->  cur.next克隆节点找到
            map.get(cur).next = map.get(cur.next);
            map.get(cur).random = map.get(cur.random);
            cur = cur.next;
        }
        return map.get(head);
    }

    /**
     * 不使用容器的方法
     *
     * @param head 链表头节点
     * @return 拷贝的新链表
     */
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        // 先将拷贝的节点放到给插入到链表的next节点,
        Node cur = head;
        Node next;
        while (cur != null) {
            next = cur.next;
            cur.next = new Node(cur.val);
            cur.next.next = next;
            cur = next;
        }

        // 参照就链表的random节点，建立新链表的random节点关系
        cur = head;
        Node clone;
        while (cur != null) {
            next = cur.next.next;
            clone = cur.next;
            clone.random = cur.random == null ? null : cur.random.next;
            cur = next;
        }
        // 将新旧链表拆分
        Node newHead = head.next;
        cur = head;
        while (cur != null) {
            next = cur.next.next;
            clone = cur.next;
            cur.next = next;
            clone.next = next == null ? null : next.next;
            cur = next;
        }
        return newHead;
    }

}
