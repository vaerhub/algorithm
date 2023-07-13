package io.arkvaer.algorithm.basic.day11;

import com.sun.source.tree.Tree;
import io.arkvaer.algorithm.basic.day11.EncodeNaryTreeToBinaryTree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://leetcode.com/problems/encode-n-ary-tree-to-binary-tree">本题测试链接(需要Plus会员)</a>
 * 将多叉树转换为二叉树
 */
public class EncodeNaryTreeToBinaryTree {

    // 提交时不要提交这个类
    public static class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    // 提交时不要提交这个类
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    static class Codec {


        /**
         * 将多叉树转为二叉树
         *
         * @param root 多叉树根节点
         * @return 二叉树根节点
         */
        public TreeNode encode(Node root) {
            if (root == null) {
                return null;
            }
            TreeNode head = new TreeNode(root.val);
            head.left = en(root.children);
            return head;
        }

        public TreeNode en(List<Node> children) {
            TreeNode head = null;
            TreeNode cur = null;
            for (Node child : children) {
                TreeNode node = new TreeNode(child.val);
                if (head == null) {
                    head = node;
                } else {
                    cur.right = node;
                }
                cur = node;
                cur.left = en(child.children);
            }
            return head;
        }


        /**
         * 将二叉树转为多叉树
         *
         * @param root 二叉树根节点
         * @return 多叉树根节点
         */
        public Node decode(TreeNode root) {
            if (root == null) {
                return null;
            }
            return new Node(root.val, de(root.left));
        }

        public List<Node> de(TreeNode treeNode) {
            List<Node> children = new ArrayList<>();
            while (treeNode != null) {
                Node cur = new Node(treeNode.val, de(treeNode.left));
                children.add(cur);
                treeNode = treeNode.right;
            }
            return children;
        }
    }

}
