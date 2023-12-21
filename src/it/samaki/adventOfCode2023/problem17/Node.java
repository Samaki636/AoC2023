package it.samaki.adventOfCode2023.problem17;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author : Samaki01
 **/
public class Node {
    private final int value;
    private Integer distanceFromSource = Integer.MAX_VALUE;
    private List<Node> shortestPath = new LinkedList<>();
    private Map<Node,Integer> adjacentNodes = new HashMap<>();
    private int row;
    private int column;

    public Node(int value, int row, int column) {
        this.value = value;
        this.row = row;
        this.column = column;
        shortestPath.add(this);
    }

    public  void addDestination(Node destination, int distance) {
        adjacentNodes.put(destination, distance);
    }

    public Integer getDistanceFromSource() {
        return distanceFromSource;
    }

    public Map<Node, Integer> getAdjacentNodes() {
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

    public void setDistanceFromSource(Integer distanceFromSource) {
        this.distanceFromSource = distanceFromSource;
    }

    public void setShortestPath(List<Node> shortestPath) {
        this.shortestPath = shortestPath;
    }
}
