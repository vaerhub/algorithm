package io.arkvaer.algorithm.basic.day29;

import io.arkvaer.algorithm.utils.AlgUtil;

import java.util.Arrays;

/**
 * 给定一个无序数组arr中，长度为N，给定一个正数L，返回top K个最大的数
 * 不同时间复杂度三个方法
 * 1) O(N*logN)
 * 2) O(N + K*logN)
 * 3) O(n + k*logK)
 */
public class MaxTopK {

    // 时间复杂度O(N*logN)
    // 排序+收集
    public static int[] maxTopK1(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return new int[0];
        }
        int len = arr.length;
        Arrays.sort(arr);
        k = Math.min(len, k);
        int[] ans = new int[k];
        for (int i = 0, j = len - 1; i < k; i++, j--) {
            ans[i] = arr[j];
        }
        return ans;
    }

    /**
     * 方法二，时间复杂度O(N + K*logN)
     */
    public static int[] maxTopK2(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return new int[0];
        }
        int len = arr.length;
        k = Math.min(k, len);
        for (int i = len - 1; i >= 0; i--) {
            heapify(arr, i, len);
        }
        int heapSize = len;
        AlgUtil.swap(arr, 0, --heapSize);
        int count = 1;
        while (heapSize > 0 && count < k) {
            heapify(arr, 0, heapSize);
            AlgUtil.swap(arr, 0, --heapSize);
            count++;
        }
        int[] ans = new int[k];
        for (int i = 0, j = len - 1; i < k; i++, j--) {
            ans[i] = arr[j];
        }
        return ans;
    }

    public static void heapify(int[] arr, int index, int heapSize) {
        int left = index * 2 + 1;
        while (left < heapSize) {
            int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
            largest = arr[index] > arr[largest] ? index : largest;
            if (largest == index) {
                break;
            }
            AlgUtil.swap(arr, largest, index);
            index = largest;
            left = index * 2 + 1;
        }
    }

    /**
     * 方法3 O(n + k*logK)
     */
    public static int[] maxTopK3(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return new int[0];
        }
        int len = arr.length;
        k = Math.min(len, k);
        int[] ans = new int[k];
        // 时间复杂度 O(N)
        // 获取len - k 小的数字的下标
        int num = minKth(arr, len - k);
        int index = 0;
        for (int j : arr) {
            if (j > num) {
                ans[index++] = j;
            }
        }
        for (; index < k; index++) {
            ans[index] = num;
        }
        // 时间复杂度 O(k*logk)
        Arrays.sort(ans);
        for (int l = 0, r = k - 1; l < r; l++, r--) {
            AlgUtil.swap(ans, l, r);
        }
        return ans;
    }

    public static int minKth(int[] arr, int index) {
        int l = 0;
        int r = arr.length - 1;
        int pivot;
        int[] range;
        while (l < r) {
            pivot = arr[l + AlgUtil.random.nextInt(r - l + 1)];
            range = partition(arr, l, r, pivot);
            if (index < range[0]) {
                r = range[0] - 1;
            } else if (index > range[1]) {
                l = range[1] + 1;
            } else {
                return pivot;
            }
        }
        return arr[l];
    }

    private static int[] partition(int[] arr, int l, int r, int pivot) {
        int less = l - 1;
        int more = r + 1;
        int cur = l;
        while (cur < more) {
            if (arr[cur] < pivot) {
                AlgUtil.swap(arr, cur++, ++less);
            } else if (arr[cur] > pivot) {
                AlgUtil.swap(arr, cur, --more);
            } else {
                cur++;
            }
        }
        return new int[]{less + 1, more - 1};
    }


    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            // [-? , +?]
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
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

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // 生成随机数组测试
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean pass = true;
        System.out.println("测试开始，没有打印出错信息说明测试通过");
        for (int i = 0; i < testTime; i++) {
            int k = (int) (Math.random() * maxSize) + 1;
            int[] arr = generateRandomArray(maxSize, maxValue);
            int[] arr1 = AlgUtil.copyArr(arr);
            int[] arr2 = AlgUtil.copyArr(arr);
            int[] arr3 = AlgUtil.copyArr(arr);

            int[] ans1 = maxTopK1(arr1, k);
            int[] ans2 = maxTopK2(arr2, k);
            int[] ans3 = maxTopK3(arr3, k);
            if (!isEqual(ans1, ans2) || !isEqual(ans1, ans3)) {
                pass = false;
                System.out.println("出错了！");
                printArray(ans1);
                printArray(ans2);
                printArray(ans3);
                break;
            }
        }
        System.out.println("测试结束了，测试了" + testTime + "组，是否所有测试用例都通过？" + (pass ? "是" : "否"));
    }
}
