package p15SegmentTree;

import java.util.LinkedList;
import java.util.List;

/**
 * @description:
 * @author: Flash
 * @create: 2022-04-23 17:43
 **/
public class Code04_AC {

    public class Node{
        public String end;
        public boolean endUse;
        public Node fail;
        public Node[] nexts;

        public Node () {
            this.nexts = new Node[26];
        }
    }

    public class ACAutomation {
        private Node root;

        public ACAutomation(){
            root = new Node();
        }

        public void insert(String s) {
            char[] str = s.toCharArray();
            Node cur = root;
            for (char c : str) {
                int idx = c - 'a';
                if (cur.nexts[idx] == null) {
                    cur.nexts[idx] = new Node();
                }
                cur = cur.nexts[idx];
            }
            cur.end = s;
        }

        public void buildPoint() {
            LinkedList<Node> queue = new LinkedList<>();
            queue.add(root);
            Node cur;
            Node cFail;
            while (!queue.isEmpty()) {
                cur = queue.poll();
                for (int i = 0; i < 26; i++) {
                    if (cur.nexts[i] != null) {
                        cur.nexts[i].fail = root;
                        cFail = cur.fail;
                        while (cFail != null) {
                            if (cFail.nexts[i] != null) {
                                cur.nexts[i].fail = cFail.nexts[i];
                                break;
                            }
                            cFail = cFail.fail;
                        }
                        queue.add(cur.nexts[i]);
                    }
                }
            }
        }

        // TODO: need to implements
        public List<String> containWords(String content) {
            return null;
        }
    }
}
