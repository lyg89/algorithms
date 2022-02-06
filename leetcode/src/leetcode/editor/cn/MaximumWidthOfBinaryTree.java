//Given the root of a binary tree, return the maximum width of the given tree. 
//
// The maximum width of a tree is the maximum width among all levels. 
//
// The width of one level is defined as the length between the end-nodes (the le
//ftmost and rightmost non-null nodes), where the null nodes between the end-nodes
// are also counted into the length calculation. 
//
// It is guaranteed that the answer will in the range of 32-bit signed integer. 
//
//
// 
// Example 1: 
//
// 
//Input: root = [1,3,2,5,3,null,9]
//Output: 4
//Explanation: The maximum width existing in the third level with the length 4 (
//5,3,null,9).
// 
//
// Example 2: 
//
// 
//Input: root = [1,3,null,5,3]
//Output: 2
//Explanation: The maximum width existing in the third level with the length 2 (
//5,3).
// 
//
// Example 3: 
//
// 
//Input: root = [1,3,2,5]
//Output: 2
//Explanation: The maximum width existing in the second level with the length 2 
//(3,2).
// 
//
// 
// Constraints: 
//
// 
// The number of nodes in the tree is in the range [1, 3000]. 
// -100 <= Node.val <= 100 
// 
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 
// 👍 298 👎 0


package leetcode.editor.cn;

import leetcode.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class MaximumWidthOfBinaryTree {
    public static void main(String[] args) {
        Solution solution = new MaximumWidthOfBinaryTree().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // 最大宽度
        int max = 1;

        Queue<AnnotatedNode> queue = new LinkedList<>();
        queue.offer(new AnnotatedNode(root, 1, 1));
        int curLevel = 1;
        int curLevelLeftPos = 1;
        while (!queue.isEmpty()) {
            AnnotatedNode curNode = queue.poll();
            if (curNode.node != null) {
                queue.offer(new AnnotatedNode(curNode.node.left, curNode.level + 1, curNode.pos * 2));
                queue.offer(new AnnotatedNode(curNode.node.right, curNode.level + 1, curNode.pos * 2 + 1));
                if (curNode.level != curLevel) {
                    curLevelLeftPos = curNode.pos;
                    curLevel++;
                }
                max = Math.max(max, curNode.pos - curLevelLeftPos + 1);
            }

        }
        return max;
    }
    class AnnotatedNode {
        TreeNode node;
        int level, pos;
        AnnotatedNode(TreeNode n, int l, int p) {
            node = n;
            level = l;
            pos = p;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}