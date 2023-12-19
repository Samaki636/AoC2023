package it.samaki.adventOfCode2023.problem17;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.Map.Entry;

/**
 * @author : Samaki01
 **/
public class Problem17 {
    public static void main(String[] args) throws FileNotFoundException {
        LinkedList<String> input = new LinkedList<>();
        try (Scanner scanner = new Scanner(new File("res/it/samaki/adventOfCode2023/problem17/test1.txt"))) {
            while (scanner.hasNextLine())
                input.add(scanner.nextLine());
        }

        Node[][] map = new Node[input.size()][input.get(0).length()];
        for (int i = 0; i < input.size(); i++)
            for (int j = 0; j < input.get(0).length(); j++)
                map[i][j] = new Node(Integer.parseInt(input.get(i).substring(j, j+1)));

        for (int i = 0; i < input.size(); i++) {
            //TODO https://www.baeldung.com/java-dijkstra
        }
        Graph graph = new Graph();
    }

    public static Graph calculateShortestPathFromSource(Graph graph, Node source) {
        source.setDistance(0);

        Set<Node> settledNodes = new HashSet<>();
        Set<Node> unsettledNodes = new HashSet<>();

        unsettledNodes.add(source);

        while (!unsettledNodes.isEmpty()) {
            Node currentNode = getLowestDistanceNode(unsettledNodes);
            unsettledNodes.remove(currentNode);
            for (Entry<Node, Integer> adjacencyPair : currentNode.getAdjacentNodes().entrySet()) {
                Node adjacentNode = adjacencyPair.getKey();
                Integer edgeWeight = adjacencyPair.getValue();
                if (!settledNodes.contains(adjacentNode)) {
                    calculateMinimumDistance(adjacentNode, edgeWeight, currentNode);
                    unsettledNodes.add(adjacentNode);
                }
            }
            settledNodes.add(currentNode);
        }
        return graph;
    }

    private static Node getLowestDistanceNode(Set<Node> unsettledNodes) {
        Node lowestDistanceNode = null;
        int lowestDistance = Integer.MAX_VALUE;
        for (Node node : unsettledNodes) {
            int nodeDistance = node.getDistance();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        return lowestDistanceNode;
    }

    private static void calculateMinimumDistance(Node evaluationNode, Integer edgeWeight, Node sourceNode) {
        Integer sourceDistance = sourceNode.getDistance();
        if (sourceDistance + edgeWeight < evaluationNode.getDistance()) {
            evaluationNode.setDistance(sourceDistance + edgeWeight);
            LinkedList<Node> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            shortestPath.add(sourceNode);
            evaluationNode.setShortestPath(shortestPath);
        }
    }
}
