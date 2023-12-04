package it.samaki.adventOfCode2023.problem2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author : Samaki
 **/
public class Problem2 {
    public static void main(String[] args) throws IOException {
        final int MAX_RED_CUBES = 12;
        final int MAX_GREEN_CUBES = 13;
        final int MAX_BLUE_CUBES = 14;

        int minRedCubes;
        int minGreenCubes;
        int minBlueCubes;

        int IDGame = 0;
        int redCubes;
        int greenCubes;
        int blueCubes;
        int IDGamesSum = 0;
        int gamePowerSum = 0;

        boolean isGameValid;

        String currentLine;

        try (BufferedReader input = new BufferedReader(new FileReader(
                "./res/it/samaki/adventOfCode2023/problem2/test2.txt"))) {

            while ((currentLine = input.readLine()) != null) {
                int cubesNumber;
                char cubesType;

                Pattern pattern = Pattern.compile("\\d+");
                Matcher matcher = pattern.matcher(currentLine);

                if(matcher.find())
                    IDGame = Integer.parseInt(matcher.group());

                isGameValid = true;

                redCubes = 0;
                greenCubes = 0;
                blueCubes = 0;

                minRedCubes = -1;
                minGreenCubes = -1;
                minBlueCubes = -1;

                while(matcher.find()) {
                    cubesNumber = Integer.parseInt(matcher.group());
                    cubesType = currentLine.charAt(matcher.end() + 1);

                    switch (cubesType) {
                        case 'r' -> redCubes += cubesNumber;
                        case 'g' -> greenCubes += cubesNumber;
                        case 'b' -> blueCubes += cubesNumber;
                    }

                    if(matcher.end() + 6 < currentLine.length()) {
                        boolean isSemicolonPresent = currentLine.charAt(matcher.end() + 4) == ';' ||
                                currentLine.charAt(matcher.end() + 5) == ';' ||
                                currentLine.charAt(matcher.end() + 6) == ';';

                        if (isSemicolonPresent) {
                            isGameValid = isGameValid && !(redCubes > MAX_RED_CUBES ||
                                    greenCubes > MAX_GREEN_CUBES ||
                                    blueCubes > MAX_BLUE_CUBES);

                            if((minRedCubes == -1 || minRedCubes < redCubes) && redCubes != 0)
                                minRedCubes = redCubes;
                            if((minGreenCubes == -1 || minGreenCubes < greenCubes) && greenCubes != 0)
                                minGreenCubes = greenCubes;
                            if((minBlueCubes == -1 || minBlueCubes < blueCubes) && blueCubes != 0)
                                minBlueCubes = blueCubes;

                            redCubes = 0;
                            greenCubes = 0;
                            blueCubes = 0;
                        }
                    } else {
                        isGameValid = isGameValid && !(redCubes > MAX_RED_CUBES ||
                                greenCubes > MAX_GREEN_CUBES ||
                                blueCubes > MAX_BLUE_CUBES);

                        if((minRedCubes == -1 || minRedCubes < redCubes) && redCubes != 0)
                            minRedCubes = redCubes;
                        if((minGreenCubes == -1 || minGreenCubes < greenCubes) && greenCubes != 0)
                            minGreenCubes = greenCubes;
                        if((minBlueCubes == -1 || minBlueCubes < blueCubes) && blueCubes != 0)
                            minBlueCubes = blueCubes;

                        redCubes = 0;
                        greenCubes = 0;
                        blueCubes = 0;

                        if(minRedCubes == -1) {
                            if(minGreenCubes == -1 && minBlueCubes != -1) {
                                gamePowerSum += minBlueCubes;
                            } else if(minBlueCubes == -1 && minGreenCubes != -1) {
                                gamePowerSum += minGreenCubes;
                            } else {
                                gamePowerSum += minGreenCubes * minBlueCubes;
                            }
                        } else {
                            if(minGreenCubes == -1 && minBlueCubes == -1) {
                                gamePowerSum += minRedCubes;
                            } else if(minGreenCubes == -1){
                                gamePowerSum += minRedCubes * minBlueCubes;
                            } else if(minBlueCubes == -1) {
                                gamePowerSum += minRedCubes * minGreenCubes;
                            } else {
                                gamePowerSum += minRedCubes * minGreenCubes * minBlueCubes;
                            }
                        }

                        if(isGameValid)
                            IDGamesSum += IDGame;
                    }
                }
            }
            System.out.println("The sum of the IDs of valid games is: " + IDGamesSum);
            System.out.println("The sum of the power of the minimum sets of cubes is: " + gamePowerSum);
        }
    }
}