package io.arkvaer.algorithm.basic.day05;

import io.arkvaer.algorithm.utils.AlgUtil;

import java.util.Arrays;
import java.util.Stack;

/**
 * 快速排序
 *
 * @author waver
 * @date 2023/6/30 16:55
 */
public class QuickSort {

    public static void quickSort1(int[] arr) {
        if (AlgUtil.notNeedSort(arr)) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    public static void process(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int[] equalArea = partition(arr, l, r);
        process(arr, l, equalArea[0] - 1);
        process(arr, equalArea[1] + 1, r);
    }

    public static int[] partition(int[] arr, int l, int r) {
        if (l > r) {
            return new int[]{-1, -1};
        }
        if (l == r) {
            return new int[]{l, r};
        }
        int lessIndex = l - 1;
        int greaterIndex = r;
        int index = l;
        while (index < greaterIndex) {
            if (arr[index] < arr[r]) {
                AlgUtil.swap(arr, index++, ++lessIndex);
            } else if (arr[index] > arr[r]) {
                AlgUtil.swap(arr, index, --greaterIndex);
            } else {
                index++;
            }
        }
        AlgUtil.swap(arr, r, greaterIndex);
        return new int[]{lessIndex + 1, greaterIndex};
    }


    /**
     * 随机快排
     *
     * @param arr
     */
    public static void randomQuickSort(int[] arr) {
        if (AlgUtil.notNeedSort(arr)) {
            return;
        }
        randomProcess(arr, 0, arr.length - 1);
    }


    public static void randomProcess(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        AlgUtil.swap(arr, l + AlgUtil.random.nextInt(r - l + 1), r);
        int[] equalArea = partition(arr, l, r);
        process(arr, l, equalArea[0] - 1);
        process(arr, equalArea[1] + 1, r);

    }


    //***********************************************************************************/

    /**
     * 快速排序非递归版本
     *
     * @param arr 数组
     */
    public static void quickSortRecursiveAndDontUseRecursive(int[] arr) {
        if (AlgUtil.notNeedSort(arr)) {
            return;
        }
        int len = arr.length;
        AlgUtil.swap(arr, AlgUtil.random.nextInt(len), len - 1);
        int[] partition = partition(arr, 0, len - 1);
        Stack<Op> stack = new Stack<>();
        stack.push(new Op(0, partition[0] - 1));
        stack.push(new Op(partition[1] + 1, len - 1));
        while (!stack.isEmpty()) {
            Op op = stack.pop();
            if (op.left < op.right) {
                AlgUtil.swap(arr, op.left + AlgUtil.random.nextInt(op.right - op.left + 1), op.right);
                partition = partition(arr, op.left, op.right);
                stack.push(new Op(op.left, partition[0] - 1));
                stack.push(new Op(partition[1] + 1, op.right));

            }
        }
    }

    public static class Op {
        private int left;
        private int right;

        public Op(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }


    // 生成随机数组（用于测试）
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[AlgUtil.random.nextInt(maxSize + 1)];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = AlgUtil.random.nextInt(maxValue + 1) - AlgUtil.random.nextInt(maxValue + 1);
        }
        return arr;
    }


    // 跑大样本随机测试（对数器）
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = AlgUtil.copyArr(arr1);
            quickSort1(arr1);
            quickSortRecursiveAndDontUseRecursive(arr2);
            if (!AlgUtil.isEqual(arr1, arr2)) {
                succeed = false;
                System.out.println("出错了!");
                break;
            }
        }
        System.out.println("test end");
        System.out.println("测试" + testTime + "组是否全部通过：" + (succeed ? "是" : "否"));
    }

}
