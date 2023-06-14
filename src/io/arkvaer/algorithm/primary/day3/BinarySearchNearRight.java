package io.arkvaer.algorithm.primary.day3;

import io.arkvaer.algorithm.utils.AlgUtil;

/**
 * 通过二分法查找 >= num 的值的位置
 *
 * @author waver
 * @date 2023/6/4 下午8:20
 */
public class BinarySearchNearRight {

    /**
     * 查找arr中>=num 最左的位置
     *
     * @param arr 数组
     * @param num 寻找的num
     * @return >=num 最左的位置
     */
    public static int searchNearLeft(int[] arr, int num) {
        int index = -1;
        int length = arr.length;
        int left = 0;
        int right = length;
        for (int i = 0; i < length; i++) {
            if (left <= right) {
                int mid = (left + right) / 2;
                if (arr[mid] <= num) {
                    left = mid;
                    index = mid;
                } else {
                    right = mid;
                }
            }
        }
        return index;
    }



    public static void main(String[] args) {
        int[] ints = {0,1,2,3,4,4,4,4,4,5,5,5,6,10};
        AlgUtil.console(ints.length);
        int index = searchNearLeft(ints, 1);
        AlgUtil.print(ints);
        AlgUtil.printIndex(ints.length);
        AlgUtil.console(index);
    }
}
