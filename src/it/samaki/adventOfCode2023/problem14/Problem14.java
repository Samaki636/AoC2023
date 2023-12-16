package it.samaki.adventOfCode2023.problem14;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author : Samaki01
 **/
public class Problem14 {
    public static void main(String[] args) throws FileNotFoundException {
        LinkedList<String> input = new LinkedList<>();
        try (Scanner scanner = new Scanner(new File("res/it/samaki/adventOfCode2023/problem14/test2.txt"))) {
            while (scanner.hasNextLine()) {
                input.add(scanner.nextLine());
            }
        }

        HashMap<String, LinkedList<String>> map = new HashMap<>();
        for (int k = 0; k < 1000000000; k++) {
            input = cycle(input, map);
        }

        //compute load
        String currentLine;
        int load = 0;
        int nBoulders;
        for (int i = 0; i < input.size(); i++) {
            nBoulders = 0;
            currentLine = input.get(i);
            for (int j = 0; j < input.get(0).length(); j++)
                if (currentLine.charAt(j) == 'O')
                    nBoulders++;
            load += nBoulders*(input.size() - i);
        }

        System.out.println("The total load on the north support beams after rotation is: " + load);
    }

    private static LinkedList<String> cycle(LinkedList<String> input, HashMap<String, LinkedList<String>> map) {
        String hash2 = input.toString();
        if (map.containsKey(hash2))
            return map.get(hash2);
        //north rotation
        while (true) {
            String lastLine;
            String currentLine = input.get(0);

            //loop on all the lines one time
            String hash1 = input.toString();
            for (int i = 1; i < input.size(); i++) {
                lastLine = currentLine;
                currentLine = input.get(i);

                //optimization
                if (!currentLine.contains("O"))
                    continue;

                //loop on all the character of each line and move boulder north if its path is free
                for (int j = 0; j < input.get(0).length(); j++) {
                    if (currentLine.charAt(j) == 'O' && lastLine.charAt(j) == '.') {
                        lastLine = lastLine.substring(0, j) + 'O' + lastLine.substring(j + 1);
                        currentLine = currentLine.substring(0, j) + '.' + currentLine.substring(j + 1);
                    }
                }
                input.set(i - 1, lastLine);
                input.set(i, currentLine);
            }

            //check if boulders have finished moving, then break
            if (hash1.equals(input.toString()))
                break;
        }

        //west rotation
        while (true) {
            String currentLine;

            //loop on all the lines one time
            String hash1 = input.toString();
            for (int i = 0; i < input.size(); i++) {
                currentLine = input.get(i);

                //optimization
                if (!currentLine.contains("O"))
                    continue;

                //loop on all the character of each line and move boulder west if its path is free
                for (int j = 1; j < input.get(0).length(); j++)
                    if (currentLine.charAt(j) == 'O' && currentLine.charAt(j - 1) == '.')
                        currentLine = currentLine.substring(0, j - 1) + "O." + currentLine.substring(j + 1);
                input.set(i, currentLine);
            }

            //check if boulders have finished moving, then break
            if (hash1.equals(input.toString()))
                break;
        }

        //south rotation
        while (true) {
            String lastLine;
            String currentLine = input.get(0);

            //loop on all the lines one time
            String hash1 = input.toString();
            for (int i = 1; i < input.size(); i++) {
                lastLine = currentLine;
                currentLine = input.get(i);

                //optimization
                if (!lastLine.contains("O"))
                    continue;

                //loop on all the character of each line and move boulder south if its path is free
                for (int j = 0; j < input.get(0).length(); j++) {
                    if (currentLine.charAt(j) == '.' && lastLine.charAt(j) == 'O') {
                        lastLine = lastLine.substring(0, j) + '.' + lastLine.substring(j + 1);
                        currentLine = currentLine.substring(0, j) + 'O' + currentLine.substring(j + 1);
                    }
                }
                input.set(i - 1, lastLine);
                input.set(i, currentLine);
            }

            //check if boulders have finished moving, then break
            if (hash1.equals(input.toString()))
                break;
        }

        //east rotation
        while (true) {
            String currentLine;

            //loop on all the lines one time
            String hash1 = input.toString();
            for (int i = 0; i < input.size(); i++) {
                currentLine = input.get(i);

                //optimization
                if (!currentLine.contains("O"))
                    continue;

                //loop on all the character of each line and move boulder east if its path is free
                for (int j = 1; j < input.get(0).length(); j++)
                    if (currentLine.charAt(j) == '.' && currentLine.charAt(j - 1) == 'O')
                        currentLine = currentLine.substring(0, j - 1) + ".O" + currentLine.substring(j + 1);
                input.set(i, currentLine);
            }

            //check if boulders have finished moving, then break
            if (hash1.equals(input.toString()))
                break;
        }
        map.put(hash2, new LinkedList<>(input));
        return input;
    }
}