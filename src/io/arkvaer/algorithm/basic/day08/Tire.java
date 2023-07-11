package io.arkvaer.algorithm.basic.day08;

/**
 * 前缀树
 * 该前缀树的路用数组实现
 */
public class Tire {

    class Node {
        public int pass;
        public int end;
        public Node[] nexts;

        public Node() {
            pass = 0;
            end = 0;
            nexts = new Node[26];
        }
    }

    private final Node root;

    public Tire() {
        root = new Node();
    }

    public void insert(String word) {
        if (word == null) {
            return;
        }
        Node node = root;
        node.pass++;
        int path = 0;
        char[] charArray = word.toCharArray();
        for (char c : charArray) {
            path = c - 'a';
            if (root.nexts[path] == null) {
                root.nexts[path] = new Node();
            }
            node = node.nexts[path];
        }
        node.pass++;
    }

    public void erase(String word) {
        if (countWordsEqualTo(word) == 0) {
            return;
        }
        Node node = root;
        char[] charArray = word.toCharArray();
        int index = 0;
        for (char c : charArray) {
            index = c - 'a';
            if (--node.nexts[index].pass == 0) {
                node.nexts[index] = null;
                return;
            }
            node = node.nexts[index];
        }
        node.end--;

    }


    public int countWordsEqualTo(String word) {
        if (word == null) {
            return 0;
        }
        char[] charArray = word.toCharArray();
        Node node = root;
        int index = 0;
        for (char c : charArray) {
            index = c - 'a';
            if (node.nexts[index] == null) {
                return 0;
            }
            node = node.nexts[index];
        }
        return node.end;
    }
    public int countWordsStartingWith(String pre) {
        if (pre == null) {
            return 0;
        }
        char[] charArray = pre.toCharArray();
        Node node = root;
        int index = 0;
        for (char c : charArray) {
            index = c - 'a';
            if (node.nexts[index] == null) {
                return 0;
            }
            node = node.nexts[index];
        }
        return node.pass;
    }


}
