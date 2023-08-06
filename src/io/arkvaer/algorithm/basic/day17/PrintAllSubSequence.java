package io.arkvaer.algorithm.basic.day17;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 打印字符串的所有字串
 *
 * @author waver
 * @date 2023/8/5 19:47
 */
public class PrintAllSubSequence {

    public static List<String> printSub(String str) {
        List<String> result = new ArrayList<>();
        char[] charArray = str.toCharArray();
        String path = "";
        process1(charArray, 0, result, path);
        return result;
    }


    /**
     * 暴力递归实现打印字符串的所有字串
     *
     * @param chars  字符串的char数组
     * @param index  当前到达的下标
     * @param result 结果数组
     * @param path   之前选择到的字符串
     */
    public static void process1(char[] chars, int index, List<String> result, String path) {
        // 当当前下标时字符串的最后一个字符时, 结束遍历, 将得到的子序列添加到结果中
        if (index == chars.length) {
            result.add(path);
            return;
        }
        // 当不选择当前字符时, 进行下面这个递归
        process1(chars, index + 1, result, path);
        // 当选择当前字符时, 进行下面这个递归
        process1(chars, index + 1, result, path + chars[index]);
    }


    /**
     * 打印所有str的[不重复]的子串
     * @param str
     * @return
     */
    public static Set<String> printNoRepeat(String str) {
        Set<String> result = new HashSet<>();
        char[] charArray = str.toCharArray();
        String path = "";
        process2(charArray, 0, result, path);
        return result;
    }


    /**
     * 暴力递归实现打印字符串的所有字串
     *
     * @param chars  字符串的char数组
     * @param index  当前到达的下标
     * @param result 结果数组
     * @param path   之前选择到的字符串
     */
    public static void process2(char[] chars, int index, Set<String> result, String path) {
        // 当当前下标时字符串的最后一个字符时, 结束遍历, 将得到的子序列添加到结果中
        if (index == chars.length) {
            result.add(path);
            return;
        }
        // 当不选择当前字符时, 进行下面这个递归
        process2(chars, index + 1, result, path);
        // 当选择当前字符时, 进行下面这个递归
        process2(chars, index + 1, result, path + chars[index]);
    }


    public static void main(String[] args) {
        String s = "aabcd";
        List<String> print = printSub(s);
        System.out.println(print);
        Set<String> noRepeat = printNoRepeat(s);
        System.out.println(noRepeat);

    }

}
