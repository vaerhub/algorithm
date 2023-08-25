package io.arkvaer.algorithm.basic.day23;

import cn.hutool.core.date.StopWatch;

/**
 * N皇后问题:
 * 在N*N的棋盘上要摆N个皇后
 * 要求任何两个皇后不同行、不同列，也不在同一条斜线上
 * 给定一个整数N，返回N皇后的摆法有多少种
 * N=1，返回1
 * N=2或3，2皇后和3皇后问题无论怎么摆都不行，返回0
 * N=8，返回92
 */
public class NQueens {

    public static int totalNQueens(int n) {
        if (n < 1) {
            return 0;
        }
        int[] record = new int[n];
        return process(record, n, 0);
    }

    public static int process(int[] record, int n, int index) {
        if (index == n) {
            return 1;
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (isCanSet(record, index, i)) {
                record[index] = i;
                res += process(record, n, index + 1);
            }
        }
        return res;
    }


    public static boolean isCanSet(int[] record, int index, int j) {
        for (int i = 0; i < index; i++) {
            if (record[i] == j || Math.abs(record[i] - j) == Math.abs(index - i)) {
                return false;
            }
        }
        return true;
    }


    public static int totalNQueens1(int n) {
        if (n < 1 || n > 32) {
            return 0;
        }
        // 获得右侧n个值为1的值 例如 n 为 3时  1<<3 得到 0000000001000 -1后得到 0000000000111
        int limit = n == 32 ? -1 : (1 << n) - 1;
        return process1(limit, 0, 0, 0);
    }

    public static int process1(int limit, int colLimit, int leftLimit, int rightLimit) {
        if (limit == colLimit) {
            return 1;
        }
        // 求当前可以放皇后的位置
        int pos = limit & ~(colLimit | leftLimit | rightLimit);
        // 求出最右侧1的值
        int mostRightOne;
        int result = 0;
        while (pos != 0) {
            mostRightOne = pos & (~pos + 1);
            pos -= mostRightOne;
            result += process1(limit, colLimit | mostRightOne, (leftLimit | mostRightOne) << 1, (rightLimit | mostRightOne) >>> 1);
        }
        return result;
    }

    public static void main(String[] args) {
        int times = 2;
        int n = 9;
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("数组计算");
        for (int i = 0; i < times; i++) {
            totalNQueens(n);
        }
        stopWatch.stop();
        System.out.println(stopWatch.getLastTaskInfo());
        stopWatch.start("位运算");
        stopWatch.getTotalTimeMillis();
        for (int i = 0; i < times; i++) {
            totalNQueens1(n);
        }
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
    }
}
