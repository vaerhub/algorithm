package io.arkvaer.algorithm.basic.day18;

import java.util.Arrays;

/**
 * 假设有排成一行的N个位置，记为1~N，N一定大于或等于2,
 * 开始时机器人在其中的M位置上(M 一定是1~N 中的一个)
 * 如果机器人来到1位置，那么下一步只能往右来到2位置;
 * 如果机器人来到N位置，那么下一步只能往左来到 N-1位置;
 * 如果机器人来到中间位置，那么下一步可以往左走或者往右走,
 * 规定机器人必须走K步，最终能来到P位置(P也是1~N中的一个)的方法有多少种,
 * 给定四个参数 N、M、K、P，返回方法数。
 */
public class RobotWalk {

    /**
     * @param length N 路线长度
     * @param start  M 起点位置
     * @param step   K 剩余需要走的步数
     * @param target P 终点位置
     * @return 从M走K步到P的方案数
     */
    public static int planCount(int length, int start, int step, int target) {
        if (length < 2 || start > length || target > length || target < 1 || step < 1) {
            return -1;
        }
        return process1(length, target, start, step);
    }

    public static int process1(int length, int target, int cur, int rest) {
        if (rest == 0) {
            return cur == target ? 1 : 0;
        }
        if (cur == 1) {
            return process1(length, target, cur + 1, rest - 1);
        }
        if (cur == length) {
            return process1(length, target, cur - 1, rest - 1);
        }

        return process1(length, target, cur - 1, rest - 1) + process1(length, target, cur + 1, rest - 1);
    }

    public static int planCountCache(int length, int start, int step, int target) {
        if (length < 2 || start > length || target > length || target < 1 || step < 1) {
            return -1;
        }
        int[][] cache = new int[length + 1][step + 1];
        for (int[] ints : cache) {
            Arrays.fill(ints, -1);
        }
        return process2(length, target, start, step, cache);
    }

    public static int process2(int length, int target, int cur, int rest, int[][] cache) {
        if (cache[cur][rest] != -1) {
            return cache[cur][rest];
        }
        int count;
        if (rest == 0) {
            count = cur == target ? 1 : 0;
        } else if (cur == 1) {
            count = process2(length, target, 2, rest - 1, cache);
        } else if (cur == length) {
            count = process2(length, target, length - 1, rest - 1, cache);
        } else {
            count = process2(length, target, cur + 1, rest - 1, cache) + process2(length, target, cur - 1, rest - 1, cache);
        }
        cache[cur][rest] = count;
        return count;
    }


    public static int planCountDP(int length, int start, int step, int target) {
        int[][] dp = new int[length + 1][step + 1];
        dp[target][0] = 1;

        for (int rest = 1; rest <= step; rest++) {
            dp[1][rest] = dp[2][rest - 1];
            for (int cur = 2; cur < length; cur++) {
                dp[cur][rest] = dp[cur - 1][rest - 1] + dp[cur + 1][rest - 1];
            }
            dp[length][rest] = dp[length - 1][rest - 1];
        }
        return dp[start][step];
    }

    public static void main(String[] args) {
        int length = 5;
        int start = 2;
        int step = 6;
        int target = 2;
        int count = planCount(length, start, step, target);
        int count1 = planCountCache(length, start, step, target);
        int count2 = planCountDP(length, start, step, target);
        System.out.println(count);
        System.out.println(count1);
        System.out.println(count2);
    }

}
