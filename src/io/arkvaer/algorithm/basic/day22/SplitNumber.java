package io.arkvaer.algorithm.basic.day22;

/**
 * 给定一个数字, 求该数字的分裂方法数, 要求后边的数不可以大于前边的数字
 * 给定一个正数1，裂开的方法有一种，(1)
 * 给定一个正数2，裂开的方法有两，(1,1)(2)
 * 给定一个正数3，裂开的方法有三种，(1,1,1)(1,2)(3)
 * 给定一个正4开法有五种，(1,1,11)、(1,1,2)、(1,3)、(2,2)、(4)
 * 给定一个正数n，求裂开的方法数。
 * 动态规划优化状态依赖的技巧
 */
public class SplitNumber {
    public static int splitNum(int n) {
        if (n <= 0) {
            return 0;
        }
        return process(1, n);
    }

    public static int process(int pre, int rest) {
        if (rest == 0) {
            return 1;
        }
        if (pre > rest) {
            return 0;
        }
        int ways = 0;

        for (int first = pre; first <= rest; first++) {
            ways += process(first, rest - first);
        }
        return ways;
    }


    public static int dp1(int n) {
        int[][] dp = new int[n + 1][n + 1];
        for (int pre = 0; pre <= n; pre++) {
            dp[pre][0] = 1;
            dp[pre][pre] = 1;
        }
        for (int pre = n - 1; pre >= 0; pre--) {
            for (int rest = pre + 1; rest <= n; rest++) {
                int ways = 0;
                for (int i = pre; i <= rest; i++) {
                    ways += dp[i][rest - i];
                }
                dp[pre][rest] = ways;
            }
        }
        return dp[1][n];
    }

    public static int dp2(int n) {
        int[][] dp = new int[n + 1][n + 1];
        for (int pre = 0; pre <= n; pre++) {
            dp[pre][0] = 1;
            dp[pre][pre] = 1;
        }
        for (int pre = n - 1; pre >= 0; pre--) {
            for (int rest = pre + 1; rest <= n; rest++) {
                dp[pre][rest] = dp[pre + 1][rest] + dp[pre][rest - pre];
            }
        }
        return dp[1][n];
    }

    public static void main(String[] args) {
        int n = 20;
        int count = splitNum(n);
        int ans1 = dp1(n);
        int ans2 = dp2(n);
        System.out.println(count);
        System.out.println(ans1);
        System.out.println(ans2);
    }
}
