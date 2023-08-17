package io.arkvaer.algorithm.basic.day19;

/**
 * 我们有 n 种不同的贴纸。每个贴纸上都有一个小写的英文单词。
 * 您想要拼写出给定的字符串 target ，方法是从收集的贴纸中切割单个字母并重新排列它们。
 * 如果你愿意，你可以多次使用每个贴纸，每个贴纸的数量是无限的。
 * 返回你需要拼出 target 的 <font color='green'>[最小贴纸数量]</font>。如果任务不可能，则返回 -1 。
 * 注意：在所有的测试用例中，所有的单词都是从 1000 个最常见的美国英语单词中随机选择的，并且 target 被选择为两个随机单词的连接。
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

    public static void main(String[] args) {
        String[] stickers = {"with","example","science"};
        String target = "thehat";
        int count = minStickers1(stickers, target);
        System.out.println(count);
    }
}
