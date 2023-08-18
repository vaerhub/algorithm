package io.arkvaer.algorithm.basic.day20;

import cn.hutool.core.util.StrUtil;

import java.util.Arrays;

/**
 * 最长回文子序列
 * 给你一个字符串 s ，找出其中最长的回文子序列，并返回该序列的长度。
 * 子序列定义为：不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列。
 * <a href="https://leetcode.cn/problems/longest-palindromic-subsequence/">测试链接</a>
 */
public class PalindromeSubsequence {


    /**
     * 暴力递归实现方式
     *
     * @param s 给定字符串
     * @return 最长公共子序列
     */
    public static int longestPalindromeSubsequence(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        char[] str = s.toCharArray();
        return process(str, 0, str.length - 1);
    }

    public static int process(char[] s, int left, int right) {
        if (left == right) {
            return 1;
        }
        if (left == right - 1) {
            return s[left] == s[right] ? 2 : 1;
        }
        int p1 = process(s, left + 1, right - 1);
        int p2 = process(s, left, right - 1);
        int p3 = process(s, left + 1, right);
        int p4 = s[left] == s[right] ? (2 + process(s, left + 1, right - 1)) : 0;
        return Math.max(Math.max(p1, p2), Math.max(p3, p4));
    }


    /**
     * 将字符串反转后求最长公共子序列
     *
     * @param s 字符串
     * @return 最长回文子序列长度
     */
    public static int longestPalindromeSubsequence1(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        String reverse = new StringBuilder(s).reverse().toString();
        return longestCommonSubsequence(s, reverse);
    }

    public static int longestCommonSubsequence(String s1, String s2) {
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


    public static int longestPalindromeSubsequence2(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }
        char[] str = s.toCharArray();
        int length = s.length();
        int[][] dp = new int[length][length];
        dp[length - 1][length - 1] = 1;
        for (int i = 0; i < length - 1; i++) {
            dp[i][i] = 1;
            dp[i][i + 1] = str[i] == str[i + 1] ? 2 : 1;
        }
        for (int i = length - 3; i >= 0; i--) {
            for (int j = i + 2; j < length; j++) {
                dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
                if (str[i] == str[j]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i + 1][j - 1] + 2);
                }
            }
        }
        return dp[0][length - 1];
    }

    public static void main(String[] args) {
        String str = "bbbab";
        int count = longestPalindromeSubsequence(str);
        int count1 = longestPalindromeSubsequence1(str);
        int count2 = longestPalindromeSubsequence2(str);
        System.out.println(count);
        System.out.println(count1);
        System.out.println(count2);
    }
}
