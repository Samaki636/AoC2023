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
        int speed = 0;
        int waysToWin = 0;
        int waysToWinTotal = 1;

        long record;
        long distance;

        Pattern pattern1 = Pattern.compile("\\d+");
        Pattern pattern2 = Pattern.compile("\\d");
        Matcher matcherTime;
        Matcher matcherDistance;

        StringBuilder stringBuilder;

        try (Scanner scanner = new Scanner(new File(
                "./res/it/samaki/adventOfCode2023/problem6/test2.txt"))) {
            timeLine = scanner.nextLine();
            recordLine = scanner.nextLine();
        }

        matcherTime = pattern1.matcher(timeLine);
        matcherDistance = pattern1.matcher(recordLine);
        while (matcherTime.find() & matcherDistance.find()) {
            time = Integer.parseInt(matcherTime.group());
            record = Integer.parseInt(matcherDistance.group());

            //i is the time the button remain pressed, for i = 0 and i = time the distance = 0
            for (int i = 1; i < time; i++) {
                speed++;
                distance = (long) speed * (time - i);
                if (record < distance)
                    waysToWin++;
            }
            if (waysToWin != 0)
                waysToWinTotal *= waysToWin;
            speed = 0;
            waysToWin = 0;
        }
        System.out.println("The product of the number of ways to win each race is: " + waysToWinTotal);

        matcherTime = pattern2.matcher(timeLine);
        matcherDistance = pattern2.matcher(recordLine);

        stringBuilder = new StringBuilder();
        while (matcherTime.find())
            stringBuilder.append(matcherTime.group());
        time = Integer.parseInt(stringBuilder.toString());

        stringBuilder = new StringBuilder();
        while (matcherDistance.find())
            stringBuilder.append(matcherDistance.group());
        record = Long.parseLong(stringBuilder.toString());

        for (int i = 1; i < time; i++) {
            speed++;
            distance = (long) speed * (time - i);
            if (record < distance)
                waysToWin++;
        }
        System.out.println("The number of ways to win long race is: " + waysToWin);
    }
}
