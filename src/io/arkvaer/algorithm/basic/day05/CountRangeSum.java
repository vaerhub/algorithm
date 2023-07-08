package io.arkvaer.algorithm.basic.day05;

/**
 * @author waver
 * @date 2023/6/27 15:55
 */
public class CountRangeSum {

    public static int countRangeSum(int[] nums, int lower, int upper) {
        long[] sums = getPerSumArr(nums);
        return count(sums, 0, sums.length - 1, lower, upper);
    }

    public static int count(long[] sums, int l, int r, int lower, int upper) {
        if (l == r) {
            return sums[l] >= lower && sums[l] <= upper ? 1 : 0;
        }
        int mid = l + ((r - l) >> 1);
        return count(sums, l, mid, lower, upper) +
                count(sums, mid + 1, r, lower, upper) +
                merge(sums, l, mid, r, lower, upper);
    }

    public static long[] getPerSumArr(int[] nums) {
        long[] sums = new long[nums.length];
        sums[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sums[i] = sums[i - 1] + nums[i];
        }
        return sums;
    }

    public static int merge(long[] sums, int l, int mid, int r, int lower, int upper) {
        int result = 0;
        int leftWindow = l;
        int rightWindow = l;
        for (int i = mid + 1; i <= r; i++) {
            long min = sums[i] - upper;
            long max = sums[i] - lower;
            // 当窗口左边界到达第一个 不小于 min 的位置时停止
            while (sums[leftWindow] < min && leftWindow <= mid) {
                leftWindow++;
            }
            // 当窗口左边界到达最后一个不大于 max 的位置时停止
            while (sums[rightWindow] <= max && rightWindow <= mid) {
                rightWindow++;
            }
            result += rightWindow - leftWindow;
        }
        int p1 = l;
        int p2 = mid + 1;
        long[] help = new long[r - l + 1];
        int index = 0;
        while (p1 <= mid && p2 <= r) {
            help[index++] = sums[p1] <= sums[p2] ? sums[p1++] : sums[p2++];
        }

        while (p1 <= mid) {
            help[index++] = sums[p1++];
        }

        while (p2 <= r) {
            help[index++] = sums[p2++];
        }
        System.arraycopy(help, 0, sums, l, help.length);
        return result;
    }


    public static void main(String[] args) {
        int[] arr = {-2147483647,0,-2147483647,2147483647};
        int i = countRangeSum(arr, -2, 2);
        System.out.println(i);

    }
}
