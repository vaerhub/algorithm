package io.arkvaer.algorithm.basic.day24;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 测试链接：<a href="https://leetcode.com/problems/gas-station">加油站</a>
 */
public class GasStation {
    public static int[] getGasNum(int[] gas, int[] cost) {
        int length = gas.length;
        int[] ans = new int[length];
        int[] gap = new int[length];
        int[] help = new int[length * 2];

        int sum = 0;
        for (int i = 0; i < length; i++) {
            gap[i] = gas[i] - cost[i];
            sum += gap[i];
            help[i] = sum;
        }
        for (int i = length; i < length * 2; i++) {
            sum += gap[i - length];
            help[i] = sum;
        }
        // [-2, -4, -6, -3, 0, -2, -4, -6, -3, 0]

        Deque<Integer> minGas = new LinkedList<>();
        int left = 0;
        for (int right = 0; right < help.length; right++) {
            while (!minGas.isEmpty() && help[minGas.peekLast()] >= help[right]) {
                minGas.pollLast();
            }
            minGas.addLast(right);
            if (right - left == length) {
                if (!minGas.isEmpty() && help[minGas.peekFirst()] - (left == 0 ? 0 : help[left - 1]) >= 0) {
                    ans[left > length ? left - length : left] = 1;
                }
            }
            if (right - left >= length) {
                if (!minGas.isEmpty() && minGas.peekFirst() == left) {
                    minGas.pollFirst();
                }
                left++;
            }
        }
        return ans;
    }


    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int length = gas.length;
        int[] ans = new int[length];
        int[] gap = new int[length];
        int[] help = new int[length * 2];

        int sum = 0;
        for (int i = 0; i < length; i++) {
            gap[i] = gas[i] - cost[i];
        }
        for (int i = 0; i < length * 2; i++) {
            sum += gap[i >= length ? i - length : i];
            help[i] = sum;
        }
        Deque<Integer> minGas = new LinkedList<>();
        int left = 0;
        for (int right = 0; right < help.length; right++) {
            while (!minGas.isEmpty() && help[minGas.peekLast()] >= help[right]) {
                minGas.pollLast();
            }
            minGas.addLast(right);
            if (right - left == length) {
                if (!minGas.isEmpty() && help[minGas.peekFirst()] - (left == 0 ? 0 : help[left - 1]) >= 0) {
                    return left > length ? left - length : left;
                }
            }
            if (right - left >= length) {
                if (!minGas.isEmpty() && minGas.peekFirst() == left) {
                    minGas.pollFirst();
                }
                left++;
            }
        }
        return -1;
    }

    public int best(int[] gas, int[] cost) {
        // [-2, -4, -6, -3, 0, -2, -4, -6, -3, 0]
        int n = gas.length;
        // 相当于图像中的坐标点和最低点
        int sum = 0, minSum = 0;
        int start = 0;
        for (int i = 0; i < n; i++) {
            sum += gas[i] - cost[i];
            if (sum < minSum) {
                // 经过第 i 个站点后，使 sum 到达新低
                // 所以站点 i + 1 就是最低点（起点）
                start = i + 1;
                minSum = sum;
            }
        }
        if (sum < 0) {
            // 总油量小于总的消耗，无解
            return -1;
        }
        // 环形数组特性
        return start == n ? 0 : start;
    }

    public static void main(String[] args) {
        int[] gas = {1, 2, 3, 4, 5};
        int[] cost = {3, 4, 5, 1, 2};
        int[] gasNum = getGasNum(gas, cost);
        System.out.println(Arrays.toString(gasNum));
    }
}
