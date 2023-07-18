package io.arkvaer.algorithm.basic.day13;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * 查找在head为头节点的二叉树上, 找到 a, b 两个节点的最低共同祖先
 */
public class LowestAncestor {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    // region lowestAncestor1
    public static Node lowestAncestor1(Node head, Node a, Node b) {
        if (head == null) {
            return null;
        }
        HashMap<Node, Node> parentMap = new HashMap<>();
        parentMap.put(head, null);
        fillParentMap(head, parentMap);
        HashSet<Node> set = new HashSet<>();
        Node cur = a;
        set.add(cur);
        // 将a节点的所有祖父节点放入set
        while (parentMap.get(cur) != null) {
            cur = parentMap.get(cur);
            set.add(cur);
        }
        cur = b;
        // 不断寻找 b节点的祖父节点, 知道找到与 a 节点共同的祖父节点, 返回
        while (!set.contains(cur)) {
            cur = parentMap.get(cur);
        }
        return cur;
    }


    /**
     * 将所有节点和父节点的对应关系放入Map
     *
     * @param head      给定头节点
     * @param parentMap 父节点对应关系Map
     */
    public static void fillParentMap(Node head, Map<Node, Node> parentMap) {
        if (head.left != null) {
            parentMap.put(head.left, head);
            fillParentMap(head.left, parentMap);
        }
        if (head.right != null) {
            parentMap.put(head.right, head);
            fillParentMap(head.right, parentMap);
        }
    }
    // endregion

    public static class Info {
        /**
         * 已经找到A节点了
         */
        public boolean aHasBeenFound;
        /**
         * 已经找到B节点了
         */
        public boolean bHasBeenFound;

        public Node ans;

        public Info(boolean aHasBeenFound, boolean bHasBeenFound, Node ans) {
            this.aHasBeenFound = aHasBeenFound;
            this.bHasBeenFound = bHasBeenFound;
            this.ans = ans;
        }
    }

    public static Node lowestAncestor2(Node head, Node a, Node b) {
        if (head == null) {
            return null;
        }
        return process(head, a, b).ans;
    }

    public static Info process(Node head, Node a, Node b) {
        if (head == null) {
            return new Info(false, false, null);
        }
        Info leftInfo = process(head.left, a, b);
        Info rightInfo = process(head.right, a, b);
        boolean hasFindA = head == a || leftInfo.aHasBeenFound || rightInfo.aHasBeenFound;
        boolean hasFindB = head == b || leftInfo.bHasBeenFound || rightInfo.bHasBeenFound;
        Node ans = null;
        if (leftInfo.ans != null) {
            ans = leftInfo.ans;
        } else if (rightInfo.ans != null) {
            ans = rightInfo.ans;
        } else if (hasFindA && hasFindB) {
            ans = head;
        }
        return new Info(hasFindA, hasFindB, ans);


    }

    // region For Test
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    // for test
    public static Node pickRandomOne(Node head) {
        if (head == null) {
            return null;
        }
        ArrayList<Node> arr = new ArrayList<>();
        fillPrelist(head, arr);
        int randomIndex = (int) (Math.random() * arr.size());
        return arr.get(randomIndex);
    }

    // for test
    public static void fillPrelist(Node head, ArrayList<Node> arr) {
        if (head == null) {
            return;
        }
        arr.add(head);
        fillPrelist(head.left, arr);
        fillPrelist(head.right, arr);
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            Node o1 = pickRandomOne(head);
            Node o2 = pickRandomOne(head);
            if (lowestAncestor1(head, o1, o2) != lowestAncestor2(head, o1, o2)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
    // endregion


}
