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
    private Integer distance = Integer.MAX_VALUE;
    private List<Node> shortestPath = new LinkedList<>();
    Map<Node,Integer> adjacentNodes = new HashMap<>();

    public Node(int value) {
        this.value = value;
    }

    public  void addDestination(Node destination, int distance) {
        adjacentNodes.put(destination, distance);
    }

    public Integer getDistance() {
        return distance;
    }

    public Map<Node, Integer> getAdjacentNodes() {
        return adjacentNodes;
    }

    public List<Node> getShortestPath() {
        return shortestPath;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public void setShortestPath(List<Node> shortestPath) {
        this.shortestPath = shortestPath;
    }
}
