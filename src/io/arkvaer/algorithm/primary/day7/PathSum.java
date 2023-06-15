package io.arkvaer.algorithm.primary.day7;

import io.arkvaer.algorithm.primary.structs.TreeNode;

/**
 * 路径总和
 * <a href="https://leetcode.cn/problems/path-sum/">path-sum</a>
 *
 * @author waver
 * @date 2023/6/15 22:56
 */
public class PathSum {
    private static boolean isSum = false;

    public static boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        isSum = false;
        process(root, 0, targetSum);
        return isSum;
    }

    public static void process(TreeNode root, int preSum, int sum) {
        if (root.left == null && root.right == null) {
            if (preSum + root.val == sum) {
                isSum = true;
            }
        } else {
            preSum += root.val;
            if (root.left != null) {
                process(root.left, preSum, sum);
            }
            if (root.right != null) {
                process(root.right, preSum, sum);
            }
        }

    }


}
