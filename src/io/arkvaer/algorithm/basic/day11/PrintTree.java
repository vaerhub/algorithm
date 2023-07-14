package io.arkvaer.algorithm.basic.day11;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PrintTree {


    /**
     * 1. 第n层节点数字到开始的距离为2^(high - n) -1
     * 2. 第n层分支节最左端点到开始的距离为 2^(high - n -1) -1
     * 3. 第n层的分支长度为 2^n
     */

    public static String branch = "-----";
    public static String leftBranch = "  |--";
    public static String rightBranch = "--|  ";
    public static String midBranch = "--|--";
    public static String space = "     ";
    public static String spaceVal = "  ";

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int v) {
            value = v;
        }
    }


    public static void printTree(Node root) {
        if (root == null) {
            return;
        }

        int maxHigh = getMaxHigh(root);
        Node curEnd = root;
        Node nextEnd = null;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        List<String> nodeList = new ArrayList<>(maxHigh);
        List<String> brancheList = new ArrayList<>(maxHigh);
        StringBuilder nodeBuilder = new StringBuilder();

        StringBuilder branchBuilder = new StringBuilder();
        while (!queue.isEmpty()) {
            root = queue.poll();
            nodeBuilder.append(getSpaceRepeatNum(space, (int) Math.pow(2, maxHigh) - 1).append(getValueSpace(root.value)));
            branchBuilder.append(getSpaceRepeatNum(space, (int) Math.pow(2, maxHigh - 1) - 1)
                    .append(leftBranch)
                    .append(getSpaceRepeatNum(branch, (int) Math.pow(2, maxHigh - 1) - 1))
                    .append(midBranch)
                    .append(getSpaceRepeatNum(branch, (int) Math.pow(2, maxHigh - 1) - 1))
                    .append(rightBranch));
            if (root.left != null) {
                queue.add(root.left);
                nextEnd = root.left;
            }

            if (root.right != null) {
                queue.add(root.right);
                nextEnd = root.right;
            }
            nodeBuilder.append(getSpaceRepeatNum(space, (int) Math.pow(2, maxHigh) - 1)).append(space);
            branchBuilder.append(getSpaceRepeatNum(space, (int) Math.pow(2, maxHigh - 1)));
            if (root == curEnd) {
                nodeList.add(nodeBuilder.toString());
                brancheList.add(branchBuilder.toString());
                nodeBuilder = new StringBuilder();
                branchBuilder = new StringBuilder();
                curEnd = nextEnd;
                maxHigh--;
            }
        }

        print(nodeList, brancheList);

    }

    public static StringBuilder getValueSpace(int value) {
        int num = value;
        int bit = 0;
        StringBuilder bitBuilder = new StringBuilder();
        while (num > 0) {
            num /= 10;
            bit++;
        }
        String str = " ";
        int sp;
        if (bit > 1) {
            if (bit % 2 == 0) {
                if (bit < 3) {
                    bitBuilder.append(getSpaceRepeatNum(str, 2)).append(value).append(getSpaceRepeatNum(str, 3 - bit));
                } else {
                    sp = 2 - (bit / 2);
                    bitBuilder.append(getSpaceRepeatNum(str, sp)).append(value).append(getSpaceRepeatNum(str, sp));
                }
            } else {
                sp = 2 - ((bit - 1) / 2);
                bitBuilder.append(getSpaceRepeatNum(str, sp)).append(value).append(getSpaceRepeatNum(str, sp));
            }
        } else {
            bitBuilder.append(spaceVal).append(value).append(spaceVal);
        }

        return bitBuilder;
    }


    public static void print(List<String> nodeList, List<String> branchList) {
        for (int i = 0; i < nodeList.size(); i++) {
            System.out.println(nodeList.get(i));
            if (i < nodeList.size() - 1) {
                System.out.println(branchList.get(i));
            }
        }
    }

    public static StringBuilder getSpaceRepeatNum(String str, int num) {
        StringBuilder stringBuilder = new StringBuilder();
        while (num != 0) {
            stringBuilder.append(str);
            num--;
        }
        return stringBuilder;
    }

    /**
     * 获取树的最大高度
     *
     * @param root 根节点
     * @return 树的最大高度
     */
    public static int getMaxHigh(Node root) {
        if (root == null) {
            return 0;
        }
        int high = 0;
        Node curEnd = root;
        Node nextEnd = null;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            root = queue.poll();
            if (root.left != null) {
                queue.add(root.left);
                nextEnd = root.left;
            }

            if (root.right != null) {
                queue.add(root.right);
                nextEnd = root.right;
            }

            if (root == curEnd) {
                high++;
                curEnd = nextEnd;
            }
        }
        return high;
    }


    public static void main(String[] args) {
        Node head = new Node(1);

        head.left = new Node(10001);
        head.right = new Node(11111);

        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);

        head.left.left.left = new Node(8);
        head.left.left.right = new Node(9);
        head.left.right.left = new Node(10);
        head.left.right.right = new Node(11);
        head.right.left.left = new Node(12);
        head.right.left.right = new Node(13);
        head.right.right.left = new Node(14);
        head.right.right.right = new Node(15);
        printTree(head);
    }
}