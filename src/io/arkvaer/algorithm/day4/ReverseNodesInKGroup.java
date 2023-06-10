package io.arkvaer.algorithm.day4;

/**
 * 反转K范围内的节点
 */
public class ReverseNodesInKGroup<T> {
    public static class Node<T>{
        public T value;
        public Node<T> next;

        public Node(T value) {
            this.value = value;
            this.next = null;
        }
    }

    public Node<T> reverseKGroup(Node<T> node, int k) {

        int n = 0;
        while (n <= k) {

            n++;
        }
        return node;
    }


}
