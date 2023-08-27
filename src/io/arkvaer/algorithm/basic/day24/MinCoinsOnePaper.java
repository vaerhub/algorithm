package io.arkvaer.algorithm.basic.day24;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * arr是货币数组，其中的值都是正数。再给定一个正数aim。每个值都认为是一张货币
 * 返回组成aim的最少货币数
 * 注意:
 * 因为是求最少货币数，所以每一张货币认为是相同或者不同就不重要了
 *
 * @author waver
 * @date 2023/8/26 18:58
 */
public class MinCoinsOnePaper {

    // region 暴力递归
    /**
     * 暴力递归
     *
     * @param arr 货币数组
     * @param aim 目标数
     * @return 最小的货币数
     */
    public static int minCoins(int[] arr, int aim) {
        return process(arr, 0, aim);
    }

    private static int process(int[] arr, int index, int rest) {
        if (rest < 0) {
            return Integer.MAX_VALUE;
        }
        if (index == arr.length) {
            return rest == 0 ? 0 : Integer.MAX_VALUE;
        }

        int p1 = process(arr, index + 1, rest);
        int p2 = process(arr, index + 1, rest - arr[index]);
        if (p2 != Integer.MAX_VALUE) {
            p2++;
        }
        return Math.min(p1, p2);
    }
    // region


