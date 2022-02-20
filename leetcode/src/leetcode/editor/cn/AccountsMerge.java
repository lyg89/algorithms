//Given a list of accounts where each element accounts[i] is a list of strings, 
//where the first element accounts[i][0] is a name, and the rest of the elements 
//are emails representing emails of the account. 
//
// Now, we would like to merge these accounts. Two accounts definitely belong 
//to the same person if there is some common email to both accounts. Note that even 
//if two accounts have the same name, they may belong to different people as 
//people could have the same name. A person can have any number of accounts initially, 
//but all of their accounts definitely have the same name. 
//
// After merging the accounts, return the accounts in the following format: the 
//first element of each account is the name, and the rest of the elements are 
//emails in sorted order. The accounts themselves can be returned in any order. 
//
// 
// Example 1: 
//
// 
//Input: accounts = [["John","johnsmith@mail.com","john_newyork@mail.com"],[
//"John","johnsmith@mail.com","john00@mail.com"],["Mary","mary@mail.com"],["John",
//"johnnybravo@mail.com"]]
//Output: [["John","john00@mail.com","john_newyork@mail.com","johnsmith@mail.
//com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
//Explanation:
//The first and second John's are the same person as they have the common email 
//"johnsmith@mail.com".
//The third John and Mary are different people as none of their email addresses 
//are used by other accounts.
//We could return these lists in any order, for example the answer [['Mary', 
//'mary@mail.com'], ['John', 'johnnybravo@mail.com'], 
//['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] 
//would still be accepted.
// 
//
// Example 2: 
//
// 
//Input: accounts = [["Gabe","Gabe0@m.co","Gabe3@m.co","Gabe1@m.co"],["Kevin",
//"Kevin3@m.co","Kevin5@m.co","Kevin0@m.co"],["Ethan","Ethan5@m.co","Ethan4@m.co",
//"Ethan0@m.co"],["Hanzo","Hanzo3@m.co","Hanzo1@m.co","Hanzo0@m.co"],["Fern","Fern5@
//m.co","Fern1@m.co","Fern0@m.co"]]
//Output: [["Ethan","Ethan0@m.co","Ethan4@m.co","Ethan5@m.co"],["Gabe","Gabe0@m.
//co","Gabe1@m.co","Gabe3@m.co"],["Hanzo","Hanzo0@m.co","Hanzo1@m.co","Hanzo3@m.
//co"],["Kevin","Kevin0@m.co","Kevin3@m.co","Kevin5@m.co"],["Fern","Fern0@m.co",
//"Fern1@m.co","Fern5@m.co"]]
// 
//
// 
// Constraints: 
//
// 
// 1 <= accounts.length <= 1000 
// 2 <= accounts[i].length <= 10 
// 1 <= accounts[i][j] <= 30 
// accounts[i][0] consists of English letters. 
// accounts[i][j] (for j > 0) is a valid email. 
// 
// Related Topics 深度优先搜索 广度优先搜索 并查集 数组 字符串 👍 344 👎 0


package leetcode.editor.cn;

import java.util.*;

public class AccountsMerge {
    public static void main(String[] args) {
        Solution solution = new AccountsMerge().new Solution();
        //[["John","johnsmith@mail.com","john_newyork@mail.com"],["John","johnsmith@mail.com","john00@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<String>> accountsMerge(List<List<String>> accounts) {
            UnionFind<List<String>> unionFind = new UnionFind<>(accounts);
            HashMap<String, List<String>> map = new HashMap<>();
            for (List<String> account : accounts) {
                for (int i = 1; i < account.size(); i++) {
                    if (map.containsKey(account.get(i))) {
                        unionFind.union(map.get(account.get(i)), account);
                    } else {
                        map.put(account.get(i), account);
                    }
                }
            }

            HashMap<List<String>, Set<List<String>>> merge = new HashMap<>();
            for (List<String> cur : unionFind.nodes.keySet()) {
                Node<List<String>> parent = unionFind.parents.get(unionFind.nodes.get(cur));
                if (merge.containsKey(parent.value)) {
                    merge.get(parent.value).add(cur);
                } else {
                    HashSet<List<String>> tmp = new HashSet<>();
                    tmp.add(cur);
                    tmp.add(parent.value);
                    merge.put(parent.value, tmp);
                }
            }
            System.out.println(merge);
            ArrayList<List<String>> ans = new ArrayList<>();
            for (Set<List<String>> set : merge.values()) {
                Set<String> emails = new TreeSet<>();
                ArrayList<String> ansElement = new ArrayList<>();
                for (List<String> strings : set) {
                    if (ansElement.size() == 0) {
                        ansElement.add(strings.get(0));
                    }
                    emails.addAll(strings.subList(1, strings.size()));
                }
                ansElement.addAll(emails);
                ans.add(ansElement);
            }
            return ans;
        }

        private class Node<V> {
            private V value;

            public Node(V value) {
                this.value = value;
            }
        }

        public class UnionFind<V> {
            /**
             * 存储所有值对应的节点
             */
            private Map<V, Node<V>> nodes;
            /**
             * 存储所有节点对应的父节点，表示节点的父节点为自己
             */
            private Map<Node<V>, Node<V>> parents;
            /**
             * 表示节点所在链的元素个数
             */
            private Map<Node<V>, Integer> sizeMap;


            public UnionFind(List<V> values) {
                nodes = new HashMap<>(values.size());
                parents = new HashMap<>(values.size());
                sizeMap = new HashMap<>(values.size());
                for (V value : values) {
                    Node<V> node = new Node<>(value);
                    nodes.put(value, node);
                    parents.put(node, node);
                    sizeMap.put(node, 1);
                }
            }

            private Node<V> findFather(Node<V> cur) {
                Queue<Node<V>> path = new LinkedList<>();
                while (cur != parents.get(cur)) {
                    path.add(cur);
                    cur = parents.get(cur);
                }
                while (!path.isEmpty()) {
                    parents.put(path.poll(), cur);
                }
                return cur;
            }

            public boolean isSameSet(V a, V b) {
                return this.findFather(nodes.get(a)) == this.findFather(nodes.get(b));
            }

            public void union(V a, V b) {
                Node<V> aHead = this.findFather(nodes.get(a));
                Node<V> bHead = this.findFather(nodes.get(b));
                if (aHead == bHead) {
                    return;
                }

                Node<V> big = sizeMap.get(aHead) > sizeMap.get(bHead) ? aHead : bHead;
                Node<V> small = big == aHead ? bHead : aHead;
                parents.put(small, big);
                sizeMap.put(big, sizeMap.get(big) + sizeMap.get(small));
                sizeMap.remove(small);
            }

        }
    }


//leetcode submit region end(Prohibit modification and deletion)

}