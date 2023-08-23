package io.arkvaer.algorithm.basic.day21;

import java.util.HashMap;
import java.util.Map;

/**
 * arr是货币数组，其中的值都是正数。再给定一个正数aim。
 * 每个值都认为是一张货币
 * 认为值相同的货币没有任何不同
 * 返回组成aim的方法数
 * 例如:arr ={1,2,1,1,2,1,2}，aim =4
 * 方法:1+1+1+1、1+1+2、2+2一共就3种方法，所以返回3
 */
public class CoinsWaySameValueSamePaper {

    public static class Info {
        public int[] coins;
        public int[] counts;

        public Info(int[] coins, int[] counts) {
            this.coins = coins;
            this.counts = counts;
        }
    }

    public static Info getInfo(int[] arr) {
        Map<Integer, Integer> countsMap = new HashMap<>();
        for (int value : arr) {
            if (!countsMap.containsKey(value)) {
                countsMap.put(value, 1);
            } else {
                countsMap.put(value, countsMap.get(value) + 1);
            }
        }
        int len = countsMap.size();
        int[] coins = new int[len];
        int[] counts = new int[len];
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : countsMap.entrySet()) {
            coins[index] = entry.getKey();
            counts[index++] = entry.getValue();
        }
        return new Info(coins, counts);
    }

    public static int coinsWay(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        Info info = getInfo(arr);
        return process(info.coins, info.counts, 0, aim);
    }


    public static int process(int[] coins, int[] counts, int index, int rest) {
        if (index == coins.length) {
            return rest == 0 ? 1 : 0;
        }
        int ways = 0;
        for (int count = 0; count * coins[index] <= rest && count <= counts[index]; count++) {
            ways += process(coins, counts, index + 1, rest - (count * coins[index]));
        }
        return ways;
    }


    public static int dp1(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        Info info = getInfo(arr);
        int[] coins = info.coins;
        int[] counts = info.counts;
        int len = coins.length;
        int[][] dp = new int[len + 1][aim + 1];
        dp[len][0] = 1;
        for (int index = len - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                int ways = 0;
                for (int count = 0; count * coins[index] <= rest && count <= counts[index]; count++) {
                    ways += dp[index + 1][rest - (count * coins[index])];
                }
                dp[index][rest] = ways;
            }
        }
        return dp[0][aim];
    }

    public static int dp2(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        Info info = getInfo(arr);
        int[] coins = info.coins;
        int[] counts = info.counts;
        int len = coins.length;
        int[][] dp = new int[len + 1][aim + 1];
        dp[len][0] = 1;
        for (int index = len - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                dp[index][rest] = dp[index+1][rest];
                if (rest - coins[index] >= 0) {
                    dp[index][rest] += dp[index][rest - coins[index]];
                }
                if (rest - coins[index] * (counts[index] + 1) >= 0 ) {
                    dp[index][rest] -= dp[index + 1][rest - coins[index] * (counts[index] + 1)];
                }
            }
        }
        return dp[0][aim];
    }


    // 为了测试
    public static int[] randomArray(int maxLen, int maxValue) {
        int N = (int) (Math.random() * maxLen);
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
        int maxLen = 10;
        int maxValue = 20;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(maxLen, maxValue);
            int aim = (int) (Math.random() * maxValue);
            int ans1 = coinsWay(arr, aim);
            int ans2 = dp1(arr, aim);
            int ans3 = dp2(arr, aim);

            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(aim);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(ans3);
                break;
            }
        }
        System.out.println("测试结束");
    }


}
