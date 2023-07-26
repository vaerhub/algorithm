package io.arkvaer.algorithm.basic.day16;

import java.util.*;

/**
 * 基于深度优先遍历的(基于图的)
 * OJ链接：<a href="https://www.lintcode.com/problem/topological-sorting">LintCode</a>
 */
public class TopologicalOrderDFS2 {
    // 不要提交这个类
    public static class DirectedGraphNode {
        public int label;
        public ArrayList<DirectedGraphNode> neighbors;

        public DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<>();
        }
    }


    public static class Record {
        public DirectedGraphNode node;
        public long nodeCount;

        public Record(DirectedGraphNode node, long nodeCount) {
            this.node = node;
            this.nodeCount = nodeCount;
        }
    }

    public static class RecordComparator implements Comparator<Record> {
        @Override
        public int compare(Record o1, Record o2) {
            return Long.compare(o2.nodeCount, o1.nodeCount);
        }
    }

    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        HashMap<DirectedGraphNode, Record> recordMap = new HashMap<>(graph.size());
        for (DirectedGraphNode node : graph) {
            getRecordDeep(node, recordMap);
        }
        ArrayList<Record> records = new ArrayList<>(recordMap.values());
        records.sort(new RecordComparator());
        ArrayList<DirectedGraphNode> graphNodes = new ArrayList<>();
        for (Record r : records) {
            graphNodes.add(r.node);
        }
        return graphNodes;
    }

    public static Record getRecordDeep(DirectedGraphNode cur, HashMap<DirectedGraphNode, Record> recordMap) {
        if (recordMap.containsKey(cur)) {
            return recordMap.get(cur);
        }
        long count = 0;
        for (DirectedGraphNode neighbor : cur.neighbors) {
            count += getRecordDeep(neighbor, recordMap).nodeCount;
        }
        Record ans = new Record(cur, count + 1);
        recordMap.put(cur, ans);
        return ans;
    }

    public static void main(String[] args) {
        TopologicalOrderDFS2 dfs2 = new TopologicalOrderDFS2();
        String[] arr = {"0", "1", "2", "3#1", "4#2", "4", "5#3", "4", "5#4#5"};
        ArrayList<DirectedGraphNode> graph = initGraph(arr);
        List<DirectedGraphNode> ans = dfs2.topSort(graph);
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
