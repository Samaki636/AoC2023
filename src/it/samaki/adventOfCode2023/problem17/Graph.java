package it.samaki.adventOfCode2023.problem17;

import java.util.HashSet;
import java.util.Set;

/**
 * @author : Samaki01
 **/
public class Graph {
    private Set<Node> nodes = new HashSet<>();

    public void addNode(Node node) {
        nodes.add(node);
    }
}
