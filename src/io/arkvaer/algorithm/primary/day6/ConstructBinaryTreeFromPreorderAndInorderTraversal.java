package io.arkvaer.algorithm.primary.day6;

import io.arkvaer.algorithm.primary.structs.TreeNode;

import java.util.HashMap;

/**
 * 从前序与中序遍历序列构造二叉树
 * 测试链接: <a href="https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal/">construct-binary-tree-from-preorder-and-inorder-traversal</a>
 *
 * @author waver
 * @date 2023/6/15 0:11
 */
public class ConstructBinaryTreeFromPreorderAndInorderTraversal {
    private HashMap<Integer, Integer> valueIndexMap;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length != inorder.length) {
            return null;
        }
        valueIndexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            valueIndexMap.put(inorder[i], i);
        }
        return build(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    public TreeNode build(int[] preorder, int preorderLeft, int preorderRight, int[] inorder, int inorderLeft, int inorderRight) {
        if (preorderLeft > preorderRight) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preorderLeft]);
        if (preorderLeft == preorderRight) {
            return root;
        }
        Integer rootIndex = valueIndexMap.get(preorder[preorderLeft]);
        root.left = build(preorder, preorderLeft + 1, preorderLeft + rootIndex - inorderLeft, inorder, inorderLeft, rootIndex - 1);
        root.right = build(preorder, preorderLeft + rootIndex - inorderLeft + 1, preorderRight, inorder, rootIndex + 1, inorderRight);
        return root;
    }

}
