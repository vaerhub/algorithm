package io.arkvaer.algorithm.basic.day08;

import io.arkvaer.algorithm.utils.AlgUtil;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 基数排序
 */
public class RadixSort {

    public static void radixSort(int[] arr) {
        if (AlgUtil.notNeedSort(arr)) {
            return;
        }
        radixSort(arr, 0, arr.length - 1, maxBit(arr));
    }

    public static int maxBit(int[] arr) {
        int max = Integer.MIN_VALUE;

        for (int i : arr) {
            max = Math.max(max, i);
        }
        int result = 0;
        while (max != 0) {
            result++;
            max /= 10;
        }
        return result;

    }

    public static void radixSort(int[] arr, int l, int r, int digit) {
        final int radix = 10;
        int i = 0;
        int j = 0;
        int[] help = new int[r - l + 1];
        for (int d = 1; d <= digit; d++) {
            int[] count = new int[radix];
            for (i = l; i <= r; i++) {
                j = getDigit(arr[i], d);
                count[j]++;
            }
            // 将count[] 转为 前缀和数组
            for (i = 1; i < radix; i++) {
                count[i] = count[i] + count[i - 1];
            }
            // 从后向前便利, 模拟从桶中倒出
            for (i = r; i >= l; i--) {
                // 计算当前数字要放到第几个位置上
                j = getDigit(arr[i], d);
                help[count[j] - 1] = arr[i];
                count[j]--;
            }
            for (i = l, j = 0; i <= r; i++, j++) {
                arr[i] = help[j];
            }
        }

    }

    /**
     * 计算数字在第d位上的值
     *
     * @param num 要计算的数字
     * @param d   位数
     * @return 数字在第d位上的值
     */
    public static int getDigit(int num, int d) {
        return ((num / (int) Math.pow(10, (d - 1))) % 10);
    }

    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 10;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = AlgUtil.generateRandomArr(maxSize, maxValue);
            int[] arr2 = AlgUtil.copyArr(arr1);
            radixSort(arr1);
            Arrays.sort(arr2);
            if (!AlgUtil.isEqual(arr1, arr2)) {
                succeed = false;
                AlgUtil.printArray(arr1);
                AlgUtil.printArray(arr2);
                break;
            }
        }
        AlgUtil.console(succeed ? "Nice!" : "Fucking fucked!");
        int[] arr = AlgUtil.generateRandomArr(maxSize, maxValue);
        AlgUtil.printArray(arr);
        radixSort(arr);
        AlgUtil.printArray(arr);
    }

}
