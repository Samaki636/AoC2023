package it.samaki.adventOfCode2023.problem7;

import java.util.Arrays;

/**
 * @author : Samaki01
 **/
public class Hand implements Comparable<Hand> {
    private final String hand;
    private String formattedHand;
    private final int bid;
    private int type;

    public Hand(String hand, int bid) {
        this.hand = hand;
        this.bid = bid;
        formatHand();
        computeStrength();
    }

    private void formatHand() {
        formattedHand = hand.replace('T', ':');
        formattedHand = formattedHand.replace('J', ';');
        formattedHand = formattedHand.replace('Q', '<');
        formattedHand = formattedHand.replace('K', '=');
        formattedHand = formattedHand.replace('A', '>');
    }

    private void computeStrength() {
        int[] count = new int[13];
        for (char c : formattedHand.toCharArray())
            count[c - '2']++;
        Arrays.sort(count);
        if (count[12] == 5)
            type = 6;
        else if (count[12] == 4)
            type = 5;
        else if (count[12] == 3 && count[11] == 2)
            type = 4;
        else if (count[12] == 3)
            type = 3;
        else if (count[12] == 2 && count[11] == 2)
            type = 2;
        else if (count[12] == 2)
            type = 1;
        else if (count[12] == 1)
            type = 0;
    }

    @Override
    public int compareTo(Hand hand) {
        if (type < hand.getType())
            return -1;
        if (type > hand.getType())
            return 1;
        return compareSameTypeHands(hand.hand);
    }

    private int compareSameTypeHands(String hand) {
        for (int i = 0; i < hand.length(); i++) {
            if (this.hand.charAt(i) != hand.charAt(i))
                return Integer.compare(hand.charAt(i), this.hand.charAt(i));
        }
        return 0;
    }

    public String getHand() {
        return hand;
    }

    public int getBid() {
        return bid;
    }

    public int getType() {
        return type;
    }
}
