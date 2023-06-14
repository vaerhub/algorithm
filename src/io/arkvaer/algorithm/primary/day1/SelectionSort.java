package io.arkvaer.algorithm.primary.day1;

import io.arkvaer.algorithm.utils.AlgUtil;

/**
 * 选择排序
 * 主要逻辑, 每次找到最小值的下标, 与当前下标交换位置
 *
 * @author waver
 * @date 2023/5/28 下午12:03
 */
public class SelectionSort {
    public static void selectionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            int minValIndex = i;
            // 找到当前未排序数组中最小值的下标
            for (int j = i + 1; j < arr.length; j++) {
                minValIndex = arr[j] < arr[minValIndex] ? j : minValIndex;
            }
            AlgUtil.swap(arr, minValIndex, i);
        }

    }

    public static void main(String[] args) {
        int[] arr = {4, 7, 9, 0, 2, 7, 4, 9, 4, 6, 8};
        selectionSort(arr);
        AlgUtil.print(arr);
    }
}
