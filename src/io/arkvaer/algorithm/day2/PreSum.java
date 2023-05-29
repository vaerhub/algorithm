package io.arkvaer.algorithm.day2;

/**
 * 需求: 实现可以快速计算数组L位置到R位置的和
 * @author waver
 * @date 2023/5/29 下午9:26
 */
public class PreSum {
    /**
     * 方法1: 暴力算法
     */
    public static class RangeSum1 {

        private int[] arr;

        public RangeSum1(int[] array) {
            arr = array;
        }

        public int rangeSum(int L, int R) {
            int sum = 0;
            for (int i = L; i <= R; i++) {
                sum += arr[i];
            }
            return sum;
        }

    }

    /**
     * 方法2: 实现逻辑:
     * 创建一个数组, 数组的长度 = 当前数组的长度
     * 数组值= 从 0 到 i 位置的累加和
     *
     */
    public static class RangeSum2 {

        private int[] preSum;

        public RangeSum2(int[] array) {
            int N = array.length;
            preSum = new int[N];
            preSum[0] = array[0];
            for (int i = 1; i < N; i++) {
                preSum[i] = preSum[i - 1] + array[i];
            }
        }

        public int rangeSum(int L, int R) {
            return L == 0 ? preSum[R] : preSum[R] - preSum[L - 1];
        }

    }
}
