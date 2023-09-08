package io.arkvaer.algorithm.basic.day26;

/**
 * <a href="https://leetcode.cn/problems/sum-of-subarray-minimums/">测试链接</a>
 * 给定一个整数数组 arr，找到 min(b) 的总和，其中 b 的范围为 arr 的每个（连续）子数组。
 * 由于答案可能很大，因此 返回答案模 10^9 + 7 。
 *
 * @author waver
 * @date 2023/9/3 20:37
 */
public class SumOfSubarrayMinimums {
    public static int subArrayMinSum1(int[] arr) {
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                int min = arr[i];
                for (int k = i + 1; k <= j; k++) {
                    min = Math.min(min, arr[k]);
                }
                ans += min;
            }
        }
        return ans;
    }

    public static int subArrayMinSum2(int[] arr) {
        // left[i] = x : arr[i]左边，离arr[i]最近，<=arr[i]，位置在x
        int[] left = leftNearLessEqual2(arr);
        // right[i] = y : arr[i]右边，离arr[i]最近，< arr[i],的数，位置在y
        int[] right = rightNearLess2(arr);// 1 2 3 4
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            int start = i - left[i];
            int end = right[i] - i;
            ans += start * end * arr[i];
        }
        return ans;
    }

    public static int[] leftNearLessEqual2(int[] arr) {
        int len = arr.length;
        int[] left = new int[len];
        for (int i = 0; i < len; i++) {
            int ans = -1;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] <= arr[i]) {
                    ans = j;
                    break;
                }
            }
            left[i] = ans;
        }
        return left;
    }

    public static int[] rightNearLess2(int[] arr) {
        int len = arr.length;
        int[] right = new int[len];
        for (int i = 0; i < len; i++) {
            int ans = -1;
            for (int j = 0; j < len; j++) {
                if (arr[j] < arr[i]) {
                    ans = j;
                    break;
                }
            }
            right[i] = ans;
        }
        return right;
    }


    public static int sumSubarrayMins(int[] arr) {
        int[] stack = new int[arr.length];
        int[] left = nearLessEqualLeft(arr, stack);
        int[] right = nearLessRight(arr, stack);
        long ans = 0;
        for (int i = 0; i < arr.length; i++) {
            long start = i - left[i];
            long end = right[i] - i;
            ans += start * end * (long) arr[i];
            ans %= 1000000007;
        }
        return (int) ans;
    }

    public static int[] nearLessEqualLeft(int[] arr, int[] stack) {
        int len = arr.length;
        int[] left = new int[len];
        int size = 0;
        for (int i = len - 1; i >= 0; i--) {
            while (size != 0 && arr[i] <= arr[stack[size - 1]]) {
                left[stack[--size]] = i;
            }
            stack[size++] = i;
        }
        while (size != 0) {
            left[stack[--size]] = -1;
        }
        return left;
    }

    public static int[] nearLessRight(int[] arr, int[] stack) {
        int len = arr.length;
        int[] right = new int[len];
        int size = 0;
        for (int i = 0; i < len; i++) {
            while (size != 0 && arr[i] < arr[stack[size - 1]]) {
                right[stack[--size]] = i;
            }
            stack[size++] = i;
        }
        while (size != 0) {
            right[stack[--size]] = len;
        }
        return right;
    }

    public static void main(String[] args) {
        int[] a = {3, 1, 2, 4};
        int i1 = sumSubarrayMins(a);
        System.out.println(i1);
    }
}
