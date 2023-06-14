package io.arkvaer.algorithm.primary.day3;

import io.arkvaer.algorithm.utils.AlgUtil;

/**
 * 二分法查找
 *
 * @author waver
 * @date 2023/5/30 下午5:37
 */
public class BinarySearch {
    public static int find(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int leftIndex = 0;
        int rightIndex = arr.length - 1;

        while (leftIndex <= rightIndex) {
            int mid = (leftIndex + rightIndex) / 2;
            if (arr[mid] == num) {
                return mid;
            } else if (arr[mid] > num) {
                rightIndex = mid - 1;
            } else {
                leftIndex = mid + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {1, 5, 6, 11, 88, 99, 100, 166, 222, 333};
        int index = find(arr, 88);
        AlgUtil.print(arr);

    }
}
