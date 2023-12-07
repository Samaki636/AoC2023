package it.samaki.adventOfCode2023.problem7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author : Samaki01
 **/
public class Problem7 {
    public static void main (String[] args) throws FileNotFoundException {
        LinkedList<Hand> hands = new LinkedList<>();
        String line;
        int totalWinnings = 0;

        try (Scanner scanner = new Scanner(new File("./res/it/samaki/adventOfCode2023/problem7/test2.txt"))) {
            while (scanner.hasNext()) {
                line = scanner.nextLine();
                hands.add(new Hand(line.substring(0, 5), Integer.parseInt(line.substring(6))));
            }
        }
        hands.sort(new HandsComparator());
        for (Hand hand : hands) {
            System.out.println(hand.getHand());
            totalWinnings += (hands.indexOf(hand) + 1) * hand.getBid();
        }
        System.out.println("The total winnings are: " + totalWinnings);
    }
}
