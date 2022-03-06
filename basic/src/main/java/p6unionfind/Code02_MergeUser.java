package p6unionfind;

import java.util.*;

public class Code02_MergeUser {

    private static class Node<V> {
        private V value;

        public Node(V value) {
            this.value = value;
        }
    }

    public static class UnionFind<V> {
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


        public UnionFind(List<V> values){
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

        public int getSetNum() {
            return sizeMap.size();
        }
    }

    private class User {
        private String a;
        private String b;
        private String c;
    }

    public static int mergeUser (List<User> users) {
        UnionFind<User> unionFind = new UnionFind<User>(users);

        HashMap<String, User> aMap = new HashMap<>();
        HashMap<String, User> bMap = new HashMap<>();
        HashMap<String, User> cMap = new HashMap<>();
        for (User user : users) {
            if (aMap.containsKey(user.a)) {
                unionFind.union(user, aMap.get(user));
            } else {
                aMap.put(user.a, user);
            }

            if (bMap.containsKey(user.b)) {
                unionFind.union(user, bMap.get(user));
            } else {
                bMap.put(user.b, user);
            }

            if (cMap.containsKey(user.c)) {
                unionFind.union(user, cMap.get(user));
            } else {
                cMap.put(user.c, user);
            }
        }
        return unionFind.getSetNum();
    }


}
