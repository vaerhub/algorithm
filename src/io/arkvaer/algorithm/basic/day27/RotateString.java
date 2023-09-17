package io.arkvaer.algorithm.basic.day27;


/**
 * 给定两个字符串, s 和 goal。如果在若干次旋转操作之后，s 能变成 goal ，那么返回 true 。
 * <p>
 * s 的 旋转操作 就是将 s 最左边的字符移动到最右边。
 * <p>
 * 例如, 若 s = 'abcde'，在旋转一次之后结果就是'bcdea' 。
 * <a href="https://leetcode.cn/problems/rotate-string/">rotate-string</a>
 *
 * @author waver
 * @date 2023/9/17 下午9:54
 */
public class RotateString {
    public boolean rotateString(String s, String goal) {
        if (s == null || goal == null || s.length() != goal.length()) {
            return false;
        }
        String tmp = goal + goal;
        return getIndexOf(tmp, s) != -1;
    }


    public static int getIndexOf(String s, String m) {
        if (s.length() < m.length()) {
            return -1;
        }
        char[] sCharArray = s.toCharArray();
        char[] mCharArray = m.toCharArray();
        int si = 0;
        int mi = 0;
        int[] next = getNextArray(mCharArray);
        while (si < s.length() && mi < m.length()) {
            if (sCharArray[si] == mCharArray[mi]) {
                si++;
                mi++;
            } else if (next[mi] == -1) {
                si++;
            } else {
                mi = next[mi];
            }
        }
        return mi == m.length() ? si - mi : -1;
    }

    public static int[] getNextArray(char[] chars) {
        if (chars.length == 1) {
            return new int[]{-1};
        }
        int[] next = new int[chars.length];
        next[0] = -1;
        next[1] = 0;
        int index = 2;
        int cur = 0;
        while (index < next.length) {
            if (chars[index - 1] == chars[cur]) {
                next[index++] = ++cur;
            } else if (cur > 0) {
                cur = next[cur];
            } else {
                next[index++] = 0;
            }
        }
        return next;
    }
}
