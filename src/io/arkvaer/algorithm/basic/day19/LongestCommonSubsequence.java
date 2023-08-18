package io.arkvaer.algorithm.basic.day19;

/**
 * 最长公共子序列
 * <a href="https://leetcode.cn/problems/longest-common-subsequence/">测试链接</a>
 * 给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
 * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
 * 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
 */
public class LongestCommonSubsequence {

    public static int longestCommonSubsequence1(String s1, String s2) {
        if (s1 == null || s2 == null || s1.isEmpty() || s2.isEmpty()) {
            return 0;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        return process1(str1, str2, str1.length - 1, str2.length - 1);
    }

    public static int process1(char[] str1, char[] str2, int i, int j) {
        if (i == 0 && j == 0) {
            return str1[i] == str2[j] ? 1 : 0;
        } else if (i == 0) {
            if (str1[i] == str2[j]) {
                return 1;
            } else {
                return process1(str1, str2, i, j - 1);
            }
        } else if (j == 0) {
            if (str1[i] == str2[j]) {
                return 1;
            } else {
                return process1(str1, str2, i - 1, j);
            }
        } else {
            int p1 = process1(str1, str2, i, j - 1);
            int p2 = process1(str1, str2, i - 1, j);
            int p3 = str1[i] == str2[j] ? (1 + process1(str1, str2, i - 1, j - 1)) : 0;
            return Math.max(p1, Math.max(p2, p3));
        }
    }


    public static int longestCommonSubsequence2(String s1, String s2) {
        if (s1 == null || s2 == null || s1.isEmpty() || s2.isEmpty()) {
            return 0;
        }
        int m = s1.length();
        int n = s2.length();
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int[][] dp = new int[m][n];
        dp[0][0] = str1[0] == str2[0] ? 1 : 0;
        for (int i = 1; i < n; i++) {
            dp[0][i] = str1[0] == str2[i] ? 1 : dp[0][i - 1];
        }
        for (int i = 1; i < m; i++) {
            dp[i][0] = str1[i] == str2[0] ? 1 : dp[i - 1][0];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                int p1 = dp[i][j - 1];
                int p2 = dp[i - 1][j];
                int p3 = str1[i] == str2[j] ? (1 + dp[i - 1][j - 1]) : 0;
                dp[i][j] = Math.max(p1, Math.max(p2, p3));
            }
        }
        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
        String str1 = "abcde";
        String str2 = "ace";
        int count = longestCommonSubsequence1(str1, str2);
        int count1 = longestCommonSubsequence2(str1, str2);
        System.out.println(count);
        System.out.println(count1);

    }
}
