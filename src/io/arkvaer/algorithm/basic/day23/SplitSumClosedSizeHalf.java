package io.arkvaer.algorithm.basic.day23;

/**
 * 给定一个正数数组arr，请把arr中所有的数分成两个集合
 * 如果arr长度为偶数，两个集合包含数的个数要一样多
 * 如果arr长度为奇数，两个集合包含数的个数必须只差一个
 * 请尽量让两个集合的累加和接近
 * 返回:
 * 最接近的情况下，[累加和]较小集合的累加和
 */
public class SplitSumClosedSizeHalf {
    public static int right(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int len = arr.length;
        int sum = 0;
        for (int j : arr) {
            sum += j;
        }
        sum /= 2;
        if ((len & 1) == 0) {
            return process(arr, 0, sum, len / 2);
        } else {
            int p1 = process(arr, 0, sum, len / 2);
            int p2 = process(arr, 0, sum, len / 2 + 1);
            return Math.max(p1, p2);
        }
    }

    public static int process(int[] arr, int index, int rest, int picks) {
        if (index == arr.length) {
            return picks == 0 ? 0 : -1;
        }
        int p1 = process(arr, index + 1, rest, picks);
        int p2 = -1;
        int next = -1;
        if (rest >= arr[index]) {
            next = process(arr, index + 1, rest - arr[index], picks - 1);
        }
        if (next != -1) {
            p2 = arr[index] + next;
        }
        return Math.max(p1, p2);
    }


    public static int dp(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int sum = 0;
        for (int j : arr) {
            sum += j;
        }
        sum /= 2;
        int len = arr.length;
        int m = (len + 1) / 2;
        int[][][] dp = new int[len + 1][sum + 1][m + 1];
        for (int index = 0; index <= len; index++) {
            for (int rest = 0; rest <= sum; rest++) {
                for (int picks = 0; picks <= m; picks++) {
                    dp[index][rest][picks] = -1;
                }
            }
        }
        for (int rest = 0; rest <= sum; rest++) {
            dp[len][rest][0] = 0;
        }
        for (int index = len - 1; index >= 0; index--) {
            for (int rest = 0; rest <= sum; rest++) {
                for (int picks = 0; picks <= m; picks++) {
                    int p1 = dp[index + 1][rest][picks];
                    int p2 = -1;
                    int next = -1;
                    if (picks -1 >= 0 && rest >= arr[index]) {
                        next = dp[index + 1][rest - arr[index]][picks - 1];
                    }
                    if (next != -1) {
                        p2 = arr[index] + next;
                    }
                    dp[index][rest][picks] = Math.max(p1, p2);
                }

            }
        }

        int p1 = dp[0][sum][len / 2];
        if ((len & 1) == 0) {
            return p1;
        } else {
            int p2 = dp[0][sum][len / 2 + 1];
            return Math.max(p1, p2);
        }
    }

    public static void main(String[] args) {
        int[] arr = {100, 1, 1, 1, 1};
        int ans1 = right(arr);
        int ans2 = dp(arr);
        System.out.println(ans1);
        System.out.println(ans2);

    }

}
