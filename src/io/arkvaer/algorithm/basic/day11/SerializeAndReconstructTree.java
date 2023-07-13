package io.arkvaer.algorithm.basic.day11;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * 序列化和反序列化树
 */
public class SerializeAndReconstructTree {


    /**
     * <p>
     * 二叉树可以通过先序、后序或者按层遍历的方式序列化和反序列化，
     * 以下代码全部实现了。
     * 但是，二叉树无法通过中序遍历的方式实现序列化和反序列化
     * 因为不同的两棵树，可能得到同样的中序序列，即便补了空位置也可能一样。
     * 比如如下两棵树
     * <br/>
     * &nbsp;&nbsp;&nbsp;&nbsp;__2<br>
     * &nbsp;&nbsp;/<br/>
     * &nbsp;1<br/>
     * 和<br/>
     * &nbsp;1__<br/>
     * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\<br/>
     * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2<br/>
     * 补足空位置的中序遍历结果都是{ null, 1, null, 2, null}
     * </p>
     */
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    /**
     * 通过先序遍历进行序列化
     *
     * @param head 头节点
     */
    public static Queue<String> perSerialize(Node head) {
        Queue<String> result = new LinkedList<>();
        pres(head, result);
        return result;
    }

    /**
     * 通过先序遍历进行序列化
     *
     * @param node   当前节点
     * @param result 结果集
     */
    public static void pres(Node node, Queue<String> result) {
        if (node != null) {
            result.add(String.valueOf(node.value));
            pres(node.left, result);
            pres(node.right, result);
        } else {
            result.add(null);
        }
    }

    /**
     * 通过中序遍历进行序列化
     *
     * @param head 头节点
     * @return 结果集
     * @deprecated 中序遍历存在歧义, 不可用
     */
    public static Queue<String> inSerial(Node head) {
        Queue<String> ans = new LinkedList<>();
        ins(head, ans);
        return ans;
    }


    public static void ins(Node head, Queue<String> ans) {
        if (head == null) {
            ans.add(null);
        } else {
            ins(head.left, ans);
            ans.add(String.valueOf(head.value));
            ins(head.right, ans);
        }
    }


    /**
     * 通过先序遍历进行序列化
     *
     * @param head 头节点
     */
    public static Queue<String> posSerialize(Node head) {
        Queue<String> result = new LinkedList<>();
        poss(head, result);
        return result;
    }

    /**
     * 通过先序遍历进行序列化
     *
     * @param node   当前节点
     * @param result 结果集
     */
    public static void poss(Node node, Queue<String> result) {
        if (node != null) {
            poss(node.left, result);
            poss(node.right, result);
            result.add(String.valueOf(node.value));
        } else {
            result.add(null);
        }
    }


    /**
     * 将先序遍历的结果进行反序列化
     *
     * @param queue 序列化队列
     * @return 反序列化后的二叉树
     */
    public static Node buildByPreQueue(Queue<String> queue) {
        if (queue == null || queue.isEmpty()) {
            return null;
        }
        return preBuild(queue);
    }


    /**
     * 将先序遍历的结果进行反序列化
     *
     * @param queue 序列化队列
     * @return 反序列化后的二叉树节点
     */
    public static Node preBuild(Queue<String> queue) {
        String value = queue.poll();
        if (value == null) {
            return null;
        }
        Node head = new Node(Integer.parseInt(value));
        head.left = preBuild(queue);
        head.right = preBuild(queue);
        return head;
    }

    /**
     * 将先序遍历的结果进行反序列化
     *
     * @param queue 序列化队列
     * @return 反序列化后的二叉树
     */
    public static Node buildByPosQueue(Queue<String> queue) {
        if (queue == null || queue.isEmpty()) {
            return null;
        }
        Stack<String> stack = new Stack<>();
        while (!queue.isEmpty()) {
            stack.push(queue.poll());
        }
        return posBuild(stack);
    }


    /**
     * 将先序遍历的结果进行反序列化
     *
     * @param queue 序列化队列
     * @return 反序列化后的二叉树节点
     */
    public static Node posBuild(Stack<String> queue) {
        String value = queue.pop();
        if (value == null) {
            return null;
        }
        Node head = new Node(Integer.parseInt(value));
        head.right = posBuild(queue);
        head.left = posBuild(queue);
        return head;
    }


    public static Queue<String> levelSerialize(Node head) {
        Queue<String> result = new LinkedList<>();
        if (head == null) {
            result.add(null);
        } else {
            result.add(String.valueOf(head.value));
            Queue<Node> queue = new LinkedList<>();
            queue.add(head);
            while (!queue.isEmpty()) {
                head = queue.poll();
                if (head.left != null) {
                    queue.add(head.left);
                    result.add(String.valueOf(head.left.value));
                } else {
                    result.add(null);
                }
                if (head.right != null) {
                    queue.add(head.right);
                    result.add(String.valueOf(head.right.value));
                } else {
                    result.add(null);
                }
            }
        }
        return result;
    }


    public static Node buildByLevel(Queue<String> queue) {
        if (queue == null || queue.isEmpty()) {
            return null;
        }
        Node head = generateNode(queue.poll());
        Queue<Node> nodeQueue = new LinkedList<>();
        if (head != null) {
            nodeQueue.add(head);
        }
        Node cur;
        while (!nodeQueue.isEmpty()) {
            cur = nodeQueue.poll();
            cur.left = generateNode(queue.poll());
            cur.right = generateNode(queue.poll());
            if (cur.left != null) {
                nodeQueue.add(cur.left);
            }
            if (cur.right != null) {
                nodeQueue.add(cur.right);
            }
        }
        return head;
    }

    public static Node generateNode(String value) {
        if (value == null) {
            return null;
        } else {
            return new Node(Integer.parseInt(value));
        }
    }

    // for test
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    // for test
    public static boolean isSameValueStructure(Node head1, Node head2) {
        if (head1 == null && head2 != null) {
            return false;
        }
        if (head1 != null && head2 == null) {
            return false;
        }
        if (head1 == null) {
            return true;
        }
        if (head1.value != head2.value) {
            return false;
        }
        return isSameValueStructure(head1.left, head2.left) && isSameValueStructure(head1.right, head2.right);
    }


    public static void main(String[] args) {
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            Queue<String> pre = perSerialize(head);
            Queue<String> pos = posSerialize(head);
            Queue<String> level = levelSerialize(head);
            Node preBuild = buildByPreQueue(pre);
            Node posBuild = buildByPosQueue(pos);
            Node levelBuild = buildByLevel(level);
            if (!isSameValueStructure(preBuild, posBuild) || !isSameValueStructure(posBuild, levelBuild)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish!");

    }
}
