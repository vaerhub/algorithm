package io.arkvaer.algorithm.basic.day11;

/**
 * 找到给定节点的后继节点
 * 后继节点: 中序遍历中节点的后一个节点为其后继
 */
public class FindSuccessorNode {
    public static class Node {
        public int value;
        public Node left;
        public Node right;
        public Node parent;
        public Node(int v) {
            value = v;
        }
    }


    public static Node find(Node head) {
        if (head == null) {
            return null;
        }
        // 存在右子树时, 右子树的根节点就是当前节点的 后继
        if (head.right != null) {
            return getMostLeftNode(head.right);
        } else {
            Node parent = head.parent;
            while (parent != null && head != parent.left) {
                head = parent;
                parent = parent.parent;
            }
            return parent;
        }
    }


    public static Node getMostLeftNode(Node head) {
        if (head == null) {
            return null;
        }
        while (head.left != null) {
            head = head.left;
        }
        return head;

    }

    public static void main(String[] args) {
        Node head = new Node(6);
        head.parent = null;
        head.left = new Node(3);
        head.left.parent = head;
        head.left.left = new Node(1);
        head.left.left.parent = head.left;
        head.left.left.right = new Node(2);
        head.left.left.right.parent = head.left.left;
        head.left.right = new Node(4);
        head.left.right.parent = head.left;
        head.left.right.right = new Node(5);
        head.left.right.right.parent = head.left.right;
        head.right = new Node(9);
        head.right.parent = head;
        head.right.left = new Node(8);
        head.right.left.parent = head.right;
        head.right.left.left = new Node(7);
        head.right.left.left.parent = head.right.left;
        head.right.right = new Node(10);
        head.right.right.parent = head.right;

        Node test = head.left.left;
        System.out.println(test.value + " next: " + find(test).value);
        test = head.left.left.right;
        System.out.println(test.value + " next: " + find(test).value);
        test = head.left;
        System.out.println(test.value + " next: " + find(test).value);
        test = head.left.right;
        System.out.println(test.value + " next: " + find(test).value);
        test = head.left.right.right;
        System.out.println(test.value + " next: " + find(test).value);
        test = head;
        System.out.println(test.value + " next: " + find(test).value);
        test = head.right.left.left;
        System.out.println(test.value + " next: " + find(test).value);
        test = head.right.left;
        System.out.println(test.value + " next: " + find(test).value);
        test = head.right;
        System.out.println(test.value + " next: " + find(test).value);
        test = head.right.right; // 10's next is null
        System.out.println(test.value + " next: " + find(test));
    }

}
