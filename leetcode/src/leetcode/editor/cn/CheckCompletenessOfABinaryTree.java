//给定一个二叉树的 root ，确定它是否是一个 完全二叉树 。 
//
// 在一个 完全二叉树 中，除了最后一个关卡外，所有关卡都是完全被填满的，并且最后一个关卡中的所有节点都是尽可能靠左的。它可以包含 1 到 2ʰ 节点之间的最
//后一级 h 。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：root = [1,2,3,4,5,6]
//输出：true
//解释：最后一层前的每一层都是满的（即，结点值为 {1} 和 {2,3} 的两层），且最后一层中的所有结点（{4,5,6}）都尽可能地向左。
// 
//
// 示例 2： 
//
// 
//
// 
//输入：root = [1,2,3,4,5,null,7]
//输出：false
//解释：值为 7 的结点没有尽可能靠向左侧。
// 
//
// 
//
// 提示： 
//
// 
// 树的结点数在范围 [1, 100] 内。 
// 1 <= Node.val <= 1000 
// 
// Related Topics 树 广度优先搜索 二叉树 👍 173 👎 0


package leetcode.editor.cn;

import leetcode.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class CheckCompletenessOfABinaryTree {
    public static void main(String[] args) {
        Solution solution = new CheckCompletenessOfABinaryTree().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)


    class Solution {
        public boolean isCompleteTree(TreeNode root) {
            if (root == null) {
                return true;
            }

            //Queue<TreeNode> queue = new LinkedList<>();
            //queue.add(root);
            //
            //boolean isStartLeaf = false;
            //TreeNode node;
            //TreeNode l;
            //TreeNode r;
            //while (!queue.isEmpty()) {
            //    node = queue.poll();
            //    l = node.left;
            //    r = node.right;
            //    if (r != null && l == null) {
            //        return false;
            //    }
            //    if (isStartLeaf && (l != null || r != null)) {
            //        return false;
            //    }
            //    if (l != null) {
            //        queue.add(l);
            //    }
            //    if (r != null) {
            //        queue.add(r);
            //    }
            //    if (!isStartLeaf && (l == null || r == null)) {
            //        isStartLeaf = true;
            //    }
            //}
            //return true;

            // method 02
            return process(root).isCBT;
        }

        public static class Info {
            public boolean isFull;
            public boolean isCBT;
            public int height;

            public Info(boolean full, boolean cbt, int h) {
                isFull = full;
                isCBT = cbt;
                height = h;
            }
        }

        public static Info process(TreeNode xNode) {
            if (xNode == null) {
                return new Info(true, true, 0);
            }
            Info leftInfo = process(xNode.left);
            Info rightInfo = process(xNode.right);

            int height = Math.max(leftInfo.height, rightInfo.height) + 1;
            boolean isFull = leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height;
            boolean isCBT = false;
            if (isFull) {
                isCBT = true;
            } else if (leftInfo.isCBT && rightInfo.isCBT) {

                if (!leftInfo.isFull && rightInfo.isFull && leftInfo.height - rightInfo.height == 1) {
                    isCBT = true;
                } else if (
                        leftInfo.isFull && rightInfo.isFull && leftInfo.height - rightInfo.height == 1
                ) {
                    isCBT = true;
                } else if (
                        leftInfo.isFull && !rightInfo.isFull && leftInfo.height == rightInfo.height
                ) {
                    isCBT = true;
                }
            }

            return new Info(isFull, isCBT, height);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}