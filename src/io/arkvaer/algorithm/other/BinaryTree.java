package io.arkvaer.algorithm.other;

import java.util.ArrayList;
import java.util.List;

class TreeNode {
    public Integer value;
    public TreeNode left;
    public TreeNode right;

    public TreeNode parent;

    public TreeNode(Integer value, TreeNode parent, TreeNode left, TreeNode right) {
        this.value = value;
        this.parent = parent;
        this.left = left;
        this.right = right;
    }
}
public class BinaryTree {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        preorder(root, res);
        return res;
    }

    public void preorder(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        res.add(root.value);
        preorder(root.left, res);
        preorder(root.right, res);
    }
}



