package it.samaki.adventOfCode2023.problem3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author : Samaki
 **/
public class Problem3 {
    public static void main(String[] args) throws IOException {
        String lastLine;
        String currentLine;
        String nextLine;

        LinkedList<Integer> partNumberAddedLastLine = new LinkedList<>();
        LinkedList<Integer> gearRatioParts = new LinkedList<>();

        int partNumberSum = 0;
        int gearRatioSum = 0;

        try (BufferedReader input = new BufferedReader(new FileReader(
                "./res/it/samaki/adventOfCode2023/problem3/test2.txt"))) {

            lastLine = input.readLine();
            currentLine = input.readLine();

            while ((nextLine = input.readLine()) != null) {
                Pattern pattern1 = Pattern.compile("\\d+");
                Pattern pattern2 = Pattern.compile("[^.\\d]");
                Pattern pattern3 = Pattern.compile("[*]");
                Matcher matcher1 = pattern1.matcher(lastLine);
                Matcher matcher2 = pattern2.matcher(currentLine);
                Matcher matcher3 = pattern1.matcher(nextLine);
                Matcher matcher4 = pattern3.matcher(currentLine);
                Matcher matcher5 = pattern1.matcher(currentLine);

                while(matcher1.find()) {
                    if(matcher1.start() != 0)
                        if(String.valueOf(lastLine.charAt(matcher1.start() - 1)).matches("[^.\\d]+")) {
                            partNumberSum += Integer.parseInt(matcher1.group());
                            partNumberAddedLastLine.add(matcher1.start());
                        }
                    if(matcher1.end() < lastLine.length())
                        if(String.valueOf(lastLine.charAt(matcher1.end())).matches("[^.\\d]+")) {
                            partNumberSum += Integer.parseInt(matcher1.group());
                            partNumberAddedLastLine.add(matcher1.start());
                        }
                }

                while(matcher2.find()) {
                    if(String.valueOf(lastLine.charAt(matcher2.start())).matches("\\d")
                            || String.valueOf(lastLine.charAt(matcher2.start() - 1)).matches("\\d")
                            || String.valueOf(lastLine.charAt(matcher2.start() + 1)).matches("\\d")) {
                        matcher1.reset();
                        while(matcher1.find()) {
                            if(matcher2.start() >= matcher1.start() - 1 && matcher2.start() < matcher1.end() + 1)
                                if(!partNumberAddedLastLine.contains(matcher1.start()))
                                    partNumberSum += Integer.parseInt(matcher1.group());
                        }
                    }

                    if(String.valueOf(nextLine.charAt(matcher2.start())).matches("\\d")
                            || String.valueOf(nextLine.charAt(matcher2.start() - 1)).matches("\\d")
                            || String.valueOf(nextLine.charAt(matcher2.start() + 1)).matches("\\d")) {
                        while(matcher3.find()) {
                            if(matcher2.start() >= matcher3.start() - 1 && matcher2.start() < matcher3.end() + 1)
                                partNumberSum += Integer.parseInt(matcher3.group());
                        }
                        matcher3.reset();
                    }
                }

                while(matcher4.find()) {
                    //parti sulla riga precedente
                    if((String.valueOf(lastLine.charAt(matcher4.start())).matches("\\d")
                            || String.valueOf(lastLine.charAt(matcher4.start() - 1)).matches("\\d")
                            || String.valueOf(lastLine.charAt(matcher4.start() + 1)).matches("\\d"))) {
                        matcher1.reset();
                        while(matcher1.find()) {
                            if(matcher4.start() >= matcher1.start() - 1 && matcher4.start() < matcher1.end() + 1)
                                gearRatioParts.add(Integer.parseInt(matcher1.group()));
                        }
                    }

                    //parti sulla riga successiva
                    if((String.valueOf(nextLine.charAt(matcher4.start())).matches("\\d")
                            || String.valueOf(nextLine.charAt(matcher4.start() - 1)).matches("\\d")
                            || String.valueOf(nextLine.charAt(matcher4.start() + 1)).matches("\\d"))) {
                        matcher3.reset();
                        while(matcher3.find()) {
                            if(matcher4.start() >= matcher3.start() - 1 && matcher4.start() < matcher3.end() + 1)
                                gearRatioParts.add(Integer.parseInt(matcher3.group()));
                        }
                    }

                    //parti sulla riga corrente
                    if(String.valueOf(currentLine.charAt(matcher4.start() - 1)).matches("\\d") ||
                            String.valueOf(currentLine.charAt(matcher4.end())).matches("\\d")) {
                        matcher5.reset();
                        while(matcher5.find()) {
                            if(matcher5.end() == matcher4.start() || matcher5.start() - 1 == matcher4.start())
                                gearRatioParts.add(Integer.parseInt(matcher5.group()));
                        }
                    }

                    if(gearRatioParts.size() == 2)
                        gearRatioSum += gearRatioParts.get(0) * gearRatioParts.get(1);
                    gearRatioParts.clear();
                }


                partNumberAddedLastLine.clear();
                lastLine = currentLine;
                currentLine = nextLine;
            }

            System.out.println("The sum of all of the part numbers in the engine schematic is: " + partNumberSum);
            System.out.println("The sum of all of the gear ratios in the engine schematic is: " + gearRatioSum);
        }
    }
}