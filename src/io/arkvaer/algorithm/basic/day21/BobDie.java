package io.arkvaer.algorithm.basic.day21;

/**
 * 给定5个参数，m，n，row，col，k表示在N*M的区域上，
 * 醉汉Bob初始在(row, col)位置
 * Bob一共要迈出k步，且每步都会等概率向上下左右四个方向走一个单位
 * 任何时候Bob只要离开N*M的区域，就直接死亡
 * 返回k步之后，Bob还在N*M的区域的概率
 */
public class BobDie {
    public static double liveProbability1(int m, int n, int row, int col, int k) {
        double ans = process(m, n, row, col, k);
        return ans / Math.pow(4, k);

    }

    public static long process(int m, int n, int row, int col, int rest) {
        if (row < 0 || col < 0 || row >= m || col >= n) {
            return 0;
        }
        if (rest == 0) {
            return 1;
        }
        long ways = 0;
        ways += process(m, n, row + 1, col, rest - 1);
        ways += process(m, n, row - 1, col, rest - 1);
        ways += process(m, n, row, col + 1, rest - 1);
        ways += process(m, n, row, col - 1, rest - 1);
        return ways;
    }


    public static double liveProbability2(int m, int n, int row, int col, int k) {
        long[][][] dp = new long[m][n][k + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j][0] = 1;
            }
        }
        for (int rest = 1; rest <= k; rest++) {
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    long ways = pick(dp, m, n, i + 1, j, rest - 1);
                    ways += pick(dp, m, n, i - 1, j, rest - 1);
                    ways += pick(dp, m, n, i, j + 1, rest - 1);
                    ways += pick(dp, m, n, i, j - 1, rest - 1);
                    dp[i][j][rest] = ways;
                }
            }
        }
        return (double) dp[row][col][k] / Math.pow(4, k);
    }

    public static long pick(long[][][] dp, int N, int M, int r, int c, int rest) {
        if (r < 0 || r == N || c < 0 || c == M) {
            return 0;
        }
        return dp[r][c][rest];
    }

    public static void main(String[] args) {
        System.out.println(liveProbability1(6, 6, 3, 3, 6));
        System.out.println(liveProbability2(6, 6, 3, 3, 6));

    }
}
