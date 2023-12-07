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
        int firstNumberPos;
        int lastNumberPos;

        String currentLine;
        String currentLineValue;
        String firstNumber;
        String lastNumber;

        Pattern pattern = Pattern.compile("\\d");
        Matcher matcher;

        String[] subset = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

        try (BufferedReader input = new BufferedReader(new FileReader(
                "./res/it/samaki/adventOfCode2023/problem1/test2.txt"))) {
            while ((currentLine = input.readLine()) != null) {
                matcher = pattern.matcher(currentLine);

                firstNumberPos = -1;
                firstNumber = "";
                lastNumberPos = -1;
                lastNumber = "";

                //Get first word
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

                if(matcher.find()) {
                    String tmp = matcher.group();
                    if(firstNumberPos == -1) {
                        currentLineValue = tmp;
                    } else {
                        if (currentLine.indexOf(tmp) < firstNumberPos) {
                            tmp = String.valueOf(tmp.charAt(0));
                            currentLineValue = tmp;
                        } else {
                            currentLineValue = toDigit(firstNumber);
                        }
                    }
                } else {
                    currentLineValue = toDigit(firstNumber);
                }

                //Get last word
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
                        currentLineValue += toDigit(lastNumber);
                    }
                } else {
                    currentLineValue += toDigit(lastNumber);
                }

                sumOfValues += Integer.parseInt(currentLineValue, 10);
            }
            System.out.println("The sum of all of the calibration values is: " + sumOfValues);
        }
    }

    private static String toDigit(String string) {
        String digit;
        digit = switch (string) {
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
        return digit;
    }
}