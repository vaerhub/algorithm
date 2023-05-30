package io.arkvaer.algorithm.utils;

import java.util.Arrays;
import java.util.Random;

/**
 * @author waver
 * @date 2023/5/28 下午12:05
 */
public class AlgUtil {
    public static final Random random = new Random();

    /**
     * 交换数组i位置和j位置的值
     *
     * @param arr 数组对象
     * @param i   i位置下标
     * @param j   j位置下标
     */
    public static void swap(int[] arr, int i, int j) {
        if (i != j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    /**
     * 判断当前数组是否不需要排序
     *
     * @param arr 需要排序的数组
     * @return 当前数组是否不需要排序
     */
    public static boolean notNeedSort(int[] arr) {
        return arr == null || arr.length < 2;
    }

    /**
     * 打印当前数组
     *
     * @param arr 数组对象
     */
    public static void print(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    public static int[] copyArr(int[] arr) {
        return Arrays.copyOf(arr, arr.length);
    }

    public static boolean isEqual(int[] arr1, int[] arr2) {
        return Arrays.equals(arr1, arr2);
    }

    public static int[] generateRandomArr(int maxSize, int maxValue) {
        int[] arr = new int[random.nextInt(maxSize + 1)];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(maxValue + 1);
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] ints = generateRandomArr(10, 5);
        print(ints);
    }

}
