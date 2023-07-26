package io.arkvaer.algorithm.basic.day16;

import io.arkvaer.algorithm.utils.AlgUtil;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 图的广度优先遍历 Breadth-first search(BFS)
 */
public class BreadthFirstSearch {
    public static void bfs(Node start) {
        if (start == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        Set<Node> set = new HashSet<>();
        queue.add(start);
        set.add(start);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            AlgUtil.console(cur.value);
            for (Node node : cur.neighbor) {
                if (!set.contains(node)) {
                    queue.add(node);
                    set.add(node);
                }
            }
        }
    }

}