    // region 常规动态规划方法
    /**
     * 常规DP方法
     *
     * @param arr 货币数组
     * @param aim 目标数
     * @return 最小的货币数
     */
    public static int dp1(int[] arr, int aim) {
        if (aim == 0) {
            return 0;
        }
        int len = arr.length;
        int[][] dp = new int[len + 1][aim + 1];
        dp[len][aim] = 0;
        for (int rest = 1; rest <= aim; rest++) {
            dp[len][rest] = Integer.MAX_VALUE;
        }
        for (int index = len - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                int p1 = dp[index + 1][rest];
                int p2 = rest - arr[index] >= 0 ? dp[index + 1][rest - arr[index]] : Integer.MAX_VALUE;
                if (p2 != Integer.MAX_VALUE) {
                    p2++;
                }
                dp[index][rest] = Math.min(p1, p2);
            }
        }
        return dp[0][aim];
    }

    // endregion

    public static class Info {
        /**
         * 货币的面值
         */
        public int[] values;
        /**
         * 货币的张树
         */
        public int[] pages;

        public Info(int[] values, int[] pages) {
            this.values = values;
            this.pages = pages;
        }
    }


    public static Info getInfo(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int j : arr) {
            if (map.containsKey(j)) {
                map.put(j, map.get(j) + 1);
            } else {
                map.put(j, 1);
            }
        }
        int[] values = new int[map.size()];
        int[] pages = new int[map.size()];
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            values[index] = entry.getKey();
            pages[index++] = entry.getValue();
        }
        return new Info(values, pages);
    }

    public static int dp2(int[] arr, int aim) {
        if (aim == 0) {
            return 0;
        }
        Info info = getInfo(arr);
        int[] values = info.values;
        int[] pages = info.pages;
        int len = values.length;
        int[][] dp = new int[len + 1][aim + 1];
        dp[len][0] = 0;
        for (int rest = 1; rest <= aim; rest++) {
            dp[len][rest] = Integer.MAX_VALUE;
        }
        for (int index = len - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                dp[index][rest] = dp[index + 1][rest];
                for (int page = 1; page <= pages[index] && page * values[index] <= rest; page++) {
                    if (dp[index + 1][rest - page * values[index]] != Integer.MAX_VALUE) {
                        dp[index][rest] = Math.min(page + dp[index + 1][rest - page * values[index]], dp[index][rest]);
                    }
                }
            }
        }
        return dp[0][aim];
    }


    public static int dp3(int[] arr, int aim) {
        if (aim == 0) {
            return 0;
        }
        Info info = getInfo(arr);
        int[] values = info.values;
        int[] pages = info.pages;
        int len = values.length;
        int[][] dp = new int[len + 1][aim + 1];
        dp[len][0] = 0;
        for (int rest = 1; rest <= aim; rest++) {
            dp[len][rest] = Integer.MAX_VALUE;
        }
        for (int index = len - 1; index >= 0; index--) {
            for (int mod = 0; mod < Math.min(aim + 1, values[index]); mod++) {
                Deque<Integer> deque = new LinkedList<>();
                deque.add(mod);
                dp[index][mod] = dp[index + 1][mod];
                for (int rest = mod + values[index]; rest <= aim; rest += values[index]) {
                    // 与当前
                    while (!deque.isEmpty() &&
                            (dp[index + 1][deque.peekLast()] == Integer.MAX_VALUE || dp[index + 1][deque.peekLast()] + ((rest - deque.peekLast()) / values[index]) >= dp[index + 1][rest])) {
                        deque.pollLast();
                    }
                    deque.addLast(rest);
                    // 剩余钱数 - (该面值总张数 + 1) * 当前面额数 == 0 证明当前的余额中已经不包含当前面额的货币了
                    int overdue = rest - values[index] * (pages[index] + 1);
                    if (deque.peekFirst() == overdue) {
                        deque.pollFirst();
                    }
                    if (dp[index + 1][deque.peekFirst()] == Integer.MAX_VALUE) {
                        dp[index][rest] = Integer.MAX_VALUE;
                    } else {
                        dp[index][rest] = dp[index + 1][deque.peekFirst()] + (rest - deque.peekFirst()) / values[index];
                    }

                }
            }
        }
        return dp[0][aim];
    }

    // 为了测试
    public static int[] randomArray(int N, int maxValue) {
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = (int) (Math.random() * maxValue) + 1;
        }
        return arr;
    }

    // 为了测试
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // 为了测试
    public static void main(String[] args) {


        int[] moneys = {7, 28, 29, 19, 3, 16, 8, 26, 25, 25, 14};

        int maxLen = 20;
        int maxValue = 30;
        int testTime = 300000;
        System.out.println("功能测试开始");
        for (int i = 0; i < testTime; i++) {
            int N = (int) (Math.random() * maxLen);
            int[] arr = randomArray(N, maxValue);
            int aim = (int) (Math.random() * maxValue);
            int ans1 = minCoins(arr, aim);
            int ans2 = dp1(arr, aim);
            int ans3 = dp2(arr, aim);
            int ans4 = dp3(arr, aim);
            if (ans1 != ans2 || ans3 != ans4 || ans1 != ans3) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(aim);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(ans3);
                System.out.println(ans4);
                break;
            }
        }
        System.out.println("功能测试结束");

        System.out.println("==========");

        int aim = 0;
        int[] arr = null;
        long start;
        long end;
        int ans2;
        int ans3;

        System.out.println("性能测试开始");
        maxLen = 30000;
        maxValue = 20;
        aim = 60000;
        arr = randomArray(maxLen, maxValue);

        start = System.currentTimeMillis();
        ans2 = dp2(arr, aim);
        end = System.currentTimeMillis();
        System.out.println("dp2答案 : " + ans2 + ", dp2运行时间 : " + (end - start) + " ms");

        start = System.currentTimeMillis();
        ans3 = dp3(arr, aim);
        end = System.currentTimeMillis();
        System.out.println("dp3答案 : " + ans3 + ", dp3运行时间 : " + (end - start) + " ms");
        System.out.println("性能测试结束");

        System.out.println("===========");

        System.out.println("货币大量重复出现情况下，");
        System.out.println("大数据量测试dp3开始");
        maxLen = 20000000;
        aim = 10000;
        maxValue = 10000;
        arr = randomArray(maxLen, maxValue);
        start = System.currentTimeMillis();
        ans3 = dp3(arr, aim);
        end = System.currentTimeMillis();
        System.out.println("dp3运行时间 : " + (end - start) + " ms");
        System.out.println("大数据量测试dp3结束");

        System.out.println("===========");

        System.out.println("当货币很少出现重复，dp2比dp3有常数时间优势");
        System.out.println("当货币大量出现重复，dp3时间复杂度明显优于dp2");
        System.out.println("dp3的优化用到了窗口内最小值的更新结构");
    }
}
