package it.samaki.adventOfCode2023.problem6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author : Samaki
 **/
public class Problem6 {
    public static void main(String[] args) throws FileNotFoundException {
        String timeLine;
        String recordLine;

        int time;
        int record;
        int speed = 0;
        int distance;
        int waysToWin = 0;
        int waysToWinTotal = 1;

        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcherTime;
        Matcher matcherDistance;

        try (Scanner scanner = new Scanner(new File(
                "./res/it/samaki/adventOfCode2023/problem6/test2.txt"))) {
            timeLine = scanner.nextLine();
            recordLine = scanner.nextLine();
        }

        matcherTime = pattern.matcher(timeLine);
        matcherDistance = pattern.matcher(recordLine);
        while (matcherTime.find() & matcherDistance.find()) {
            time = Integer.parseInt(matcherTime.group());
            record = Integer.parseInt(matcherDistance.group());

            //i is the time the button remain pressed, for i = 0 and i = time the distance = 0
            for (int i = 1; i < time; i++) {
                speed++;
                distance = speed * (time - i);
                if (record < distance)
                    waysToWin++;
            }
            if (waysToWin != 0)
                waysToWinTotal *= waysToWin;
            speed = 0;
            waysToWin = 0;
        }
        System.out.println("The product of the number of ways to win each race is: " + waysToWinTotal);
    }
}
