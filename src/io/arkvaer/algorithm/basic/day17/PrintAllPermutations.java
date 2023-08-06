package io.arkvaer.algorithm.basic.day17;

import io.arkvaer.algorithm.utils.AlgUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author waver
 * @date 2023/8/6 19:08
 */

public class PrintAllPermutations {

    public static List<String> permutation1(String str) {
        List<String> result = new ArrayList<>();
        if (str == null || str.isEmpty()) {
            return result;
        }
        char[] chars = str.toCharArray();
        List<Character> rest = new ArrayList<>();
        for (char c : chars) {
            rest.add(c);
        }
        String path = "";
        process1(rest, path, result);
        return result;
    }


    public static void process1(List<Character> rest, String path, List<String> ans) {
        if (rest.isEmpty()) {
            ans.add(path);
        } else {
            int len = rest.size();
            for (int i = 0; i < len; i++) {
                char cur = rest.get(i);
                rest.remove(i);
                process1(rest, path + cur, ans);
                rest.add(i, cur);
            }
        }
    }

    public static List<String> permutation2(String str) {
        List<String> result = new ArrayList<>();
        if (str == null || str.isEmpty()) {
            return result;
        }
        char[] chars = str.toCharArray();
        process2(chars, 0, result);
        return result;
    }


    public static void process2(char[] chars, int index, List<String> result) {
        if (index == chars.length) {
            result.add(Arrays.toString(chars));
        } else {
            for (int i = index; i < chars.length; i++) {
                swap(chars, i, index);
                process2(chars, index + 1, result);
                swap(chars, index, i);
            }
        }
    }


    public static List<String> permutation3(String str) {
        List<String> ans = new ArrayList<>();
        if (str == null || str.isEmpty()) {
            return ans;
        }
        char[] chars = str.toCharArray();
        process3(chars, 0, ans);
        return ans;
    }

    public static void process3(char[] str, int index, List<String> result) {
        if (index == str.length) {
            result.add(Arrays.toString(str));
        } else {
            boolean[] visited = new boolean[256];
            for (int i = 0; i < str.length; i++) {
                if (!visited[str[i]]) {
                    visited[str[i]] = true;
                    swap(str, index, i);
                    process3(str, index + 1, result);
                    swap(str, index, i);
                }
            }
        }
    }

    public static void swap(char[] chars, int i, int j) {
        char tmp = chars[i];
        chars[i] = chars[j];
        chars[j] = tmp;
    }

    public static void main(String[] args) {
        String s = "acc";
        List<String> ans1 = permutation1(s);
        for (String str : ans1) {
            System.out.println(str);
        }
        System.out.println("=======");
        List<String> ans2 = permutation2(s);
        for (String str : ans2) {
            System.out.println(str);
        }
        System.out.println("=======");
        List<String> ans3 = permutation3(s);
        for (String str : ans3) {
            System.out.println(str);
        }

    }

}
