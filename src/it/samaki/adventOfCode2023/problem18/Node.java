package it.samaki.adventOfCode2023.problem18;

/**
 * @author : Samaki01
 **/
public class Node {
    private int x;
    private int y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object node) {
        return (node instanceof Node) && (this.x == ((Node) node).getX() && this.y == ((Node) node).getY());
    }

    @Override
    public int hashCode() {
        return (x + "," + y).hashCode();
    }

    public int getX() {
        return x;
    }

    public void incrementX(int n) {
        x += n;
    }

    public void decrementX(int n) {
        x -= n;
    }

    public int getY() {
        return y;
    }

    public void incrementY(int n) {
        y += n;
    }

    public void decrementY(int n) {
        y -= n;
    }
}
