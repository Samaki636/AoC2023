package it.samaki.adventOfCode2023.problem10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author : Samaki01
 **/
public class Problem10 {
    public static void main (String[] args) throws FileNotFoundException {
        LinkedList<String> input = new LinkedList<>();
        String currentLine = "";
        int stepsToMake = 1;

        try (Scanner scanner = new Scanner(new File("./res/it/samaki/adventOfCode2023/problem10/test2.txt"))) {
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

        boolean[][] visited = new boolean[input.size()][currentLine.length()];
        for (int i = 0; i < visited.length; i++)
            for (int j = 0; j < currentLine.length(); j++)
                visited[i][j] = false;

        currentPipe = currentLine.charAt(CPIndex);
        visited[input.indexOf(currentLine)][CPIndex] = true;
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
                default -> System.out.println("ERROR");
            }
            currentPipe = currentLine.charAt(CPIndex);
            stepsToMake++;
            visited[input.indexOf(currentLine)][CPIndex] = true;
        }

        System.out.println("Does it take " + stepsToMake/2 + " steps to get from the starting position to the point " +
                "farthest from the starting position.");

        int i = 0;
        for (boolean[] booleans : visited) {
            i++;
            if (i < 10)
                System.out.print("Line: 00" + i);
            else if (i < 100)
                System.out.print("Line: 0" + i);
            else System.out.print("Line: " + i);
            for (int j = 0; j < currentLine.length(); j++) {
                if (booleans[j])
                    System.out.print("[X]");
                else
                    System.out.print("[ ]");
            }
            System.out.println();
        }

        int countL7FJ;
        int count1;
        int nEnclosedTiles = 0;
        Pattern pattern = Pattern.compile("F[^J7LFS|.]*J");
        Pattern pattern1 = Pattern.compile("[LS][^7JFSL|.]*7");
        Pattern pattern2 = Pattern.compile("\\|");
        Matcher matcher;
        Matcher matcher1;
        Matcher matcher2;
        for (int l = 1; l < visited.length; l++) {
            boolean[] line = visited[l];
            matcher = pattern.matcher(input.get(l));
            matcher1 = pattern1.matcher(input.get(l));
            matcher2 = pattern2.matcher(input.get(l));
            for (int j = 0; j < visited[0].length; j++) {
                if (line[j])
                    continue;
                countL7FJ = 0;
                count1 = 0;
                matcher.reset();
                matcher1.reset();
                matcher2.reset();
                matcher.region(0, j);
                matcher1.region(0, j);
                matcher2.region(0, j);
                while (matcher.find()) {
                    System.out.println(matcher.group());
                    boolean valid = false;
                    for (int m = matcher.start(); m < matcher.end(); m++) {
                        if (!line[m]) {
                            valid = false;
                            break;
                        } else
                            valid = true;
                    }
                    if (valid)
                        countL7FJ++;
                }
                while (matcher1.find()) {
                    System.out.println(matcher1.group());
                    boolean valid = false;
                    for (int m = matcher1.start(); m < matcher1.end(); m++) {
                        if (!line[m]) {
                            valid = false;
                            break;
                        } else
                            valid = true;
                    }
                    if (valid)
                        countL7FJ++;
                }

                while (matcher2.find()) {
                    if (line[matcher2.start()])
                        count1++;
                }

                if ((countL7FJ + count1) %2 != 0)
                    nEnclosedTiles++;
            }
        }
        System.out.println("The number of enclosed tiles is: " + nEnclosedTiles);
    }
}
