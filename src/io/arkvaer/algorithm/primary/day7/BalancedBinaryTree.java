package io.arkvaer.algorithm.primary.day7;

import com.sun.source.tree.Tree;
import io.arkvaer.algorithm.primary.structs.TreeNode;

/**
 * 检测是否为平衡二叉树
 * 测试链接：<a href="https://leetcode.cn/problems/balanced-binary-tree">balanced-binary-tree</a>
 *
 * @author waver
 * @date 2023/6/15 17:54
 */
public class BalancedBinaryTree {

    public static class TreeInfo {
        public boolean isBalanced;
        public int height;

        public TreeInfo(boolean isBalanced, int height) {
            this.isBalanced = isBalanced;
            this.height = height;
        }
    }

    public boolean isBalanced(TreeNode root) {
        TreeInfo info = process(root);
        return info.isBalanced;
    }

    public TreeInfo process(TreeNode root) {
        if (root == null) {
            return new TreeInfo(true, 0);
        }
        TreeInfo leftInfo = process(root.left);
        TreeInfo rightInfo = process(root.right);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        boolean isBalanced = leftInfo.isBalanced && rightInfo.isBalanced && Math.abs(leftInfo.height - rightInfo.height) < 2;
        return new TreeInfo(isBalanced, height);
    }

    public boolean isBalanced2(TreeNode root) {
        return height(root) >= 0;
    }

    public int height(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);
        if (leftHeight == -1 || rightHeight == -1 || Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }
        return Math.max(leftHeight, rightHeight) + 1;
    }


}
