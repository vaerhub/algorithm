package io.arkvaer.algorithm.utils;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.util.logging.Logger;

/**
 * @author waver
 * @date 2023/5/28 下午12:05
 */
public class AlgUtil {
    public static Logger global = Logger.getGlobal();
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
    public static void printArray(int[] arr) {
        if (arr == null) {
            throw new AlgException("Array is null");
        }
        global.info(Arrays.toString(arr));
    }

    public static void console(Object ...obj) {
        if (Objects.isNull(obj)) {
            global.info("null");
        } else {
            global.info(Arrays.toString(obj));
        }
    }

    public static void printIndex(int length) {
        int[] indexArr = new int[length];
        for (int i = 0; i < length; i++) {
            indexArr[i] = i;
        }
        printArray(indexArr);
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

    /**
     * 产生递增的随机数组
     *
     * @param maxSize  最大长度
     * @param maxValue 最大值
     * @return 生成的随即数组
     */
    public static int[] generateSortedRandomArr(int maxSize, int maxValue) {
        int count = 0;
        int[] arr = new int[random.nextInt(maxSize + 1)];
        int length = arr.length;
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(maxValue + 1);
            count++;
            while (i > 0 && arr[i - 1] > arr[i]) {
                arr[i] = random.nextInt(maxValue + 1);
            }
        }
        if (count < arr.length) {
            throw new AlgException("没有填充满数组");
        }
        if (length > 0 && arr[0] == arr[length - 1]) {
            AlgUtil.printArray(arr);
            throw new AlgException("数组中仅有一个值");
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] ints = generateSortedRandomArr(15, 3);
        printArray(ints);
    }

}
