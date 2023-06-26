package io.arkvaer.algorithm.basic.day04;

import io.arkvaer.algorithm.utils.AlgUtil;

import java.util.Arrays;

/**
 * 归并排序
 *
 * @author waver
 */
public class MergeSort {

    /**
     * 通过递归方法实现归并排序
     *
     * @param arr 需要排序的数组
     */
    public static void loopSort(int[] arr) {
        if (AlgUtil.notNeedSort(arr)) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    public static void process(int[] arr, int l, int r) {
        if (l == r) {
            return;
        }
        int mid = l + ((r - l) >> 1);
        process(arr, l, mid);
        process(arr, mid + 1, r);
        merge(arr, l, mid, r);
    }

    public static void merge(int[] arr, int l, int mid, int r) {
        int leftIndex = l;
        int rightIndex = mid + 1;
        int[] help = new int[r - l + 1];
        int index = 0;
        while (leftIndex <= mid && rightIndex <= r) {
            help[index++] = arr[leftIndex] <= arr[rightIndex] ? arr[leftIndex++] : arr[rightIndex++];
        }
        while (leftIndex <= mid) {
            help[index++] = arr[leftIndex++];
        }
        while (rightIndex <= r) {
            help[index++] = arr[rightIndex++];
        }

        System.arraycopy(help, 0, arr, l, help.length);

    }


    public static void stepSort(int[] arr) {
        if (AlgUtil.notNeedSort(arr)) {
            return;
        }

        int length = arr.length;
        int step = 1;
        while (step < length) {
            int l = 0;
            while (l < length) {
                if (step >= length - l) {
                    break;
                }
                int mid = l + step - 1;
                int r = mid + Math.min(step, length - mid - 1);
                merge(arr, l, mid, r);
                l = r + 1;
            }
            if (step > length / 2) {
                break;
            }
            step <<= 1;
        }
    }


    public static void main(String[] args) {
        int testTime = 100;
        int maxSize = 10000;
        int maxRange = 100;
        AlgUtil.console("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = AlgUtil.generateRandomArr(maxSize, maxRange);
            int[] arr1 = AlgUtil.copyArr(arr);
            int[] arr2 = AlgUtil.copyArr(arr);
            int[] arr3 = AlgUtil.copyArr(arr);
            Arrays.sort(arr1);
            loopSort(arr2);
            stepSort(arr3);
            for (int j = 0; j < arr.length; j++) {
                if (arr1[j] != arr3[j]) {
                    AlgUtil.console(arr1[j], arr3[j]);
                }
            }
        }
        AlgUtil.console("测试完成");
    }
}
