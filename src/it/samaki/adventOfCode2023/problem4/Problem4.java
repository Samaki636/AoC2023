package it.samaki.adventOfCode2023.problem4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author : Samaki
 **/
public class Problem4 {
    public static void main(String[] args) throws IOException {
        String currentLine;

        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher;

        final int NUMBER_WINNING_NUMBERS = 10;
        final int NUMBER_NUMBERS_YOU_HAVE = 25;
        final int MAX_NUMBER_OF_CARDS = 204;

//        final int NUMBER_WINNING_NUMBERS = 5;
//        final int NUMBER_NUMBERS_YOU_HAVE = 8;
//        final int MAX_NUMBER_OF_CARDS = 6;

        int[] winningNumbers = new int[NUMBER_WINNING_NUMBERS];
        int[] numbersYouHave = new int[NUMBER_NUMBERS_YOU_HAVE];
        int[] numberOfCopiesOfCards = new int[MAX_NUMBER_OF_CARDS];

        int currentCardPoints = 0;
        int totalCardPoints = 0;
        int currentCardNumber = 0;
        int totalNumberOfCards = 0;

        Arrays.fill(numberOfCopiesOfCards, 1);

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(
                "./res/it/samaki/adventOfCode2023/problem4/test2.txt"))) {
            //For each card:
            while ((currentLine = bufferedReader.readLine()) != null) {
                matcher = pattern.matcher(currentLine);
                if(matcher.find()) {
                    //Read all the winning numbers in the card.
                    for (int i = 0; i < NUMBER_WINNING_NUMBERS; i++) {
                        if (matcher.find())
                            winningNumbers[i] = Integer.parseInt(matcher.group());
                    }

                    //Read all the numbers you have in the card.
                    for (int i = 0; i < NUMBER_NUMBERS_YOU_HAVE; i++) {
                        if (matcher.find())
                            numbersYouHave[i] = Integer.parseInt(matcher.group());
                    }
                }

                //Calculate points for the card.
                for (int number : numbersYouHave) {
                    for (int winningNumber : winningNumbers) {
                        if (number == winningNumber)
                            if (currentCardPoints == 0) {
                                currentCardPoints = 1;
                            } else {
                                currentCardPoints *= 2;
                            }
                    }
                }

                totalCardPoints += currentCardPoints;
                currentCardPoints = 0;

                //Calculate number of winning numbers in the card.
                for (int number : numbersYouHave) {
                    for (int winningNumber : winningNumbers) {
                        if (number == winningNumber)
                            currentCardPoints++;
                    }
                }

                if (matcher.find(0))
                    currentCardNumber = Integer.parseInt(matcher.group());

                //Calculate number of copies won.
                for (int i = numberOfCopiesOfCards[currentCardNumber - 1]; i > 0; i--) {
                    for (int j = currentCardNumber; j < currentCardNumber + currentCardPoints; j++) {
                        if (j >= MAX_NUMBER_OF_CARDS)
                            break;
                        numberOfCopiesOfCards[j]++;
                    }
                }
            }
        }
        for (int i = 0; i < MAX_NUMBER_OF_CARDS; i++)
            totalNumberOfCards += numberOfCopiesOfCards[i];

        System.out.println("The total number of scratchcards do we end up with is: " + totalNumberOfCards);
        System.out.println("The pile of cards is worth " + totalCardPoints + " points in total.");
    }
}