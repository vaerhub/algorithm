package io.arkvaer.algorithm.basic.day32;

import java.util.LinkedList;
import java.util.Queue;

/**
 * AC 自动机
 */
public class AC1 {

    public static class Node {
        /**
         * 有多少个字符串以该节点结尾
         */
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

        public void build() {
            Queue<Node> queue = new LinkedList<>();
            queue.add(root);
            // 当前节点
            Node father;
            Node faFail;
            while (!queue.isEmpty()) {
                father = queue.poll();
                // 检查当前的节点所有自节点
                for (int i = 0; i < 26; i++) {
                    // 当前子节点不为空
                    if (father.nextArr[i] != null) {
                        // 初始预设fail节点为root节点
                        father.nextArr[i].fail = root;
                        // 父节点的fail节点
                        faFail = father.fail;
                        // 如果父节点的fail不为空
                        while (faFail != null) {
                            // 父节点的fail节点是否有指向 与当前子节点相同的子节点
                            if (faFail.nextArr[i].fail != null) {
                                // 如果存在, 则将当前节点的fail指向该子节点
                                father.nextArr[i].fail = faFail.nextArr[i];
                                break;
                            }
                            // 若不存在, 则找到父节点的fail节点
                            faFail = faFail.fail;
                        }
                    }
                }
            }
        }


    }

}
