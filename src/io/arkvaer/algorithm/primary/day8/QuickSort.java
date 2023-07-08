package io.arkvaer.algorithm.primary.day8;

import io.arkvaer.algorithm.utils.AlgUtil;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 快速排序
 *
 * @author waver
 * @date 2023/6/17 20:06
 */
public class QuickSort {

    /**
     * 对指定范围进行分区
     * @param arr  需要排序的数组
     * @param l 左边界
     * @param r 右边界
     * @return 分区后 分界值的左边界和右边界下标
     */
    public int[] partition(int[] arr, int l, int r) {
        int lessIndex = l - 1;
        int moreIndex = r;
        int index = l;
        while (index < moreIndex) {
            if (arr[index] < arr[r]) {
                AlgUtil.swap(arr, ++lessIndex, index++);
            } else if (arr[index] > arr[r]) {
                AlgUtil.swap(arr, --moreIndex, index);
            } else {
                index++;
            }
        }
        AlgUtil.swap(arr, moreIndex, r);
        return new int[]{lessIndex + 1, moreIndex};
    }

    public void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    public void process(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int[] partition = partition(arr, l, r);
        process(arr, l, partition[0] - 1);
        process(arr, partition[1] + 1, r);
    }

    public static class SortJob {
        int left;
        int right;

        public SortJob(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }

    // 快速排序非递归方式
    public void quickSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        Deque<SortJob> stack = new LinkedList<>();
        stack.push(new SortJob(0, arr.length - 1));
        while (!stack.isEmpty()) {
            SortJob sortJob = stack.pop();
            int[] partition = partition(arr, sortJob.left, sortJob.right);
            if (partition[0] > sortJob.left) {
                stack.push(new SortJob(sortJob.left, partition[0] - 1));
            }
            if (partition[1] < sortJob.right) {
                stack.push(new SortJob(partition[1] + 1, sortJob.right));
            }
        }
    }


    public static void main(String[] args) {
        QuickSort quickSort = new QuickSort();
        int[] arr = {1,5,6,8,4,2,7,4,9};
        quickSort.quickSort(arr);

        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        AlgUtil.console("test begin");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = AlgUtil.generateRandomArr(maxSize, maxValue);
            int[] arr2 = AlgUtil.copyArr(arr1);
            int[] arr3 = AlgUtil.copyArr(arr1);
            Arrays.sort(arr1);
            quickSort.quickSort2(arr2);
            quickSort.quickSort(arr3);
            if (!AlgUtil.isEqual(arr1, arr2) || !AlgUtil.isEqual(arr1, arr3)) {
                AlgUtil.console("Oops!");
                AlgUtil.printArray(arr2);
                AlgUtil.printArray(arr3);
                succeed = false;
                break;
            }
        }
        AlgUtil.console("test end");
        AlgUtil.console(succeed ? "Nice!" : "Oops!");
    }
}
