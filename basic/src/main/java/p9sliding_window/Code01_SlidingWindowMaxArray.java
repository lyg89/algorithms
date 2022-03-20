package p9sliding_window;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class Code01_SlidingWindowMaxArray {

    public static int[] getMaxWindow(int[] nums, int k) {
        if (nums == null || k < 1 || nums.length < k) {
            return null;
        }

        Deque<Integer> dqMax = new LinkedList<>();
        int[] result = new int[nums.length - k + 1];
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            while (!dqMax.isEmpty() && nums[dqMax.peekLast()] <= nums[i]) {
                dqMax.pollLast();
            }
            dqMax.addLast(i);
            if (dqMax.peekFirst() == i - k) {
                dqMax.pollFirst();
            }
            if (i >= k - 1) {
                result[index++] = nums[dqMax.peekFirst()];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] res = getMaxWindow(new int[]{1, -1}, 1);
        System.out.println(Arrays.toString(res));
    }
}
