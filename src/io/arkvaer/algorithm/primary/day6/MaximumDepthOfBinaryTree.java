package io.arkvaer.algorithm.primary.day6;

import io.arkvaer.algorithm.primary.structs.TreeNode;

/**
 * 求数的最大深度
 * 测试链接：<a href="https://leetcode.cn/problems/maximum-depth-of-binary-tree">maximum-depth-of-binary-tree</a>
 * @author waver
 * @date 2023/6/14 23:29
 */
public class MaximumDepthOfBinaryTree {

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;

    }


}
