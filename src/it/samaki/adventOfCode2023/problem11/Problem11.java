package it.samaki.adventOfCode2023.problem11;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author : Samaki01
 **/
public class Problem11 {
    public static void main (String[] args) throws FileNotFoundException {
        LinkedList<String> input = new LinkedList<>();
        LinkedList<Integer> indexesRowsToExpand = new LinkedList<>();
        LinkedList<Integer> indexesColumnsToExpand = new LinkedList<>();
        int MULTIPLIER = 1000000;
        try (Scanner scanner = new Scanner(new File("./res/it/samaki/adventOfCode2023/problem11/test2.txt"))) {
            while (scanner.hasNext())
                input.add(scanner.nextLine());
        }

        //expansion
        for (int i = 0; i < input.size(); i++) {
            String s = input.get(i);
            if (!s.matches(".*[^.].*"))
                indexesRowsToExpand.add(i);
        }

        for (int i = 0; i < input.get(0).length(); i++) {
            for (int j = 0; j < input.size(); j++) {
                if (input.get(j).charAt(i) != '.')
                    break;
                if (j == input.size() - 1)
                    indexesColumnsToExpand.add(i);
            }
        }

        //build pairs 92235 and calculate distances; i, j coordinates of first #; k, l of the second #;
        long distance = 0;
        int nPair = 0;
        int m;
        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.get(0).length(); j++) {
                if (input.get(i).charAt(j) == '#') {
                    m = j + 1;
                    for (int k = i; k < input.size(); k++) {
                        for (int l = m; l < input.get(0).length(); l++) {
                            if (input.get(k).charAt(l) == '#') {
                                int sum = Math.abs(k - i) + Math.abs(l - j);
                                for (Integer num : indexesColumnsToExpand)
                                    if ((j < num && num < l) || (l < num && num < j))
                                        sum += MULTIPLIER - 1;
                                for (Integer num : indexesRowsToExpand)
                                    if (i < num && num < k)
                                        sum += MULTIPLIER - 1;
                                System.out.println("Sum: " + sum);
                                distance += sum;
                                nPair++;
                            }
                        }
                        m = 0;
                    }
                }
            }
        }

        //test print
        for (String s : input) {
            for (int j = 0; j < input.get(0).length(); j++)
                System.out.print(s.charAt(j));
            System.out.println();
        }

        System.out.println("The sum of the shortest path between all the pairs of galaxies is: " + distance);
        System.out.println("The number of the pairs is: " + nPair);
    }
}
