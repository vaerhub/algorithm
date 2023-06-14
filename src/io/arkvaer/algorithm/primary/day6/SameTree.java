package io.arkvaer.algorithm.primary.day6;


import io.arkvaer.algorithm.primary.structs.TreeNode;

/**
 * 判断两棵树是否为相
 * 测试链接：<a href="https://leetcode.com/problems/same-tree">https://leetcode.cn/problems/same-tree</a>
 *
 * @author waver
 * @date 2023/6/14 22:56
 */
public class SameTree {


    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null ^ q == null) {
            return false;
        }
        if (p == null) {
            return true;
        }
        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
