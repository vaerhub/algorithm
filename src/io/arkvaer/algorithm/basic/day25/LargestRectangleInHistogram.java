package io.arkvaer.algorithm.basic.day25;

import java.util.Stack;

/**
 * <a href="https://leetcode.com/problems/largest-rectangle-in-histogram">测试链接</a>
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 */
public class LargestRectangleInHistogram {
    public static int largestRectangleArea(int[] arr) {
        int length = arr.length;
        Stack<Integer> stack = new Stack<>();
        int maxArea = Integer.MIN_VALUE;
        for (int i = 0; i < length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                Integer pop = stack.pop();
                int left = stack.isEmpty() ? -1 : stack.peek();
                maxArea = Math.max(maxArea, arr[pop] * (i - left - 1));
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            Integer pop = stack.pop();
            int left = stack.isEmpty() ? -1 : stack.peek();
            maxArea = Math.max(maxArea, arr[pop] * (length - left - 1));
        }
        return maxArea;
    }


    /**
     * 使用数组代替单调栈
     *
     * @param heights
     * @return
     */
    public static int largestRectangleArea2(int[] heights) {
        if (heights== null || heights.length == 0) {
            return 0;
        }
        int length = heights.length;
        int[] stack = new int[length];
        int index = -1;
        int maxArea = Integer.MIN_VALUE;
        for (int i = 0; i < length; i++) {
            while (index != -1 && heights[stack[index]] >= heights[i]) {
                int pop = stack[index--];
                int left = index == -1 ? -1 : stack[index];
                maxArea = Math.max(maxArea, heights[pop] * (i - left - 1));
            }
            stack[++index] = i;
        }
        while (index != -1) {
            int pop = stack[index--];
            int left = index == -1 ? -1 : stack[index];
            maxArea = Math.max(maxArea, heights[pop] * (length - left - 1));
        }
        return maxArea;
    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 10, 9, 5, 3, 4};
        System.out.println(largestRectangleArea(arr));
        System.out.println(largestRectangleArea2(arr));


    }
}
