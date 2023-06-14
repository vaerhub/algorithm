package io.arkvaer.algorithm.primary.day3;

import io.arkvaer.algorithm.utils.AlgUtil;

import java.util.HashMap;
import java.util.TreeMap;

/**
 * 哈希表和有序表
 */
public class HashMapAndTreeMap {

    public static class Node{
        public int value;
        public Node(int value) {
            this.value = value;
        }
    }
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("a", "A");
        map.put("b", "B");
        AlgUtil.console(map.get("a"));
        String a = "a";
        String b = "b";
        AlgUtil.console(map.get(a));
        AlgUtil.console(map.get(b));

        HashMap<Integer, String> hashMap = new HashMap<>();
        hashMap.put(12345, "a");
        hashMap.put(23456, "b");
        Integer m = 12345;
        Integer n = 12345;
        AlgUtil.console(m == n);
        AlgUtil.console(hashMap.get(m));
        AlgUtil.console(hashMap.get(n));

        TreeMap<Integer, String> treeMap = new TreeMap<>();
        treeMap.put(2, "a");
        treeMap.put(1, "b");
        treeMap.put(8, "c");
        treeMap.put(4, "d");
        treeMap.put(5, "e");
        treeMap.put(3, "f");
        AlgUtil.console(treeMap.firstKey());
        AlgUtil.console(treeMap.firstEntry());
        AlgUtil.console(treeMap.floorKey(7));
        AlgUtil.console(treeMap.ceilingKey(7));
    }
}
