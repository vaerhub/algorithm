package io.arkvaer.algorithm.primary.day7;

import io.arkvaer.algorithm.primary.structs.TreeNode;

/**
 * 98. 验证是否为搜索二叉树
 * <a href="https://leetcode.cn/problems/validate-binary-search-tree/">validate-binary-search-tree</a>
 *
 * @author waver
 * @date 2023/6/15 22:11
 */
public class ValidateBinarySearchTree {

    public class TreeInfo {
        public boolean isBST;
        public int min;
        public int max;

        public TreeInfo(boolean isBST, int min, int max) {
            this.isBST = isBST;
            this.min = min;
            this.max = max;
        }
    }


    public boolean isValidBST(TreeNode root) {
        TreeInfo process = process(root);
        return process == null || process.isBST;
    }

    public TreeInfo process(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeInfo leftInfo = process(root.left);
        TreeInfo rightInfo = process(root.right);

        int min = root.val;
        int max = root.val;
        boolean isBST = true;

        if (leftInfo != null) {
            min = Math.min(leftInfo.min, min);
            max = Math.max(leftInfo.max, max);
        }

        if (rightInfo != null) {
            min = Math.min(rightInfo.min, min);
            max = Math.max(rightInfo.max, max);
        }
        if (leftInfo != null && !leftInfo.isBST) {
            isBST = false;
        }


        if (rightInfo != null && !rightInfo.isBST) {
            isBST = false;
        }
        boolean leftMaxLessThanRoot = leftInfo == null || (leftInfo.max < root.val);
        boolean rightMinBiggerThanRoot = rightInfo == null || (rightInfo.min > root.val);
        if (!leftMaxLessThanRoot || !rightMinBiggerThanRoot) {
            isBST = false;
        }
        return new TreeInfo(isBST, min, max);
    }



    public boolean isValidBST1(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    public boolean isValidBST(TreeNode node, long lower, long upper) {
        if (node == null) {
            return true;
        }
        if (node.val <= lower || node.val >= upper) {
            return false;
        }
        return isValidBST(node.left, lower, node.val) && isValidBST(node.right, node.val, upper);
    }

}
