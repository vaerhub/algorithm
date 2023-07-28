package io.arkvaer.algorithm.basic.day16;

import java.util.*;

/**
 * leetCode 743题，可以用Dijkstra算法解
 * 测试链接 : <a href="https://leetcode.cn/problems/network-delay-time">测试链接</a>
 */
public class NetworkDelayTime {
    public int networkDelayTime(int[][] times, int n, int k) {
        /*
        通过一个二维List代表graph
        graph.get(i)
        i 代表第i个节点
        graph.get(i) 代表第i个节点的边的集合
        graph.get(i).get(j) 代表第i个节点的第j条边
        graph.get(i).get(j)[0] 代表第i个节点的第j条边连接的节点
        graph.get(i).get(j)[1] 代表第i个节点的第j条边的长度
        */
        List<List<int[]>> graph = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] time : times) {
            graph.get(time[0]).add(new int[]{time[1], time[2]});
        }
        PriorityQueue<int[]> heap = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        boolean[] used = new boolean[n + 1];
        heap.add(new int[]{k, 0});
        int max = 0;
        int count = 0;
        while (!heap.isEmpty() && count < n) {
            int[] edge = heap.poll();
            int toNode = edge[0];
            int delay = edge[1];
            // 如果当前节点使用过了
            if (used[toNode]) {
                continue;
            }
            used[toNode] = true;
            count++;
            max = Math.max(max, delay);
            for (int[] next : graph.get(toNode)) {
                if (!used[next[0]]) {
                    heap.add(new int[]{next[0], delay + next[1]});
                }
            }
        }
        // 如果访问到的点小于n, 则存在没访问到的节点, 返回-1
        return count < n ? -1 : max;

    }


    public int networkDelayTime2(int[][] times, int n, int k) {
        List<List<int[]>> network = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            network.add(new ArrayList<>());
        }
        for (int[] time : times) {
            network.get(time[0]).add(new int[]{time[1], time[2]});
        }
        NetHeap netHeap = new NetHeap(n);
        netHeap.add(k, 0);
        int max = 0;
        int count = 0;
        while (!netHeap.isEmpty()) {
            int[] record = netHeap.poll();
            int cur = record[0];
            int delay = record[1];
            count++;
            max = Math.max(max, delay);
            for (int[] next : network.get(cur)) {
                netHeap.add(next[0], delay + next[1]);
            }
        }

        return count < n ? -1 : max;
    }

    public static class NetHeap {
        private int[][] heap;
        private boolean[] used;
        private int[] heapIndex;
        private int size;

        public NetHeap(int size) {
            heap = new int[size + 1][2];
            used = new boolean[size + 1];
            heapIndex = new int[size + 1];
            Arrays.fill(heapIndex, -1);
            this.size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public int[] poll() {
            int[] result = heap[0];
            swap(0, --size);
            heapify(0);
            heapIndex[result[0]] = -1;
            used[result[0]] = true;
            return result;
        }

        public void add(int node, int delay) {
            if (used[node]) {
                return;
            }
            if (heapIndex[node] == -1) {
                heap[size][0] = node;
                heap[size][1] = delay;
                heapIndex[node] = size;
                heapInsert(size++);
            } else {
                int index = heapIndex[node];
                if (heap[index][1] > delay) {
                    heap[index][1] = delay;
                    heapInsert(index);
                }
            }
        }


        public void heapify(int index) {
            int left = index * 2 + 1;
            while (left < size) {
                int smallest = left + 1 < size && heap[left][1] > heap[left + 1][1] ? left + 1 : left;
                smallest = heap[index][1] < heap[smallest][1] ? index : smallest;
                if (smallest == index) {
                    break;
                }
                swap(index, smallest);
                index = smallest;
                left = index * 2 + 1;
            }
        }

        public void heapInsert(int index) {
            int parent = (index - 1) / 2;
            while (heap[index][1] < heap[parent][1]) {
                swap(index, parent);
                index = parent;
                parent = (index - 1) / 2;
            }
        }

        public void swap(int x, int y) {
            int[] tmp = heap[x];
            heap[x] = heap[y];
            heap[y] = tmp;
            int temp = heapIndex[heap[y][0]];
            heapIndex[heap[y][0]] = heapIndex[heap[x][0]];
            heapIndex[heap[x][0]] = temp;
        }


    }


    public static void main(String[] args) {
        int[][] times = {{3, 5, 78}, {2, 1, 1}, {1, 3, 0}, {4, 3, 59}, {5, 3, 85}, {5, 2, 22}, {2, 4, 23}, {1, 4, 43}, {4, 5, 75}, {5, 1, 15}, {1, 5, 91}, {4, 1, 16}, {3, 2, 98}, {3, 4, 22}, {5, 4, 31}, {1, 2, 0}, {2, 5, 4}, {4, 2, 51}, {3, 1, 36}, {2, 3, 59}};
        NetworkDelayTime networkDelayTime = new NetworkDelayTime();
        int i = networkDelayTime.networkDelayTime(times, 5, 5);
//        int i1 = networkDelayTime.networkDelayTime2(times, 5, 5);
        System.out.println(i);
//        System.out.println(i1);
    }


}
