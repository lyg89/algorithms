package p5greedy;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 *
 * https://leetcode-cn.com/problems/ipo/
 * @author liyaguang11
 * @date 2022/2/18
 */
public class Code05_IPO {

    /**
     *
     * @param k 最多投资次数
     * @param w 初始资金
     * @param profits 纯利润
     * @param capital 每个项目最小启动资本
     * @return
     */
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        PriorityQueue<Program> costPriorityQueue = new PriorityQueue<>(new MinCostComparator());
        PriorityQueue<Program> profitPriorityQueue = new PriorityQueue<>(new MaxProfitComparator());
        for (int i = 0; i < profits.length; i++) {
            costPriorityQueue.add(new Program(capital[i], profits[i]));
        }

        int i = 0;
        while (i < k) {
            while (!costPriorityQueue.isEmpty() && costPriorityQueue.peek().cost <= w) {
                profitPriorityQueue.add(costPriorityQueue.poll());
            }
            if (profitPriorityQueue.isEmpty()) {
                return w;
            }
            w += profitPriorityQueue.poll().profit;
            i++;

        }
        return w;
    }

    private static class Program {
        private int cost;
        private int profit;

        public Program(int cost, int profit) {
            this.cost = cost;
            this.profit = profit;
        }
    }

    private static class MinCostComparator implements Comparator<Program> {
        @Override
        public int compare(Program o1, Program o2) {
            return o1.cost - o2.cost;
        }
    }

    private static class MaxProfitComparator implements Comparator<Program> {
        @Override
        public int compare(Program o1, Program o2) {
            return o2.profit - o1.profit;
        }
    }
}
