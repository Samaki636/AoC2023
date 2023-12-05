package it.samaki.adventOfCode2023.problem5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author : Samaki
 **/
public class Problem5bis {
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void main (String[] args) throws FileNotFoundException {
        String seeds;

        StringBuilder seedToSoilMap = new StringBuilder();
        StringBuilder soilToFertilizerMap = new StringBuilder();
        StringBuilder fertilizerToWaterMap = new StringBuilder();
        StringBuilder waterToLightMap = new StringBuilder();
        StringBuilder lightToTemperatureMap = new StringBuilder();
        StringBuilder temperatureToHumidity = new StringBuilder();
        StringBuilder humidityToLocation = new StringBuilder();

        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher1;
        Matcher matcher2;
        Matcher matcher3;
        Matcher matcher4;
        Matcher matcher5;
        Matcher matcher6;
        Matcher matcher7;
        Matcher matcher8;

        long destinationRangeStart;
        long sourceRangeStart;
        long rangeLength;
        long startingSeed;
        long range;
        long value = -1;
        long nearestPosition = Long.MAX_VALUE;

        try (Scanner scanner = new Scanner(new File("./res/it/samaki/adventOfCode2023/problem5/test2.txt"))) {
            seeds = scanner.nextLine();
            scanner.nextLine();
            scanner.nextLine();

            for (int i = 4; i < 51; i++) seedToSoilMap.append(scanner.nextLine()).append("\n");
            scanner.nextLine();
            scanner.nextLine();
            for (int i = 53; i < 81; i++) soilToFertilizerMap.append(scanner.nextLine()).append("\n");
            scanner.nextLine();
            scanner.nextLine();
            for (int i = 83; i < 127; i++) fertilizerToWaterMap.append(scanner.nextLine()).append("\n");
            scanner.nextLine();
            scanner.nextLine();
            for (int i = 129; i < 138; i++) waterToLightMap.append(scanner.nextLine()).append("\n");
            scanner.nextLine();
            scanner.nextLine();
            for (int i = 140; i < 172; i++) lightToTemperatureMap.append(scanner.nextLine()).append("\n");
            scanner.nextLine();
            scanner.nextLine();
            for (int i = 174; i < 210; i++) temperatureToHumidity.append(scanner.nextLine()).append("\n");
            scanner.nextLine();
            scanner.nextLine();
            for (int i = 212; i < 251; i++) humidityToLocation.append(scanner.nextLine()).append("\n");
        }

        matcher1 = pattern.matcher(seeds);
        matcher2 = pattern.matcher(seedToSoilMap);
        matcher3 = pattern.matcher(soilToFertilizerMap);
        matcher4 = pattern.matcher(fertilizerToWaterMap);
        matcher5 = pattern.matcher(waterToLightMap);
        matcher6 = pattern.matcher(lightToTemperatureMap);
        matcher7 = pattern.matcher(temperatureToHumidity);
        matcher8 = pattern.matcher(humidityToLocation);

        while (matcher1.find()) {
            startingSeed = Long.parseLong(matcher1.group());
            matcher1.find();
            range = Long.parseLong(matcher1.group());

            for (long i = startingSeed; i < startingSeed + range; i++) {
                for (int j = 4; j < 51; j++) {
                    matcher2.find();
                    destinationRangeStart = Long.parseLong(matcher2.group());
                    matcher2.find();
                    sourceRangeStart = Long.parseLong(matcher2.group());
                    matcher2.find();
                    rangeLength = Long.parseLong(matcher2.group());

                    if (i >= sourceRangeStart && i < sourceRangeStart + rangeLength) {
                        value = destinationRangeStart + i - sourceRangeStart;
                        break;
                    }
                }
                matcher2.reset();

                for (int j = 53; j < 81; j++) {
                    matcher3.find();
                    destinationRangeStart = Long.parseLong(matcher3.group());
                    matcher3.find();
                    sourceRangeStart = Long.parseLong(matcher3.group());
                    matcher3.find();
                    rangeLength = Long.parseLong(matcher3.group());

                    if (value >= sourceRangeStart && value < sourceRangeStart + rangeLength) {
                        value = destinationRangeStart + value - sourceRangeStart;
                        break;
                    }
                }
                matcher3.reset();

                for (int j = 83; j < 127; j++) {
                    matcher4.find();
                    destinationRangeStart = Long.parseLong(matcher4.group());
                    matcher4.find();
                    sourceRangeStart = Long.parseLong(matcher4.group());
                    matcher4.find();
                    rangeLength = Long.parseLong(matcher4.group());

                    if (value >= sourceRangeStart && value < sourceRangeStart + rangeLength) {
                        value = destinationRangeStart + value - sourceRangeStart;
                        break;
                    }
                }
                matcher4.reset();

                for (int j = 129; j < 138; j++) {
                    matcher5.find();
                    destinationRangeStart = Long.parseLong(matcher5.group());
                    matcher5.find();
                    sourceRangeStart = Long.parseLong(matcher5.group());
                    matcher5.find();
                    rangeLength = Long.parseLong(matcher5.group());

                    if (value >= sourceRangeStart && value < sourceRangeStart + rangeLength) {
                        value = destinationRangeStart + value - sourceRangeStart;
                        break;
                    }
                }
                matcher5.reset();

                for (int j = 140; j < 171; j++) {
                    matcher6.find();
                    destinationRangeStart = Long.parseLong(matcher6.group());
                    matcher6.find();
                    sourceRangeStart = Long.parseLong(matcher6.group());
                    matcher6.find();
                    rangeLength = Long.parseLong(matcher6.group());

                    if (value >= sourceRangeStart && value < sourceRangeStart + rangeLength) {
                        value = destinationRangeStart + value - sourceRangeStart;
                        break;
                    }
                }
                matcher6.reset();

                for (int j = 174; j < 210; j++) {
                    matcher7.find();
                    destinationRangeStart = Long.parseLong(matcher7.group());
                    matcher7.find();
                    sourceRangeStart = Long.parseLong(matcher7.group());
                    matcher7.find();
                    rangeLength = Long.parseLong(matcher7.group());

                    if (value >= sourceRangeStart && value < sourceRangeStart + rangeLength) {
                        value = destinationRangeStart + value - sourceRangeStart;
                        break;
                    }
                }
                matcher7.reset();

                for (int j = 212; j < 251; j++) {
                    matcher8.find();
                    destinationRangeStart = Long.parseLong(matcher8.group());
                    matcher8.find();
                    sourceRangeStart = Long.parseLong(matcher8.group());
                    matcher8.find();
                    rangeLength = Long.parseLong(matcher8.group());

                    if (value >= sourceRangeStart && value < sourceRangeStart + rangeLength) {
                        value = destinationRangeStart + value - sourceRangeStart;
                        break;
                    }
                }
                matcher8.reset();

                if (nearestPosition > value)
                    nearestPosition = value;
            }
        }
        System.out.println("The closes location that needs a seed is the number: " + nearestPosition);
    }
}
