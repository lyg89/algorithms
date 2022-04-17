package leetcode.editor.cn;

import java.util.PriorityQueue;

/**
 * @description:
 * @author: Flash
 * @create: 2022-04-17 21:50
 **/
public class KthLargestElementInAStream {

    //leetcode submit region begin(Prohibit modification and deletion)
    class KthLargest {

        private final int k;
        private final PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

        public KthLargest(int k, int[] nums) {
            this.k = k;
            for (int num : nums) {
                priorityQueue.add(num);
            }
        }

        public int add(int val) {
            this.priorityQueue.add(val);
            while (priorityQueue.size() > k) {
                priorityQueue.poll();
            }
            return priorityQueue.peek();
        }
    }
}
