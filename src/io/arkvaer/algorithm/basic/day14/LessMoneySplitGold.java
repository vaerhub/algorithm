package io.arkvaer.algorithm.basic.day14;

import io.arkvaer.algorithm.utils.AlgUtil;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * [分金问题]
 * 块金条切成两半，是需要花费和长度数值一样的铜板的。
 * 比如长度为20的金条，不管怎么切，都要花费20个板。
 * 一群人想整分整块金条，怎么分最省铜板?
 * 例如,给定数组 [10,20,30]代表一共三个人，整块金条长度为60，金条要分成10，20，30 三个部分.
 * 如果先把长度60的金条分成10和50，花费60: 再把长度50的金条分成20和30，花费50:一共花费110铜板
 * 但如果先把长度60的金条分成30和30，花费60:再把长度30金条分成10和20 花费30:一共花费90铜板
 * 输入一个数组，返回分割的最小代价。
 */
public class LessMoneySplitGold {
    public static int lessMoney1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return process(arr, 0);
    }

    /**
     * 等待合并的数都在arr里，pre之前的合并行为产生了多少总代价
     * arr中只剩一个数字的时候，停止合并，返回最小的总代价
     *
     * @param arr 等待合并的数
     * @param pre 之前的合并行为产生了多少总代价
     * @return 最小的总代价
     */
    public static int process(int[] arr, int pre) {
        if (arr.length == 1) {
            return pre;
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                ans = Math.min(ans, process(copyAndMergeTwo(arr, i, j), pre + arr[i] + arr[j]));
            }
        }
        return ans;
    }

    public static int[] copyAndMergeTwo(int[] arr, int i, int j) {
        int[] ans = new int[arr.length - 1];
        int ansi = 0;
        for (int arri = 0; arri < arr.length; arri++) {
            if (arri != i && arri != j) {
                ans[ansi++] = arr[arri];
            }
        }
        ans[ansi] = arr[i] + arr[j];
        return ans;
    }

    public static int lessMoney2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        Queue<Integer> queue = new PriorityQueue<>();
        for (int integer : arr) {
            queue.add(integer);
        }
        int cost = 0;
        int cur;
        int pre;
        int last;
        while (queue.size() > 1) {
            pre = queue.poll();
            Integer poll = queue.poll();
            last = poll == null ? 0 : poll;
            cur = pre + last;
            cost += cur;
            queue.add(cur);
        }
        return cost;
    }

    // region for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[AlgUtil.random.nextInt(maxSize + 1)];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = AlgUtil.random.nextInt(maxValue + 1);
        }
        return arr;
    }

    public static void main(String[] args) {
        int testTime = 100000;
        int maxSize = 6;
        int maxValue = 1000;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            if (lessMoney1(arr) != lessMoney2(arr)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
    // endregion
}
