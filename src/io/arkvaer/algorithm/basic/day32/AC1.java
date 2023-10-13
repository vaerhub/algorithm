package io.arkvaer.algorithm.basic.day32;

/**
 * AC 自动机
 */
public class AC1 {

    public static class Node {
        private int end;
        private Node fail;
        private Node[] nextArr;

        public Node() {
            this.nextArr = new Node[26];
        }
    }

    public static class ACAutomation {
        private Node root;

        public ACAutomation() {
            this.root = new Node();
        }

        public void insert(String str) {
            char[] charArray = str.toCharArray();
            Node cur = root;
            int index;
            for (char c : charArray) {
                index = c - 'a';
                if (cur.nextArr[index] == null) {
                    Node next = new Node();
                    cur.nextArr[index] = next;
                }
                cur = cur.nextArr[index];
            }
            cur.end++;
        }


    }

}
