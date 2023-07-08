package io.arkvaer.algorithm.basic.day06;

import io.arkvaer.algorithm.utils.AlgUtil;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author waver
 * @date 2023/7/1 21:11
 */
public class SortArrayDistanceLessK {
    public static void sortedArrDistanceLessK(int[] arr, int k) {
        if (AlgUtil.notNeedSort(arr) || k == 0) {
            return;
        }
        PriorityQueue<Integer> heap = new PriorityQueue<>(k);
        int index = 0;
        for (; index <= Math.min(arr.length - 1, k - 1); index++) {
            heap.add(arr[index]);
        }
        int i = 0;
        for (; index < arr.length; i++, index++) {
            heap.add(arr[index]);
            arr[i] = heap.poll();
        }

        while (!heap.isEmpty()) {
            arr[i++] = heap.poll();
        }

    }

    public static int[] randomArrayNoMoveMoreK(int maxSize, int maxValue, int k) {
        int[] arr = new int[AlgUtil.random.nextInt(maxSize + 1)];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = AlgUtil.random.nextInt(maxValue + 1) - AlgUtil.random.nextInt(maxValue);
        }
        // 先将数组排序
        Arrays.sort(arr);
        // 然后开始随意交换，但是保证每个数距离不超过K
        // swap[i] == true, 表示i位置已经参与过交换
        // swap[i] == false, 表示i位置没有参与过交换
        boolean[] isSwap = new boolean[arr.length];
        for (int i = 0; i < arr.length; i++) {
            int j = Math.min(i + AlgUtil.random.nextInt(k + 1), arr.length - 1);
            if (!isSwap[i] && !isSwap[j]) {
                isSwap[i] = true;
                isSwap[j] = true;
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
            }
        }
        return arr;
    }


    // for test
    public static void main(String[] args) {
        System.out.println("test begin");
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int k = (int) (Math.random() * maxSize) + 1;
            int[] arr = randomArrayNoMoveMoreK(maxSize, maxValue, k);
            int[] arr1 = AlgUtil.copyArr(arr);
            int[] arr2 = AlgUtil.copyArr(arr);
            sortedArrDistanceLessK(arr1, k);
            Arrays.sort(arr2);
            if (!AlgUtil.isEqual(arr1, arr2)) {
                succeed = false;
                System.out.println("K : " + k);
                AlgUtil.printArray(arr);
                AlgUtil.printArray(arr1);
                AlgUtil.printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }
}
