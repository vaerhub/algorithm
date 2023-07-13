package io.arkvaer.algorithm.basic.day11;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PrintTree {

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
        String branch = "----";
        String space = "    ";
        int maxHigh = getMaxHigh(root);
        Node curEnd = root;
        Node nextEnd = null;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        List<String> nodeList = new ArrayList<>(maxHigh);
        List<String> brancheList = new ArrayList<>(maxHigh);
        int level = 0;
        StringBuilder spaceBuilder = new StringBuilder();
        StringBuilder branchBuilder = new StringBuilder();
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
            spaceBuilder.append(getSpaceRepeatNum(space, maxHigh));
            spaceBuilder.append(root.value);

            if (root == curEnd) {
                level++;
                branchBuilder.append(getSpaceRepeatNum(branch, maxHigh));
                nodeList.add(spaceBuilder.toString());
                brancheList.add(branchBuilder.toString());
                branchBuilder = new StringBuilder();
                spaceBuilder = new StringBuilder();
                curEnd = nextEnd;
                maxHigh--;
            }
        }

        print(nodeList, brancheList);

    }


    public static void print(List<String> nodeList, List<String> branchList) {
        for (int i = 0; i < nodeList.size(); i++) {
            System.out.println(nodeList.get(i));
            System.out.println(branchList.get(i));
        }
    }

    public static String getSpaceRepeatNum(String str, int num) {
        StringBuilder stringBuilder = new StringBuilder();
        while (num != 0) {
            stringBuilder.append(str);
            num--;
        }
        return stringBuilder.toString();
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
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);
        head.left.left.left = new Node(8);
        head.left.left.right = new Node(9);
        head.left.right.left = new Node(10);
        head.left.right.right = new Node(11);
        head.right.left.left = new Node(12);
        printTree(head);
    }
}