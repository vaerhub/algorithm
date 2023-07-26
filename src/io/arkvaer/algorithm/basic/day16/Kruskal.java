package io.arkvaer.algorithm.basic.day16;

import java.util.*;

/**
 * Kruskal 图最小生成树算法
 * 使用并查集, 过滤会产生环的边
 * 仅限无定向图(undirected graph only)
 */
public class Kruskal {

    public static class UnionFind {
        private final Map<Node, Node> fatherMap;
        private final Map<Node, Integer> sizeMap;

        public UnionFind() {
            fatherMap = new HashMap<>();
            sizeMap = new HashMap<>();
        }

        public void makeSet(Collection<Node> nodes) {
            fatherMap.clear();
            sizeMap.clear();
            for (Node node : nodes) {
                fatherMap.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        private Node findFather(Node node) {
            Deque<Node> queue = new LinkedList<>();
            while (node != fatherMap.get(node)) {
                queue.push(node);
                node = fatherMap.get(node);
            }
            while (!queue.isEmpty()) {
                fatherMap.put(queue.pop(), node);
            }
            return node;
        }

        private boolean isSameSet(Node a, Node b) {
            return findFather(a) == findFather(b);
        }

        private void union(Node a, Node b) {
            Node fa = findFather(a);
            Node fb = findFather(b);
            if (fa != fb) {
                Integer sa = sizeMap.get(fa);
                Integer sb = sizeMap.get(fb);
                if (sa > sb) {
                    fatherMap.put(fb, fa);
                    sizeMap.put(fa, sa + sb);
                } else {
                    fatherMap.put(fa, fb);
                    sizeMap.put(fb, sa + sb);
                }
            }
        }
    }

    public Set<Edge> kruskalMST(Graph graph) {
        UnionFind uf = new UnionFind();
        uf.makeSet(graph.nodes.values());
        // 将所有边按从小到大排序
        PriorityQueue<Edge> edgePriorityQueue = new PriorityQueue<>(graph.edges);
        Set<Edge> edgeSet = new HashSet<>();
        while (!edgePriorityQueue.isEmpty()) {
            Edge edge = edgePriorityQueue.poll();
            // 排除掉会产生环的边
            if (!uf.isSameSet(edge.from, edge.to)) {
                uf.union(edge.from, edge.to);
                edgeSet.add(edge);
            }
        }
        return edgeSet;
    }


}
