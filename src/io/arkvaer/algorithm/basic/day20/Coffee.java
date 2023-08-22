package io.arkvaer.algorithm.basic.day20;


import io.arkvaer.algorithm.utils.AlgUtil;

import javax.crypto.Mac;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 题目
 * 数组arr代表每一个咖啡机冲一杯咖啡的时间，每个咖啡机只能串行的制造咖啡。
 * 现在有n个人需要喝咖啡，只能用咖啡机来制造咖啡。
 * 认为每个人喝咖啡的时间非常短，冲好的时间即是喝完的时间。
 * 每个人喝完之后咖啡杯可以选择洗或者自然挥发干净，只有一台洗咖啡杯的机器，只能串行的洗咖啡杯。
 * 洗杯子的机器洗完一个杯子时间为a，任何一个杯子自然挥发干净的时间为b。
 * 四个参数：arr, n, a, b
 * 假设时间点从0开始，返回所有人喝完咖啡并洗完咖啡杯的全部过程结束后，至少来到什么时间点。
 *
 * @author waver
 * @date 2023/8/20 13:50
 */
public class Coffee {
    /**
     * 暴力实现方法
     *
     * @param machine       代表每一个咖啡机冲一杯咖啡的时间，每个咖啡机只能串行的制造咖啡。
     * @param n             n个人需要喝咖啡，只能用咖啡机来制造咖啡
     * @param washTime      洗杯子的机器洗完一个杯子时间
     * @param evaporateTime 任何一个杯子自然挥发干净的时间为
     * @return 最短使所有杯子干净的时间
     */
    public static int minTime(int[] machine, int n, int washTime, int evaporateTime) {
        int[] times = new int[machine.length];
        int[] drinks = new int[n];
        return forceMake(machine, times, 0, drinks, washTime, evaporateTime);
    }

    private static int forceMake(int[] machine, int[] times, int index, int[] drinks, int washTime, int evaporateTime) {
        if (index == drinks.length) {
            int[] drinkSorted = Arrays.copyOf(drinks, index);
            Arrays.sort(drinkSorted);
            return forceWash(drinkSorted, washTime, evaporateTime, 0, 0, 0);
        }
        int time = Integer.MAX_VALUE;
        for (int i = 0; i < machine.length; i++) {
            int work = machine[i];
            int pre = times[i];
            drinks[index] = pre + work;
            times[i] = pre + work;
            time = Math.min(time, forceMake(machine, times, index + 1, drinks, washTime, evaporateTime));
            drinks[index] = 0;
            times[i] = pre;
        }
        return time;
    }

    private static int forceWash(int[] drinks, int washTime, int evaporateTime, int index, int washLine, int time) {
        if (index == drinks.length) {
            return time;
        }
        int wash = Math.max(drinks[index], washLine) + washTime;
        int ans1 = forceWash(drinks, washTime, evaporateTime, index + 1, wash, Math.max(wash, time));

        int dry = drinks[index] + evaporateTime;
        int ans2 = forceWash((drinks), washTime, evaporateTime, index + 1, washLine, Math.max(dry, time));
        return Math.min(ans1, ans2);
    }


    public static class Machine {
        public int timePoint;
        public int workTime;

        public Machine(int timePoint, int workTime) {
            this.timePoint = timePoint;
            this.workTime = workTime;
        }
    }

    public static class MachineComparator implements Comparator<Machine> {
        @Override
        public int compare(Machine o1, Machine o2) {
            return (o1.timePoint + o1.workTime) - (o2.timePoint + o2.workTime);
        }
    }

    public static int minTime1(int[] arr, int n, int a, int b) {
        PriorityQueue<Machine> heap = new PriorityQueue<>(new MachineComparator());
        for (int j : arr) {
            heap.add(new Machine(0, j));
        }

        int[] drinks = new int[n];
        for (int i = 0; i < n; i++) {
            Machine cur = heap.poll();
            cur.timePoint += cur.workTime;
            drinks[i] = cur.timePoint;
            heap.add(cur);
        }
        return bestTime(drinks, a, b, 0, 0);
    }

    /**
     * drinks 所有杯子可以开始洗的时间
     * wash 单杯洗干净的时间（串行）
     * air 挥发干净的时间(并行)
     * free 洗的机器什么时候可用
     * drinks[index.....]都变干净，最早的结束时间（返回）
     */
    public static int bestTime(int[] drinks, int wash, int air, int index, int free) {
        if (index == drinks.length) {
            return 0;
        }
        int washClean = Math.max(drinks[index], free) + wash;
        int dryClean = bestTime(drinks, wash, air, index + 1, washClean);
        int p1 = Math.max(washClean, dryClean);

        int washClean1 = drinks[index] + air;
        int dryClean1 = bestTime(drinks, wash, air, index + 1, free);
        int p2 = Math.max(washClean1, dryClean1);
        return Math.min(p1, p2);
    }



    public static int minTime2(int[] arr, int n, int a, int b) {
        PriorityQueue<Machine> heap = new PriorityQueue<>(new MachineComparator());
        for (int j : arr) {
            heap.add(new Machine(0, j));
        }
        int[] drinks = new int[n];
        for (int i = 0; i < n; i++) {
            Machine cur = heap.poll();
            if (cur != null) {
                cur.timePoint += cur.workTime;
                drinks[i] = cur.timePoint;
                heap.add(cur);
            }
        }
        return bestTimeDp(drinks, a, b);
    }

    public static int bestTimeDp(int[] drinks, int wash, int air) {
        int len = drinks.length;
        int maxFree = 0;
        for (int drink : drinks) {
            maxFree = Math.max(maxFree, drink) + wash;
        }
        int[][] dp = new int[len + 1][maxFree + 1];
        for (int index = len - 1; index >= 0; index--) {
            for (int free = 0; free < maxFree; free++) {
                int washClean1 = Math.max(drinks[index], free) + wash;
                if (washClean1 > maxFree) {
                    break;
                }
                int dryClean1 = dp[index + 1][washClean1];
                int p1 = Math.max(washClean1, dryClean1);

                int washClean2 = drinks[index] + air;
                int dryClean2 = dp[index + 1][free];
                int p2 = Math.max(washClean2, dryClean2);
                dp[index][free] = Math.min(p1, p2);
            }
        }
        return dp[0][0];
    }


    public static int[] randomArray(int len, int max) {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * max) + 1;
        }
        return arr;
    }
    public static void main(String[] args) {
        int len = 10;
        int max = 10;
        int testTime = 10;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(len, max);
            int n = AlgUtil.random.nextInt(7) + 1;
            int a = AlgUtil.random.nextInt(7) + 1;
            int b = AlgUtil.random.nextInt(10) + 1;
            int ans1 = minTime(arr, n, a, b);
            int ans2 = minTime1(arr, n, a, b);
            int ans3 = minTime2(arr, n, a, b);
            if (ans1 != ans2 || ans2 != ans3) {
                AlgUtil.printArray(arr);
                System.out.println("n : " + n);
                System.out.println("a : " + a);
                System.out.println("b : " + b);
                System.out.println(ans1 + " , " + ans2 + " , " + ans3);
                System.out.println("===============");
                break;
            }
        }
        System.out.println("测试结束");
    }
}
