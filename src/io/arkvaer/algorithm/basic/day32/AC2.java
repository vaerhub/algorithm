package io.arkvaer.algorithm.basic.day32;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class AC2 {

    public static class Node {
        public String end; // 有多少个字符串以该节点结尾
        public Node fail;
        public Node[] nextArr;

        public boolean isEndUsed;

        public Node() {
            nextArr = new Node[26];
        }
    }
    public static class ACAutomation{
        public Node root;

        public ACAutomation() {
            this.root = new Node();
        }

        public void insert(String key) {
            char[] chars = key.toCharArray();
            Node cur = root;
            int index;
            for (int i = 0; i < chars.length; i++) {
                index = chars[i] - 'a';
                if (cur.nextArr[index] == null) {
                    cur.nextArr[index] = new Node();
                }
                cur = cur.nextArr[index];
            }
            cur.end = key;
        }

        public void build() {
            Queue<Node> queue = new LinkedList<>();
            queue.add(root);
            Node cur;
            Node curFail;
            while (!queue.isEmpty()) {
                cur = queue.poll();
                for (int i = 0; i < 26; i++) {
                    if (cur.nextArr[i] != null) {
                        cur.nextArr[i].fail = root;
                        curFail = cur.fail;
                        while (curFail != null) {
                            if (curFail.nextArr[i] != null) {
                                cur.nextArr[i].fail = curFail.nextArr[i];
                                break;
                            }
                            curFail = curFail.fail;
                        }
                        queue.add(cur.nextArr[i]);
                    }
                }
            }
        }

        public List<String> containWords(String content) {
            char[] charArray = content.toCharArray();
            Node cur = root;
            Node follow;
            int index;
            List<String> result = new ArrayList<>();
            for (int i = 0; i < charArray.length; i++) {
                index = charArray[i] - 'a';
                while (cur.nextArr[index] == null && cur != root) {
                    cur = cur.fail;
                }
                cur = cur.nextArr[index] != null ? cur.nextArr[index] : root;
                follow = cur;
                while (follow != root) {
                    if (follow.isEndUsed) {
                        break;
                    }
                    if (follow.end != null) {
                        result.add(follow.end);
                        follow.isEndUsed = true;
                    }
                    follow = follow.fail;
                }

            }
            return result;
        }

    }

    public static void main(String[] args) {
        ACAutomation ac = new ACAutomation();
        ac.insert("dhe");
        ac.insert("he");
        ac.insert("abcdheks");
        // 设置fail指针
        ac.build();
        List<String> contains = ac.containWords("abcdhekskdjfafhasldkflskdjhwqaeruv");
        for (String word : contains) {
            System.out.println(word);
        }
    }
}
