import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: liyaguang
 * @create: 2018-09-21 13:10
 **/
public class S1TwoSum {

    public static void main(String[] args) {
        int[] r = new S1TwoSum().twoSum(new int[]{11, 15, 2, 7}, 9);
        for (int i = 0; i < r.length; i++) {
            System.out.println(r[i]);
        }
    }

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> mapNums = new HashMap<>(nums.length);
        int[] results = new int[2];
        for (int i = 0; i < nums.length; i++) {
            if (mapNums.keySet().contains(target - nums[i])) {
                results[0] = mapNums.get(target - nums[i]);
                results[1] = i;
                return results;
            }
            mapNums.put(nums[i], i);
        }
        return results;
    }
}