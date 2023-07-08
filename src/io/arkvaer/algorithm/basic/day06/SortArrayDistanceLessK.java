package io.arkvaer.algorithm.basic.day06;

import io.arkvaer.algorithm.utils.AlgUtil;

import java.util.PriorityQueue;

/**
 *
 *
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
}
