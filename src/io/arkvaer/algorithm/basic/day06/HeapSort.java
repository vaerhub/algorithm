package io.arkvaer.algorithm.basic.day06;

import io.arkvaer.algorithm.utils.AlgUtil;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 堆排序
 *
 * @author waver
 * @date 2023/7/1 19:36
 */
public class HeapSort {

    public static void heapInsert(int[] heap, int index) {
        while (heap[index] > heap[(index - 1) / 2]) {
            AlgUtil.swap(heap, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    public static void heapify(int[] heap, int index, int heapSize) {
        int left = index * 2 + 1;
        while (left < heapSize) {
            int largest = left + 1 < heapSize && heap[left] < heap[left + 1] ? left + 1 : left;
            largest = heap[index] > heap[largest] ? index : largest;
            if (index == largest) {
                break;
            }
            AlgUtil.swap(heap, index, largest);
            index = largest;
            left = index * 2 + 1;
        }
    }

    public static void heapSort(int[] arr) {
        if (AlgUtil.notNeedSort(arr)) {
            return;
        }

        //1. 使用HeapInsert生成堆, 时间复杂度 O(N*logN)
        /*for (int i = 0; i < arr.length; i++) {
            heapInsert(arr, i);
        }*/
        //2. 使用Heapify生成堆, 时间复杂度 O(N)
        for (int i = arr.length; i >= 0; i--) {
            heapify(arr, i, arr.length);
        }
        int heapSize = arr.length;
        AlgUtil.swap(arr, 0, --heapSize);
        // 时间复杂度: O(N)
        while (heapSize > 0) {
            // 时间复杂度: O(logN)
            heapify(arr, 0, heapSize);
            // 时间复杂度: O(1)
            AlgUtil.swap(arr, 0, --heapSize);
        }
    }

    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
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

    // for test
    public static void main(String[] args) {

        // 默认小根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        heap.add(6);
        heap.add(8);
        heap.add(0);
        heap.add(2);
        heap.add(9);
        heap.add(1);

        while (!heap.isEmpty()) {
            System.out.println(heap.poll());
        }

        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            heapSort(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

        int[] arr = generateRandomArray(maxSize, maxValue);
        printArray(arr);
        heapSort(arr);
        printArray(arr);
    }

}
