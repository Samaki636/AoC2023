package it.samaki.adventOfCode2023.problem16;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

/**
 * @author : Samaki01
 **/
public class Problem16 {
    public static void main(String[] args) throws FileNotFoundException {
        LinkedList<String> input = new LinkedList<>();
        try (Scanner scanner = new Scanner(new File("res/it/samaki/adventOfCode2023/problem16/test2.txt"))) {
            while (scanner.hasNextLine())
                input.add(scanner.nextLine());
        }

        int maxEnergizedTiles = 0;
        for (int i = 0; i < input.size(); i++) {
            boolean[][] energized = new boolean[input.size()][input.get(0).length()];
            int energizedTiles = 0;
            Stack<String> toDoList = new Stack<>();
            LinkedList<String> visited = new LinkedList<>();

            energized = computeBeam('U', i, input.size(), input, energized, toDoList, visited);

            //compute energized tiles number
            for (boolean[] booleans : energized) {
                for (int j = 0; j < energized[0].length; j++) {
                    if (booleans[j]) {
                        energizedTiles++;
                        System.out.print("[X]");
                    } else {
                        System.out.print("[ ]");
                    }
                }
                System.out.println();
            }
            System.out.println();
            if (maxEnergizedTiles < energizedTiles)
                maxEnergizedTiles = energizedTiles;
        }

        System.out.println(maxEnergizedTiles + " tiles end up being energized at max.");
    }

    //Problem: termitation condition is passing n energized tiles while cycling, for now it works, check timer
    private static boolean[][] computeBeam
    (char dir, int x, int y, LinkedList<String> input, boolean[][] energized, Stack<String> toDoList, LinkedList<String> visited) {
        String hash = dir+","+x+","+y;
        boolean firstLoop = true;
        while (!(dir+","+x+","+y).equals(hash) || firstLoop) {
            if (firstLoop) firstLoop = false;
            if (y < input.size())
                energized[y][x] = true;
            switch (dir) {
                case 'R' -> {
                    x++;
                    if (x >= input.get(y).length())
                        break;
                    switch (input.get(y).charAt(x)) {
                        case '/' -> dir = 'U';
                        case '\\' -> dir = 'D';
                        case '|' -> {
                            if (!visited.contains('U'+","+x+","+y)) {
                                visited.add('U'+","+x+","+y);
                                toDoList.push('U'+","+x+","+y);
                            }
                            dir = 'D';
                        }
                    }
                }
                case 'L' -> {
                    x--;
                    if (x < 0)
                        break;
                    switch (input.get(y).charAt(x)) {
                        case '/' -> dir = 'D';
                        case '\\' -> dir = 'U';
                        case '|' -> {
                            if (!visited.contains('U'+","+x+","+y)) {
                                visited.add('U'+","+x+","+y);
                                toDoList.push('U'+","+x+","+y);
                            }
                            dir = 'D';
                        }
                    }
                }
                case 'U' -> {
                    y--;
                    if (y < 0)
                        break;
                    switch (input.get(y).charAt(x)) {
                        case '/' -> dir = 'R';
                        case '\\' -> dir = 'L';
                        case '-' -> {
                            if (!visited.contains('R'+","+x+","+y)) {
                                visited.add('R'+","+x+","+y);
                                toDoList.push('R' + "," + x + "," + y);
                            }
                            dir = 'L';
                        }
                    }
                }
                case 'D' -> {
                    y++;
                    if (y >= input.size())
                        break;
                    switch (input.get(y).charAt(x)) {
                        case '/' -> dir = 'L';
                        case '\\' -> dir = 'R';
                        case '-' -> {
                            if (!visited.contains('R'+","+x+","+y)) {
                                visited.add('R'+","+x+","+y);
                                toDoList.push('R'+","+x+","+y);
                            }
                            dir = 'L';
                        }
                    }
                }
                default -> System.out.println("Error.");
            }

            if (x < 0 || y < 0 || y >= input.size() || x >= input.get(y).length())
                break;
        }

        while (!toDoList.isEmpty()) {
            String s = toDoList.pop();
            String[] tmp = s.split(",");
            energized = computeBeam(tmp[0].charAt(0), Integer.parseInt(tmp[1]), Integer.parseInt(tmp[2]), input, energized, toDoList, visited);
        }

        return energized;
    }
}
//2322 timer 3; stackOverflow timer 10; 3424 timer 5; 4157 timer 7; stackOverflow timer 8;
//4566 cache timer 8; 5023 timer 10; 5258 cache timer 11
//input was wrong.
// 4442 cache timer 8 too low;

//D 8136 low D 8148?; U 8058; R 8136; L 8047