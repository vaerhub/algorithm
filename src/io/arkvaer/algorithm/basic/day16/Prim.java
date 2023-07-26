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
            // 确定只有一颗树时可以直接break
            // break;
        }
        return result;
    }
}
