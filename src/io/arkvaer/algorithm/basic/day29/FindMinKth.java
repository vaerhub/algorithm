package io.arkvaer.algorithm.basic.day29;

import io.arkvaer.algorithm.utils.AlgUtil;

import java.util.PriorityQueue;

/**
 * 寻找数组中第K小的数
 */
public class FindMinKth {
    /**
     * 利用大根堆，时间复杂度O(N*logK)
     */
    public static int minKth1(int[] arr, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> Integer.compare(o2, o1));
        for (int i = 0; i < k; i++) {
            maxHeap.add(arr[i]);
        }
        for (int i = k; i < arr.length; i++) {
            if (arr[i] < maxHeap.peek()) {
                maxHeap.poll();
                maxHeap.add(arr[i]);
            }
        }
        return maxHeap.peek();
    }


    /**
     * 改写快排，时间复杂度O(N)
     * k >= 1
     *
     * @param array 数组
     * @param k     K
     * @return 第K小的数
     */
    public static int minKth2(int[] array, int k) {
        int[] arr = AlgUtil.copyArr(array);
        return process(arr, 0, arr.length - 1, k - 1);
    }

    public static int process(int[] arr, int l, int r, int index) {
        if (l == r) {
            return arr[l];
        }
        int pivot = arr[l + AlgUtil.random.nextInt(r - l + 1)];
        int[] range = partition(arr, l, r, pivot);
        if (index >= range[0] && index <= range[1]) {
            return arr[index];
        } else if (index < range[0]) {
            return process(arr, l, range[0] - 1, index);
        } else {
            return process(arr, range[1] + 1, r, index);
        }

    }

    public static int[] partition(int[] arr, int l, int r, int pivot) {
        int less = l - 1;
        int more = r + 1;
        int cur = l;
        while (cur < more) {
            if (arr[cur] < pivot) {
                AlgUtil.swap(arr, ++less, cur++);
            } else if (arr[cur] > pivot) {
                AlgUtil.swap(arr, --more, cur);
            } else {
                cur++;
            }
        }
        return new int[]{less + 1, more - 1};
    }

    public static int minKth3(int[] array, int k) {
        int[] arr = AlgUtil.copyArr(array);
        return bfprt(arr, 0, arr.length - 1, k - 1);
    }

    public static int bfprt(int[] arr, int L, int R, int index) {
        if (L == R) {
            return arr[L];
        }
        int pivot = medianOfMedians(arr, L, R);
        int[] range = partition(arr, L, R, pivot);
        if (index >= range[0] && index <= range[1]) {
            return arr[index];
        } else if (index < range[0]) {
            return bfprt(arr, L, range[0] - 1, index);
        } else {
            return bfprt(arr, range[0] + 1, R, index);
        }

    }

    public static int medianOfMedians(int[] arr, int L, int R) {
        int size = R - L + 1;
        int offset = size % 5 == 0 ? 0 : 1;
        int[] midArr = new int[size / 5 + offset];
        for (int team = 0; team < midArr.length; team++) {
            int teamFirst = L + team * 5;
            midArr[team] = getMedian(arr, teamFirst, Math.min(R, teamFirst + 4));
        }
        return bfprt(midArr, 0, midArr.length - 1, midArr.length / 2);
    }

    public static int getMedian(int[] arr, int L, int R) {
        insertSort(arr, L, R);
        return arr[(L + R) / 2];
    }

    public static void insertSort(int[] arr, int L, int R) {
        for (int i = L + 1; i <= R; i++) {
            for (int j = i - 1; j >= L && arr[j] > arr[j + 1]; j--) {
                AlgUtil.swap(arr, j, j + 1);
            }
        }
    }
    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) (Math.random() * maxSize) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    public static void main(String[] args) {
        int testTime = 1000000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int k = (int) (Math.random() * arr.length) + 1;
            int ans1 = minKth1(arr, k);
            int ans2 = minKth2(arr, k);
            int ans3 = minKth3(arr, k);
//            System.out.println(ans1);
//            System.out.println(ans2);
//            System.out.println(ans3);
//            System.out.println("====================");
            if (ans1 != ans2 || ans2 != ans3) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");
    }



}
