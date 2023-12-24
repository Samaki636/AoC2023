package it.samaki.adventOfCode2023.problem17;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * @author : Samaki01
 **/
@SuppressWarnings("DataFlowIssue")
public class Problem17 {
    public static void main(String[] args) throws FileNotFoundException {
        int[][] grid = null;
        try (Scanner scanner = new Scanner(new File("res/it/samaki/adventOfCode2023/problem17/test1.txt"))) {
            int y = 0;
            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                if (y == 0)
                    grid = new int[s.length()][s.length()];
                for (int x = 0; x < s.length(); x++) {
                    grid[y][x] = s.charAt(x)-'0';
                }
                y++;
            }
        }

        Map<NodeTris, NodeTris> cameFrom = aStar(grid);
        print(cameFrom, grid);
    }

    private static Map<NodeTris, NodeTris> aStar(int[][] grid) {
        NodeTris start = new NodeTris(0, 0, Node.EAST,1, 0);
        int endX = grid[0].length-1;
        int endY = grid.length-1;

        Map<NodeTris, NodeTris> cameFrom = new HashMap<>();
        Map<NodeTris, Integer> costSoFar = new HashMap<>();
        Queue<NodeTris> frontier = new PriorityQueue<>();

        frontier.add(start);
        cameFrom.put(start, null);
        costSoFar.put(start, 0);

        while (!frontier.isEmpty()) {
            NodeTris currentNode = frontier.poll();

            if (currentNode.getX() == endX && currentNode.getY() == endY) {
                System.out.println(currentNode.getHeatLoss());
                break;
            }

            for (NodeTris nextNode : currentNode.getNeighbors(grid)) {
                int newCost = costSoFar.get(currentNode) + grid[nextNode.getX()][nextNode.getY()];
                if (((!costSoFar.containsKey(nextNode) || newCost < costSoFar.get(nextNode))) && !frontier.contains(nextNode)) {
                    costSoFar.put(nextNode, newCost);
                    frontier.add(nextNode);
                    cameFrom.put(nextNode, currentNode);
                }
            }
        }
        return cameFrom;
    }

    private static void print(Map<NodeTris, NodeTris> cameFrom, int[][] grid) {
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_RESET = "\u001B[0m";
        boolean isPrinted = false;

        Set<NodeTris> nodesToPrint = new HashSet<>();
        NodeTris node = new NodeTris(12, 12, NodeTris.SOUTH, 1, 78);
        nodesToPrint.add(node);
        while (cameFrom.get(node) != null) {
            node = cameFrom.get(node);
            nodesToPrint.add(node);
        }

        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[0].length; x++) {
                for (NodeTris nodeToPrint : nodesToPrint) {
                    if (nodeToPrint.getX() == x && nodeToPrint.getY() == y) {
                        System.out.print("[" + ANSI_RED + grid[y][x] + ANSI_RESET + "]");
                        isPrinted = true;
                        break;
                    }
                }

                if (!isPrinted) {
                    System.out.print("[" + grid[y][x] + "]");
                } else {
                    isPrinted = false;
                }
            }
            System.out.println();
        }
    }
}
