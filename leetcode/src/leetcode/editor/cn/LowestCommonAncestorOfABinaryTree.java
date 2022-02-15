//给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。 
//
// 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（
//一个节点也可以是它自己的祖先）。” 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
//输出：3
//解释：节点 5 和节点 1 的最近公共祖先是节点 3 。
// 
//
// 示例 2： 
//
// 
//输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
//输出：5
//解释：节点 5 和节点 4 的最近公共祖先是节点 5 。因为根据定义最近公共祖先节点可以为节点本身。
// 
//
// 示例 3： 
//
// 
//输入：root = [1,2], p = 1, q = 2
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数目在范围 [2, 10⁵] 内。 
// -10⁹ <= Node.val <= 10⁹ 
// 所有 Node.val 互不相同 。 
// p != q 
// p 和 q 均存在于给定的二叉树中。 
// 
// Related Topics 树 深度优先搜索 二叉树 👍 1535 👎 0


package leetcode.editor.cn;

import leetcode.common.TreeNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LowestCommonAncestorOfABinaryTree {
    public static void main(String[] args) {
        Solution solution = new LowestCommonAncestorOfABinaryTree().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)


    class Solution {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            //return process(root, p, q).lowestCommonAncestor;

            if (root == null) {
                return null;
            }
            Map<TreeNode, TreeNode> parentMap = new HashMap<>();
            parentMap.put(root, null);
            fillParentMap(root, parentMap);

            TreeNode cur = p;
            Set<TreeNode> pParents = new HashSet<>();
            pParents.add(p);
            while (cur != null) {
                cur = parentMap.get(cur);
                pParents.add(cur);
            }

            cur = q;
            while (!pParents.contains(cur)) {
                cur = parentMap.get(cur);
            }
            return cur;
        }

        private void fillParentMap(TreeNode node, Map<TreeNode, TreeNode> parentMap) {
            if (node == null) {
                return;
            }
            if (node.left != null) {
                parentMap.put(node.left, node);
                fillParentMap(node.left, parentMap);
            }
            if (node.right != null) {
                parentMap.put(node.right, node);
                fillParentMap(node.right, parentMap);
            }
        }

        private Info process(TreeNode xNode, TreeNode p, TreeNode q) {
            if (xNode == null) {
                return new Info(null, false, false);
            }
            Info leftInfo = process(xNode.left, p, q);
            Info rightInfo = process(xNode.right, p, q);
            boolean findP = leftInfo.findP || rightInfo.findP || xNode == p;
            boolean findQ = leftInfo.findQ || rightInfo.findQ || xNode == q;
            TreeNode lowestCommonAncestor = null;
            if (leftInfo.lowestCommonAncestor != null) {
                lowestCommonAncestor = leftInfo.lowestCommonAncestor;
            } else if (rightInfo.lowestCommonAncestor != null) {
                lowestCommonAncestor = rightInfo.lowestCommonAncestor;
            } else if (findP && findQ) {
                lowestCommonAncestor = xNode;
            }
            return new Info(lowestCommonAncestor, findP, findQ);
        }

        private static class Info {
            private TreeNode lowestCommonAncestor;
            private boolean findP;
            private boolean findQ;

            public Info(TreeNode lowestCommonAncestor, boolean findP, boolean findQ) {
                this.lowestCommonAncestor = lowestCommonAncestor;
                this.findP = findP;
                this.findQ = findQ;
            }
        }
    }


//leetcode submit region end(Prohibit modification and deletion)

}