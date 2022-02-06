//Given a binary tree, determine if it is height-balanced. 
//
// For this problem, a height-balanced binary tree is defined as: 
//
// 
// a binary tree in which the left and right subtrees of every node differ in he
//ight by no more than 1. 
// 
//
// 
// Example 1: 
//
// 
//Input: root = [3,9,20,null,null,15,7]
//Output: true
// 
//
// Example 2: 
//
// 
//Input: root = [1,2,2,3,3,null,null,4,4]
//Output: false
// 
//
// Example 3: 
//
// 
//Input: root = []
//Output: true
// 
//
// 
// Constraints: 
//
// 
// The number of nodes in the tree is in the range [0, 5000]. 
// -104 <= Node.val <= 104 
// 
// Related Topics 树 深度优先搜索 二叉树 
// 👍 879 👎 0


package leetcode.editor.cn;

import leetcode.common.TreeNode;

public class BalancedBinaryTree {
    public static void main(String[] args) {
        Solution solution = new BalancedBinaryTree().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode() {}
     * TreeNode(int val) { this.val = val; }
     * TreeNode(int val, TreeNode left, TreeNode right) {
     * this.val = val;
     * this.left = left;
     * this.right = right;
     * }
     * }
     */
    class Solution {
        public boolean isBalanced(TreeNode root) {
            return process(root).isBalanced;
        }

        private Info process(TreeNode node) {
            if (node == null) {
                return new Info(true, 0);
            }
            Info leftInfo = process(node.left);
            Info rightInfo = process(node.right);

            int height = Math.max(leftInfo.height, rightInfo.height) + 1;
            boolean isBalanced = true;
            if (!leftInfo.isBalanced || !rightInfo.isBalanced || Math.abs(leftInfo.height - rightInfo.height) > 1) {
                isBalanced = false;
            }
            return new Info(isBalanced, height);
        }


        class Info {
            boolean isBalanced;
            int height;

            public Info(boolean isBalanced, int height) {
                this.isBalanced = isBalanced;
                this.height = height;
            }
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}