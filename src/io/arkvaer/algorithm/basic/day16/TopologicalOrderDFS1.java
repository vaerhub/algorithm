package io.arkvaer.algorithm.basic.day16;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

/**
 * 基于深度优先遍历的(基于图的)
 * OJ链接：<a href="https://www.lintcode.com/problem/topological-sorting">LintCode</a>
 */
public class TopologicalOrderDFS1 {
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
        public int deep;

        public Record(DirectedGraphNode node, int deep) {
            this.node = node;
            this.deep = deep;
        }
    }

    public static class RecordComparator implements Comparator<Record> {
        @Override
        public int compare(Record o1, Record o2) {
            return o2.deep - o1.deep;
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
        int maxDeep = 0;
        for (DirectedGraphNode neighbor : cur.neighbors) {
            maxDeep = Math.max(maxDeep, getRecordDeep(neighbor, recordMap).deep);
        }
        Record ans = new Record(cur, maxDeep + 1);
        recordMap.put(cur, ans);
        return ans;
    }
}
