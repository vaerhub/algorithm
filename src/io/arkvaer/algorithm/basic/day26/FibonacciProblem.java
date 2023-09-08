package io.arkvaer.algorithm.basic.day26;

import java.math.BigDecimal;

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
            cur = pre.add(cur) ;
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
                {1,1},
                {1,0}};
        int[][] res = matrixPower(base, n - 2);
        // |F(n), F(n-1)|  = |F(2), F(1)|* [[a, b][c, t]]
        // F(n) = F(2) * a + F(2) * c
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
        int[][] result = new int[base.length][base[0].length];
        for (int i = 0; i < base.length; i++) {
            for (int j = 0; j < base[0].length; j++) {
                for (int k = 0; k < p[0].length; k++) {
                    result[i][j] += base[i][k] * p[k][j];
                }
            }
        }
        return result;
    }

    /**
     * 第一年农场有1只成熟的母牛A，往后的每年
     * 1)每一只成熟的母牛都会生一只母牛
     * 2)每一只新出生的母牛都在出生的第三年成熟
     * 3)每一只母牛永远不会死返回N年后牛的数量
     */


    public static void main(String[] args) {
        int n = 100;
        //1,1,2,3,5
        //System.out.println(f1(n));
        System.out.println(f2(n));
        System.out.println(f3(n));
    }
}
