package it.samaki.adventOfCode2023.problem17;

import java.util.LinkedList;
import java.util.List;

/**
 * @author : Samaki01
 **/
public class Node {
    private final int value;
    private Integer distanceFromSource = Integer.MAX_VALUE;
    private List<Node> shortestPath = new LinkedList<>();
    private final List<Node> adjacentNodes = new LinkedList<>();
    private final List<List<Node>> shortestPaths = new LinkedList<>();
    private final int row;
    private final int column;

    public Node(int value, int row, int column) {
        this.value = value;
        this.row = row;
        this.column = column;
        shortestPath.add(this);
    }

    public void addDestination(Node destination) {
        adjacentNodes.add(destination);
    }

    public void addShortestPath(LinkedList<Node> shortestPath) {
        shortestPaths.add(shortestPath);
    }

    public Integer getDistanceFromSource() {
        return distanceFromSource;
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

    public List<Node> getShortestPath() {
        return shortestPath;
    }

    public List<List<Node>> getShortestPaths() {
        return shortestPaths;
    }

    public void setDistanceFromSource(Integer distanceFromSource) {
        this.distanceFromSource = distanceFromSource;
    }

    public void setShortestPath(List<Node> shortestPath) {
        this.shortestPath = shortestPath;
    }
}
