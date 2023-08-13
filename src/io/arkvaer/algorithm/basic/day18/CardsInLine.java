package io.arkvaer.algorithm.basic.day18;

import java.util.Arrays;

/**
 * 给定一个整型数组arr，代表数值不同的纸牌排成一条线
 * 玩家A和玩家B依次拿走每张纸牌,规定玩家A先拿，玩家B后拿,
 * 但是每个玩家每次只能拿走最左或最右的纸牌
 * 玩家A和玩家B都绝顶聪明
 * 请返回最后获胜者的分数
 */
public class CardsInLine {
    public static int win1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int first = f1(arr, 0, arr.length - 1);
        int last = g1(arr, 0, arr.length - 1);
        return Math.max(first, last);
    }


    public static int f1(int[] arr, int left, int right) {
        if (left == right) {
            return arr[left];
        }
        int p1 = arr[left] + g1(arr, left + 1, right);
        int p2 = arr[right] + g1(arr, left, right - 1);
        return Math.max(p1, p2);
    }

    public static int g1(int[] arr, int left, int right) {
        if (left == right) {
            return 0;
        }
        int p1 = f1(arr, left + 1, right);
        int p2 = f1(arr, left, right - 1);
        return Math.min(p1, p2);
    }

    public static int win2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int len = arr.length;
        int[][] fMap = new int[len][len];
        int[][] gMap = new int[len][len];
        for (int i = 0; i < len; i++) {
            Arrays.fill(fMap[i], -1);
            Arrays.fill(gMap[i], -1);
        }
        int p1 = f2(arr, 0, arr.length - 1, fMap, gMap);
        int p2 = g2(arr, 0, arr.length - 1, fMap, gMap);
        return Math.max(p1, p2);
    }

    public static int f2(int[] arr, int left, int right, int[][] fMap, int[][] gMap) {
        if (fMap[left][right] != -1) {
            return fMap[left][right];
        }
        if (left == right) {
            return arr[left];
        }
        int p1 = arr[left] + g2(arr, left + 1, right, fMap, gMap);
        int p2 = arr[right] + g2(arr, left, right - 1, fMap, gMap);
        int max = Math.max(p1, p2);
        fMap[left][right] = max;
        return max;
    }

    public static int g2(int[] arr, int left, int right, int[][] fMap, int[][] gMap) {
        if (gMap[left][right] != -1) {
            return gMap[left][right];
        }
        if (left == right) {
            return 0;
        }
        int p1 = f2(arr, left + 1, right, fMap, gMap);
        int p2 = f2(arr, left, right - 1, fMap, gMap);
        int min = Math.min(p1, p2);
        gMap[left][right] = min;
        return min;
    }


    public static int win3(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int len = arr.length;
        int[][] fMap = new int[len][len];
        int[][] gMap = new int[len][len];
        for (int i = 0; i < len; i++) {
            fMap[i][i] = arr[i];
        }
        for (int startCol = 1; startCol < len; startCol++) {
            int left = 0;
            int right = startCol;
            while (right < len) {
                fMap[left][right] = Math.max(arr[left] + gMap[left + 1][right], arr[right] + gMap[left][right - 1]);
                gMap[left][right] = Math.min(fMap[left + 1][right], fMap[left][right - 1]);
                left++;
                right++;
            }
        }
        return Math.max(fMap[0][len - 1], gMap[0][len - 1]);
    }



    public static void main(String[] args) {
        int[] arr = {5, 7, 4, 5, 8, 1, 6, 0, 3, 4, 6, 1, 7};
        System.out.println(win1(arr));
        System.out.println(win2(arr));
        System.out.println(win3(arr));
    }
}
