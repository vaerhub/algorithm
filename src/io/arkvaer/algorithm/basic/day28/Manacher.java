package io.arkvaer.algorithm.basic.day28;

import java.util.Objects;

/**
 * @author arkvaer
 */
public class Manacher {

    public static int manacher(String str) {
        if (Objects.isNull(str) || str.isEmpty()) {
            return 0;
        }
        char[] chars = preprocessing(str);
        int[] radiusArr = new int[chars.length];
        // 当前回文子串的右边界最大的位置+1
        int R = -1;
        // 使当前R更新的回文字串的对称点
        int C = -1;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < chars.length; i++) {
            // 当 R > i 时，以i为中心点的回文字串的最小长度为 i 关于中心的对称点的回文长度和
            radiusArr[i] = R > i ? Math.min(radiusArr[2 * C - i], R - i) : 1;
            while (i + radiusArr[i] < chars.length && i - radiusArr[i] > -1) {
                if (chars[i + radiusArr[i]] == chars[i - radiusArr[i]]) {
                    radiusArr[i]++;
                } else {
                    break;
                }
            }
            if (i + radiusArr[i] > R) {
                R = i + radiusArr[i];
                C = i;
            }
            max = Math.max(max, radiusArr[i]);
        }
        return max - 1;
    }

    /**
     * 预处理字符串, 将字符串字符间添加 '#' 如 str = "abcde" 预处理后为 "#a#b#c#d#e#"
     *
     * @param str 待处理的字符串
     * @return 预处理完成的字符串
     */
    public static char[] preprocessing(String str) {
        char[] chars = str.toCharArray();
        char[] result = new char[str.length() * 2 + 1];
        int index = 0;
        for (int i = 0; i < result.length; i++) {
            result[i] = (i & 1) == 0 ? '#' : chars[index++];
        }
        return result;
    }


    // for test
    public static int right(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = preprocessing(s);
        int max = 0;
        for (int i = 0; i < str.length; i++) {
            int L = i - 1;
            int R = i + 1;
            while (L >= 0 && R < str.length && str[L] == str[R]) {
                L--;
                R++;
            }
            max = Math.max(max, R - L - 1);
        }
        return max / 2;
    }

    // for test
    public static String getRandomString(int possibilities, int size) {
        char[] ans = new char[(int) (Math.random() * size) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (char) ((int) (Math.random() * possibilities) + 'a');
        }
        return String.valueOf(ans);
    }

    public static void main(String[] args) {
        int possibilities = 5;
        int strSize = 20;
        int testTimes = 5000000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            String str = getRandomString(possibilities, strSize);
            if (manacher(str) != right(str)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");
    }
}
