package it.samaki.adventOfCode2023.problem17;

import java.util.HashSet;
import java.util.Set;

/**
 * @author : Samaki01
 **/
public class NodeTris implements Comparable<NodeTris> {
    public static final int NORTH = 0;
    public static final int EAST = 1;
    public static final int SOUTH = 2;
    public static final int WEST = 3;

    private final int x;
    private final int y;
    private final int direction;
    private final int steps;
    private final int heatLoss;

    public NodeTris(int x, int y, int direction, int steps, int heatLoss) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.steps = steps;
        this.heatLoss = heatLoss;
    }

    public Set<NodeTris> getNeighbors(int[][] grid) {
        Set<NodeTris> neighbours = new HashSet<>();

        NodeTris left = getNextNode(Math.floorMod(direction-1, 4), grid, 1);
        if (left != null) neighbours.add(left);

        NodeTris right = getNextNode((direction+1) % 4, grid, 1);
        if (right != null) neighbours.add(right);

        if (steps < 3) {
            NodeTris straight = getNextNode(direction, grid, steps+1);
            if (straight != null) neighbours.add(straight);
        }

        return neighbours;
    }

    private NodeTris getNextNode(int direction, int[][] grid, int steps) {
        int nextX = getNextX(direction);
        int nextY = getNextY(direction);
        if (nextX >= 0 && nextX < grid[0].length && nextY >= 0 && nextY < grid.length)
            return new NodeTris(nextX, nextY, steps, direction, heatLoss + grid[nextY][nextX]);
        return null;
    }

    private int getNextX(int newDirection) {
        return newDirection == NORTH || newDirection == SOUTH ? x : newDirection == EAST ? x + 1 : x - 1;
    }

    private int getNextY(int newDirection) {
        return newDirection == EAST || newDirection == WEST ? y : newDirection == NORTH ? y - 1 : y + 1;
    }

    @Override
    public int compareTo(NodeTris node) {
        if (this.heatLoss != node.getHeatLoss()) {
            return Integer.compare(this.heatLoss, node.getHeatLoss());
        } else if (this.direction == node.getDirection() && this.steps != node.getSteps()) {
            return Integer.compare(this.steps, node.getSteps());
        } else if (this.y != node.getY()) {
            return Integer.compare(this.y, node.getY());
        } else {
            return Integer.compare(this.x, node.getX());
        }
    }

    @Override
    public boolean equals(Object node) {
        if (!(node instanceof NodeTris)) return false;
        return this.compareTo((NodeTris) node) == 0;
    }

    public int getHeatLoss() {
        return heatLoss;
    }

    public int getDirection() {
        return direction;
    }

    public int getSteps() {
        return steps;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
