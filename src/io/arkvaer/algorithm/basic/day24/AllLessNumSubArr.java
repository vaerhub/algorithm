package io.arkvaer.algorithm.basic.day24;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 给定一个整型数组arr，和一个整数num
 * 某个arr中的子数组sub，如果想达标，必须满足sub中最大值 -sub中最小值 <= num,
 * 返回arr中达标子数组的数量
 */
public class AllLessNumSubArr {

    /**
     * 暴力的对数器方法
     */
    public static int right(int[] arr, int sum) {
        if (arr == null || arr.length == 0 || sum < 0) {
            return 0;
        }
        int N = arr.length;
        int count = 0;
        for (int L = 0; L < N; L++) {
            for (int R = L; R < N; R++) {
                int max = arr[L];
                int min = arr[L];
                for (int i = L + 1; i <= R; i++) {
                    max = Math.max(max, arr[i]);
                    min = Math.min(min, arr[i]);
                }
                if (max - min <= sum) {
                    count++;
                }
            }
        }
        return count;
    }

    public static int getAllQualifiedSubArr(int[] arr, int num) {
        if (arr == null || arr.length == 0 || num < 0) {
            return 0;
        }
        int len = arr.length;
        Deque<Integer> maxQue = new LinkedList<>();
        Deque<Integer> minQue = new LinkedList<>();
        int ans = 0;
        int right = 0;
        for (int left = 0; left < len; left++) {
            while (right < len) {
                while (!maxQue.isEmpty() && arr[maxQue.peekLast()] <= arr[right]) {
                    maxQue.pollLast();
                }
                maxQue.addLast(right);
                while (!minQue.isEmpty() && arr[minQue.peekLast()] >= arr[right]) {
                    minQue.pollLast();
                }
                minQue.addLast(right);
                if (!maxQue.isEmpty() && !minQue.isEmpty() && arr[maxQue.peekFirst()] - arr[minQue.peekFirst()] > num) {
                    break;
                } else {
                    right++;
                }

            }
            ans += right - left;
            if (!maxQue.isEmpty() && maxQue.peekFirst() == left) {
                maxQue.pollFirst();
            }
            if (!minQue.isEmpty() && minQue.peekFirst() == left) {
                minQue.pollFirst();
            }
        }
        return ans;

    }


    // for test
    public static int[] generateRandomArray(int maxLen, int maxValue) {
        int len = (int) (Math.random() * (maxLen + 1));
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr != null) {
            for (int j : arr) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int maxLen = 100;
        int maxValue = 200;
        int testTime = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxLen, maxValue);
            int sum = (int) (Math.random() * (maxValue + 1));
            int ans1 = right(arr, sum);
            int ans2 = getAllQualifiedSubArr(arr, sum);
            if (ans1 != ans2) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(sum);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("测试结束");

    }


}
