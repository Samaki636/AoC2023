package it.samaki.adventOfCode2023.problem5;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author : Samaki
 **/
public class Problem5 {
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void main(String[] args) throws IOException {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher;

        String line;

        //final int NUMBER_OF_SEEDS = 4;
        final int NUMBER_OF_SEEDS = 20;
        final int NUMBER_OF_ATTRIBUTES = 8;

        long destinationRangeStart;
        long sourceRangeStart;
        long rangeLength;

        long[] seeds = new long[NUMBER_OF_SEEDS];
        LinkedList<Long>[] attributes = new LinkedList[NUMBER_OF_ATTRIBUTES];

        for (int i = 1; i < NUMBER_OF_ATTRIBUTES; i++) {
            attributes[i] = new LinkedList<>();
            for (int j = 0; j < NUMBER_OF_SEEDS; j++) {
                attributes[i].add((long) -1);
            }
        }

        try (Scanner scanner = new Scanner(new FileReader(
                "./res/it/samaki/adventOfCode2023/problem5/test2.txt"))) {
            while (scanner.hasNext()) {
                //Read all the seeds numbers, put them in seeds and sort them
                while (!(line = scanner.nextLine()).isEmpty()) {
                    matcher = pattern.matcher(line);
                    for (int i = 0; i < NUMBER_OF_SEEDS; i++) {
                        matcher.find();
                        seeds[i] = Long.parseLong(matcher.group());
                    }
                    Arrays.sort(seeds);
                }

                //Put the seeds in the first LinkedList of attributes
                attributes[0] = new LinkedList<>();
                for (int i = 0; i < NUMBER_OF_SEEDS; i++) {
                    attributes[0].add(seeds[i]);
                }

                //Read all the maps
                for (int k = 0; k < NUMBER_OF_ATTRIBUTES - 1; k++) {
                    scanner.nextLine();

                    for (int i = 0; i < NUMBER_OF_SEEDS; i++)
                        attributes[k + 1].set(i, attributes[k].get(i));

                    while (!(line = scanner.nextLine()).isEmpty() && scanner.hasNext()) {
                        matcher = pattern.matcher(line);

                        matcher.find();
                        destinationRangeStart = Long.parseLong(matcher.group());
                        matcher.find();
                        sourceRangeStart = Long.parseLong(matcher.group());
                        matcher.find();
                        rangeLength = Long.parseLong(matcher.group());

                        for (int i = 0; i < NUMBER_OF_SEEDS; i++) {
                            if (attributes[k].get(i) >= sourceRangeStart &&
                                    attributes[k].get(i) < sourceRangeStart + rangeLength) {
                                attributes[k + 1].set(i, destinationRangeStart +
                                        attributes[k].get(i) - sourceRangeStart);
                            }
                        }
                    }
                }
            }
        }
        Arrays.sort(attributes[NUMBER_OF_ATTRIBUTES - 1].toArray());
        for (long element : attributes[NUMBER_OF_ATTRIBUTES -1]) {
            if (element != 0) {
                System.out.println("The closes location that needs a seed is the number: " + element);
                break;
            }
        }
    }
}
