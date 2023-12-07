package it.samaki.adventOfCode2023.problem5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author : Samaki
 **/
public class Problem5tris {
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void main(String[] args) throws FileNotFoundException {
        String line;

        long destinationStart;
        long sourceStart;
        long range;
        long seedStart;
        long seedRange;

        Map<Long, Long> seedBuckets = new HashMap<>();

        LinkedList<Pipe> seedToSoilPipes = new LinkedList<>();
        LinkedList<Pipe> soilToFertilizerPipes = new LinkedList<>();
        LinkedList<Pipe> fertilizerToWaterPipes = new LinkedList<>();
        LinkedList<Pipe> waterToLightPipes = new LinkedList<>();
        LinkedList<Pipe> lightToTemperaturePipes = new LinkedList<>();
        LinkedList<Pipe> temperatureToHumidityPipes = new LinkedList<>();
        LinkedList<Pipe> humidityToLocationPiepes = new LinkedList<>();

        Pattern pattern = Pattern.compile("\\d+");

        Matcher matcher1;
        Matcher matcher2;

        PipeComparator pipeComparator = new PipeComparator();

        final int NUMBER_OF_ATTRIBUTES = 7;

        try (Scanner scanner = new Scanner(new File("./res/it/samaki/adventOfCode2023/problem5/test2.txt"))) {
            matcher1 = pattern.matcher(scanner.nextLine());
            while (matcher1.find()) {
                seedStart = Long.parseLong(matcher1.group());
                matcher1.find();
                seedRange = Long.parseLong(matcher1.group());

                seedBuckets.put(seedStart, seedRange);
            }

            for (int i = 0; i < NUMBER_OF_ATTRIBUTES; i++) {
                scanner.nextLine();
                scanner.nextLine();
                while (scanner.hasNext() && !(line = scanner.nextLine()).isEmpty()) {
                    matcher2 = pattern.matcher(line);
                    matcher2.find();
                    destinationStart = Long.parseLong(matcher2.group());
                    matcher2.find();
                    sourceStart = Long.parseLong(matcher2.group());
                    matcher2.find();
                    range = Long.parseLong(matcher2.group());

                    switch (i) {
                        case 0 -> seedToSoilPipes.add(new Pipe(sourceStart, range, destinationStart));
                        case 1 -> soilToFertilizerPipes.add(new Pipe(sourceStart, range, destinationStart));
                        case 2 -> fertilizerToWaterPipes.add(new Pipe(sourceStart, range, destinationStart));
                        case 3 -> waterToLightPipes.add(new Pipe(sourceStart, range, destinationStart));
                        case 4 -> lightToTemperaturePipes.add(new Pipe(sourceStart, range, destinationStart));
                        case 5 -> temperatureToHumidityPipes.add(new Pipe(sourceStart, range, destinationStart));
                        case 6 -> humidityToLocationPiepes.add(new Pipe(sourceStart, range, destinationStart));
                    }
                }
                switch (i) {
                    case 0 -> seedToSoilPipes.sort(pipeComparator);
                    case 1 -> soilToFertilizerPipes.sort(pipeComparator);
                    case 2 -> fertilizerToWaterPipes.sort(pipeComparator);
                    case 3 -> waterToLightPipes.sort(pipeComparator);
                    case 4 -> lightToTemperaturePipes.sort(pipeComparator);
                    case 5 -> temperatureToHumidityPipes.sort(pipeComparator);
                    case 6 -> humidityToLocationPiepes.sort(pipeComparator);
                }
            }
        }

//        for (Long value : seedBuckets.keySet()) {
//            for (Pipe pipe : seedToSoilPipes) {
//                if (value >= pipe.getInputStart() && value < pipe.getInputEnd())
//                    ;
//            }
//        }
    }
}
