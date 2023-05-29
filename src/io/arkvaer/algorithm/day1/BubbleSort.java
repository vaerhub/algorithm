package io.arkvaer.algorithm.day1;

import io.arkvaer.algorithm.utils.AlgUtil;

/**
 * 实现逻辑: arr[i] 与 arr[i+1] 对比, 若arr[i] > arr[i + 1], 则将两个值交换
 *
 * @author waver
 * @date 2023/5/28 下午10:14
 */
public class BubbleSort {
    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int compCount = 0;
        int swapCount = 0;
        for (int i = arr.length - 1; i >= 0; i--) {
            for (int j = 1; j < i; j++) {
                compCount++;
                if (arr[j-1] > arr[j]) {
                    swapCount++;
                    AlgUtil.swap(arr, j-1, j);
                }
            }
        }
        System.out.println("对比次数: "+ compCount);
        System.out.println("交换次数: " + swapCount);

    }

    public static void bubbleSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int compCount = 0;
        int swapCount = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                compCount++;
                if (arr[i] > arr[j]) {
                    AlgUtil.swap(arr, i, j);
                    swapCount++;
                }
            }
        }
        System.out.println("对比次数: "+ compCount);
        System.out.println("交换次数: " + swapCount);
    }

    public static void main(String[] args) {
        int[] arr = {9, 8, 7, 6, 1, 2, 5, 4, 0, 7, 6, 3,5,4};
        AlgUtil.print(arr);
        bubbleSort(arr);
        AlgUtil.print(arr);
    }

}
