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

        for (Pipe pipe1 : humidityToLocationPiepes) {
            long outputStart = pipe1.getInputStart();
            long outputEnd = pipe1.getInputEnd();
            long inputStart;
            for (Pipe pipe2 : temperatureToHumidityPipes) {
                inputStart = pipe2.getOutputStart();
                if (inputStart > outputStart && inputStart < outputEnd) {
                    System.out.print("\nPipe1: " + humidityToLocationPiepes.indexOf(pipe1) +
                            " Pipe2: " + temperatureToHumidityPipes.indexOf(pipe2));
                    outputStart = pipe2.getInputStart();
                    outputEnd = pipe2.getInputEnd();
                    for (Pipe pipe3 : lightToTemperaturePipes) {
                        inputStart = pipe3.getOutputStart();
                        if (inputStart > outputStart && inputStart < outputEnd) {
                            System.out.print(" Pipe2: " + temperatureToHumidityPipes.indexOf(pipe2) +
                                    " Pipe3: " + lightToTemperaturePipes.indexOf(pipe3));
                            outputStart = pipe3.getInputStart();
                            outputEnd = pipe3.getInputEnd();
                            for (Pipe pipe4 : waterToLightPipes) {
                                inputStart = pipe4.getOutputStart();
                                if (inputStart > outputStart && inputStart < outputEnd) {
                                    System.out.print(" Pipe3: " + lightToTemperaturePipes.indexOf(pipe3) +
                                            " Pipe4: " + waterToLightPipes.indexOf(pipe4));
                                    outputStart = pipe4.getInputStart();
                                    outputEnd = pipe4.getInputEnd();
                                    for (Pipe pipe5 : fertilizerToWaterPipes) {
                                        inputStart = pipe5.getOutputStart();
                                        if (inputStart > outputStart && inputStart < outputEnd) {
                                            System.out.print(" pipe4: " + waterToLightPipes.indexOf(pipe4) +
                                                    " Pipe5: " + fertilizerToWaterPipes.indexOf(pipe5));
                                            outputStart = pipe5.getInputStart();
                                            outputEnd = pipe5.getInputEnd();
                                            for (Pipe pipe6 : soilToFertilizerPipes) {
                                                inputStart = pipe6.getOutputStart();
                                                if (inputStart > outputStart && inputStart < outputEnd) {
                                                    System.out.print(" Pipe5: " + fertilizerToWaterPipes.indexOf(pipe5) +
                                                            " Pipe6: " + soilToFertilizerPipes.indexOf(pipe6));
                                                    outputStart = pipe6.getInputStart();
                                                    outputEnd = pipe6.getInputEnd();
                                                    for (Pipe pipe7 : seedToSoilPipes) {
                                                        inputStart = pipe7.getOutputStart();
                                                        if (inputStart > outputStart && inputStart < outputEnd) {
                                                            System.out.print(" Pipe6: " + soilToFertilizerPipes.indexOf(pipe6) +
                                                                    " Pipe7: " + seedToSoilPipes.indexOf(pipe7));

                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    outputStart = pipe1.getInputStart();
                    outputEnd = pipe1.getInputEnd();
                }
            }
        }
    }
}
