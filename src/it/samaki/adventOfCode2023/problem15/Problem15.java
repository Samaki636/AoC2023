package it.samaki.adventOfCode2023.problem15;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author : Samaki01
 **/
public class Problem15 {
    public static void main(String[] args) throws FileNotFoundException {
        String tmp;
        try (Scanner scanner = new Scanner(new File("res/it/samaki/adventOfCode2023/problem15/test2.txt"))) {
            tmp = scanner.nextLine();
        }
        String[] input = tmp.split(",");

        //apply ash function
        int currentValue;
        int sum = 0;
        for (String s : input) {
            currentValue = 0;
            for (char c : s.toCharArray()) {
                if (c == '\n') continue;
                currentValue += c;
                currentValue *= 17;
                currentValue %= 256;
            }
            System.out.println("Current value :" + currentValue);
            sum += currentValue;
        }

        System.out.println("The sum of the results is: " + sum);
    }
}