package io.arkvaer.algorithm.basic.day15;

/**
 * 有 n 个城市，其中一些彼此相连，另一些没有相连。如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连。
 * 省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。
 * 给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，而 isConnected[i][j] = 0 表示二者不直接相连。
 * 返回矩阵中 省份 的数量。
 *
 * @author waver
 * @date 2023/7/23 19:07
 */
public class FindCircles {
    public int findCircleNum(int[][] isConnected) {
        Province province = new Province(isConnected.length);
        for (int i = 0; i <isConnected.length; i++) {
            for (int j = 0; j < isConnected.length; j++) {
                if (i != j && isConnected[i][j] == 1) {
                    province.union(i, j);
                }
            }
        }
        return province.circleNum;
    }

    public static class Province {

        /**
         * 记录各个城市的代表城市
         * provincial capital
         */
        private final int[] pc;
        private final int[] size;

        private final int[] help;

        int circleNum;

        public Province(int len) {
            this.pc = new int[len];
            this.size = new int[len];
            this.help = new int[len];
            this.circleNum = len;
            for (int i = 0; i <len; i++) {
                this.pc[i] = i;
                this.size[i] = 1;
            }
        }


        /**
         * 返回x的代表城市
         *
         * @param x x
         * @return x的代表城市
         */
        public int find(int x) {
            int hi = 0;
            while (x != pc[x]) {
                help[hi++] = x;
                x = pc[x];
            }
            for (hi--; hi > 0; hi--) {
                pc[help[hi]] = x;
            }
            return x;
        }


        public void union(int i, int j) {
            int pi = find(i);
            int pj = find(j);
            if (pi != pj) {
                if (size[pi] > size[pj]) {
                    size[pi] += size[pj];
                    pc[pj] = pi;
                } else {
                    size[pj] = size[pi];
                    pc[pi] = pj;
                }
                circleNum--;
            }
        }
    }


    public static void main(String[] args) {
        int[][] isConnected = {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
        FindCircles fc = new FindCircles();
        int circleNum = fc.findCircleNum(isConnected);
        System.out.println(circleNum);
    }
}
