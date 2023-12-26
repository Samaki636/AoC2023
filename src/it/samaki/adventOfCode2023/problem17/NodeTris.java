package it.samaki.adventOfCode2023.problem17;

import java.util.HashSet;
import java.util.Set;

/**
 * @author : Samaki01
 **/
public record NodeTris(NodeBis node, int heatLoss) implements Comparable<NodeTris> {
    public static final int NORTH = 0;
    public static final int EAST = 1;
    public static final int SOUTH = 2;
    public static final int WEST = 3;

    public Set<NodeTris> getNeighboursForPart2(int[][] grid) {
        Set<NodeTris> neighbours = new HashSet<>();

        if (node.steps() > 3) {
            NodeTris left = getNextNode(Math.floorMod(node.direction() - 1, 4), grid, 1);
            if (left != null) neighbours.add(left);

            NodeTris right = getNextNode((node.direction() + 1) % 4, grid, 1);
            if (right != null) neighbours.add(right);
        }

        if (node.steps() < 10) {
            NodeTris straight = getNextNode(node.direction(), grid, node.steps() + 1);
            if (straight != null) neighbours.add(straight);
        }

        return neighbours;
    }

    public Set<NodeTris> getNeighbours(int[][] grid) {
        Set<NodeTris> neighbours = new HashSet<>();

        NodeTris left = getNextNode(Math.floorMod(node.direction() - 1, 4), grid, 1);
        if (left != null) neighbours.add(left);

        NodeTris right = getNextNode((node.direction() + 1) % 4, grid, 1);
        if (right != null) neighbours.add(right);

        if (node.steps() < 3) {
            NodeTris straight = getNextNode(node.direction(), grid, node.steps() + 1);
            if (straight != null) neighbours.add(straight);
        }

        return neighbours;
    }

    private NodeTris getNextNode(int direction, int[][] grid, int steps) {
        int nextX = getNextX(direction);
        int nextY = getNextY(direction);
        if (nextX >= 0 && nextX < grid[0].length && nextY >= 0 && nextY < grid.length)
            return new NodeTris(new NodeBis(nextX, nextY, steps, direction), heatLoss + grid[nextY][nextX]);
        return null;
    }

    private int getNextX(int newDirection) {
        return newDirection == NORTH || newDirection == SOUTH ? node.x() : newDirection == EAST ? node.x() + 1 : node.x() - 1;
    }

    private int getNextY(int newDirection) {
        return newDirection == EAST || newDirection == WEST ? node.y() : newDirection == NORTH ? node.y() - 1 : node.y() + 1;
    }

    @Override
    public int compareTo(NodeTris node) {
        if (this.heatLoss != node.heatLoss()) {
            return Integer.compare(this.heatLoss, node.heatLoss());
        } else if (this.node.direction() == node.node.direction() && this.node.steps() != node.node.steps()) {
            return Integer.compare(this.node.steps(), node.node.steps());
        } else if (this.node.y() != node.node.y()) {
            return Integer.compare(this.node.y(), node.node.y());
        } else {
            return Integer.compare(this.node.x(), node.node.x());
        }
    }

    @Override
    public boolean equals(Object node) {
        if (!(node instanceof NodeTris)) return false;
        return this.compareTo((NodeTris) node) == 0;
    }
}
