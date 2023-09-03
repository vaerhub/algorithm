package io.arkvaer.algorithm.basic.day25;

/**
 * 给你一个 m x n 的二进制矩阵 mat ，请你返回有多少个 子矩形 的元素全部都是 1 。
 * <a href="https://leetcode.cn/problems/count-submatrices-with-all-ones">测试链接</a>
 * 测试链接
 *
 * @author waver
 * @date 2023/9/2 17:17
 */
public class CountSubmatricesWithAllOnes {

    public static int numSubmat(int[][] mat) {
        if (mat == null || mat.length == 0 || mat[0].length == 0) {
            return 0;
        }
        int nums = 0;
        int[] height = new int[mat[0].length];
        for (int[] m : mat) {
            for (int i = 0; i < m.length; i++) {
                height[i] = m[i] == 0 ? 0 : height[i] + 1;
            }
            nums += countFormBottom(height);
        }
        return nums;
    }

    public static int countFormBottom(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int nums = 0;
        int[] stack = new int[height.length];
        int index = -1;
        for (int i = 0; i < height.length; i++) {
            while (index != -1 && height[stack[index]] >= height[i]) {
                int cur = stack[index--];
                if (height[cur] > height[i]) {
                    int left = index == -1 ? -1 : stack[index];
                    int n = i - left - 1;
                    int down = Math.max(left == -1 ? 0 : height[left], height[i]);
                    nums += (height[cur] - down) * num(n);
                }
            }
            stack[++index] = i;
        }
        while (index != -1) {
            int cur = stack[index--];

            int left = index == -1 ? -1 : stack[index];
            int n = height.length - left - 1;
            int down = left == -1 ? 0 : height[left];
            nums += (height[cur] - down) * num(n);
        }
        return nums;
    }

    public static int num(int n) {
        return (n * (n + 1)) >> 1;
    }

    public static void main(String[] args) {
        int[][] mat = {{1, 0, 1}, {1, 1, 0}, {1, 1, 0}};
        int count = numSubmat(mat);
        System.out.println(count);
    }


}



