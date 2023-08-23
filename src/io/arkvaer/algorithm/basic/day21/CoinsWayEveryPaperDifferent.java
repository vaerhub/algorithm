package io.arkvaer.algorithm.basic.day21;

import io.arkvaer.algorithm.utils.AlgUtil;

/**
 * arr是货币数组，其中的值都是正数。再给定一个正数aim. 每个值都认为是一张货币，
 * 即便是值相同的货币也认为每一张都是不同的返回组成aim的方法数
 * 例如:arr={1,1,1}，aim=2
 * 第0个和第1个能组成2，
 * 第1个和第2个能组成2，
 * 第0个和第2个能组成2共就3种方法，所以返回3
 */
public class CoinsWayEveryPaperDifferent {
    public static int coinWays(int[] arr, int aim) {
        return process(arr, 0, aim);
    }

    public static int process(int[] arr, int index, int rest) {
        if (rest < 0) {
            return 0;
        }
        if (index == arr.length) {
            return rest == 0 ? 1 : 0;
        } else {
            return process(arr, index + 1, rest) + process(arr, index + 1, rest - arr[index]);
        }
    }


    public static int dp(int[] arr, int aim) {
        if (aim == 0) {
            return 1;
        }
        int len = arr.length;
        int[][] dp = new int[len + 1][aim + 1];
        dp[len][0] = 1;
        for (int index = len - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                dp[index][rest] = dp[index + 1][rest] + (rest - arr[index] >= 0 ? dp[index + 1][rest - arr[index]] : 0);
            }

        }
        return dp[0][aim];
    }


    public static int[] randomArray(int maxLen, int maxValue) {
        int N = (int) (Math.random() * maxLen);
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = (int) (Math.random() * maxValue) + 1;
        }
        return arr;
    }

    public static void main(String[] args) {
        int maxLen = 20;
        int maxValue = 30;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(maxLen, maxValue);
            int aim = AlgUtil.random.nextInt(maxValue);
            int ans1 = coinWays(arr, aim);
            int ans2 = dp(arr, aim);
            if (ans1 != ans2) {
                System.out.println("Oops!");
                AlgUtil.printArray(arr);
                System.out.println(aim);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("测试结束");
    }
}
