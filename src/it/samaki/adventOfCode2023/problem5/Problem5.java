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

        final int NUMBER_OF_SEEDS = 4;
        //final int NUMBER_OF_SEEDS = 20;
        final int NUMBER_OF_ATTRIBUTES = 8;

        int destinationRangeStart;
        int sourceRangeStart;
        int rangeLength;

        Integer[] seeds = new Integer[NUMBER_OF_SEEDS];
        LinkedList<Integer>[] attributes = new LinkedList[NUMBER_OF_ATTRIBUTES];

        for (int i = 0; i < NUMBER_OF_ATTRIBUTES; i++) {
            attributes[i] = new LinkedList<>();
        }

        try (Scanner scanner = new Scanner(new FileReader(
                "./res/it/samaki/adventOfCode2023/problem5/test1.txt"))) {
            while (scanner.hasNext()) {
                //Read all the seeds numbers, put them in seeds and sort them.
                while (!(line = scanner.nextLine()).isEmpty()) {
                    matcher = pattern.matcher(line);
                    for (int i = 0; i < NUMBER_OF_SEEDS; i++) {
                        matcher.find();
                        seeds[i] = Integer.parseInt(matcher.group());
                    }
                    Arrays.sort(seeds);
                }

                //Put the seeds in the first LinkedList of attributes
                for (int i = 0; i < NUMBER_OF_SEEDS; i++) {
                    attributes[0].add(seeds[i]);
                }

                //Read the seed to soil map
                scanner.nextLine();
                while (!(line = scanner.nextLine()).isEmpty()) {
                    matcher = pattern.matcher(line);

                    matcher.find();
                    destinationRangeStart = Integer.parseInt(matcher.group());
                    matcher.find();
                    sourceRangeStart = Integer.parseInt(matcher.group());
                    matcher.find();
                    rangeLength = Integer.parseInt(matcher.group());

                    for (int i = 0; i < rangeLength; i++) {
                        int seed;
                        int fertilizer;

                        seed = sourceRangeStart + i;
                        fertilizer = destinationRangeStart + i;

                        if (!attributes[0].contains(seed))
                            break;

                        for (int j = 0; j < NUMBER_OF_SEEDS; j++) {
                            attributes[1].add(attributes[0].indexOf(seed), fertilizer);
                        }
                    }
                }
                //TODO Delete this line;
                break;
            }
        }
    }
}
