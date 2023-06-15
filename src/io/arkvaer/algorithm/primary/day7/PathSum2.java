package io.arkvaer.algorithm.primary.day7;

import io.arkvaer.algorithm.primary.structs.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 路径总和
 * <a href="https://leetcode.cn/problems/path-sum/">path-sum</a>
 *
 * @author waver
 * @date 2023/6/15 22:56
 */
public class PathSum2 {

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> paths = new ArrayList<>();
        if (root == null) {
            return paths;
        }
        List<Integer> currentPath = new ArrayList<>();
        process(root, targetSum, currentPath, paths);
        return paths;
    }

    public void process(TreeNode root, int sum, List<Integer> currentPath, List<List<Integer>> paths) {
        if (root.left == null && root.right == null && sum == root.val) {
            currentPath.add(root.val);
            paths.add(copy(currentPath));
            currentPath.remove(currentPath.size() - 1);
            return;
        }
        currentPath.add(root.val);
        if (root.left != null) {
            process(root.left, sum - root.val, currentPath, paths);
        }
        if (root.right != null) {
            process(root.right, sum - root.val, currentPath, paths);
        }
        currentPath.remove(currentPath.size() - 1);
    }


    List<Integer> copy(List<Integer> linkedList) {
        List<Integer> result = new ArrayList<>(linkedList.size());
        result.addAll(linkedList);
        return result;
    }

}
