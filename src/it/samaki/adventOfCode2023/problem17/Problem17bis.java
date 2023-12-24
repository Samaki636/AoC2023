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
        int[][] map = null;
        try (Scanner scanner = new Scanner(new File("res/it/samaki/adventOfCode2023/problem17/test1.txt"))) {
            String s;
            int i = 0;
            while (scanner.hasNextLine()) {
                s = scanner.nextLine();
                if (i == 0)
                    map = new int[s.length()][s.length()];
                for (int j = 0; j < s.length(); j++) {
                    map[i][j] = s.charAt(j)-'0';
                }
                i++;
            }
        }

        //A*
//        System.out.println("The least heat loss the crucible can incur is: " +  aStar(map));
    }

//    public static int aStar(int[][] map) {
//        Queue<Element> queue = new PriorityQueue<>();
//        Set<NodeBis> visited = new HashSet<>();
//        int endX = grid[grid.length - 1].length - 1;
//        int endY = grid.length - 1;
//
//        NodeBis eastStart = new NodeBis(1, 0, 1, Element.EAST);
//        NodeBis southStart = new NodeBis(0, 1, 1, Element.SOUTH);
//        queue.add(new Element(eastStart, grid[0][1]));
//        queue.add(new Element(southStart, grid[1][0]));
//
//        while (!queue.isEmpty()) {
//            final Element current = queue.poll();
//            if (visited.contains(current.node())) {
//                continue;
//            }
//            visited.add(current.node());
//            if (current.node().x() == endX && current.node().y() == endY
//                    && (part1 || current.node().blocks() >= 4)) {
//                return current.heatLoss();
//            }
//
//            queue.addAll(part1 ? current.getNeighbours(grid) : current.getNeighboursForPart2(grid));
//        }
//
//        return 0;
//        }
//
//        //visualization and compute result
//        final String ANSI_RED = "\u001B[31m";
//        final String ANSI_RESET = "\u001B[0m";
//        Set<Node> toPrint = new HashSet<>();
//
//        Node currentNode = goal;
//        int sum = goal.getValue();
//        toPrint.add(goal);
//        while (cameFrom.get(currentNode) != null) {
//            currentNode = cameFrom.get(currentNode);
//            sum += currentNode.getValue();
//            toPrint.add(currentNode);
//        }
//
//        for (Node[] nodes : map) {
//            for (int j = 0; j < map[0].length; j++) {
//                if (toPrint.contains(nodes[j]))
//                    System.out.print("[" + ANSI_RED + nodes[j].getValue()+ ANSI_RESET + "]");
//                else
//                    System.out.print("[" + nodes[j].getValue() + "]");
//            }
//            System.out.println();
//        }
//
//        return sum;
//    }

    public static int heuristic(Node a, Node b) {
        return Math.abs(a.getColumn() - b.getColumn()) + Math.abs(a.getRow() - b.getRow());
    }
}
