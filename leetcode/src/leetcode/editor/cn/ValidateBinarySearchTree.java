//给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。 
//
// 有效 二叉搜索树定义如下： 
//
// 
// 节点的左子树只包含 小于 当前节点的数。 
// 节点的右子树只包含 大于 当前节点的数。 
// 所有左子树和右子树自身必须也是二叉搜索树。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [2,1,3]
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：root = [5,1,4,null,null,3,6]
//输出：false
//解释：根节点的值是 5 ，但是右子节点的值是 4 。
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数目范围在[1, 10⁴] 内 
// -2³¹ <= Node.val <= 2³¹ - 1 
// 
// Related Topics 树 深度优先搜索 二叉搜索树 二叉树 👍 1406 👎 0

  
  package leetcode.editor.cn;

import leetcode.common.TreeNode;

public class ValidateBinarySearchTree{
      public static void main(String[] args) {
           Solution solution = new ValidateBinarySearchTree().new Solution();
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
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        return process(root).isBST;
    }

    private static Info process(TreeNode node) {
        if (node == null) {
            return null;
        }
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);

        boolean isLeftBST = leftInfo == null ? true : leftInfo.isBST;
        boolean isRightBST = rightInfo == null ? true : rightInfo.isBST;

        boolean isLeftValueValid = leftInfo == null ? true : leftInfo.max < node.val;
        boolean isRightValueValid = rightInfo == null ? true : rightInfo.min > node.val;
        boolean isBST = isLeftBST && isRightBST && isLeftValueValid && isRightValueValid;

        int min = node.val;
        int max = node.val;
        if (leftInfo != null) {
            min = Math.min(leftInfo.min, min);
            max = Math.max(leftInfo.max, max);
        }
        if (rightInfo != null) {
            min = Math.min(rightInfo.min, min);
            max = Math.max(rightInfo.max, max);
        }
        return new Info(isBST, min, max);
    }

    private static class Info {
        private boolean isBST;
        private int min;
        private int max;
        public Info(boolean i, int min, int max) {
            isBST = i;
            this.min = min;
            this.max = max;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

  }