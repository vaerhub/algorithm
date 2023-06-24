package io.arkvaer.algorithm.basic.day01;

import io.arkvaer.algorithm.utils.AlgUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @author waver
 * @date 2023/6/22 下午9:19
 */
public class KM {

    public static int test(int[] arr, int k, int m) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : arr) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        int ans = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == k) {
                ans = entry.getKey();
                break;
            }
        }
        return ans;
    }

    public static int oneKTime(int[] arr, int k, int m) {
        int[] hits = new int[32];
        for (int num : arr) {
            for (int i = 0; i < 32; i++) {
                hits[i] += ((num >> i) & 1);
            }
        }
        int ans = 0;
        for (int j = 0; j < 32; j++) {
            hits[j] %= m;
            if (hits[j] != 0) {
                ans |= 1 << j;
            }
        }
        return ans;
    }

    public static int randomNum(int range) {
        return AlgUtil.random.nextInt(-range, range + 1);
    }

    public static int[] randomArray(int maxKinds, int range, int k, int m) {
        int kTimeNum = AlgUtil.random.nextInt(range);
        int times = k;
        int numKinds = AlgUtil.random.nextInt(maxKinds) + 2;
        int[] arr = new int[times + (numKinds - 1) * m];
        int index = 0;
        for (; index < times; index++) {
            arr[index] = kTimeNum;
        }
        numKinds--;
        HashSet<Integer> set = new HashSet<>(maxKinds);
        set.add(kTimeNum);
        while (numKinds != 0) {
            int curNum;
            do {
                curNum = randomNum(range);
            } while (set.contains(curNum));
            set.add(curNum);
            numKinds--;
            for (int i = 0; i < m; i++) {
                arr[index++] = curNum;
            }
        }

        // 将arr 打乱
//        for (int i = 0; i < arr.length; i++) {
//            int j = AlgUtil.random.nextInt(arr.length);
//            int temp = arr[j];
//            arr[i] = arr[j];
//            arr[j] = temp;
//        }
        return arr;
    }

    public static void test2() {
        int kinds = 5;
        int range = 30;
        int testTime = 10;
        int max = 9;
        AlgUtil.console("===================测试开始===========================");
        for (int i = 0; i < testTime; i++) {
            int a = AlgUtil.random.nextInt(max);
            int b = AlgUtil.random.nextInt(max);
            int k = Math.min(a, b);
            int m = Math.max(a, b);
            if (k == m) {
                m++;
            }
            int[] arr = randomArray(kinds, range, k, m);
            Arrays.sort(arr);
            int ans1 = test(arr, k, m);
            int ans2 = oneKTime(arr, k, m);
            if (ans1 != ans2) {
                AlgUtil.print(arr);
                AlgUtil.console(ans1);
                AlgUtil.console(ans2);
                AlgUtil.console("!!!!!!出错了!!!!!!");
                break;
            }

        }
        AlgUtil.console("===================测试结束===========================");
    }

    public static void main(String[] args) {
        test2();
    }

}
