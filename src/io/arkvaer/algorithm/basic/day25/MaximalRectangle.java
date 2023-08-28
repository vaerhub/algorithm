package io.arkvaer.algorithm.basic.day25;

/**
 * <a href="https://leetcode.cn/problems/maximal-rectangle/description/">最大矩形</a>
 * 给定一个仅包含 0 和 1 、大小为 rows x cols 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
 */
public class MaximalRectangle {
    public static int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int len = matrix[0].length;
        int[] help = new int[len];
        int max = Integer.MIN_VALUE;
        for (char[] chars : matrix) {
            for (int j = 0; j < len; j++) {
                // 注意, 此处应使用++help[j]
                help[j] = chars[j] == '0' ? 0 : ++help[j];
            }
            max = Math.max(max, getMaxArea(help));
        }
        return max;
    }


    public static int getMaxArea(int[] help) {
        if (help == null || help.length == 0) {
            return 0;
        }
        int[] stack;
        int index;
        int len = help.length;
        stack = new int[len];
        index = -1;
        int max = Integer.MIN_VALUE;
        for (int j = 0; j < len; j++) {
            while (index != -1 && help[stack[index]] >= help[j]) {
                int pop = stack[index--];
                int k = index == -1 ? -1 : stack[index];
                max = Math.max(max, help[pop] * (j - k - 1));
            }
            stack[++index] = j;
        }
        while (index != -1) {
            int pop = stack[index--];
            int k = index == -1 ? -1 : stack[index];
            max = Math.max(max, help[pop] * (len - k - 1));
        }
        return max;
    }

    public static void main(String[] args) {
        char[][] matrix = {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        };
        int ans1 = maximalRectangle(matrix);
        System.out.println(ans1);
    }
}
