package it.samaki.adventOfCode2023.problem17;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Samaki01
 **/
public record Element(NodeBis node, int heatLoss) implements Comparable<Element> {
    public static final int NORTH = 0;
    public static final int EAST = 1;
    public static final int SOUTH = 2;
    public static final int WEST = 3;

    public List<Element> getNeighboursForPart2(int[][] grid) {
        List<Element> neighbours = new ArrayList<>();
        if (node.steps() >= 4) {
            Element left = getNextElement(Math.floorMod(node.direction() - 1, 4), grid, 1);
            if (left != null) {
                neighbours.add(left);
            }

            Element right = getNextElement((node.direction() + 1) % 4, grid, 1);
            if (right != null) {
                neighbours.add(right);
            }
        }
        if (node.steps() < 10) {
            Element straight = getNextElement(node.direction(), grid, node.steps() + 1);
            if (straight != null) {
                neighbours.add(straight);
            }
        }
        return neighbours;
    }


    public List<Element> getNeighbours(int[][] grid) {
        List<Element> neighbours = new ArrayList<>();

        Element left = getNextElement(Math.floorMod(node.direction() - 1, 4), grid, 1);
        if (left != null) {
            neighbours.add(left);
        }

        Element right = getNextElement((node.direction() + 1) % 4, grid, 1);
        if (right != null) {
            neighbours.add(right);
        }

        if (node.steps() < 3) {
            Element straight = getNextElement(node.direction(), grid, node.steps() + 1);
            if (straight != null) {
                neighbours.add(straight);
            }
        }

        return neighbours;
    }

    private Element getNextElement(int direction, int[][] grid, int blocks) {
        int x = getNextX(direction);
        int y = getNextY(direction);
        if (x >= 0 && x < grid[0].length && y >= 0 && y < grid.length) {
            NodeBis nextNode = new NodeBis(x, y, blocks, direction);
            return new Element(nextNode, heatLoss + grid[nextNode.y()][nextNode.x()]);
        }
        return null;
    }

    private int getNextX(int newDirection) {
        return newDirection == NORTH || newDirection == SOUTH ? node.x()
                : newDirection == EAST ? node.x() + 1 : node.x() - 1;
    }

    private int getNextY(int newDirection) {
        return newDirection == EAST || newDirection == WEST ? node.y()
                : newDirection == NORTH ? node.y() - 1 : node.y() + 1;
    }

    @Override
    public int compareTo(Element o) {
        if (this.heatLoss != o.heatLoss()) {
            return Integer.compare(this.heatLoss, o.heatLoss());
        } else if (this.node.direction() == o.node().direction() && this.node.steps() != o.node().steps()) {
            return Integer.compare(this.node.steps(), o.node().steps());
        } else if (this.node.y() != o.node().y()) {
            return Integer.compare(this.node.y(), o.node().y());
        } else {
            return Integer.compare(this.node.x(), o.node().x());
        }
    }
}
