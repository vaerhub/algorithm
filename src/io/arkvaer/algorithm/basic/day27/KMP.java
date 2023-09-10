package io.arkvaer.algorithm.basic.day27;

import io.arkvaer.algorithm.utils.AlgUtil;

/**
 * @author waver
 * @date 2023/9/10 21:23
 */
public class KMP {
    public static int getIndexOf(String src, String target) {
        if (src == null || target == null || src.isEmpty() || target.isEmpty()) {
            return -1;
        }
        char[] c1 = src.toCharArray();
        char[] c2 = target.toCharArray();
        int x = 0;
        int y = 0;
        int[] next = getNextArray(c2);
        while (x < c1.length && y < c2.length) {
            if (c1[x] == c2[y]) {
                x++;
                y++;
            } else if (next[y] == -1) {
                x++;
            } else {
                y = next[y];
            }
        }
        return y == c2.length ? x - y : -1;
    }

    private static int[] getNextArray(char[] c) {
        if (c.length == 1) {
            return new int[]{-1};
        }
        int[] next = new int[c.length];
        next[0] = -1;
        next[1] = 0;
        int i = 2;
        int cur = 0;
        while (i < next.length) {
            if (c[i - 1] == c[cur]) {
                next[i++] = ++cur;
            } else if (cur > 0) {
                cur = next[cur];
            } else {
                next[i++] = 0;
            }
        }
        return next;
    }

    public static String getRandomString(int possibilities, int size) {
        char[] ans = new char[AlgUtil.random.nextInt(size) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (char) (AlgUtil.random.nextInt(possibilities) + 'a');
        }
        return String.valueOf(ans);
    }

    public static void main(String[] args) {
        int possibilities = 5;
        int strSize = 20;
        int matchSize = 5;
        int testTimes = 5000000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            String str = getRandomString(possibilities, strSize);
            String match = getRandomString(possibilities, matchSize);
            if (getIndexOf(str, match) != str.indexOf(match)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");
    }
}
