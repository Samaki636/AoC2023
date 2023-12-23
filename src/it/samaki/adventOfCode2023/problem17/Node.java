package it.samaki.adventOfCode2023.problem17;

import java.util.LinkedList;
import java.util.List;

/**
 * @author : Samaki01
 **/
public class Node implements Comparable<Node> {
    private final int value;
    private int priority = Integer.MAX_VALUE;
    private List<Node> shortestPath = new LinkedList<>();
    private final List<Node> adjacentNodes = new LinkedList<>();
    private final int row;
    private final int column;

    public Node(int value, int row, int column) {
        this.value = value;
        this.row = row;
        this.column = column;
        shortestPath.add(this);
    }

    @Override
    public int compareTo(Node node) {
        return Integer.compare(node.getPriority(), priority);
    }

    public  void addDestination(Node destination) {
        adjacentNodes.add(destination);
    }

    public Integer getDistanceFromSource() {
        return priority;
    }

    public List<Node> getAdjacentNodes() {
        return adjacentNodes;
    }

    public int getValue() {
        return value;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getPriority() {
        return priority;
    }

    public List<Node> getShortestPath() {
        return shortestPath;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setDistanceFromSource(int priority) {
        this.priority = priority;
    }

    public void setShortestPath(List<Node> shortestPath) {
        this.shortestPath = shortestPath;
    }
}
