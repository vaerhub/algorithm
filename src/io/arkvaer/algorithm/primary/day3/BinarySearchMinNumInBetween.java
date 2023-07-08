package io.arkvaer.algorithm.primary.day3;

import io.arkvaer.algorithm.utils.AlgUtil;

/**
 * 查找局部最小值
 *
 * @author waver
 * @date 2023/6/4 下午8:20
 */
public class BinarySearchMinNumInBetween {

    /**
     * 查找arr中>=num 最左的位置
     *
     * @param arr 数组
     * @return 局部最小值下标
     */
    public static int searchMinNum(int[] arr) {
        int length = arr.length;
        int left = 0;
        int right = length - 1;
        if (length == 1) {
            return 0;
        }
        if (AlgUtil.notNeedSort(arr)) {
            return -1;
        }
        if (arr[0] < arr[1]) {
            return 0;
        }
        if (arr[length - 2] > arr[length - 1]) {
            return length - 1;
        }
        while (left < right - 1) {
            int mid = (left + right) / 2;
            if (arr[mid - 1] > arr[mid] && arr[mid + 1] > arr[mid]) {
                return mid;
            } else {
                if (arr[mid - 1] < arr[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        return arr[left] < arr[right] ? left : right;
    }


    // 生成随机数组，且相邻数不相等
    public static int[] randomArray(int maxLen, int maxValue) {
        int len = (int) (Math.random() * maxLen);
        int[] arr = new int[len];
        if (len > 0) {
            arr[0] = (int) (Math.random() * maxValue);
            for (int i = 1; i < len; i++) {
                do {
                    arr[i] = (int) (Math.random() * maxValue);
                } while (arr[i] == arr[i - 1]);
            }
        }
        return arr;
    }

    // 也用于测试
    public static boolean check(int[] arr, int minIndex) {
        if (arr.length == 0 || minIndex == -1) {
            return minIndex == -1;
        }
        int left = minIndex - 1;
        int right = minIndex + 1;
        boolean leftBigger = left < 0 || arr[left] > arr[minIndex];
        if (right < 0 || minIndex < 0){
            System.out.println(right);
            System.out.println(minIndex);;
        }
        boolean rightBigger = right >= arr.length || arr[right] > arr[minIndex];
        return leftBigger && rightBigger;
    }


    public static void main(String[] args) {
        int maxLen = 10;
        int maxValue = 200;
        int testTime = 100;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(maxLen, maxValue);
            //int[] arr = {4,6,7};
            int ans = searchMinNum(arr);
            //AlgUtil.console(ans);
            AlgUtil.printArray(arr);
            if (check(arr, ans)) {
                AlgUtil.console(ans);
                break;
            } else {
                AlgUtil.console(-1);
            }
        }
        System.out.println("测试结束");

    }


}
