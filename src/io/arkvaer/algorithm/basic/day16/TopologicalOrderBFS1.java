package io.arkvaer.algorithm.basic.day16;

import java.util.*;

/**
 * 图的拓扑序-使用广度优先遍历
 * OJ链接：https://www.lintcode.com/problem/topological-sorting
 */
public class TopologicalOrderBFS1 {
    public static class DirectedGraphNode {
        public int label;
        public List<DirectedGraphNode> neighbors;

        public DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<>();
        }
    }

    public List<DirectedGraphNode> topSort(List<DirectedGraphNode> graph) {
        HashMap<DirectedGraphNode, Integer> integerHashMap = new HashMap<>();
        for (DirectedGraphNode directedGraphNode : graph) {
            integerHashMap.put(directedGraphNode, 0);
        }
        for (DirectedGraphNode graphNode : graph) {
            for (DirectedGraphNode neighbor : graphNode.neighbors) {
                integerHashMap.put(neighbor, integerHashMap.get(neighbor) + 1);
            }
        }
        Queue<DirectedGraphNode> zeroQueue = new LinkedList<>();
        for (Map.Entry<DirectedGraphNode, Integer> entry : integerHashMap.entrySet()) {
            if (entry.getValue() == 0) {
                zeroQueue.add(entry.getKey());
            }
        }
        List<DirectedGraphNode> ans = new ArrayList<>();
        while (!zeroQueue.isEmpty()) {
            DirectedGraphNode cur = zeroQueue.poll();
            ans.add(cur);
            for (DirectedGraphNode neighbor : cur.neighbors) {
                integerHashMap.put(neighbor, integerHashMap.get(neighbor) - 1);
                if (integerHashMap.get(neighbor) == 0) {
                    zeroQueue.add(neighbor);
                }
            }

        }
        return ans;
    }


    public static void main(String[] args) {
        TopologicalOrderBFS1 bfs1 = new TopologicalOrderBFS1();
        String[] arr = {"0", "1", "2", "3#1", "4#2", "4", "5#3", "4", "5#4#5"};
        ArrayList<DirectedGraphNode> graph = initGraph(arr);
        List<DirectedGraphNode> ans = bfs1.topSort(graph);
        for (DirectedGraphNode an : ans) {
            System.out.println(an.label);
        }
    }


    public static ArrayList<DirectedGraphNode> initGraph(String[] arr) {
        ArrayList<DirectedGraphNode> graph = new ArrayList<>();
        Map<Integer, DirectedGraphNode> nodeMap = new HashMap<>();
        ArrayList<Integer> pre = new ArrayList<>();
        for (String value : arr) {
            if (value.contains("#")) {
                String[] split = value.split("#");
                for (int i = 0; i < split.length; i++) {
                    int label = Integer.parseInt(split[i]);
                    pre.add(label);
                    if (i < split.length - 1) {
                        createNode(nodeMap, pre, graph);
                        pre.clear();
                    }
                }
            } else {
                pre.add(Integer.parseInt(value));
            }
        }
        return graph;
    }
    public static void createNode(Map<Integer, DirectedGraphNode> nodeMap, List<Integer> pre, List<DirectedGraphNode> graph) {
        DirectedGraphNode cur;
        Integer label = pre.get(0);
        if (nodeMap.containsKey(label)) {
            cur = nodeMap.get(label);
        } else {
            cur = new DirectedGraphNode(label);
            graph.add(cur);
            nodeMap.put(label, cur);
        }
        for (int j = 1; j < pre.size(); j++) {
            DirectedGraphNode neighbor;
            label = pre.get(j);
            if (nodeMap.containsKey(label)) {
                neighbor = nodeMap.get(label);
            } else {
                neighbor = new DirectedGraphNode(label);
                graph.add(neighbor);
                nodeMap.put(label, neighbor);
            }
            cur.neighbors.add(neighbor);
        }
    }
}
