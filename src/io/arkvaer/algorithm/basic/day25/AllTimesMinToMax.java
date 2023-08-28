package io.arkvaer.algorithm.basic.day25;

import java.util.Stack;

/**
 * 给定一个只包含正数的数组arr，
 * arr中任何一个子数组sub, 一定都可以算出(sub累加和)* (sub中的最小值)的值,
 * 那么所有子数组中，这个值最大是多少?
 */
public class AllTimesMinToMax {

    public static int max1(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                int minNum = Integer.MAX_VALUE;
                int sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += arr[k];
                    minNum = Math.min(minNum, arr[k]);
                }
                max = Math.max(max, minNum * sum);
            }
        }
        return max;
    }

    public static int max2(int[] arr) {
        int length = arr.length;
        int[] preSum = new int[length];
        preSum[0] = arr[0];
        for (int i = 1; i < length; i++) {
            preSum[i] += preSum[i - 1] + arr[i];
        }
        int max = Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                Integer pop = stack.pop();
                max = Math.max(max, arr[pop] * (preSum[i - 1] - (stack.isEmpty() ? 0 : preSum[stack.peek()])));
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            Integer pop = stack.pop();
            max = Math.max(max, arr[pop] * (preSum[length - 1] - (stack.isEmpty() ? 0 : preSum[stack.peek()])));
        }
        return max;
    }


    // 本题可以在leetcode上找到原题
    // 测试链接 : https://leetcode.cn/problems/maximum-subarray-min-product/
    // 注意测试题目数量大，要取模，但是思路和课上讲的是完全一样的
    // 注意溢出的处理即可，也就是用long类型来表示累加和
    // 还有优化就是，你可以用自己手写的数组栈，来替代系统实现的栈，也会快很多
    public static int maxSumMinProduct(int[] arr) {
        int size = arr.length;
        long[] sums = new long[size];
        sums[0] = arr[0];
        for (int i = 1; i < size; i++) {
            sums[i] = sums[i - 1] + arr[i];
        }
        long max = Long.MIN_VALUE;
        int[] stack = new int[size];
        int stackSize = 0;
        for (int i = 0; i < size; i++) {
            while (stackSize != 0 && arr[stack[stackSize - 1]] >= arr[i]) {
                int j = stack[--stackSize];
                max = Math.max(max,
                        (stackSize == 0 ? sums[i - 1] : (sums[i - 1] - sums[stack[stackSize - 1]])) * arr[j]);
            }
            stack[stackSize++] = i;
        }
        while (stackSize != 0) {
            int j = stack[--stackSize];
            max = Math.max(max,
                    (stackSize == 0 ? sums[size - 1] : (sums[size - 1] - sums[stack[stackSize - 1]])) * arr[j]);
        }
        return (int) (max % 1000000007);
    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 5, 7, 9, 5, 2, 0};
        //    sums= {2, 5, 10, 17, 26, 31, 33, 33}

        int ans = max1(arr);
        int max = max2(arr);
        System.out.println(ans);
        System.out.println(max);

    }
}
