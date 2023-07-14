package io.arkvaer.algorithm.basic.day11;

/**
 * 纸条折叠:
 * 面对纸条, 将纸条从下往上向内对折, 重复此过程, 打印打开纸条后从上到下折痕的方向
 */
public class PaperFolding {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int v) {
            value = v;
        }
    }


    /**
     * 打印折痕
     *
     * @param count 纸条折叠次数
     */
    public static void printCrease(int count) {
        print(1, count, true);
    }

    public static void print(int i, int count, boolean down) {
        if (i > count) {
            return;
        }
        print(i + 1, count, true);
        System.out.print(down? "凹 ": "凸 ");
        print(i + 1, count, false);

    }


    public static void main(String[] args) {
        printCrease(4);
    }
}
