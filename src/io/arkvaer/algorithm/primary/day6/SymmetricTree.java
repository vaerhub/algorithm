package io.arkvaer.algorithm.primary.day6;

import com.sun.source.tree.Tree;
import io.arkvaer.algorithm.primary.structs.TreeNode;

/**
 * 判断两棵树是否为相
 * 测试链接：<a href="https://leetcode.com/problems/symmetric-tree">https://leetcode.cn/problems/symmetric-tree</a>
 *
 * @author waver
 * @date 2023/6/14 22:56
 */
public class SymmetricTree {

    public boolean isSymmetric(TreeNode root) {
        return isMirror(root.left, root.right);
    }

    public boolean isMirror(TreeNode node1, TreeNode node2) {
        if (node1 == null ^ node2 == null) {
            return false;
        }
        if (node1 == null) {
            return true;
        }
        return node1.val == node2.val && isMirror(node1.left, node2.right) && isMirror(node2.left, node1.right);
    }
}
