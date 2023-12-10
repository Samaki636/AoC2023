package it.samaki.adventOfCode2023.problem10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author : Samaki01
 **/
public class Problem10 {
    public static void main (String[] args) throws FileNotFoundException {
        LinkedList<String> input = new LinkedList<>();
        String currentLine = "";
        int stepsToMake = 1;

        try (Scanner scanner = new Scanner(new File("./res/it/samaki/adventOfCode2023/problem10/test3.txt"))) {
            while (scanner.hasNextLine()) {
                input.add(scanner.nextLine());
                if (input.getLast().contains("S")) {
                    currentLine = input.getLast();
                }
            }
        }

        char currentPipe;
        int CPIndex = currentLine.indexOf('S') + 1;
        char lastDirection = 'R';

        currentPipe = currentLine.charAt(CPIndex);
        while (currentPipe != 'S') {
            switch (currentPipe) {
                case '-' -> {
                    if (lastDirection == 'R') CPIndex++;
                    else CPIndex--;
                }
                case '|' -> {
                    if (lastDirection == 'U') currentLine = input.get(input.indexOf(currentLine) - 1);
                    else currentLine = input.get(input.indexOf(currentLine) + 1);
                }
                case 'L' -> {
                    if (lastDirection == 'L') {
                        lastDirection = 'U';
                        currentLine = input.get(input.indexOf(currentLine) - 1);
                    } else {
                        lastDirection = 'R';
                        CPIndex++;
                    }
                }
                case 'J' -> {
                    if (lastDirection == 'R') {
                        lastDirection = 'U';
                        currentLine = input.get(input.indexOf(currentLine) - 1);
                    } else {
                        lastDirection = 'L';
                        CPIndex--;
                    }
                }
                case '7' -> {
                    if (lastDirection == 'U') {
                        lastDirection = 'L';
                        CPIndex--;
                    } else {
                        lastDirection = 'D';
                        currentLine = input.get(input.indexOf(currentLine) + 1);
                    }
                }
                case 'F' -> {
                    if (lastDirection == 'U') {
                        lastDirection = 'R';
                        CPIndex++;
                    } else {
                        lastDirection = 'D';
                        currentLine = input.get(input.indexOf(currentLine) + 1);
                    }
                }
            }
            currentPipe = currentLine.charAt(CPIndex);
            stepsToMake++;
        }

        System.out.println("Does it take " + stepsToMake/2 + " steps to get from the starting position to the point " +
                "farthest from the starting position.");
    }
}
