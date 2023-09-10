package io.arkvaer.algorithm.basic.day26;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * 求斐波那契数列矩阵乘法的方法
 * 1)斐波那契数列的线性求解(O(N))的方式非常好理解
 * 2)同时利用线性代数，也可以改写出另一种表示:
 * |F(N),F(N-1)| = F(2),F(1)* 某个二阶矩阵的N-2次方
 * 3)求出这个二阶矩阵，进而最快求出这个二阶矩阵的N-2次方
 */

public class FibonacciProblem {


    /**
     * 求斐波那契数列第n项的值 [递归实现]
     *
     * @return 第n项的值
     */
    public static int f1(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        return f1(n - 1) + f1(n - 2);
    }

    /**
     * 求斐波那契数列第n项的值 [递归实现]
     *
     * @return 第n项的值
     */
    public static BigDecimal f2(int n) {
        if (n == 1 || n == 2) {
            return BigDecimal.ONE;
        }
        BigDecimal cur = new BigDecimal(1);

        BigDecimal pre = new BigDecimal(1);
        BigDecimal tmp;
        for (int i = 0; i < n - 2; i++) {
            tmp = cur;
            cur = pre.add(cur);
            pre = tmp;

        }
        return cur;
    }

    public static int f3(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        int[][] base = {
                {1, 1},
                {1, 0}};
        int[][] res = matrixPower(base, n - 2);
        // |F(n), F(n-1)|  = |F(2), F(1)|* [[a, b][c, t]]
        // F(n) = F(2) * a + F(1) * c
        // 因为 F(2) = 1, 故 F(n) = 1* res[0][0] + res[1][0]
        return res[0][0] + res[1][0];
    }

    public static int[][] matrixPower(int[][] base, int n) {
        int[][] result = new int[base.length][base[0].length];
        // 生成单位矩阵
        for (int i = 0; i < result.length; i++) {
            result[i][i] = 1;
        }
        int[][] t = base;
        for (; n != 0; n >>= 1) {
            if ((n & 1) == 1) {
                result = multiplyMatrix(result, t);
            }
            t = multiplyMatrix(t, t);
        }
        return result;
    }

    public static int[][] multiplyMatrix(int[][] base, int[][] p) {
        int[][] result = new int[base.length][p[0].length];
        for (int i = 0; i < base.length; i++) {
            for (int j = 0; j < p[0].length; j++) {
                for (int k = 0; k < base[0].length; k++) {
                    result[i][j] += base[i][k] * p[k][j];
                }
            }
        }
        return result;
    }

    /**
     * <p>
     * 第一年农场有1只成熟的母牛A，往后的每年
     * 1)每一只成熟的母牛都会生一只母牛
     * 2)每一只新出生的母牛都在出生的第三年成熟
     * 3)每一只母牛永远不会死返回N年后牛的数量
     * </p>
     * <h2>解题思路</h2>
     * <p>
     * 年份:  1  2  3  4  5  6   7   8
     * 数量:  1  2  3  4  6  9  13   19
     * |F(n), F(n-1), F(n-2)| = |F(3), F(2), F(1)| * {{a, b, c},{d, e, f},{h, i, g}}^(n-3)
     * -------------------------------------------------------------------------------
     * 以第3年作为第一项的下列等式:
     * |9, 6, 4| = |6, 4, 3| * base[][]
     * A:  6a + 4d + 3h = 9
     * B:  6b + 4e + 3i = 6
     * C:  6c + 4f + 3g = 4
     * 以第2年作为第一项的下列等式:
     * |6, 4, 3| = |4, 3, 2| * base[][]
     * D:  4a + 3d + 2h = 6
     * E:  4b + 3e + 2i = 4
     * F:  4c + 3f + 2g = 3
     * 以第1年作为第一项的下列等式:
     * |4, 3, 2| = |3, 2, 1| * base[][]
     * G:  3a + 2d + h = 4
     * H:  3b + 2e + i = 3
     * I:  3c + 2f + g = 2
     * 根据以上等式求 base[][]
     * {
     * {a, b, c},
     * {d, e, f},
     * {h, i, g}
     * }
     * A - 2G => (6a + 4d + 3h) - (6a + 4d + 2h) => h = 9 - (2 * 4) = 1 ==> [h = 1]
     * B - 2H => (6b + 4e + 3i) - (6b + 4e + 2i) => i = 6 - (2 * 3) = 0 ==> [i = 0]
     * C - 2I => (6c + 4f + 3g) - (6c + 4f + 2g) => g = 4 - (2 * 2) = 0 ==> [g = 0]
     * =============================================================================
     * 根据以上条件和等式继续可得
     * D - G => (4a + 3d + 2) - (3a + 2d + 1) = 6 - 4 => a + d + 1 = 2 ==> a + d = 1
     * E - H => (4b + 3e + 0) - (3b + 2e + 0) = 4 - 3 => b + e + 0 = 1 ==> b + e = 1
     * F - I => (4c + 3f + 0) - (3c + 2f + 0) = 3 - 2 => c + f + 0 = 1 ==> c + f = 1
     * ------------------------------------------------------------------------------
     * 将以上条件带入等式 G H I 可得
     * 根据 {[G] & [a + d = 1] & [h = 1] } => a + 2(a + d) + h = 4 => a + 2 + 1 = 4 ==> [a = 1] [d = 0]
     * 根据 {[H] & [b + e = 1] & [i = 0] } => b + 2(b + e) + i = 3 => b + 2 + 0 = 3 ==> [b = 1] [e = 0]
     * 根据 {[I] & [c + f = 1] & [g = 0] } => c + 2(c + f) + g = 2 => c + 2 + 0 = 2 ==> [c = 0] [f = 1]
     * ==============================================================================
     * 得出:
     * base = {
     * {1, 1, 0},
     * {0, 0, 1},
     * {1, 0, 0}
     * }
     * </p>
     */

    public static int c3(int n) {
        if (n < 1) {
            return 0;
        }
        if (n < 4) {
            return n;
        }
        int[][] base = {
                {1, 1, 0},
                {0, 0, 1},
                {1, 0, 0}
        };
        int[][] res = matrixPower(base, n - 3);
        // |F(n), F(n-1), F(n-2)| * |3, 2, 1| * res =>  F(n) = 3 * res[0][0] + 2 * res[1][0] + res[2][0];
        return 3 * res[0][0] + 2 * res[1][0] + res[2][0];
    }


    public static void main(String[] args) {
        int n = 10;
        //1,1,2,3,5
        //System.out.println(f1(n));
        System.out.println(f2(n));
        System.out.println(f3(n));
        int year = 8;
        System.out.println(c3(year));

        int[][] a = {{1,2},{3,4},{5,6}};
        int[][] b = {{1,2,3},{4,5,6}};
        int[][] c = multiplyMatrix(a, b);
        int[][] d = multi(a, b);
        System.out.println(Arrays.deepToString(c));
        System.out.println(Arrays.deepToString(d));
    }

    public static int[][] multi(int[][] a, int[][] b) {
        int[][] result = new int[a.length][b[0].length];
        for (int i = 0; i <a.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                for (int k = 0; k < a[0].length; k++) {
                    result[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return result;
    }
}
