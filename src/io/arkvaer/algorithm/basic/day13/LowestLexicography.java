package io.arkvaer.algorithm.basic.day13;

import java.util.*;

/**
 * [贪心算法]
 * 给定一个字符串数组, 找到使该字符串数组字典序最小的排序, 并返回排序结果
 */
public class LowestLexicography {


    public static String lowestLexicography1(String[] strArr) {

        if (strArr == null || strArr.length == 0) {
            return "";
        }
        SortedSet<String> process = process(strArr);

        return process.isEmpty() ? "" : process.first();

    }

    public static SortedSet<String> process(String[] strings) {
        SortedSet<String> strSet = new TreeSet<>();
        if (strings.length == 0) {
            strSet.add("");
            return strSet;
        }
        for (int i = 0; i < strings.length; i++) {
            String first = strings[i];
            String[] nextArr = removeIndexString(strings, i);
            SortedSet<String> nextSet = process(nextArr);
            for (String next : nextSet) {
                strSet.add(first + next);
            }
        }
        return strSet;
    }


    public static String[] removeIndexString(String[] arr, int index) {
        int length = arr.length;
        String[] ans = new String[length - 1];
        int ansIndex = 0;
        for (int i = 0; i < length; i++) {
            if (i != index) {
                ans[ansIndex++] = arr[i];
            }
        }
        return ans;
    }
    public static class MyComparator implements Comparator<String> {

        @Override
        public int compare(String a, String b) {
            return (a + b).compareTo(b + a);
        }
    }

    public static String lowestLexicography2(String[] strArr) {
        if (strArr == null || strArr.length == 0) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        Arrays.sort(strArr, new MyComparator());
        for (String s : strArr) {
            builder.append(s);
        }
        return builder.toString();
    }

    // for test
    public static String generateRandomString(int strLen) {
        char[] ans = new char[(int) (Math.random() * strLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            int value = (int) (Math.random() * 5);
            ans[i] = (Math.random() <= 0.5) ? (char) (65 + value) : (char) (97 + value);
        }
        return String.valueOf(ans);
    }

    // for test
    public static String[] generateRandomStringArray(int arrLen, int strLen) {
        String[] ans = new String[(int) (Math.random() * arrLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = generateRandomString(strLen);
        }
        return ans;
    }

    // for test
    public static String[] copyStringArray(String[] arr) {
        String[] ans = new String[arr.length];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = String.valueOf(arr[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        int arrLen = 7;
        int strLen = 6;
        int testTimes = 10000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            String[] arr1 = generateRandomStringArray(arrLen, strLen);
            String[] arr2 = copyStringArray(arr1);
            if (!lowestLexicography1(arr1).equals(lowestLexicography2(arr2))) {
                for (String str : arr1) {
                    System.out.print(str + ",");
                }
                System.out.println();
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
