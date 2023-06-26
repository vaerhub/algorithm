package io.arkvaer.algorithm.basic.day04;

import java.util.Arrays;

/**
 * LeetCode: <a href="https://leetcode.cn/problems/reverse-pairs/">LeetCode</a>
 *
 * @author waver
 * @date 2023/6/26 22:08
 */
public class BiggerThanRightTwice {
    public static int reversePairs(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        return process(nums, 0, nums.length - 1);
    }

    public static int process(int[] nums, int left, int right) {
        if (left == right) {
            return 0;
        }
        int mid = left + ((right - left) >> 1);
        return process(nums, left, mid) + process(nums, mid + 1, right) + merge(nums, left, mid, right);
    }

    public static int merge(int[] nums, int left, int mid, int right) {
        int p1 = left;
        int p2 = mid + 1;
        int[] help = new int[right - left + 1];
        int index = 0;
        int result = 0;
        int rIndex = mid + 1;
        for (int i = left; i <= mid; i++) {
            while (rIndex <= right && nums[i] > (long) nums[rIndex] * 2) {
                rIndex++;
            }
            result += rIndex - mid - 1;
        }

        while (p1 <= mid && p2 <= right) {
            help[index++] = nums[p1] < nums[p2] ? nums[p1++] : nums[p2++];
        }
        while (p1 <= mid) {
            help[index++] = nums[p1++];
        }
        while (p2 <= right) {
            help[index++] = nums[p2++];
        }

        System.arraycopy(help, 0, nums, left, help.length);
        return result;
    }

    public static void main(String[] args) {
        int[] test = {2147483647, 2147483647, 2147483647, 2147483647, 2147483647, 2147483647};
        int i = reversePairs(test);
        System.out.println(i);
        System.out.println(Arrays.toString(test));

    }
}
