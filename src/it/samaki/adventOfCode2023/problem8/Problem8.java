package it.samaki.adventOfCode2023.problem8;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

/**
 * @author : Samaki01
 **/
public class Problem8 {
    public static void main (String[] args) throws FileNotFoundException {
        LinkedList<String> currentPositions = new LinkedList<>();

        Map<String, String[]> map = new HashMap<>();

        String directions;
        String line;

        boolean isLeft;

        int stepsNumber = 0;
        int count = 0;

        long[] results = new long[6];

        BigInteger LCM;

        try (Scanner scanner = new Scanner(new File("./res/it/samaki/adventOfCode2023/problem8/test3.txt"))) {
            directions = scanner.nextLine();
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                map.put(line.substring(0, 3), new String[]{line.substring(7, 10), line.substring(12, 15)});
                if (line.charAt(2) == 'A')
                    currentPositions.add(line.substring(0, 3));
            }
        }

        while (count < 6) {
            for (int i = 0; i < directions.length(); i++) {
                isLeft = directions.charAt(i) == 'L';
                for (String s : currentPositions) {
                    if (isLeft)
                        currentPositions.set(currentPositions.indexOf(s), map.get(s)[0]);
                    else
                        currentPositions.set(currentPositions.indexOf(s), map.get(s)[1]);
                    if (s.charAt(2) == 'Z') {
                        System.out.println("Are required " + stepsNumber + " steps to reach ZZZ.");
                        results[count] = stepsNumber;
                        count++;
                    }
                    stepsNumber++;
                }
            }
        }
        LCM = BigInteger.valueOf(results[0]);
        for (int i = 1; i < results.length; i++)
            LCM = lcm(LCM, results[i]);
        System.out.println("The LCM is: " + LCM);
    }

    public static BigInteger lcm(BigInteger x, long y) {
        BigInteger max = x.max(BigInteger.valueOf(y));
        BigInteger min = x.min(BigInteger.valueOf(y));
        BigInteger lcm = max;
        while (!lcm.remainder(min).equals(BigInteger.valueOf(0)))
            lcm = lcm.add(max);
        return lcm;
    }
}
