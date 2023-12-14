package it.samaki.adventOfCode2023.problem13;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author : Samaki01
 **/
public class Problem13 {
    public static void main (String[] args) throws FileNotFoundException {
        LinkedList<String> input = new LinkedList<>();
        int sum = 0;
        int sumP2 = 0;
        int patternNumber = 0;
        int rowsNumber;
        int columnsNumber;
        int rowsNumberP2;
        int columnsNumberP2;
        try (Scanner scanner = new Scanner(new File("./res/it/samaki/adventOfCode2023/problem13/test2.txt"))) {
            String s;
            while (scanner.hasNextLine()) {
                if (!(s = scanner.nextLine()).isEmpty()) {
                    input.add(s);
                } else {
                    System.out.println("Pattern number: " + patternNumber + "----------------------------------------");

                    rowsNumber = getRowsNumberP1(input);
                    if (rowsNumber == 0)
                        columnsNumber = getColumnsNumberP1(input);
                    else
                        columnsNumber = 0;
                    sum += columnsNumber + rowsNumber*100;
                    System.out.println("Partial sum: " + sum);

                    rowsNumberP2 = getRowsNumberP2(input);
                    if (rowsNumberP2 == 0)
                        columnsNumberP2 = getColumnsNumberP2(input);
                    else
                        columnsNumberP2 = 0;
                    sumP2 += columnsNumberP2 + rowsNumberP2*100;
                    System.out.println("Partial sumP2: " + sumP2);

                    input = new LinkedList<>();
                    patternNumber++;
                }
            }
        }
        System.out.println("After summarizing all of the notes you get the number: " + sum);
        System.out.println("After summarizing all of the notes in part 2 you get the number: " + sumP2);
    }

    private static int getRowsNumberP1(LinkedList<String> input) {
        int rowsNumber = 0;
        int lineLength = input.get(0).length();
        for (int i = 0; i < input.size()-1; i++) {
            int m = 1;
            for (int l = i; l >= 0; l--) {
                String currentLine = input.get(l);
                String nextLine = input.get(l+m);
                m += 2;
                for (int j = 0; j < lineLength; j++) {
                    if (currentLine.charAt(j) != nextLine.charAt(j)) {
                        l = -1;
                        break;
                    }
                }
                if (l == 0 || l+m > input.size()) {
                    rowsNumber += i+1;
                    System.out.println("RowsNumbersP1: " + rowsNumber);
                    return rowsNumber;
                }
            }
        }
        return rowsNumber;
    }

    private static int getColumnsNumberP1(LinkedList<String> input) {
        int columnsNumber = 0;
        for (int i = 0; i < input.get(0).length() - 1; i++) {
            int m = 1;
            for (int l = i; l >= 0; l--) {
                StringBuilder currentColumn = new StringBuilder();
                for (String s : input)
                    currentColumn.append(s.charAt(l));
                StringBuilder nextColumn = new StringBuilder();
                for (String s : input)
                    nextColumn.append(s.charAt(l+m));
                m += 2;
                for (int j = 0; j < currentColumn.length(); j++) {
                    if (currentColumn.charAt(j) != nextColumn.charAt(j)) {
                        l = -1;
                        break;
                    }
                }
                if (l == 0 || l+m > input.get(0).length()) {
                    columnsNumber += i+1;
                    System.out.println("ColumnsNumberP1: " + columnsNumber);
                    return columnsNumber;
                }
            }
        }
        return columnsNumber;
    }

    private static int getRowsNumberP2(LinkedList<String> input) {
        int rowsNumber = 0;
        int lineLength = input.get(0).length();
        int nDifferences;
        boolean isSmudgeRemoved;
        for (int i = 0; i < input.size()-1; i++) {
            int m = 1;
            isSmudgeRemoved = false;
            for (int l = i; l >= 0; l--) {
                nDifferences = 0;
                String currentLine = input.get(l);
                String nextLine = input.get(l+m);
                m += 2;
                for (int j = 0; j < lineLength; j++) {
                    if (currentLine.charAt(j) != nextLine.charAt(j)) {
                        nDifferences++;
                    }
                    if (nDifferences > 1) {
                        l = -1;
                        break;
                    }
                }
                if (nDifferences == 1)
                    isSmudgeRemoved = true;
                if ((l == 0 || l+m > input.size()) && isSmudgeRemoved) {
                    rowsNumber += i+1;
                    System.out.println("RowsNumbersP2: " + rowsNumber);
                    return rowsNumber;
                } else if (l+m > input.size())
                    break;
            }
        }
        return rowsNumber;
    }

    private static int getColumnsNumberP2(LinkedList<String> input) {
        int columnsNumber = 0;
        int nDifferences;
        boolean isSmudgeRemoved;
        for (int i = 0; i < input.get(0).length() - 1; i++) {
            int m = 1;
            isSmudgeRemoved = false;
            for (int l = i; l >= 0; l--) {
                nDifferences = 0;
                StringBuilder currentColumn = new StringBuilder();
                for (String s : input)
                    currentColumn.append(s.charAt(l));
                StringBuilder nextColumn = new StringBuilder();
                for (String s : input)
                    nextColumn.append(s.charAt(l+m));
                m += 2;
                for (int j = 0; j < currentColumn.length(); j++) {
                    if (currentColumn.charAt(j) != nextColumn.charAt(j)) {
                        nDifferences++;
                    }
                    if (nDifferences > 1) {
                        l = -1;
                        break;
                    }
                }
                if (nDifferences == 1)
                    isSmudgeRemoved = true;
                if ((l == 0 || l+m > input.get(0).length()) && isSmudgeRemoved) {
                    columnsNumber += i+1;
                    System.out.println("ColumnsNumberP2: " + columnsNumber);
                    return columnsNumber;
                } else if (l+m > input.get(0).length())
                    break;
            }
        }
        return columnsNumber;
    }
}