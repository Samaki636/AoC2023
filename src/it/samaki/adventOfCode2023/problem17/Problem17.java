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
                map[i][j] = new Node(Integer.parseInt(input.get(i).substring(j, j+1)), i, j);

        Graph graph = new Graph();
        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.get(0).length(); j++) {
                if (i < input.size()-1)
                    map[i][j].addDestination(map[i+1][j], map[i+1][j].getValue());
                if (i > 0)
                    map[i][j].addDestination(map[i-1][j], map[i-1][j].getValue());
                if (j < input.get(0).length()-1)
                    map[i][j].addDestination(map[i][j+1], map[i][j+1].getValue());
                graph.addNode(map[i][j]);
            }
        }

        calculateShortestPathFromSource(map[0][0], map);

        int sum = 0;
        for (Node n : graph.getNodes().getLast().getShortestPath()) {
            sum += n.getValue();
        }

        final String ANSI_RED = "\u001B[31m";
        final String ANSI_RESET = "\u001B[0m";
        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.get(0).length();j++) {
                if (graph.getNodes().getLast().getShortestPath().contains(map[i][j]))
                    System.out.print("[" + ANSI_RED + map[i][j].getValue() + ANSI_RESET + "]");
                else
                    System.out.print("[" + map[i][j].getValue() + "]");
            }
            System.out.println();
        }

        System.out.println("The least heat loss the crucible can incur is: " + sum);
    }

    public static void calculateShortestPathFromSource(Node source, Node[][] map) {
        source.setDistanceFromSource(0);

        Set<Node> settledNodes = new HashSet<>();
        Set<Node> unsettledNodes = new HashSet<>();

        unsettledNodes.add(source);

        while (!unsettledNodes.isEmpty()) {
            Node currentNode = getLowestDistanceFromSourceNode(unsettledNodes);
            unsettledNodes.remove(currentNode);

            LinkedList<Node> shortestPath = new LinkedList<>(currentNode.getShortestPath());
            if (shortestPath.size() > 3) {
                Node last = shortestPath.get(shortestPath.size() - 1);
                Node secondToLast = shortestPath.get(shortestPath.size() - 2);
                Node thirdToLast = shortestPath.get(shortestPath.size() - 3);
                Node fourthToLast = shortestPath.get(shortestPath.size() - 4);
                boolean isMovingStraightTooLongRows = last.getRow() == secondToLast.getRow()
                        && last.getRow() == thirdToLast.getRow() && last.getRow() == fourthToLast.getRow();
                boolean isMovingStraightTooLongColumns = last.getColumn() == secondToLast.getColumn()
                        && last.getColumn() == thirdToLast.getColumn() && last.getColumn() == fourthToLast.getColumn();
                if (isMovingStraightTooLongRows && currentNode.getColumn() < map[0].length - 1
                        && currentNode.getColumn() > 3)
                    currentNode.getAdjacentNodes().remove(map[currentNode.getRow()][currentNode.getColumn() + 1]);
                if (isMovingStraightTooLongColumns && currentNode.getRow() < map.length - 1)
                    currentNode.getAdjacentNodes().remove(map[currentNode.getRow() + 1][currentNode.getColumn()]);
            }

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
    }

    //return the nearest node from source in the Set, the Set indicate the adjacency list of the current node
    private static Node getLowestDistanceFromSourceNode(Set<Node> unsettledNodes) {
        Node lowestDistanceFromSourceNode = null;
        int lowestDistanceFromSource = Integer.MAX_VALUE;
        for (Node node : unsettledNodes) {
            int nodeDistance = node.getDistanceFromSource();
            if (nodeDistance < lowestDistanceFromSource) {
                lowestDistanceFromSource = nodeDistance;
                lowestDistanceFromSourceNode = node;
            }
        }
        return lowestDistanceFromSourceNode;
    }

    //if distance of current node from source + edge weight is < of distance of adjacent node from source
    private static void calculateMinimumDistance(Node evaluationNode, Integer edgeWeight, Node sourceNode) {
        Integer sourceDistance = sourceNode.getDistanceFromSource();
        if (sourceDistance + edgeWeight <= evaluationNode.getDistanceFromSource()) {
            evaluationNode.setDistanceFromSource(sourceDistance + edgeWeight);
            LinkedList<Node> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            shortestPath.add(evaluationNode);
            evaluationNode.setShortestPath(shortestPath);
        }
    }
}
