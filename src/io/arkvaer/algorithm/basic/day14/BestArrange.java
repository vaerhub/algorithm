package io.arkvaer.algorithm.basic.day14;

import io.arkvaer.algorithm.utils.AlgUtil;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 最佳会议安排
 * 一些项目要占用一个会议室宣讲，会议室不能同时容纳两个项目的宣讲给你每一个项目开始的时间和结束的时间
 * 你来安排宣讲的日程，要求会议室进行的宣讲的场次最多
 * 返回最多的宣讲场次。
 */
public class BestArrange {
    public static class Program {
        public int start;
        public int end;

        public Program(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }


    /**
     * 暴力方式求解
     *
     * @param programs 所有会议的数组
     * @return 最大能安排的会议数
     */
    public static int bestArrange1(Program[] programs) {
        return process1(programs, 0, 0);
    }

    public static int process1(Program[] programs, int done, int timeline) {
        if (programs == null || programs.length == 0) {
            return done;
        }
        int max = done;
        for (int i = 0; i < programs.length; i++) {
            if (programs[i].start >= timeline) {
                Program[] leftover = removeIndexAndCopy(programs, i);
                max = Math.max(max, process1(leftover, done + 1, programs[i].end));
            }
        }
        return max;
    }

    public static Program[] removeIndexAndCopy(Program[] programs, int index) {
        if (programs == null || programs.length == 0) {
            return new Program[0];
        }
        Program[] copy = new Program[programs.length - 1];
        int copyIndex = 0;
        for (int i = 0; i < programs.length; i++) {
            if (i != index) {
                copy[copyIndex++] = programs[i];
            }
        }
        return copy;
    }

    /**
     * 使用贪心算法求解
     * 思路: 将会议数组按结束时间从小到大排序, 每次都选可以选择的结束时间最早的会议, 即可获得最大可以安排的会议数量
     * @param programs 所有会议
     * @return 最大可以安排的会议数量
     */
    public static int bestArrange2(Program[] programs) {
        if (programs == null || programs.length == 0) {
            return 0;
        }
        Arrays.sort(programs, new MyComparator());
        int done = 0;
        int timeline = 0;
        for (Program program : programs) {
            if (program.start >= timeline) {
                done++;
                timeline = program.end;
            }
        }
        return done;
    }

    public static class MyComparator implements Comparator<Program> {

        @Override
        public int compare(Program p1, Program p2) {
            return p1.end - p2.end;
        }
    }


    // region Test
    public static Program[] generatePrograms(int programSize, int timeMax) {
        Program[] ans = new Program[AlgUtil.random.nextInt(programSize + 1)];
        for (int i = 0; i < ans.length; i++) {
            int r1 = AlgUtil.random.nextInt(timeMax + 1);
            int r2 = AlgUtil.random.nextInt(timeMax + 1);
            if (r1 == r2) {
                ans[i] = new Program(r1, r1 + 1);
            } else {
                ans[i] = new Program(Math.min(r1, r2), Math.max(r1, r2));
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int programSize = 12;
        int timeMax = 20;
        int timeTimes = 1000000;
        for (int i = 0; i < timeTimes; i++) {
            Program[] programs = generatePrograms(programSize, timeMax);
            if (bestArrange1(programs) != bestArrange2(programs)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
    // endregion
}
