package io.arkvaer.algorithm.basic.day16;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Dijkstra 算法(获取图中两点的最短路径)
 */
public class Dijkstra {
    public static Map<Node, Integer> dijkstra1(Node from) {
        Map<Node, Integer> distanceMap = new HashMap<>();
        distanceMap.put(from, 0);
        // 以访问的节点
        Set<Node> visitNodeSet = new HashSet<>();
        // 获取当前未访问过却距离起点最近的节点
        Node minDistanceAndUnVisitNode = getMinDistanceAndUnVisitNode(distanceMap, visitNodeSet);
        while (minDistanceAndUnVisitNode != null) {
            Integer distance = distanceMap.get(minDistanceAndUnVisitNode);
            // 遍历当前节点的所有边
            for (Edge edge : minDistanceAndUnVisitNode.edges) {
                // 当前边的到达节点
                Node toNode = edge.to;
                if (!visitNodeSet.contains(toNode)) {
                    // 如果当前节点未访问过, 则将当前节点放入distanceMap, 距离为distance + 当前边的距离
                    distanceMap.put(toNode, distance + edge.weight);
                } else {
                    // 如果当前节点已经访问过, 则对比走当前边的距离是否小于distanceMap中已经存储的距离, 若小于, 则更新
                    distanceMap.put(toNode, Math.min(distanceMap.get(toNode), distance + edge.weight));
                }
            }
            // 将当前节点加入已访问的节点
            visitNodeSet.add(minDistanceAndUnVisitNode);
            // 再从未访问过的节点中寻找距离最短的节点
            minDistanceAndUnVisitNode = getMinDistanceAndUnVisitNode(distanceMap, visitNodeSet);
        }
        return distanceMap;
    }


    /**
     * 找到距离最短 的 未访问过的节点
     *
     * @param distanceMap 节点和距离对应map
     * @param visitNodes  访问过的节点
     * @return 距离最小的节点
     */
    public static Node getMinDistanceAndUnVisitNode(Map<Node, Integer> distanceMap, Set<Node> visitNodes) {
        Node minDistanceNode = null;
        int minDistance = Integer.MAX_VALUE;
        // 遍历可以访问的所有节点, 从未访问的节点中找到距离最短的一个
        for (Map.Entry<Node, Integer> entry : distanceMap.entrySet()) {
            Node curNode = entry.getKey();
            Integer curDistance = entry.getValue();
            if (!visitNodes.contains(curNode) && curDistance < minDistance) {
                minDistance = curDistance;
                minDistanceNode = curNode;
            }
        }
        return minDistanceNode;
    }


    public static class NodeRecord {
        public Node node;
        public int distance;

        public NodeRecord(Node node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    /**
     * 小根堆
     */
    public static class NodeHeap {
        private Node[] nodes;
        private Map<Node, Integer> indexMap;
        private Map<Node, Integer> distanceMap;

        private int size;

        public NodeHeap(int size) {
            this.size = 0;
            this.nodes = new Node[size];
            this.indexMap = new HashMap<>(size);
            this.distanceMap = new HashMap<>(size);
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isEntered(Node node) {
            return indexMap.containsKey(node);
        }

        public NodeRecord pop() {
            Node node = nodes[0];
            NodeRecord result = new NodeRecord(node, distanceMap.get(node));
            indexMap.put(node, -1);
            distanceMap.remove(node);
            swap(0, size - 1);
            nodes[size - 1] = null;
            heapify(0, --size);
            return result;
        }

        public Node peek() {
            return nodes[0];
        }

        public void heapify(int index, int size) {
            int leftIndex = index * 2 + 1;
            // 从左右孩子中找到最小的
            int smallest = leftIndex + 1 < size && distanceMap.get(nodes[leftIndex + 1]) < distanceMap.get(nodes[leftIndex]) ? leftIndex + 1 : leftIndex;
            smallest = distanceMap.get(nodes[smallest]) < distanceMap.get(nodes[index]) ? smallest : index;
            if (smallest != index) {
                swap(smallest, index);
            }
        }

        public void heapInsert(int index) {
            while (distanceMap.get(nodes[index]) > distanceMap.get((nodes[(index - 1) / 2]))) {
                swap(index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        public void swap(int x, int y) {
            // 交换x和y位置的node
            Node temp = nodes[x];
            nodes[x] = nodes[y];
            nodes[y] = temp;
            // 更新 x y 在indexMap中的index
            indexMap.put(nodes[y], y);
            indexMap.put(nodes[x], x);
        }

        /**
         * 新增/更新/忽略节点
         *
         * @param node     当前节点
         * @param distance 当前节点的距离
         */
        public void addOrUpdateOrIgnore(Node node, int distance) {
            // 如果当前节点还在堆中, 则判断当前距离是否小于之前记录的, 若小于, 则更新
            if (inHeap(node)) {
                distanceMap.put(node, Math.min(distanceMap.get(node), distance));
                heapInsert(indexMap.get(node));
            }
            // 如果节点之前没有加入过堆中, 则加入堆中, 并排好序
            if (!isEntered(node)) {
                nodes[size] = node;
                distanceMap.put(node, distance);
                indexMap.put(node, size);
                heapInsert(size++);
            }
        }

        /**
         * 判断当前节点是否在
         *
         * @param node 当前节点
         * @return 是否在堆中
         */
        public boolean inHeap(Node node) {
            return isEntered(node) && indexMap.get(node) != -1;
        }


    }


    /**
     * 使用加强堆实现Dijkstra算法
     *
     * @param head 图的开始节点
     * @param size 图的大小
     * @return 图中所有节点到头节点的最短距离
     */
    public static Map<Node, Integer> dijkstra2(Node head, int size) {
        NodeHeap nodeHeap = new NodeHeap(size);
        nodeHeap.addOrUpdateOrIgnore(head, 0);
        Map<Node, Integer> result = new HashMap<>(size);
        while (!nodeHeap.isEmpty()) {
            NodeRecord r = nodeHeap.pop();
            Node cur = r.node;
            int distance = r.distance;
            for (Edge edge : cur.edges) {
                nodeHeap.addOrUpdateOrIgnore(edge.to, edge.weight + distance);
            }
            result.put(cur, distance);
        }
        return result;
    }
}
