package io.arkvaer.algorithm.basic.day06;

import io.arkvaer.algorithm.utils.AlgException;
import io.arkvaer.algorithm.utils.AlgUtil;

/**
 * @author waver
 * @date 2023/7/1 11:18
 */
public class Heap {

    public static class MyMaxHeap {
        private int[] heap;
        private final int limit;
        private int heapSize;

        public MyMaxHeap(int limit) {
            this.limit = limit;
            this.heap = new int[limit];
            this.heapSize = 0;
        }

        public boolean isFull() {
            return heapSize == limit;
        }

        public boolean isEmpty() {
            return heapSize == 0;
        }

        public void push(int value) {
            if (heapSize == limit) {
                throw new AlgException("heap is full");
            }
            heap[heapSize] = value;
            // value heapSize
            heapInsert(heap, heapSize++);
        }

        public int pop() {
            int ans = heap[0];
            AlgUtil.swap(heap, 0, --heapSize);
            heapify(heap, 0, heapSize);
            return ans;
        }

        public void heapInsert(int[] arr, int index) {
            while (arr[index] > arr[(index - 1) / 2]) {
                AlgUtil.swap(arr, index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        public void heapify(int[] arr, int index, int heapSize) {
            int left = index * 2 + 1;
            // 如果存在左孩子
            while (left < heapSize) {
                // 获取较大的孩子的下标
                int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
                // 根节点与较大孩子比较, 将较大值的下标赋值个largest
                largest = arr[largest] > arr[index] ? largest : index;
                // 若当前节点就是最大值, 则结束循环
                if (largest == index) {
                    break;
                }
                // 若当前节点不是最大值, 则与最大值交换, 并将当前下标移动到最大值位置, 继续下次循环
                AlgUtil.swap(arr, largest, index);
                index = largest;
                left = index * 2 + 1;
            }
        }
    }



}
