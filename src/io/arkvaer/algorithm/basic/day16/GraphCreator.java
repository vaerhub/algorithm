package io.arkvaer.algorithm.basic.day16;

public class GraphCreator {

    /**
     * matrix 所有的边
     * N*3 的矩阵
     * [weight, from节点上面的值，to节点上面的值]
     * [ 5 , 0 , 7]
     * [ 3 , 0,  1]
     */
    public static Graph createGraph(int[][] matrix) {
        Graph graph = new Graph();
        for (int[] ints : matrix) {
            int weight = ints[0];
            int from = ints[1];
            int to = ints[2];
            if (!graph.nodes.containsKey(from)) {
                graph.nodes.put(from, new Node(from));
            }
            if (!graph.nodes.containsKey(to)) {
                graph.nodes.put(to, new Node(to));
            }
            Node fromNode = graph.nodes.get(from);
            Node toNode = graph.nodes.get(to);
            Edge edge = new Edge(weight, fromNode, toNode);
            fromNode.neighbor.add(toNode);
            fromNode.out++;
            toNode.in++;
            fromNode.edges.add(edge);
            graph.edges.add(edge);
        }
        return graph;
    }

}
