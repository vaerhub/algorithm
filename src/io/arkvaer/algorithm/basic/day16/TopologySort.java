package io.arkvaer.algorithm.basic.day16;

import java.util.*;

/**
 * 拓扑排序
 */
public class TopologySort {
    public static List<Node> sortedTopology(Graph graph) {
        List<Node> ans = new ArrayList<>();
        Map<Node, Integer> inMap = new HashMap<>();
        Queue<Node> zeroQueue = new LinkedList<>();
        for (Node node : graph.nodes.values()) {
            inMap.put(node, node.in);
            if (node.in == 0) {
                zeroQueue.offer(node);
            }
        }
        while (!zeroQueue.isEmpty()) {
            Node cur = zeroQueue.poll();
            ans.add(cur);
            for (Node node : cur.neighbor) {
                inMap.put(node, inMap.get(node) - 1);
                if (node.in == 0) {
                    zeroQueue.add(node);
                }
            }
        }
        return ans;
    }
}
