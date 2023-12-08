package it.samaki.adventOfCode2023.problem8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author : Samaki01
 **/
public class Problem8 {
    public static void main (String[] args) throws FileNotFoundException {
        String line;
        String currentPosition = "AAA";

        Pattern pattern = Pattern.compile("\\w");
        Matcher matcher;

        boolean isLeft;

        Map<String, String[]> map = new HashMap<>();

        int stepsNumber = 0;

        try (Scanner scanner = new Scanner(new File("./res/it/samaki/adventOfCode2023/problem8/test3.txt"))) {
            matcher = pattern.matcher(scanner.nextLine());
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                map.put(line.substring(0, 3), new String[]{line.substring(7, 10), line.substring(12, 15)});
            }
        }

        while (true) {
            matcher.reset();
            while (matcher.find()) {
                isLeft = matcher.group().equals("L");
                if (isLeft)
                    currentPosition = map.get(currentPosition)[0];
                else
                    currentPosition = map.get(currentPosition)[1];
                stepsNumber++;
                if (currentPosition.equals("ZZZ")) {
                    System.out.println("Are required " + stepsNumber + " steps to reach ZZZ.");
                    return;
                }
            }
        }
    }
}
