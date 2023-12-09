package it.samaki.adventOfCode2023.problem9;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author : Samaki01
 **/
public class Problem9 {
    public static void main (String[] args) throws FileNotFoundException {
        LinkedList<String> input = new LinkedList<>();

        Pattern pattern = Pattern.compile("-?[0-9]+");
        Matcher matcher;

        long sum = 0;

        try (Scanner scanner = new Scanner(new File("./res/it/samaki/adventOfCode2023/problem9/test2.txt"))) {
            while (scanner.hasNext())
                input.add(scanner.nextLine());
        }

        //for each line of input do
        for (String s : input) {
            LinkedList<LinkedList<Long>> results = new LinkedList<>();
            matcher = pattern.matcher(s);
            long lastN;

            //copy input on results
            results.add(new LinkedList<>());
            while (matcher.find())
                results.get(0).add(Long.parseLong(matcher.group()));

            //create lines of results
            int i = 0;
            while (!checkAll(results.getLast())) {
                results.add(new LinkedList<>());
                lastN = results.get(i).getFirst();
                for (int j = 1; j < results.get(i).size(); j++) {
                    results.getLast().add(results.get(i).get(j) - lastN);
                    lastN = results.get(i).get(j);
                }
                i++;
            }

            //add 0 and compute new values
            results.getLast().add(0L);
            for (int j = results.size() - 2; j >= 0; j--)
                results.get(j).add(results.get(j).getLast() + results.get(j + 1).getLast());
            sum += results.get(0).getLast();
            System.out.println("The partial sum is: " + sum);
        }

        System.out.println("The sum of all the extrapolated values is: " + sum);
    }

    public static boolean checkAll(LinkedList<Long> list) {
        for (Long candidate : list)
            if (candidate != 0) return false;
        return true;
    }
}