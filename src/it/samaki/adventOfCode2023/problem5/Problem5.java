package it.samaki.adventOfCode2023.problem5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author : Samaki
 **/
public class Problem5 {
    public static void main(String[] args) throws IOException {
        String currentLine;

        //TODO: set regex
        Pattern pattern = Pattern.compile("");
        Matcher matcher;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(
                "./res/it/samaki/adventOfCode2023/problem5/test1.txt"))) {
            while ((currentLine = bufferedReader.readLine()) != null) {
                matcher = pattern.matcher(currentLine);

                //TODO: complete program
                if(matcher.find()) {

                }
            }
        }
    }
}
