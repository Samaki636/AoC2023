package it.samaki.adventOfCode2023.problem17;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * @author : Samaki01
 **/
public class Problem17tris {
    public static void main(String[] args) throws FileNotFoundException {
        List<String> input = new LinkedList<>();
        try (Scanner scanner = new Scanner(new File("res/it/samaki/adventOfCode2023/problem17/test1.txt"))) {
            while (scanner.hasNextLine())
                input.add(scanner.nextLine());
        }

        int[][] grid = readGrid(input);
        System.out.println(dijkstra(grid, true));
    }

    private static int[][] readGrid(final List<String> input) {
        int[][] grid = new int[input.size()][input.get(0).length()];
        for (int y = 0; y < input.size(); y++) {
            String line = input.get(y);
            for (int x = 0; x < line.length(); x++) {
                grid[y][x] = line.charAt(x) - '0';
            }
        }
        return grid;
    }

    private static int dijkstra(int[][] grid, boolean part1) {
        Queue<Element> queue = new PriorityQueue<>();
        Set<NodeBis> visited = new HashSet<>();
        int endX = grid[grid.length - 1].length - 1;
        int endY = grid.length - 1;

        NodeBis eastStart = new NodeBis(1, 0, 1, Element.EAST);
        NodeBis southStart = new NodeBis(0, 1, 1, Element.SOUTH);
        queue.add(new Element(eastStart, grid[0][1]));
        queue.add(new Element(southStart, grid[1][0]));

        while (!queue.isEmpty()) {
            final Element current = queue.poll();
            if (visited.contains(current.node())) {
                continue;
            }
            visited.add(current.node());
            if (current.node().x() == endX && current.node().y() == endY
                    && (part1 || current.node().blocks() >= 4)) {
                return current.heatLoss();
            }

            queue.addAll(part1 ? current.getNeighbours(grid) : current.getNeighboursForPart2(grid));
        }

        return 0;
    }
}
