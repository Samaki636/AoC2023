package it.samaki.adventOfCode2023.problem17;

import java.util.LinkedList;
import java.util.List;

/**
 * @author : Samaki01
 **/
public class Node implements Comparable<Node> {
    public static final int NORTH = 0;
    public static final int EAST = 1;
    public static final int SOUTH = 2;
    public static final int WEST = 3;

    private final int value;
    private int priority = Integer.MAX_VALUE;
    private final List<Node> adjacentNodes = new LinkedList<>();
    private final int row;
    private final int column;

    public Node(int value, int row, int column, int stepsStraight, int direction) {
        this.value = value;
        this.row = row;
        this.column = column;
    }

    @Override
    public int compareTo(Node node) {
        return Integer.compare(node.getPriority(), priority);
    }

    public List<Node> getAdjacentNodes() {
        return adjacentNodes;
    }

    public int getValue() {
        return value;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
