package io.arkvaer.algorithm.primary.day1;

import io.arkvaer.algorithm.utils.AlgUtil;

/**
 * 将当前位置的值与前面的已经排序好的数值对比, 如果当前数比前面的数小, 则交换
 *
 * @author waver
 * @date 2023/5/28 下午11:07
 */
public class InsertSort {

    public static void insertSort(int[] arr) {
        if (AlgUtil.notNeedSort(arr)) {
            return;
        }
        for (int end = 1; end < arr.length; end++) {
            for (int pre = end - 1; pre >= 0 && arr[pre] > arr[pre + 1]; pre--) {
                AlgUtil.swap(arr, pre, pre + 1);
            }
        }
    }


    public static void insertSort2(int[] arr) {
        if (AlgUtil.notNeedSort(arr)) {
            return;
        }
        for (int end = 1; end < arr.length; end++) {
            int currentIndex = end;
            while (currentIndex - 1 >= 0 && arr[currentIndex - 1] > arr[currentIndex]) {
                AlgUtil.swap(arr, currentIndex, currentIndex - 1);
                currentIndex--;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {9, 8, 7, 6, 1, 2, 5, 4, 0, 7, 6, 3, 5, 4};
        AlgUtil.print(arr);
        insertSort(arr);
        AlgUtil.print(arr);
    }
}
