package p7graph;

import java.util.ArrayList;

/**
 * @author liyaguang11
 * @date 2022/2/21
 */
public class Node {

    public int value;
    public int in;
    public int out;
    public ArrayList<Node> nexts;
    public ArrayList<Edge> edges;

    public Node(int value) {
        this.value = value;
        this.in = 0;
        this.out = 0;
        this.nexts = new ArrayList<>();
        this.edges = new ArrayList<>();
    }
}
