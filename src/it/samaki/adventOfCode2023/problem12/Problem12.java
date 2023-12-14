package it.samaki.adventOfCode2023.problem12;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author : Samaki01
 **/
public class Problem12 {
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void main (String[] args) throws FileNotFoundException {
        LinkedList<String> input = new LinkedList<>();
        LinkedList<String> possibleStrings;

        try (Scanner scanner = new Scanner(new File("./res/it/samaki/adventOfCode2023/problem12/test1.txt"))) {
            while (scanner.hasNextLine())
                input.add(scanner.nextLine());
        }

        //modify input
        Pattern pattern = Pattern.compile(" ");
        Matcher matcher;
        String str1;
        String str2;
        for (String s : input) {
            matcher = pattern.matcher(s);
            matcher.find();
            str1 = s.substring(0, matcher.start());
            str2 = s.substring(matcher.end());
            input.set(input.indexOf(s), str1+"?"+str1+"?"+str1+"?"+str1+"?"+str1+" "+str2+","+str2+","+str2+","+str2+","+str2);
        }

        //build all possible strings
        Pattern pattern1 = Pattern.compile("#+");
        Pattern pattern2 = Pattern.compile("\\d+");
        Matcher matcher1;
        Matcher matcher2;
        boolean isValid = true;
        int count = 0;
        for (String s : input) {
            possibleStrings = new LinkedList<>();
            //create first two lines
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) != '?')
                    continue;
                possibleStrings.add(s.replaceFirst("\\?", "."));
                possibleStrings.add(s.replaceFirst("\\?", "#"));
                break;
            }

            //create left lines
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < s.length(); j++) {
                    if (possibleStrings.get(0).charAt(j) != '?')
                        continue;
                    possibleStrings.add(possibleStrings.get(0).replaceFirst("\\?", "."));
                    possibleStrings.add(possibleStrings.get(0).replaceFirst("\\?", "#"));
                    possibleStrings.remove(0);
                    i--;
                    break;
                }
            }

            //most expensive method
            //checks for valid strings
            for (String str : possibleStrings) {
                matcher1 = pattern1.matcher(str);
                matcher2 = pattern2.matcher(str);
                while (matcher1.find() && matcher2.find()) {
                    if (matcher1.group().length() != Integer.parseInt(matcher2.group())) {
                        isValid = false;
                        break;
                    }
                }
                if (!isValid) {
                    isValid = true;
                    continue;
                }
                if ((matcher1.hitEnd() && !matcher2.hitEnd() || (matcher2.hitEnd() && !matcher1.hitEnd())))
                    continue;
                count++;
                System.out.println("Line: " + input.indexOf(s) + " count: " + count + " String: " + str);
            }
        }
    }
}
