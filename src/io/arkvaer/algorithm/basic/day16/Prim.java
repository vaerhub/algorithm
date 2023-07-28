package io.arkvaer.algorithm.basic.day16;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Prim 算法计算图的最小生成树
 * 仅限无定向图(undirected graph only)
 */
public class Prim {

    public static class EdgeComparator implements Comparator<Edge> {
        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }

    public static Set<Edge> primMST(Graph graph) {
        // 当前已经解锁的边
        PriorityQueue<Edge> unlockEdgeQueue = new PriorityQueue<>(new EdgeComparator());
        Set<Node> unlockNodeSet = new HashSet<>();
        Set<Edge> result = new HashSet<>();
        // 随便选择一个点, 从该点开始
        for (Node node : graph.nodes.values()) {
            if (!unlockNodeSet.contains(node)) {
                unlockNodeSet.add(node);
                unlockEdgeQueue.addAll(node.edges);
                if (!unlockEdgeQueue.isEmpty()) {
                    Edge edge = unlockEdgeQueue.poll();
                    Node to = edge.to;
                    if (!unlockNodeSet.contains(to)) {
                        unlockNodeSet.add(to);
                        result.add(edge);
                        unlockEdgeQueue.addAll(to.edges);
                    }
                }
            }
            // 确定只有一颗树时可以直接break, 不加break可以防止森林的情况
            // break;
        }
        return result;
    }


    /**
     * 请保证graph是连通图
     * graph[i][j]表示点i到点j的距离，如果是系统最大值代表无路
     * 返回值是最小连通图的路径之和
     *
     * @param graph
     * @return
     */
    public static int prim(int[][] graph) {
        int size = graph.length;
        int[] distances = new int[size];
        // 记录已经访问过的点
        boolean[] visit = new boolean[size];
        visit[0] = true;
        // 将0节点发出的所有路径放入distances中
        System.arraycopy(graph[0], 0, distances, 0, size);
        int sum = 0;
        for (int i = 1; i < size; i++) {
            int minPath = Integer.MAX_VALUE;
            int minIndex = -1;
            for (int j = 0; j < size; j++) {
                // 找到不会形成环 且 距离最小的边
                if (!visit[j] && distances[j] < minPath) {
                    minPath = distances[j];
                    minIndex = j;
                }
            }
            // 如果没有找到不形成环且又通路的边, 则直接返回
            if (minIndex == -1) {
                return sum;
            }
            // 将当前节点设置为已访问的节点
            visit[minIndex] = true;
            sum += minPath;
            for (int j = 0; j < size; j++) {
                // 找到从minIndex出发的不会形成环且最短的边
                if (!visit[j] && distances[j] < graph[minIndex][j]) {
                    distances[j] = graph[minIndex][j];
                }
            }
        }
        return sum;
    }
}
