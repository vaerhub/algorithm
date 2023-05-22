package io.arkvaer.algorithm;

public class TreeNode {
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