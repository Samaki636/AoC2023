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
        computeType();
    }

    private void formatHand() {
        formattedHand = hand.replace('T', ':');
        formattedHand = formattedHand.replace('J', '1');
        formattedHand = formattedHand.replace('Q', ';');
        formattedHand = formattedHand.replace('K', '<');
        formattedHand = formattedHand.replace('A', '=');
    }

    private void computeType() {
        int[] count = new int[13];
        for (char c : formattedHand.toCharArray())
            count[c - '1']++;
        Arrays.sort(count);
        if (count[12] == 5)
            type = 6;
        if (count[12] == 4)
            type = 5;
        if (count[12] == 3 && count[11] == 2)
            type = 4;
        if (count[12] == 3 && count[11] != 2)
            type = 3;
        if (count[12] == 2 && count[11] == 2)
            type = 2;
        if (count[12] == 2 && count[11] != 2)
            type = 1;
        if (count[12] == 1)
            type = 0;

        if (count[12] == 4 && (formattedHand.length() - formattedHand.replace("1", "").length() == 1))
            type = 6;
        if (count[12] == 3 && count[11] != 2 && (formattedHand.length() - formattedHand.replace("1", "").length() == 1))
            type = 5;
        if (count[12] == 2 && count[11] == 2 && (formattedHand.length() - formattedHand.replace("1", "").length() == 1))
            type = 4;
        if (count[12] == 2 && count[11] != 2 && (formattedHand.length() - formattedHand.replace("1", "").length() == 1))
            type = 3;
        if (count[12] == 1 && (formattedHand.length() - formattedHand.replace("1", "").length() == 1))
            type = 1;

        if (count[12] == 3 && count[11] == 2 && (formattedHand.length() - formattedHand.replace("1", "").length() == 2))
            type = 6;
        if (count[12] == 2 && count[11] == 2 && (formattedHand.length() - formattedHand.replace("1", "").length() == 2))
            type = 5;
        if (count[12] == 2 && count[11] != 2 && (formattedHand.length() - formattedHand.replace("1", "").length() == 2))
            type = 3;

        if (count[12] == 3 && count[11] != 2 && (formattedHand.length() - formattedHand.replace("1", "").length() == 3))
            type = 5;
        if (count[12] == 3 && count[11] == 2 && (formattedHand.length() - formattedHand.replace("1", "").length() == 3))
            type = 6;

        if (count[12] == 4 && (formattedHand.length() - formattedHand.replace("1", "").length() == 4))
            type = 6;

        formatHand();
    }

    @Override
    public int compareTo(Hand hand) {
        if (type < hand.getType())
            return -1;
        if (type > hand.getType())
            return 1;
        return compareSameTypeHands(hand.getFormattedHand());
    }

    private int compareSameTypeHands(String hand) {
        for (int i = 0; i < hand.length(); i++) {
            if (this.formattedHand.charAt(i) != hand.charAt(i))
                return Integer.compare(this.formattedHand.charAt(i), hand.charAt(i));
        }
        return 0;
    }

    public String getFormattedHand() {
        return formattedHand;
    }

    public int getBid() {
        return bid;
    }

    public int getType() {
        return type;
    }
}
