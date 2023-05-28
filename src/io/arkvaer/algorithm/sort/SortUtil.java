package io.arkvaer.algorithm.sort;

import java.util.Arrays;

/**
 * @author waver
 * @date 2023/5/28 下午12:05
 */
public class SortUtil {
    public static void swap(int[] arr, int i, int j) {
        if (i != j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    public static boolean notNeedSort(int[] arr) {
        return arr == null || arr.length < 2;
    }

    public static void print(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }
}
