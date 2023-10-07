package io.arkvaer.algorithm.basic.day30;

import java.util.Objects;

/**
 * Morris 遍历 实现了保证时间复杂度为O(N)的情况下, 不使用系统栈, 将空间复杂度降低到O(1)
 * 思路: 二叉树中叶子节点存在大量的指向Null的指针, Morris 遍历 就是利用了叶子节点的右指针完成遍历的
 */
public class MorrisTraversal {
    public static class Node {
        public int value;
        Node left;
        Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    /**
     * 通过系统栈进行系统遍历
     *
     * @param root 二叉树头节点
     */
    public static void process(Node root) {
        if (root == null) {
            return;
        }
        // 在此处理为前序遍历
        process(root.left);
        // 在此处理为中序遍历
        process(root.right);
        // 在此处理为后序遍历
    }

    /**
     * <p>
     * morris 遍历流程:<br>
     * 假设来到当前节点cur，开始时cur来到头节点位置<br>
     * 1)如果cur没有左孩子，cur向右移动(cur = cur.right)<br>
     * 2)如果cur有左孩子，找到左子树上最右的节点mostRight<br>
     * &nbsp;&nbsp;a.如果mostRight的右指针指向空, 让其指向cur,然后cur向左移动(cur = cur.left)<br>
     * &nbsp;&nbsp;b.如果mostRight的右指针指向cur, 让其指向null然后cur向右移动(cur = cur.right)<br>
     * 3)cur为空时遍历停止<br>
     * </p>
     *
     * @param root 二叉树头节点
     */
    public static void morris(Node root) {
        if (root == null) {
            return;
        }
        Node cur = root;
        Node mostRight;
        while (cur != null) {
            mostRight = cur.left;
            if (Objects.nonNull(mostRight)) {
                while (Objects.nonNull(mostRight.right) && !Objects.equals(cur, mostRight.right)) {
                    mostRight = mostRight.right;
                }
                if (Objects.isNull(mostRight.right)) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            }
            // 情况 1 和情况 2 中的 (b 会共用
            cur = cur.right;
        }
    }

    /**
     * morris 遍历实现先序遍历
     * morris序 在第一次到达该节点是打印就是先序
     *
     * @param head 二叉树的头节点
     */
    public static void morrisPre(Node head) {
        if (Objects.isNull(head)) {
            return;
        }
        Node cur = head;
        Node mostRight;
        while (Objects.nonNull(cur)) {
            mostRight = cur.left;
            if (Objects.nonNull(mostRight)) {
                while (Objects.nonNull(mostRight.right) && !Objects.equals(cur, mostRight.right)) {
                    mostRight = mostRight.right;
                }
                if (Objects.isNull(mostRight.right)) {
                    System.out.print(cur.value + " ");
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            } else {
                System.out.print(cur.value + " ");
            }
            cur = cur.right;
        }
        System.out.println();
    }

    /**
     * morris 遍历实现中序遍历
     * morris序 如果一个节点只能到达一次, 则直接打印, 如果能到达两次, 则在第二次到达时打印
     *
     * @param head 二叉树的头节点
     */
    public static void morrisIn(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            }
            System.out.print(cur.value + " ");
            cur = cur.right;
        }
        System.out.println();
    }

    public static void morrisPos(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                    printEdge(cur.left);
                }
            }
            cur = cur.right;
        }
        printEdge(head);
        System.out.println();
    }

    public static void printEdge(Node head) {
        Node tail = reverseEdge(head);
        Node cur = tail;
        while (cur != null) {
            System.out.print(cur.value + " ");
            cur = cur.right;
        }
        reverseEdge(tail);
    }

    public static Node reverseEdge(Node from) {
        Node pre = null;
        Node next;
        while (from != null) {
            next = from.right;
            from.right = pre;
            pre = from;
            from = next;
        }
        return pre;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);
        morris(head);
        morrisPre(head);
        morrisIn(head);
        morrisPos(head);
    }


}
