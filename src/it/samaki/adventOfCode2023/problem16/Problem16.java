package it.samaki.adventOfCode2023.problem16;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

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

        boolean[][] energized = new boolean[input.size()][input.get(0).length()];
        char dir = 'R';
        int x = 0;
        int y = 0;
        int energizedTiles = 0;

        HashMap<String, boolean[][]> cache = new HashMap<>();

        energized = computeBeam(dir, x, y, input, energized, cache);
        energized[0][0] = true;

        for (boolean[] booleans : energized) {
            for (int j = 0; j < energized[0].length; j++) {
                if (booleans[j]) {
                    energizedTiles++;
                    System.out.print("[X]");
                } else System.out.print("[ ]");
            }
            System.out.println();
        }

        System.out.println(energizedTiles + " tiles end up being energized.");
    }

    //Problem: termitation condition is passing 3 energized tiles while cycling, for now it works, check timer
    private static boolean[][] computeBeam
    (char dir, int x, int y, LinkedList<String> input, boolean[][] energized, HashMap<String, boolean[][]> cache) {
        String hash = Arrays.deepToString(energized);
        if (cache.containsKey(hash))
            return energized;
        boolean firstRun = true;
        int timer = 11;
        while (timer != 0) {
            if (energized[y][x])
                timer--;
            if (firstRun) {
                firstRun = false;
            } else {
                energized[y][x] = true;
            }
            switch (dir) {
                case 'R' -> {
                    x++;
                    if (x >= input.get(y).length())
                        return energized;
                    switch (input.get(y).charAt(x)) {
                        case '/' -> dir = 'U';
                        case '\\' -> dir = 'D';
                        case '|' -> {
                            energized = computeBeam('U', x, y, input, energized, cache);
                            dir = 'D';
                        }
                    }
                }
                case 'L' -> {
                    x--;
                    if (x < 0)
                        return energized;
                    switch (input.get(y).charAt(x)) {
                        case '/' -> dir = 'D';
                        case '\\' -> dir = 'U';
                        case '|' -> {
                            energized = computeBeam('U', x, y, input, energized, cache);
                            dir = 'D';
                        }
                    }
                }
                case 'U' -> {
                    y--;
                    if (y < 0)
                        return energized;
                    switch (input.get(y).charAt(x)) {
                        case '/' -> dir = 'R';
                        case '\\' -> dir = 'L';
                        case '-' -> {
                            energized = computeBeam('R', x, y, input, energized, cache);
                            dir = 'L';
                        }
                    }
                }
                case 'D' -> {
                    y++;
                    if (y >= input.size())
                        return energized;
                    switch (input.get(y).charAt(x)) {
                        case '/' -> dir = 'L';
                        case '\\' -> dir = 'R';
                        case '-' -> {
                            energized = computeBeam('R', x, y, input, energized, cache);
                            dir = 'L';
                        }
                    }
                }
                default -> System.out.println("Error.");
            }
        }
        cache.put(hash, energized);
        return energized;
    }
}
//2322 timer 3; stackOverflow timer 10; 3424 timer 5; 4157 timer 7; stackOverflow timer 8;
//4566 cache timer 8; 5023 timer 10; 5258 cache timer 11