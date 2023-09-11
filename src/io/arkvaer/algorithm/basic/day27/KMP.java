package io.arkvaer.algorithm.basic.day27;

public class KMP {

    public static int getIndexOf(String src, String match) {
        if (src == null || match == null || src.isEmpty() || match.isEmpty()) {
            return -1;
        }
        char[] s = src.toCharArray();
        char[] m = match.toCharArray();
        int[] next = getNextArray(m);
        int x = 0;
        int y = 0;
        while (x < s.length && y < m.length) {
            if (s[x] == m[y]) {
                // s当前位置 与 m 当前位置 相等, 则同时++ 继续匹配下个字符
                x++;
                y++;
            } else if (next[y] == -1) {  // 即 y == 0, 只有在 y==0 时 next[y] 是 -1
                // 如果 m 的第一个字符与 s 当前位置不相等, m 再无法右移 则使用 s 的下一个字符 与 m 的第一个字符比较
                x++;
            } else {
                // s当前位置 与 m 当前位置 不相等, m 跳到当前位置的 next 位置
                y = next[y];
            }
        }
        return y == m.length ? x - y : -1;
    }

    /**
     * 当前位置 前缀串 与 后缀串 最长的相等 的[长度]
     *
     * @param match 给定字符串 char数组
     * @return 当前位置 前缀串 与 后缀串 最长的相等 的[长度] 的数组
     */
    public static int[] getNextArray(char[] match) {
        if (match.length == 1) {
            return new int[]{-1};
        }
        int[] next = new int[match.length];
        next[0] = -1;
        next[1] = 0;
        // 前缀的坐标
        int prefix = 0;
        // 当前坐标
        int cur = 2;
        /*
        prefix
          ↓
        abcdabce
               ↑
              cur
         */
        while (cur < match.length) {
            if (match[cur - 1] == match[prefix]) {
                // 当前位置 前缀字符和后缀字符匹配成功, 当前位置的next数组设置为前缀/后缀串的长度,
                next[cur++] = ++prefix;
            } else if (prefix > 0) {
                // 若不匹配, 将前缀的位置移动到
                prefix = next[prefix];
            } else {
                next[cur++] = 0;
            }
        }
        return next;
    }

    // region test
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
    // endregion
}
