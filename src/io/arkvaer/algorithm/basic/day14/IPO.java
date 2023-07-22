package io.arkvaer.algorithm.basic.day14;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 输入:正数数组costs, 正数数组profits、正数K、正数M
 * costs[i]表示i号项目的花费
 * profits[i]表示i号项目在扣除花费之后还能挣到的钱(利润)
 * K表示你只能串行的最多做k个项目
 * M表示你初始的资金
 * 说明: 每做完一个项目，马上获得的收益，可以支持你去做下一个项目。不能并行的做项目。
 * 输出:你最后获得的最大钱数。
 *
 * @author waver
 * @date 2023/7/22 21:12
 */
public class IPO {

    /**
     * 每次从所有项目中选择初始资金>=花费的项目中选择利润最大的项目
     * @param k       串行的最多做k个项目
     * @param m       初始的资金
     * @param profits 表示项目在扣除花费之后还能挣到的钱(利润)
     * @param costs   表示项目的花费
     * @return 最后获得的最大钱数
     */
    public static int findMaximizedCapital(int k, int m, int[] profits, int[] costs) {
        PriorityQueue<Project> minCostQueue = new PriorityQueue<>(new MinCostComparator());
        PriorityQueue<Project> maxCostQueue = new PriorityQueue<>(new MaxProfitComparator());
        for (int i = 0; i < profits.length; i++) {
            minCostQueue.add(new Project(profits[i], costs[i]));
        }
        for (int i = 0; i < k; i++) {
            while (!minCostQueue.isEmpty() && minCostQueue.peek().cost <= m) {
                maxCostQueue.add(minCostQueue.poll());
            }
            if (maxCostQueue.isEmpty()) {
                return m;
            }
            m += maxCostQueue.poll().profit;
        }
        return m;
    }


    public static class MinCostComparator implements Comparator<Project> {
        @Override
        public int compare(Project o1, Project o2) {
            return o1.cost - o2.cost;
        }
    }

    public static class MaxProfitComparator implements Comparator<Project> {
        @Override
        public int compare(Project o1, Project o2) {
            return o2.profit - o1.profit;
        }
    }


    public static class Project {
        /**
         * 利润
         */
        public int profit;
        /**
         * 花费
         */
        public int cost;

        public Project(int profit, int cost) {
            this.profit = profit;
            this.cost = cost;
        }
    }
}

