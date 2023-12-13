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
        try (Scanner scanner = new Scanner(new File("./res/it/samaki/adventOfCode2023/problem13/test2.txt"))) {
            String s;
            while (scanner.hasNextLine()) {
                if (!(s = scanner.nextLine()).isEmpty()) {
                    input.add(s);
                } else {
                    int columnsNumber = getColumnsNumber(input);
                    int rowsNumber = getRowsNumber(input);
                    sum += columnsNumber + rowsNumber*100;
                    input = new LinkedList<>();
                }
            }
        }
        System.out.println("After summarizing all of the notes you get the number: " + sum);
    }

    private static int getColumnsNumber(LinkedList<String> input) {
        int columnsNumber = 0;
        for (int i = 1; i < input.get(0).length() - 1; i++) {
            int m = 1;
            for (int l = i; l >= 0; l--) {
                if (l+m > input.get(0).length() - 1) {
                    columnsNumber += i + 1;
                    System.out.println("ColumnsNumbers: " + columnsNumber);
                    i = input.get(0).length();
                    break;
                }
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
                if (l == 0) {
                    columnsNumber += i+1;
                    System.out.println("RowsNumbers: " + columnsNumber);
                    i = input.size();
                }
            }
        }
        return columnsNumber;
    }

    private static int getRowsNumber(LinkedList<String> input) {
        int rowsNumber = 0;
        int lineLength = input.get(0).length();
        for (int i = 1; i < input.size()-1; i++) {
            int m = 1;
            for (int l = i; l >= 0; l--) {
                if (l+m > input.size() - 1) {
                    rowsNumber += i + 1;
                    System.out.println("RowsNumbers: " + rowsNumber);
                    i = input.size();
                    break;
                }
                String currentLine = input.get(l);
                String nextLine = input.get(l+m);
                m += 2;
                for (int j = 0; j < lineLength; j++) {
                    if (currentLine.charAt(j) != nextLine.charAt(j)) {
                        l = -1;
                        break;
                    }
                }
                if (l == 0) {
                    rowsNumber += i+1;
                    System.out.println("RowsNumbers: " + rowsNumber);
                    i = input.size();
                }
            }
        }
        return rowsNumber;
    }
}