package io.arkvaer.algorithm.day3;

import io.arkvaer.algorithm.utils.AlgUtil;

import java.util.Arrays;

/**
 * 通过二分法查找 >= num 的值的位置
 *
 * @author waver
 * @date 2023/6/4 下午8:20
 */
public class BinarySearchNearLeft {

    /**
     * 查找arr中>=num 最左的位置
     *
     * @param arr 数组
     * @param num 寻找的num
     * @return >=num 最左的位置
     */
    public int searchNearLeft(int[] arr, int num) {
        int index = -1;
        int length = arr.length;
        int left = 0;
        int right = length;
        for (int i = 0; i < length; i++) {
            if (left <= right) {
                int mid = (left + right) / 2;
                if (arr[mid] >= num) {
                    index = num;
                    right = mid;
                } else {
                    left = mid;
                }
            }
        }
        return index;
    }



    public static void main(String[] args) {
        int[] ints = AlgUtil.generateRandomArr(1000, 50);
        AlgUtil.print(ints);
    }
}
