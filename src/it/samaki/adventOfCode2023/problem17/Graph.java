package it.samaki.adventOfCode2023.problem17;

import java.util.LinkedList;

/**
 * @author : Samaki01
 **/
public class Graph {
    private LinkedList<Node> nodes = new LinkedList<>();

    public void addNode(Node node) {
        nodes.add(node);
    }

    public LinkedList<Node> getNodes() {
        return nodes;
    }
}
