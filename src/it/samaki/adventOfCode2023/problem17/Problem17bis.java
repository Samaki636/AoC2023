package it.samaki.adventOfCode2023.problem17;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * @author : Samaki01
 **/
@SuppressWarnings("DataFlowIssue")
public class Problem17bis {
    public static void main(String[] args) throws FileNotFoundException {
        //read file and build graph (map)
        Node[][] map = null;
        try (Scanner scanner = new Scanner(new File("res/it/samaki/adventOfCode2023/problem17/test1.txt"))) {
            String s;
            int i = 0;
            while (scanner.hasNextLine()) {
                s = scanner.nextLine();
                if (i == 0)
                    map = new Node[s.length()][s.length()];
                for (int j = 0; j < s.length(); j++) {
                    map[i][j] = new Node(s.charAt(j)-'0', i, j);
                }
                i++;
            }
        }

        //build adjacency lists
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (i < map.length-1)
                    map[i][j].addDestination(map[i+1][j]);
                if (i > 0)
                    map[i][j].addDestination(map[i-1][j]);
                if (j < map[0].length-1)
                    map[i][j].addDestination(map[i][j+1]);
            }
        }

        //dijkstra
        calculateShortestPathFromSource(map);

        //compute sum
        int sum = 0;
        for (Node node: map[map.length-1][map[0].length-1].getShortestPath())
            sum += node.getValue();
        System.out.println("The least heat loss the crucible can incur is: " + sum);

        //print visualisation
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_RESET = "\u001B[0m";
        for (Node[] nodes : map) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[map.length - 1][map[0].length - 1].getShortestPath().contains(nodes[j]))
                    System.out.print("[" + ANSI_RED + nodes[j].getValue() + ANSI_RESET + "]");
                else
                    System.out.print("[" + nodes[j].getValue() + "]");
            }
            System.out.println();
        }
    }

    public static void calculateShortestPathFromSource(Node[][] map) {
        Node source = map[0][0];
        source.setDistanceFromSource(0);

        Set<Node> settledNodes = new HashSet<>();
        Set<Node> unsettledNodes = new HashSet<>();

        unsettledNodes.add(source);

        while (!unsettledNodes.isEmpty()) {
            Stack<Node> lowestDistanceNodes = getLowestDistanceNodes(unsettledNodes);
            while (!lowestDistanceNodes.isEmpty()) {
                Node currentNode = lowestDistanceNodes.pop();

                unsettledNodes.remove(currentNode);

                LinkedList<Node> shortestPath = new LinkedList<>(currentNode.getShortestPath());
                Node lastNode;
                Node secondToLastNode;
                Node thirdToLastNode;
                boolean isRowTooLong;
                boolean isColumnTooLong;
                if (shortestPath.size() > 3) {
                    lastNode = shortestPath.get(shortestPath.size() - 1);
                    secondToLastNode = shortestPath.get(shortestPath.size() - 2);
                    thirdToLastNode = shortestPath.get(shortestPath.size() - 3);
                    isRowTooLong = currentNode.getRow() == lastNode.getRow() && currentNode.getRow() == secondToLastNode.getRow() && currentNode.getRow() == thirdToLastNode.getRow();
                    isColumnTooLong = currentNode.getColumn() == lastNode.getColumn() && currentNode.getColumn() == secondToLastNode.getColumn() && currentNode.getColumn() == thirdToLastNode.getColumn();

                    if (isRowTooLong && currentNode.getColumn() < map[0].length - 1)
                        currentNode.getAdjacentNodes().remove(map[currentNode.getRow()][currentNode.getColumn() + 1]);
                    if (isColumnTooLong && currentNode.getRow() < map.length - 1)
                        currentNode.getAdjacentNodes().remove(map[currentNode.getRow() + 1][currentNode.getColumn()]);
                }

                for (Node adjacentNode : currentNode.getAdjacentNodes()) {
                    int edgeWeight = adjacentNode.getValue();
                    if (!settledNodes.contains(adjacentNode)) {
                        calculateMinimumDistance(adjacentNode, edgeWeight, currentNode);
                        unsettledNodes.add(adjacentNode);
                    }
                }
                settledNodes.add(currentNode);
            }
        }
    }

    public static Stack<Node> getLowestDistanceNodes(Set<Node> unsettledNodes) {
        int lowestDistance = Integer.MAX_VALUE;
        for (Node node: unsettledNodes) {
            int nodeDistance = node.getDistanceFromSource();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
            }
        }

        Stack<Node> lowestDistanceNodes = new Stack<>();
        for (Node node: unsettledNodes) {
            int nodeDistance = node.getDistanceFromSource();
            if (nodeDistance == lowestDistance) {
                lowestDistanceNodes.add(node);
            }
        }
        return lowestDistanceNodes;
    }

    public static void calculateMinimumDistance(Node evaluationNode, int edgeWeight, Node sourceNode) {
        int sourceDistance = sourceNode.getDistanceFromSource();
        if (sourceDistance + edgeWeight < evaluationNode.getDistanceFromSource()) {
            evaluationNode.setDistanceFromSource(sourceDistance + edgeWeight);
            List<Node> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            shortestPath.add(evaluationNode);
            evaluationNode.setShortestPath(shortestPath);
        }

        if (sourceDistance + edgeWeight == evaluationNode.getDistanceFromSource()) {
            evaluationNode.addShortestPath(new LinkedList<>(sourceNode.getShortestPath()));
        }
    }
}
