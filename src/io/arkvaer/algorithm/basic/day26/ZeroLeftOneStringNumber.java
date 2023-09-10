package io.arkvaer.algorithm.basic.day26;

/**
 * 给定一个数N，想象只由0和1两种字符，组成的所有长度为N的字符串,
 * 如果某个字符串任何0字符的左边都有1紧挨着, 认为这个字符串达标,
 * 返回有多少达标的字符串
 */
public class ZeroLeftOneStringNumber {
    public static int getCount(int n) {
        if (n < 1) {
            return 0;
        }
        if (n <= 2) {
            return 1;
        }
        return process(1, n);

    }

    public static int process(int index, int n) {
        if (index == n - 1) {
            return 2;
        }
        if (index == n) {
            return 1;
        }
        return process(index + 1, n) + process(index + 2, n);
    }

    public static int getCount1(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int pre = 1;
        int cur = 1;
        int tmp;
        for (int i = 2; i <= n; i++) {
            tmp = cur;
            cur += pre;
            pre = tmp;
        }
        return cur;
    }

    public static int getCount2(int n) {
        if (n < 1) {
            return 0;
        }
        if (n < 3) {
            return n;
        }
        int[][] base = {{1, 1}, {1, 0}};
        int[][] result = matrixPower(base, n - 2);
        return 2 * result[0][0] + result[1][0];
    }


    public static int[][] matrixPower(int[][] base, int p) {
        int[][] res = new int[base.length][base[0].length];
        for (int i = 0; i < res.length; i++) {
            res[i][i] = 1;
        }
        int[][] tmp = base;
        for (; p != 0; p >>= 1) {
            if ((p & 1) == 1) {
                res = multiMatrix(res, tmp);
            }
            tmp = multiMatrix(tmp, tmp);
        }
        return res;
    }


    public static int[][] multiMatrix(int[][] a, int[][] b) {
        int[][] res = new int[a.length][b[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                for (int k = 0; k < a[0].length; k++) {
                    res[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return res;

    }


    public static void main(String[] args) {
        int n = 10;
        System.out.println(getCount(n));
        System.out.println(getCount1(n));
        System.out.println(getCount2(n));
    }
}
