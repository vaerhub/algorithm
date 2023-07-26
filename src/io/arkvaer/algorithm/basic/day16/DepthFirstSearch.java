package io.arkvaer.algorithm.basic.day16;

import io.arkvaer.algorithm.utils.AlgUtil;

import java.util.*;

/**
 * 图的深度优先遍历Depth-First-Search (DFS)
 */
public class DepthFirstSearch {
    public static void dfs(Node start) {
        if (start == null) {
            return;
        }
        Deque<Node> stack = new LinkedList<>();
        Set<Node> set = new HashSet<>();
        stack.push(start);
        set.add(start);
        AlgUtil.console(start.value);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            for (Node node : cur.neighbor) {
                if (!set.contains(node)) {
                    stack.push(cur);
                    stack.push(node);
                    set.add(node);
                    AlgUtil.console(node.value);
                    // 每次只取一个邻居节点
                    break;
                }
            }
        }
    }
}
