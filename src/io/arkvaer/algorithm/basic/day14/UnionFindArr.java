package io.arkvaer.algorithm.basic.day14;

import java.io.*;

/**
 * 并查集数组实现方式
 * 测试链接 : <a href="https://www.nowcoder.com/questionTerminal/e7ed657974934a30b2010046536a5372">测试链接</a>
 */
public class UnionFindArr {
    public static int MAX_N = 1000001;
    /**
     * 记录当前节点的代表节点
     */
    public static int[] father = new int[MAX_N];
    /**
     * 记录每个集合的大小
     */
    public static int[] size = new int[MAX_N];

    public static int[] help = new int[MAX_N];

    public static void init(int n) {
        for (int i = 0; i <= n; i++) {
            father[i] = i;
            size[i] = 1;
        }
    }

    /**
     * 寻找i的代表节点
     *
     * @param i 寻找的节点
     * @return i的代表节点
     */
    public static int find(int i) {
        int hi = 0;
        // 通过father记录的路径寻找index的代表节点
        while (i != father[i]) {
            // 记录在查找沿途找到的代表节点不是自己本身且[寻找路径 > 1] 的节点
            help[hi++] = i;
            i = father[i];
        }
        // 将父节点代表节点不是自己本身且[寻找路径 > 1] 的节点的代表节点直接设置为当前代表节点, 实现查询路径拍平
        // hi--过滤 寻找路径 = 1的节点
        for (hi--; hi >= 0; hi--) {
            father[help[hi]] = i;
        }
        return i;
    }

    /**
     * 判断x和y是否为同一个集合
     *
     * @param x x
     * @param y y
     * @return 是否为同一个集合
     */
    public static boolean isSameSet(int x, int y) {
        return find(x) == find(y);
    }


    public static void union(int x, int y) {
        // 找到x的代表节点
        int fx = find(x);
        // 找到x的代表节点
        int fy = find(y);
        if (fx != fy) {
            if (size[fx] > size[fy]) {
                size[fx] += size[fy];
                father[fy] = fx;
            } else {
                size[fy] += size[fx];
                father[fx] = fy;
            }
        }

    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            int n = (int) in.nval;
            init(n);
            in.nextToken();
            int m = (int) in.nval;
            for (int i = 0; i < m; i++) {
                in.nextToken();
                int op = (int) in.nval;
                in.nextToken();
                int x = (int) in.nval;
                in.nextToken();
                int y = (int) in.nval;
                if (op == 1) {
                    out.println(isSameSet(x, y) ? "Yes" : "No");
                    out.flush();
                } else {
                    union(x, y);
                }
            }
        }
    }

}
