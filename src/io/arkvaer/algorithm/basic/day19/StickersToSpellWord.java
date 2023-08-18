package io.arkvaer.algorithm.basic.day19;

import java.util.HashMap;
import java.util.Map;

/**
 * 我们有 n 种不同的贴纸。每个贴纸上都有一个小写的英文单词。
 * 您想要拼写出给定的字符串 target ，方法是从收集的贴纸中切割单个字母并重新排列它们。
 * 如果你愿意，你可以多次使用每个贴纸，每个贴纸的数量是无限的。
 * 返回你需要拼出 target 的 <font color='green'>[最小贴纸数量]</font>。如果任务不可能，则返回 -1 。
 * 注意：在所有的测试用例中，所有的单词都是从 1000 个最常见的美国英语单词中随机选择的，并且 target 被选择为两个随机单词的连接。
 * <a href="https://leetcode.cn/problems/stickers-to-spell-word/submissions">测试链接</a>
 */
public class StickersToSpellWord {
    public static int minStickers1(String[] stickers, String target) {
        int result = process1(stickers, target);
        return result == Integer.MAX_VALUE ? -1 : result;
    }

    public static int process1(String[] stickers, String target) {
        if (target.isEmpty()) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for (String sticker : stickers) {
            String rest = minus(target, sticker);
            if (rest.length() != target.length()) {
                min = Math.min(min, process1(stickers, rest));
            }
        }
        return min + (min == Integer.MAX_VALUE ? 0 : 1);
    }

    private static String minus(String target, String sticker) {
        char[] str1 = target.toCharArray();
        char[] str2 = sticker.toCharArray();
        int[] count = new int[26];
        for (char c : str1) {
            count[c - 'a']++;
        }
        for (char c : str2) {
            count[c - 'a']--;
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            if (count[i] > 0) {
                builder.append(String.valueOf((char) (i + 'a')).repeat(Math.max(0, count[i])));
            }
        }
        return builder.toString();
    }


    public static int minStickers2(String[] stickers, String target) {
        int len = stickers.length;
        int[][] counts = new int[len][26];
        for (int i = 0; i < len; i++) {
            char[] chars = stickers[i].toCharArray();
            for (char c : chars) {
                counts[i][c - 'a']++;
            }
        }
        int result = process2(counts, target);
        return result == Integer.MAX_VALUE ? -1 : result;
    }


    public static int process2(int[][] stickers, String targetStr) {
        if (targetStr.isEmpty()) {
            return 0;
        }
        char[] chars = targetStr.toCharArray();
        int[] target = new int[26];
        int min = Integer.MAX_VALUE;
        for (char c : chars) {
            target[c - 'a']++;
        }
        for (int[] sticker : stickers) {
            // 只尝试存在首字母的贴纸(贪心)
            if (sticker[chars[0] - 'a'] > 0) {
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < 26; i++) {
                    if (target[i] > 0) {
                        int nums = target[i] - sticker[i];
                        builder.append(String.valueOf((char) (i + 'a')).repeat(Math.max(0, nums)));
                    }
                }
                String rest = builder.toString();
                min = Math.min(min, process2(stickers, rest));
            }
        }

        return min + (min == Integer.MAX_VALUE ? 0 : 1);
    }


    public static int minStickers3(String[] stickers, String target) {
        int len = stickers.length;
        int[][] counts = new int[len][26];
        for (int i = 0; i < len; i++) {
            char[] chars = stickers[i].toCharArray();
            for (char c : chars) {
                counts[i][c - 'a']++;
            }
        }
        Map<String, Integer> dpMap = new HashMap<>();
        dpMap.put("", 0);
        int result = process3(counts, target, dpMap);
        return result == Integer.MAX_VALUE ? -1 : result;
    }

    public static int process3(int[][] stickers, String targetStr, Map<String, Integer> dpMap) {
        if (dpMap.containsKey(targetStr)) {
            return dpMap.get(targetStr);
        }
        char[] chars = targetStr.toCharArray();
        int[] counts = new int[26];
        for (char c : chars) {
            counts[c - 'a']++;
        }
        int min = Integer.MAX_VALUE;
        for (int[] sticker : stickers) {
            if (sticker[chars[0] - 'a'] > 0) {
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < 26; i++) {
                    if (counts[i] > 0) {
                        int num = counts[i] - sticker[i];
                        builder.append(String.valueOf((char) (i + 'a')).repeat(Math.max(0, num)));
                    }
                }
                String rest = builder.toString();
                min = Math.min(min, process3(stickers, rest, dpMap));
            }
        }
        min = min + (min == Integer.MAX_VALUE ? 0 : 1);
        dpMap.put(targetStr, min);
        return min;
    }

    public static void main(String[] args) {
        String[] stickers = {"with","example","science"};
        String target = "thehat";
        int count = minStickers1(stickers, target);
        int count1 = minStickers2(stickers, target);
        int count2 = minStickers3(stickers, target);

        System.out.println(count);
        System.out.println(count1);
        System.out.println(count2);
    }
}
