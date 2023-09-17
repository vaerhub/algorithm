package io.arkvaer.algorithm.basic.day28;

/**
 * 给定一个字符串s, 求要使 s 变成回文串最少需要在 s后拼接的字符串 ans
 *
 * @author waver
 * @date 2023/9/17 下午10:31
 */
public class AddShortestEnd {
    public static String shortestEnd(String s) {
        if (s == null || s.isEmpty()) {
            return null;
        }
        char[] str = manacherString(s);
        int[] radius = new int[str.length];
        // 当前回文子串的右边界最大的位置+1
        int r = -1;
        // 使当前R更新的回文字串的对称点
        int c = -1;
        int max = -1;
        for (int i = 0; i != str.length; i++) {
            radius[i] = r > i ? Math.min(r - i, radius[2 * c - i]) : 1;
            while (i + radius[i] < str.length && i - radius[i] >= 0) {
                if (str[i + radius[i]] == str[i - radius[i]]) {
                    radius[i]++;
                } else {
                    break;
                }
            }
            if (i + radius[i] > r) {
                r = i + radius[i];
                c = i;
            }
            if (r == str.length) {
                max = radius[i];
                break;
            }

        }
        char[] res = new char[s.length() - max + 1];
        for (int i = 0; i < res.length; i++) {
            res[res.length - 1 - i] = str[i * 2 + 1];
        }
        return String.valueOf(res);
    }


    public static char[] manacherString(String s) {
        char[] sCharArray = s.toCharArray();
        char[] res = new char[s.length() * 2 + 1];
        int index = 0;
        for (int i = 0; i != res.length; i++) {
            res[i] = (i & 1) == 0 ? '#' : sCharArray[index++];
        }
        return res;
    }

    public static void main(String[] args) {
        String str1 = "abcd123321";
        System.out.println(shortestEnd(str1));
    }
}
