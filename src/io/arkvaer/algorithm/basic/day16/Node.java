package io.arkvaer.algorithm.basic.day16;

import java.util.ArrayList;
import java.util.List;

/**
 * 图的节点
 */
public class Node {

    public int value;
    public int in;
    public int out;
    public List<Node> neighbor;
    public List<Edge> edges;

    public Node(int value) {
        this.value = value;
        this.in = 0;
        this.out = 0;
        this.neighbor = new ArrayList<>();
        this.edges = new ArrayList<>();
    }
}
