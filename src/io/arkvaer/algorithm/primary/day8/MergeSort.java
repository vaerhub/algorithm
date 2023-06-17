package io.arkvaer.algorithm.primary.day8;

import io.arkvaer.algorithm.utils.AlgUtil;

import java.util.Arrays;

/**
 * 归并排序
 *
 * @author waver
 * @date 2023/6/16 17:20
 */
public class MergeSort {

    public void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }


    public void process(int[] arr, int left, int right) {
        if (left == right) {
            return;
        }
        int mid = ((left + right) >> 1);
        process(arr, left, mid);
        process(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    public void merge(int[] arr, int left, int mid, int right) {
        int[] help = new int[right - left + 1];
        int index = 0;
        int p1 = left;
        int p2 = mid + 1;
        while (p1 <= mid && p2 <= right) {
            help[index++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= mid) {
            help[index++] = arr[p1++];
        }
        while (p2 <= right) {
            help[index++] = arr[p2++];
        }
        System.arraycopy(help, 0, arr, left, help.length);
    }


    /**
     * 归并排序非递归方式
     *
     * @param arr 需要排序的数组
     */
    public void mergeSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // 每次的步长
        int step = 1;
        int len = arr.length;
        while (step < len) {
            int left = 0;
            while (left < len) {
                int mid = 0;
                // 保证 left + step 不会溢出
                if (len - left >= step) {
                    mid = left + step - 1;
                } else {
                    mid = len - 1;
                }
                if (mid == len - 1) {
                    break;
                }
                int right = 0;
                if (len - mid - 1 >= step) {
                    // right = mid + 1 + step -1
                    right = mid + step;
                } else {
                    right = len - 1;
                }
                merge(arr, left, mid, right);
                if (right == len - 1) {
                    break;
                } else {
                    left = right + 1;
                }
            }
            //
            if (step > len / 2) {
                break;
            }
            step <<= 1;
        }
    }


    // for test
    public static void main(String[] args) {
        MergeSort mg = new MergeSort();
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        AlgUtil.console("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = AlgUtil.generateRandomArr(maxSize, maxValue);
            int[] arr2 = AlgUtil.copyArr(arr1);
            Arrays.sort(arr2);
//            mg.mergeSort(arr1);
            mg.mergeSort1(arr1);
//            mergeSort2(arr2);
            if (!AlgUtil.isEqual(arr1, arr2)) {
                AlgUtil.console("出错了！");
                AlgUtil.print(arr1);
                AlgUtil.print(arr2);
                break;
            }
        }
        AlgUtil.console("测试结束");
    }


}
