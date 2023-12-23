package it.samaki.adventOfCode2023.problem17;

import java.util.Comparator;

/**
 * @author : Samaki01
 **/
public class NodeComparator implements Comparator<Node> {
    @Override
    public int compare(Node node1, Node node2) {
        return Integer.compare(node1.getPriority(), node2.getPriority());
    }
}
