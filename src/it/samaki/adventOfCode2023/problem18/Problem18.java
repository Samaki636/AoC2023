package it.samaki.adventOfCode2023.problem18;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author : Samaki01
 **/
@SuppressWarnings("ResultOfMethodCallIgnored")
public class Problem18 {
    public static void main(String[] args) throws FileNotFoundException {
        LinkedList<String> input = new LinkedList<>();
        try (Scanner scanner = new Scanner(new File("res/it/samaki/adventOfCode2023/problem18/test2.txt"))) {
            while (scanner.hasNextLine())
                input.add(scanner.nextLine());
        }

        int[] dim = findMapDim(input);
        boolean[][] diggedMap = new boolean[dim[2]-dim[3]+1][dim[0]-dim[1]+1];

        computeLine(input, diggedMap, dim);
        System.out.println("The lagoon could contain " + floodFill(new Node(Math.abs(dim[1])+1, Math.abs(dim[3])+1), diggedMap) + " cubic meters of lava.");

        int[] dimPart2 = findMapDimPart2(input);
        boolean[][] diggedMapPart2 = new boolean[dimPart2[2]-dimPart2[3]+1][dimPart2[0]-dimPart2[1]+1];

        computeLinePart2(input, diggedMapPart2, dimPart2);
    }

    private static int[] findMapDim(LinkedList<String> input) {
        Node current = new Node(0, 0);
        char dir;
        int meters;
        int maxX = 0;
        int minX = 0;
        int maxY = 0;
        int minY = 0;
        Pattern p1 = Pattern.compile("\\d+");
        Matcher m1;

        for (String s : input) {
            dir = s.charAt(0);
            m1 = p1.matcher(s);
            m1.find();
            meters = Integer.parseInt(m1.group());

            switch (dir) {
                case 'R' -> {
                    current.incrementX(meters);
                    if (current.getX() > maxX) maxX = current.getX();
                }
                case 'D' -> {
                    current.incrementY(meters);
                    if (current.getY() > maxY) maxY = current.getY();
                }
                case 'L' -> {
                    current.decrementX(meters);
                    if (current.getX() < minX) minX = current.getX();
                }
                case 'U' -> {
                    current.decrementY(meters);
                    if (current.getY() < minY) minY = current.getY();
                }
            }
        }
        System.out.println(maxX+", "+minX+", "+maxY+", "+minY);
        return new int[] {maxX, minX, maxY, minY};
    }

    private static void computeLine(LinkedList<String> input, boolean[][] diggedMap, int[] dim) {
        Node current = new Node(Math.abs(dim[1]), Math.abs(dim[3]));
        char dir;
        int meters;
        Node last;
        Pattern p1 = Pattern.compile("\\d+");
        Matcher m1;
        for (String s : input) {
            last = new Node(current.getX(), current.getY());

            dir = s.charAt(0);
            m1 = p1.matcher(s);
            m1.find();
            meters = Integer.parseInt(m1.group());

            switch (dir) {
                case 'R' -> {
                    current.incrementX(meters);
                    for (int i = last.getX(); i <= current.getX(); i++)
                        diggedMap[last.getY()][i] = true;
                }
                case 'D' -> {
                    current.incrementY(meters);
                    for(int i = last.getY(); i <= current.getY(); i++)
                        diggedMap[i][last.getX()] = true;
                }
                case 'L' -> {
                    current.decrementX(meters);
                    for (int i = last.getX(); i >= current.getX(); i--)
                        diggedMap[last.getY()][i] = true;
                }
                case 'U' -> {
                    current.decrementY(meters);
                    for (int i = last.getY(); i >= current.getY(); i--)
                        diggedMap[i][last.getX()] = true;
                }
            }
        }
    }

    private static int[] findMapDimPart2(LinkedList<String> input) {
        Node current = new Node(0, 0);
        char dir;
        int meters;
        int maxX = 0;
        int minX = 0;
        int maxY = 0;
        int minY = 0;
        Pattern p1 = Pattern.compile("#");
        Matcher m1;
        for (String s : input) {
            m1 = p1.matcher(s);
            m1.find();
            meters = Integer.parseInt(s.substring(m1.end(), s.length()-2), 16);
            dir = s.charAt(s.length()-2);

            switch (dir) {
                case '0' -> {
                    current.incrementX(meters);
                    if (current.getX() > maxX) maxX = current.getX();
                }
                case '1' -> {
                    current.incrementY(meters);
                    if (current.getY() > maxY) maxY = current.getY();
                }
                case '2' -> {
                    current.decrementX(meters);
                    if (current.getX() < minX) minX = current.getX();
                }
                case '3' -> {
                    current.decrementY(meters);
                    if (current.getY() < minY) minY = current.getY();
                }
            }
        }
        System.out.println(maxX+", "+minX+", "+maxY+", "+minY);
        return new int[] {maxX, minX, maxY, minY};
    }

    private static void computeLinePart2(LinkedList<String> input, boolean[][] diggedMap, int[] dim) {
        Node current = new Node(Math.abs(dim[1]), Math.abs(dim[3]));
        char dir;
        int meters;
        Node last;
        Pattern p1 = Pattern.compile("#");
        Matcher m1;
        for (String s : input) {
            last = new Node(current.getX(), current.getY());

            m1 = p1.matcher(s);
            m1.find();

            meters = Integer.parseInt(s.substring(m1.end(), s.length()-2), 16);
            dir = s.charAt(s.length()-2);

            switch (dir) {
                case '0' -> {
                    current.incrementX(meters);
                    for (int i = last.getX(); i <= current.getX(); i++)
                        diggedMap[last.getY()][i] = true;
                }
                case '1' -> {
                    current.incrementY(meters);
                    for(int i = last.getY(); i <= current.getY(); i++)
                        diggedMap[i][last.getX()] = true;
                }
                case '2' -> {
                    current.decrementX(meters);
                    for (int i = last.getX(); i >= current.getX(); i--)
                        diggedMap[last.getY()][i] = true;
                }
                case '3' -> {
                    current.decrementY(meters);
                    for (int i = last.getY(); i >= current.getY(); i--)
                        diggedMap[i][last.getX()] = true;
                }
            }
        }
    }

    private static int floodFill(Node start, boolean[][] diggingMap) {
        Stack<Node> stack = new Stack<>();
        stack.push(start);
        int count = 0;
        Set<Node> visited = new HashSet<>();
        Node nextNode;
        while (!stack.isEmpty()) {
            Node currentNode = stack.pop();
            int x = currentNode.getX();
            int y = currentNode.getY();
            if (!diggingMap[y][x+1]) {
                nextNode = new Node(x+1, y);
                if (!visited.contains(nextNode))
                    stack.push(nextNode);
            }
            if (!diggingMap[y][x-1]) {
                nextNode = new Node(x-1, y);
                if (!visited.contains(nextNode))
                    stack.push(nextNode);
            }
            if (!diggingMap[y+1][x]) {
                nextNode = new Node(x, y+1);
                if (!visited.contains(nextNode))
                    stack.push(nextNode);
            }
            if (!diggingMap[y-1][x]) {
                nextNode = new Node(x, y-1);
                if (!visited.contains(nextNode))
                    stack.push(nextNode);
            }
            if (!visited.contains(currentNode))
                count++;
            visited.add(currentNode);
        }

        for (boolean[] b : diggingMap)
            for (boolean value : b)
                if (value)
                    count++;

        return count;
    }
}
