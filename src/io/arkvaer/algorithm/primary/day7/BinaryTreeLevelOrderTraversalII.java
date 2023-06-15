package io.arkvaer.algorithm.primary.day7;

import io.arkvaer.algorithm.primary.structs.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 测试链接：<a href="https://leetcode.cn/problems/binary-tree-level-order-traversal-ii">binary-tree-level-order-traversal-ii</a>
 *
 * @author waver
 * @date 2023/6/15 17:36
 */
public class BinaryTreeLevelOrderTraversalII {


    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> ans = new LinkedList<>();
        if (root == null) {
            return ans;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> curAns = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode curNode = queue.poll();
                if (curNode != null) {
                    curAns.add(curNode.val);
                    if (curNode.left != null) {
                        queue.add(curNode.left);
                    }
                    if (curNode.right != null) {
                        queue.add(curNode.right);
                    }
                }
            }
            ans.add(0, curAns);
        }
        return ans;
    }
}
