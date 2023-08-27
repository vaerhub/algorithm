package io.arkvaer.algorithm.basic.day24;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 假设一个固定大小为W的窗口，
 * 依次划过arr返回每一次滑出状况的最大值例如，
 * arr =[4,3,5,4,3,3,6,7], W = 3返回:[5,5,5,4,6,7]
 */
public class SlidingWindowMaxArray {

    // 暴力的对数器方法
    public static int[] right(int[] arr, int w) {
        if (arr == null || w < 1 || arr.length < w) {
            return new int[0];
        }
        int N = arr.length;
        int[] res = new int[N - w + 1];
        int index = 0;
        int L = 0;
        int R = w - 1;
        while (R < N) {
            int max = arr[L];
            for (int i = L + 1; i <= R; i++) {
                max = Math.max(max, arr[i]);

            }
            res[index++] = max;
            L++;
            R++;
        }
        return res;
    }


    public static int[] getMaxWindow(int[] arr, int w) {
        if (arr == null || arr.length < w || w < 1) {
            return null;
        }
        LinkedList<Integer> queue = new LinkedList<>();
        int length = arr.length;
        // 从左一次划过, 将会有 length - w + 1个最大值
        int[] ans = new int[length - w + 1];
        int index = 0;
        for (int right = 0; right < length; right++) {
            if (!queue.isEmpty() && queue.peekFirst() == right - w) {
                queue.pollFirst();
            }
            while (!queue.isEmpty() && arr[queue.peekLast()] <= arr[right]) {
                queue.pollLast();
            }
            queue.addLast(right);
            if (!queue.isEmpty() && right >= w - 1) {
                ans[index++] = arr[queue.peekFirst()];
            }
        }
        return ans;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] array = {4, 3, 5, 4, 3, 3, 6, 7};
        int width = 3;
        System.out.println(Arrays.toString(getMaxWindow(array, width)));
        System.out.println(Arrays.toString(right(array, width)));
        int testTime = 10000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int w = (int) (Math.random() * (arr.length + 1));
            int[] ans1 = getMaxWindow(arr, w);
            int[] ans2 = right(arr, w);
            if (!isEqual(ans1, ans2)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");
    }

}
