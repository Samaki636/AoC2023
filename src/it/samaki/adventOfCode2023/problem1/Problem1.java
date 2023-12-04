package it.samaki.adventOfCode2023.problem1;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author : Samaki
 **/
public class Problem1 {
    public static void main(String[] args) throws IOException {
        int sumOfValues = 0;

        try (BufferedReader input = new BufferedReader(new FileReader(
                "./res/it/samaki/adventOfCode2023/problem1/test2.txt"))) {
            String currentLine;
            String currentLineValue;
            int firstNumberPos;
            String firstNumber;
            int lastNumberPos;
            String lastNumber;
            String[] subset = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

            while ((currentLine = input.readLine()) != null) {
                currentLine = currentLine.toLowerCase();
                firstNumberPos = -1;
                firstNumber = "";
                lastNumberPos = -1;
                lastNumber = "";

                for(String word : subset) {
                    if(currentLine.contains(word)) {
                        if(firstNumber.isEmpty()) {
                            firstNumber = word;
                            firstNumberPos = currentLine.indexOf(word);
                        }
                        if(firstNumberPos > currentLine.indexOf(word)) {
                            firstNumber = word;
                            firstNumberPos = currentLine.indexOf(word);
                        }
                    }
                }

                Pattern pattern = Pattern.compile("\\d");
                Matcher matcher = pattern.matcher(currentLine);

                if(matcher.find()) {
                    String tmp = matcher.group();
                    if(firstNumberPos == -1) {
                        currentLineValue = tmp;
                    } else {
                        if (currentLine.indexOf(tmp) < firstNumberPos) {
                            tmp = String.valueOf(tmp.charAt(0));
                            currentLineValue = tmp;
                        } else {
                            currentLineValue = switch (firstNumber) {
                                case "one" -> "1";
                                case "two" -> "2";
                                case "three" -> "3";
                                case "four" -> "4";
                                case "five" -> "5";
                                case "six" -> "6";
                                case "seven" -> "7";
                                case "eight" -> "8";
                                case "nine" -> "9";
                                default -> null;
                            };
                        }
                    }
                } else {
                    currentLineValue = switch (firstNumber) {
                        case "one" -> "1";
                        case "two" -> "2";
                        case "three" -> "3";
                        case "four" -> "4";
                        case "five" -> "5";
                        case "six" -> "6";
                        case "seven" -> "7";
                        case "eight" -> "8";
                        case "nine" -> "9";
                        default -> null;
                    };
                }

                for(String word : subset) {
                    if(currentLine.contains(word)) {
                        if(lastNumber.isEmpty()) {
                            lastNumber = word;
                            lastNumberPos = currentLine.lastIndexOf(word);
                        }
                        if(lastNumberPos < currentLine.lastIndexOf(word)) {
                            lastNumber = word;
                            lastNumberPos = currentLine.lastIndexOf(word);
                        }
                    }
                }

                matcher = pattern.matcher(new StringBuilder(currentLine).reverse().toString());

                if(matcher.find()) {
                    String tmp = matcher.group();
                    String tmp2 = new StringBuilder(currentLine).reverse().toString();

                    if(tmp2.length() - tmp2.indexOf(tmp) > lastNumberPos) {
                        currentLineValue += tmp;
                    } else {
                        currentLineValue += switch (lastNumber) {
                            case "one" -> "1";
                            case "two" -> "2";
                            case "three" -> "3";
                            case "four" -> "4";
                            case "five" -> "5";
                            case "six" -> "6";
                            case "seven" -> "7";
                            case "eight" -> "8";
                            case "nine" -> "9";
                            default -> null;
                        };
                    }
                } else {
                    currentLineValue += switch (lastNumber) {
                        case "one" -> "1";
                        case "two" -> "2";
                        case "three" -> "3";
                        case "four" -> "4";
                        case "five" -> "5";
                        case "six" -> "6";
                        case "seven" -> "7";
                        case "eight" -> "8";
                        case "nine" -> "9";
                        default -> null;
                    };
                }

                sumOfValues += Integer.parseInt(currentLineValue, 10);
            }
            System.out.println("The sum of all of the calibration values is: " + sumOfValues);
        }
    }
}
