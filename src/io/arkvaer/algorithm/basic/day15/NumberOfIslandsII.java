package io.arkvaer.algorithm.basic.day15;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 本题为leetcode原题
 * 测试链接：<a href="https://leetcode.com/problems/number-of-islands-ii/">...</a>
 * 所有方法都可以直接通过
 * [LeetCode] 305. Number of Islands II 岛屿的数量之二
 * <p>
 * A 2d grid map of m rows and n columns is initially filled with water.
 * We may perform an addLand operation which turns the water at position (row, col) into a land.
 * Given a list of positions to operate, count the number of islands after each addLand operation.
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 * You may assume all four edges of the grid are all surrounded by water.
 * </p>
 * <p>
 * m 行和 n 列的二维网格图最初充满了水。
 * 我们可以执行 addLand 操作，将位置 (row, col) 处的水变成陆地。
 * 给定要操作的位置列表，计算每次 addLand 操作后的岛屿数量。
 * 岛屿四面环水，相邻陆地水平或垂直连接而成。
 * 您可以假设网格的所有四个边缘都被水包围。
 * </p>
 */
public class NumberOfIslandsII {


    public static List<Integer> numIslands21(int m, int n, int[][] positions) {
        UnionFound uf = new UnionFound(m, n);
        List<Integer> ans = new ArrayList<>();
        for (int[] position : positions) {
            ans.add(uf.connect(position[0], position[1]));
        }
        return ans;
    }

    public static class UnionFound {
        private int[] parent;
        private int[] size;
        private int[] help;
        private int row;
        private int col;
        private int sets;

        public UnionFound(int m, int n) {
            this.row = m;
            this.col = n;
            int len = m * n;
            this.parent = new int[len];
            this.size = new int[len];
            this.help = new int[len];
            sets = 0;
        }


        public int index(int r, int c) {
            return r * col + c;
        }

        public int find(int i) {
            int hi = 0;
            while (i != parent[i]) {
                help[hi++] = parent[i];
                i = parent[i];
            }
            for (hi--; hi >= 0; hi--) {
                parent[help[hi]] = i;
            }
            return i;
        }

        public void union(int r1, int c1, int r2, int c2) {
            if (r1 < 0 || r1 == row || r2 < 0 || r2 == row || c1 < 0 || c1 == col || c2 < 0 || c2 == col) {
                return;
            }
            int index1 = index(r1, c1);
            int index2 = index(r2, c2);
            if (size[index1] == 0 || size[index2] == 0) {
                return;
            }
            int i1 = find(index1);
            int i2 = find(index2);
            if (i1 != i2) {
                if (size[i1] > size[i2]) {
                    size[i1] += size[i2];
                    parent[i2] = i1;
                } else {
                    size[i2] += size[i1];
                    parent[i1] = i2;
                }
                sets--;
            }
        }


        public int connect(int r, int c) {
            int index = index(r, c);
            if (size[index] == 0) {
                parent[index] = index;
                size[index] = 1;
                sets++;
                union(r - 1, c, r, c);
                union(r + 1, c, r, c);
                union(r, c - 1, r, c);
                union(r, c + 1, r, c);
            }
            return sets;

        }
    }

    // 课上讲的如果m*n比较大，会经历很重的初始化，而k比较小，怎么优化的方法
    public static List<Integer> numIslands22(int m, int n, int[][] positions) {
        UnionFind2 uf = new UnionFind2();
        List<Integer> ans = new ArrayList<>();
        for (int[] position : positions) {
            ans.add(uf.connect(position[0], position[1]));
        }
        return ans;
    }

    public static class UnionFind2 {
        private HashMap<String, String> parent;
        private HashMap<String, Integer> size;
        private ArrayList<String> help;
        private int sets;

        public UnionFind2() {
            parent = new HashMap<>();
            size = new HashMap<>();
            help = new ArrayList<>();
            sets = 0;
        }

        private String find(String cur) {
            while (!cur.equals(parent.get(cur))) {
                help.add(cur);
                cur = parent.get(cur);
            }
            for (String str : help) {
                parent.put(str, cur);
            }
            help.clear();
            return cur;
        }

        private void union(String s1, String s2) {
            if (parent.containsKey(s1) && parent.containsKey(s2)) {
                String f1 = find(s1);
                String f2 = find(s2);
                if (!f1.equals(f2)) {
                    int size1 = size.get(f1);
                    int size2 = size.get(f2);
                    String big = size1 >= size2 ? f1 : f2;
                    String small = big == f1 ? f2 : f1;
                    parent.put(small, big);
                    size.put(big, size1 + size2);
                    sets--;
                }
            }
        }

        public int connect(int r, int c) {
            String key = r + "_" + c;
            parent.computeIfAbsent(key, k -> {
                parent.put(key, key);
                size.put(key, 1);
                sets++;
                String up = r - 1 + "_" + c;
                String down = r + 1 + "_" + c;
                String left = r + "_" + (c - 1);
                String right = r + "_" + (c + 1);
                union(up, key);
                union(down, key);
                union(left, key);
                union(right, key);
                return k;
            });
            return sets;
        }

    }
}
