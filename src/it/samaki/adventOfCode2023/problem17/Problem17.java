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
        try (Scanner scanner = new Scanner(new File("res/it/samaki/adventOfCode2023/problem17/test2.txt"))) {
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
        System.out.println(dijkstra(grid, true));
        System.out.println(dijkstra(grid, false));
    }

    private static int dijkstra(int[][] grid, boolean part1) {
        NodeBis eastStart = new NodeBis(1, 0, 1, Node.EAST);
        NodeBis southStart = new NodeBis(0, 1, 1, Node.SOUTH);
        int endX = grid[0].length-1;
        int endY = grid.length-1;

        Queue<NodeTris> frontier = new PriorityQueue<>();
        Set<NodeBis> visited = new HashSet<>();

        frontier.add(new NodeTris(eastStart, grid[0][1]));
        frontier.add(new NodeTris(southStart, grid[1][0]));

        while (!frontier.isEmpty()) {
            final NodeTris currentNode = frontier.poll();

            if (visited.contains(currentNode.node())) continue;
            visited.add(currentNode.node());

            if (currentNode.node().x() == endX && currentNode.node().y() == endY && (part1 || currentNode.node().steps() > 3))
                return currentNode.heatLoss();

            frontier.addAll(part1 ? currentNode.getNeighbours(grid) : currentNode.getNeighboursForPart2(grid));
        }
        return 0;
    }
}
