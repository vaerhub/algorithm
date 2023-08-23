package io.arkvaer.algorithm.basic.day22;

/**
 * 给定3个参数，N，M，K
 * 怪兽有N滴血，
 * 等着英雄来砍自己, 英雄每一次打击，都会让怪兽流失0~M]的血量, 到底流失多少?每一次在[0~M]上等概率的获得一个整数
 * 求K次打击之后，英雄把怪兽砍死的概率
 */
public class KillMonster {
    public static double dieProbability1(int N, int M, int K) {
        if (N < 1 || M < 1 || K < 1) {
            return 0;
        }
        return (double) process(N, M, K) / Math.pow(M + 1, K);
    }

    public static long process(int N, int M, int K) {
        if (K == 0) {
            return N <= 0 ? 1 : 0;
        }
        if (N <= 0) {
            return (long) Math.pow(M + 1, K);
        }

        long ways = 0;
        for (int i = 0; i <= M; i++) {
            ways += process(N - i, M, K - 1);
        }
        return ways;
    }


    public static double dieProbability2(int N, int M, int K) {
        if (N < 1 || M < 1 || K < 1) {
            return 0;
        }
        long[][] dp = new long[K + 1][N + 1];
        dp[0][0] = 1;
        for (int k = 1; k <= K; k++) {
            dp[k][0] = (long) Math.pow(M + 1, k);
            for (int n = 0; n <= N; n++) {
                long ways = 0;
                for (int m = 0; m <= M; m++) {
                    if (n - m >= 0) {
                        ways += dp[k - 1][n - m];
                    } else {
                        ways += (long) Math.pow(M + 1, k - 1);
                    }
                }
                dp[k][n] = ways;
            }
        }
        return dp[K][N] / Math.pow(M + 1, K);
    }

    public static double dieProbability3(int N, int M, int K) {
        if (N < 1 || M < 1 || K < 1) {
            return 0;
        }
        long[][] dp = new long[K + 1][N + 1];
        dp[0][0] = 1;
        for (int k = 1; k <= K; k++) {
            dp[k][0] = (long) Math.pow(M + 1, k);
            for (int n = 1; n <= N; n++) {
                dp[k][n] = dp[k - 1][n] + dp[k][n - 1];
                if (n - M - 1 >= 0) {
                    dp[k][n] -= dp[k - 1][n - M - 1];
                } else {
                    dp[k][n] -= (long) Math.pow(M + 1, k - 1);
                }
            }
        }
        return dp[K][N] / Math.pow(M + 1, K);
    }

    public static void main(String[] args) {
        int NMax = 10;
        int MMax = 10;
        int KMax = 10;
        int testTime = 200;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int N = (int) (Math.random() * NMax);
            int M = (int) (Math.random() * MMax);
            int K = (int) (Math.random() * KMax);
            double ans1 = dieProbability1(N, M, K);
            double ans2 = dieProbability2(N, M, K);
            double ans3 = dieProbability3(N, M, K);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");
    }
}
